package com.pele.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("home")
    public String showIndex(){
        return "/home";
    }

    @RequestMapping("attend")
    public String showAttend(){
        return  "attend";
    }

}
