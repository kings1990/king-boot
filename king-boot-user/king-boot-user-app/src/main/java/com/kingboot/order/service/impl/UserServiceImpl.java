package com.kingboot.order.service.impl;

import com.kingboot.order.entity.User;
import com.kingboot.order.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    
    @Override
    public User findById(Integer id) {
        User user = new User();
        user.setId(id);
        user.setName("ws");
        return user;
    }
}
