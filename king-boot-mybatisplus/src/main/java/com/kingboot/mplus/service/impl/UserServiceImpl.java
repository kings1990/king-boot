package com.kingboot.mplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kingboot.mplus.entity.User;
import com.kingboot.mplus.mapper.UserMapper;
import com.kingboot.mplus.service.UserService;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
