package com.pele.controller;

import com.pele.common.pojo.PageParam;
import com.pele.common.pojo.PageResult;
import com.pele.pojo.Attend;
import com.pele.pojo.User;
import com.pele.service.AttendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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

    @RequestMapping("/attendList")
    @ResponseBody
    public PageResult getAttendList(PageParam pageParam, HttpSession httpSession){
        //设置pageParam的userId
        User user = (User)httpSession.getAttribute("UserInfo");
        pageParam.setUserId(user.getId());
       PageResult pageResult = attendService.getAttendListByPageParam(pageParam);
       return pageResult;
    }
}
