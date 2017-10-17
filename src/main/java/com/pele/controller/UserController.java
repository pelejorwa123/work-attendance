package com.pele.controller;

import com.pele.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/userInfo")
    @ResponseBody
    public User getUserInfo(HttpSession httpSession){
        User user=(User)httpSession.getAttribute("UserInfo");
        System.out.println(user.getHeadImage());
        return  user;
    }
}
