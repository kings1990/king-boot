package com.kingboot.mplus.mapper;

import com.kingboot.mplus.config.MyBaseMapper;
import com.kingboot.mplus.entity.User;

/**
 * 用户
 *
 * @author Kings
 * @date 2019-07-08 14:24:50
 */
public interface UserMapper extends MyBaseMapper<User> {
	
	int deleteAll();
}
