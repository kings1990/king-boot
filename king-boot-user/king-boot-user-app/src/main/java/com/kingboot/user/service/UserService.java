package com.kingboot.user.service;


import com.kingboot.common.config.mybatis.mapper.BaseCRUDService;
import com.kingboot.user.User;
import com.kingboot.user.dto.UserNicknameDto;

public interface UserService extends BaseCRUDService<com.kingboot.user.entity.User> {
	
	User findById(Integer id);
	
	Integer updateNickName(UserNicknameDto dto);
}
