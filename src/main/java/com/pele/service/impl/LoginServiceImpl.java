package com.pele.service.impl;

import com.pele.common.utils.CookieUtils;
import com.pele.common.utils.SecurityUtils;
import com.pele.mapper.UserMapper;
import com.pele.pojo.User;
import com.pele.pojo.UserExample;
import com.pele.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserMapper userMapper;
    @Value("${SESSION_EXPIRE_TIME}")
    int SESSION_EXPIRE_TIME;

    @Override
    public Boolean checkLoginInfo(User user, HttpServletRequest request) {
        String username=user.getUsername();
        String password=user.getPassword();
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria=userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> userResult=userMapper.selectByExample(userExample);
        //如果找到存在这个用户名的用户
        if (userResult!=null&&userResult.size()>0){
            User result = userResult.get(0);
            if (result.getPassword().equals(SecurityUtils.encryptPassword(password))){
                //如果登录成功
               HttpSession httpSession= request.getSession();
               result.setPassword(null);
               httpSession.setAttribute("UserInfo",result);
               httpSession.setMaxInactiveInterval(SESSION_EXPIRE_TIME);
                return  true;
            }
            return  false;
        }
        return false;
    }

    @Override
    public void logout(HttpSession httpSession) {
        httpSession.removeAttribute("UserInfo");
    }
}
