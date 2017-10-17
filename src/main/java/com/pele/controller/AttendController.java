package com.pele.controller;

import com.pele.pojo.Attend;
import com.pele.service.AttendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/attend")
public class AttendController {

    @Autowired
    AttendService attendService;

    @RequestMapping("/sign")
    @ResponseBody
    public String attend(@RequestBody Attend attend){
        attendService.attend(attend);
        return "succ";
    }
}
