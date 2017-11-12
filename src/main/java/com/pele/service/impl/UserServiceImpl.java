package com.pele.service.impl;
;import com.pele.common.pojo.AjaxResult;

import com.pele.common.utils.JsonUtils;
import com.pele.common.utils.SecurityUtils;
import com.pele.mapper.UserMapper;
import com.pele.pojo.User;
import com.pele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    JedisCluster jedisCluster;

    @Autowired
    JavaMailSenderImpl sender;


    @Value("${DEFAULT_MALE_IMAGE_URL}")
    String DEFAULT_MALE_IMAGE_URL;

    @Value("${DEFAULT_FEMALE_IMAGE_URL}")
    String DEFAULT_FEMALE_IMAGE_URL;

    @Value("${REGISTER_EMAIL_KEY}")
    String REGISTER_EMAIL_KEY;

    @Value("${REGISTER_USERINFO_EXPIRE_TIME}")
    int REGISTER_USERINFO_EXPIRE_TIME;

    @Value("${REGISTER_EMAIL_FROM}")
    String REGISTER_EMAIL_FROM;

    @Value("${REGISTER_EMAIL_SUBJECT}")
    String REGISTER_EMAIL_SUBJECT;

    @Override
    public User getUserById(long id) {
        User user=userMapper.selectByPrimaryKey(id);
        return  user;
    }

    @Override
    public AjaxResult createUser(User user) throws MessagingException {
        AjaxResult ajaxResult = new AjaxResult();
        Boolean isOk = checkUserInfo(user,ajaxResult);
        if(!isOk){
            return ajaxResult;
        }
        //对密码进行md5加密
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        if(user.getSex()==0){
            user.setHeadImage(DEFAULT_MALE_IMAGE_URL);
        }else {
            user.setHeadImage(DEFAULT_FEMALE_IMAGE_URL );
        }
        /*
        * 将用户信息先暂时存入到redis缓存中，设置过期时间，等待激活
        * key名是UUID生成的token，值是user的json数据
        * */
        String UserInfo =  JsonUtils.objectToJson(user);
        String token = UUID.randomUUID().toString();
        jedisCluster.set(token,UserInfo);
        jedisCluster.expire(token,REGISTER_USERINFO_EXPIRE_TIME);
        jedisCluster.set(REGISTER_EMAIL_KEY+":"+user.getEmail(),"existed");
        jedisCluster.expire(REGISTER_EMAIL_KEY+":"+user.getEmail(),REGISTER_USERINFO_EXPIRE_TIME);
        sendActivateEmail(user,token);
        return AjaxResult.ok();
    }

    @Override
    public Boolean activaeUser(String token) {
        String UserInfo = jedisCluster.get(token);
        if(StringUtils.isEmpty(UserInfo)){
            return false;
        }
        User user = JsonUtils.jsonToPojo(UserInfo,User.class);
        //插入数据库
        userMapper.insertSelective(user);
        //删除redis中的类容
        jedisCluster.del(token);
        jedisCluster.del(REGISTER_EMAIL_KEY+":"+user.getEmail());
        return true;
    }

    private Boolean checkUserInfo(User user ,AjaxResult ajaxResult){
        //判断用户名,密码,邮箱是否为空
        if(StringUtils.isEmpty(user.getUsername())){
            ajaxResult.set(400,"用户名不能为空");
            return false;
        }
        if(StringUtils.isEmpty(user.getPassword())){
            ajaxResult.set(400,"密码不能为空");
            return false;
        }
        if(StringUtils.isEmpty(user.getEmail())){
            ajaxResult.set(400,"邮箱不能为空");
            return false;
        }
        if(StringUtils.isEmpty(user.getSex())){
            ajaxResult.set(400,"性别不能为空");
            return false;
        }

        //判断邮箱是否是正确合法地址
        if(!checkEmailAddressIsRight(user.getEmail(),ajaxResult)){
            return false;
        }
        //检查邮箱地址是否已经被使用
        if(!checkEmailIsRegistered(user.getEmail(),ajaxResult)){
            return false;
        }
        return true;
    }
    /**
     *@author: pele
     *@time: 2017/10/21 16:31
     *@package: com.pele.service.impl
     *@descroption:检查邮箱地址是否是合法的
     */
    private Boolean checkEmailAddressIsRight(String email,AjaxResult ajaxResult){
        Pattern pattern = Pattern.compile("^([a-z0-9A-Z][\\w-]*)(\\.[\\w-]+)*@([a-z0-9A-Z]+)(\\.[a-z0-9A-Z]+)+");
        Matcher matcher = pattern.matcher(email);
        Boolean isMatched = matcher.matches();
        if(!isMatched){
            ajaxResult.set(400,"输入的邮箱地址不合法");
            return  false;
        }
        return true;
    }

    /**
     *@author: pele
     *@time: 2017/10/21 20:56
     *@package: com.pele.service.impl
     *@descroption:检查邮箱地址是否已经被注册
     */
    private Boolean checkEmailIsRegistered(String email,AjaxResult ajaxResult){
        /*
        * 先检查在已经激活的用户中是否存在该邮箱地址
        * */
        User user = userMapper.selectUserByEmail(email);
        if(user!=null){
            ajaxResult.set(400,"邮箱已经被注册");
            return false;
        }
        /*
        * 再检查redis缓存中是否存在该邮箱地址，避免重复注册
        * */
        Boolean isRegistered = jedisCluster.exists(REGISTER_EMAIL_KEY+":"+email);
        if(isRegistered){
            ajaxResult.set(400,"邮箱已经被注册");
            return false;
        }
        return true;
    }
    /**
     *@author: pele
     *@time: 2017/11/2 19:24
     *@package: com.pele.service.impl
     *@descroption:给用户注册的邮箱发送激活邮件
     */
    private void sendActivateEmail(User user, String token) throws MessagingException {
        String email = user.getEmail();
        String subject = REGISTER_EMAIL_SUBJECT;
        InternetAddress fromAddress = new InternetAddress(REGISTER_EMAIL_FROM);
        InternetAddress toAddress = new InternetAddress(email);
        String content = generateContent(token);
        MimeMessage emailMessage = sender.createMimeMessage();
        emailMessage.setFrom(new InternetAddress(REGISTER_EMAIL_FROM));
        emailMessage.addRecipient(MimeMessage.RecipientType.TO,toAddress);
        emailMessage.setSubject(subject);
        emailMessage.setContent(content,"text/html;charset=utf-8");
        sender.send(emailMessage);
    }
    /**
     *@author: pele
     *@time: 2017/11/2 19:26
     *@package: com.pele.service.impl
     *@descroption:根据模板生成激活邮件内容
     */
    private String generateContent(String token) {
        String activateUrl = "http://localhost:8080/user/activate?token="+token;
        String content =
                "<div>" +
                    "<P>你已经成功注册，请点击以下链接进行激活：</P>\n" +
                    "<a href=\""+activateUrl+"\">点我激活</a>" +
                "</div>";
        return content;
    }

}
