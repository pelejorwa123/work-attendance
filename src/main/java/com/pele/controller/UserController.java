package com.pele.controller;

import com.pele.common.pojo.AjaxResult;
import com.pele.pojo.User;
import com.pele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public AjaxResult registerUser(@RequestBody  User user){
        AjaxResult ajaxResult = userService.createUser(user);
        return ajaxResult;
    }
}
