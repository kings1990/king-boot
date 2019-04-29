package com.kingboot.user.service.impl;

import com.kingboot.user.User;
import com.kingboot.user.service.UserService;
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
