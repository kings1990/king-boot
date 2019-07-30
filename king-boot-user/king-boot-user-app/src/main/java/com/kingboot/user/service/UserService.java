package com.kingboot.user.service;


import com.kingboot.common.config.mybatis.mapper.BaseCRUDService;
import com.kingboot.user.User;
import com.kingboot.user.dto.UserNicknameDto;

/**
 * <p class="detail">
 * 功能:用户接口
 * </p>
 * @author Kings
 * @ClassName UserService
 * @Version V1.0.
 * @date 2019.07.30 10:57:17
 */
@SuppressWarnings ("ALL")
public interface UserService extends BaseCRUDService<com.kingboot.user.entity.User> {
	
	/**
	 * <p class="detail">
	 * 功能:根据id查找
	 * </p>
	 * @param id :id
	 *
	 * @return user
	 * @author Kings
	 * @date 2019.07.30 10:57:17
	 */
	User findById(Integer id);
	
	/**
	 * <p class="detail">
	 * 功能:更新用户名
	 * </p>
	 * @param dto :dto
	 *
	 * @return integer
	 * @author Kings
	 * @date 2019.07.30 10:57:17
	 */
	Integer updateNickName(UserNicknameDto dto);
}
