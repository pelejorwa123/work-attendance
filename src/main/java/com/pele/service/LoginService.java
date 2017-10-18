package com.pele.service;

import com.pele.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface LoginService{
    public Boolean checkLoginInfo(User user, HttpServletRequest request);
    public void logout(HttpSession httpSession);
}
