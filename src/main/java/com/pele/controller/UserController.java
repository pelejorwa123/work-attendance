package com.pele.controller;

import com.pele.common.pojo.AjaxResult;
import com.pele.pojo.User;
import com.pele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/userInfo")
    @ResponseBody
    public User getUserInfo(HttpSession httpSession){
        User user=(User)httpSession.getAttribute("UserInfo");
        return  user;
    }

    @RequestMapping("/register")
    @ResponseBody
    public AjaxResult registerUser(@RequestBody  User user) throws MessagingException {
        AjaxResult ajaxResult = userService.createUser(user);
        return ajaxResult;
    }

    /**
     *@author: pele
     *@time: 2017/11/2 20:40
     *@package: com.pele.controller
     *@descroption:激活用户的链接
     */
    @RequestMapping("/activate")
    public String activateUser(String token){
       Boolean isSuccessful = userService.activaeUser(token);
       if(isSuccessful){
           return "activateSuccess";
       }else {
           return "activateDefeat";
       }
    }

    @ExceptionHandler(MessagingException.class)
    @ResponseBody
    public AjaxResult dealEmailException(){
        return AjaxResult.build(400,"激活邮件发送失败");
    }
}
