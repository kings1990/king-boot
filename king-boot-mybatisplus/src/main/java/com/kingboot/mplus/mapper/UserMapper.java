package com.kingboot.mplus.mapper;

import com.kingboot.mplus.config.MyBaseMapper;
import com.kingboot.mplus.entity.User;


/**
 * <p class="detail">
 * 功能:用户mapper
 * </p>
 * @author Kings
 * @ClassName UserMapper
 * @Version V1.0.
 * @date 2019.07.30 11:22:02
 */
public interface UserMapper extends MyBaseMapper<User> {
	
	/**
	 * <p class="detail">
	 * 功能:删除所有 使用自定义sqlmapper
	 * </p>
	 * @return int
	 * @author Kings
	 * @date 2019.07.30 11:22:02
	 */
	int deleteAll();
}
