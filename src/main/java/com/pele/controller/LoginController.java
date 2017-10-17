package com.pele.controller;

import com.pele.pojo.User;
import com.pele.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/index")
    public String login(){
        return "login";
    }


    @RequestMapping("/check")
    @ResponseBody
    public String checkLoginInfo(@RequestBody User user,HttpServletRequest request){
       Boolean isCheckedRight= loginService.checkLoginInfo(user,request);

       if (isCheckedRight){
           return "ok";
       }
       else {
           return  "error";
       }
    }

}
