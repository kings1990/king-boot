package com.kingboot.user.service.impl;

import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import com.kingboot.common.config.mybatis.mapper.BaseCRUDServiceImpl;
import com.kingboot.user.dto.UserNicknameDto;
import com.kingboot.user.entity.User;
import com.kingboot.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@SuppressWarnings ("AliDeprecation")
@Service
public class UserServiceImpl extends BaseCRUDServiceImpl<com.kingboot.user.entity.User> implements UserService {
	
	@Override
	public com.kingboot.user.User findById(Integer id) {
		com.kingboot.user.User user = new com.kingboot.user.User();
		user.setId(id);
		user.setName("ws");
		return user;
	}
	
	@Override
	@TxcTransaction (propagation = DTXPropagation.SUPPORTS)
	@Transactional(rollbackFor = Exception.class)
	public Integer updateNickName(UserNicknameDto dto) {
		String nickname = dto.getNickname();
		Integer id = dto.getId();
		if(StringUtils.isEmpty(nickname)){
			return 0;
		}
		User user4up = new User();
		user4up.setId(id);
		user4up.setNickname(nickname);
		mapper.updateByPrimaryKeySelective(user4up);
		return 1;
	}
	
	
	
	
}
