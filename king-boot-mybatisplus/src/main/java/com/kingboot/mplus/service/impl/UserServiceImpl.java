package com.kingboot.mplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kingboot.mplus.entity.User;
import com.kingboot.mplus.mapper.UserMapper;
import com.kingboot.mplus.service.UserService;
import org.springframework.stereotype.Service;


/**
 * 用户
 *
 * @author Kings
 * @date 2019-07-08 14:24:50
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
