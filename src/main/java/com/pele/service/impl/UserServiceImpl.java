package com.pele.service.impl;
;import com.pele.mapper.UserMapper;
import com.pele.pojo.User;
import com.pele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserById(long id) {
        User user=userMapper.selectByPrimaryKey(id);
        return  user;
    }

    @Override
    public User createUser(User user) {
        userMapper.insert(user);
        return user;
    }
}
