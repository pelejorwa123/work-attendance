package com.pele.service;


import com.pele.pojo.User;

public interface UserService {
    public User getUserById(long id);
    public User createUser(User user);
}
