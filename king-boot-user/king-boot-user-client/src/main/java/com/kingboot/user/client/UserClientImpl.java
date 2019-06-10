package com.kingboot.user.client;


import com.kingboot.common.model.RestResponse;
import com.kingboot.user.User;
import com.kingboot.user.dto.UserNicknameDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserClientImpl implements UserClient {
	@Override
	public RestResponse<User> findById(Integer id) {
		log.error("findById 异常");
		return new RestResponse<>(- 1, "服务异常", null);
	}
	
	@Override
	public RestResponse<Integer> updateNickname(UserNicknameDto dto) {
		log.error("updateNickname 异常");
		return new RestResponse<>(- 1, "服务异常", null);
	}
}
