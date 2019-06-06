package com.kingboot.user.service;


import com.kingboot.common.config.mybatis.mapper.BaseCRUDService;
import com.kingboot.user.User;

public interface UserService extends BaseCRUDService<com.kingboot.user.entity.User> {
	
	User findById(Integer id);
}
