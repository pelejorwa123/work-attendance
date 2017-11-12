package com.pele.service;


import com.pele.common.pojo.AjaxResult;
import com.pele.pojo.User;

import javax.mail.MessagingException;

public interface UserService {
    public User getUserById(long id);
    public AjaxResult createUser(User user) throws MessagingException;
    public Boolean activaeUser(String token);
}
