package com.pele.service.impl;
;import com.pele.common.pojo.AjaxResult;

import com.pele.common.utils.SecurityUtils;
import com.pele.mapper.UserMapper;
import com.pele.pojo.User;
import com.pele.pojo.UserExample;
import com.pele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;
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

    @Override
    public User getUserById(long id) {
        User user=userMapper.selectByPrimaryKey(id);
        return  user;
    }

    @Override
    public AjaxResult createUser(User user) {
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
        userMapper.insertSelective(user);
        return AjaxResult.ok();
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
        return true;
    }

}
