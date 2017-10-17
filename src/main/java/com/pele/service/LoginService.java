package com.pele.service;

import com.pele.pojo.User;

import javax.servlet.http.HttpServletRequest;

public interface LoginService{
    public Boolean checkLoginInfo(User user, HttpServletRequest request);
}
