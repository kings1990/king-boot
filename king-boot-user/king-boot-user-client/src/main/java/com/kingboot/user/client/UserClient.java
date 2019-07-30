package com.kingboot.user.client;

import com.kingboot.common.model.RestResponse;
import com.kingboot.user.User;
import com.kingboot.user.dto.UserNicknameDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p class="detail">
 * 功能:用户feign接口
 * </p>
 * @author Kings
 * @ClassName UserClient
 * @Version V1.0.
 * @date 2019.07.30 10:50:22
 */
@SuppressWarnings ("ALL")
@FeignClient (value = "king-boot-user", fallback = UserClientImpl.class)
public interface UserClient {
	
	@GetMapping (value = "/api/user/detail/{id}", name = "根据id查询")
	RestResponse<User> findById(@PathVariable Integer id);
	
	@PostMapping (value = "/api/user/", name = "根据id更新nickname")
	RestResponse<Integer> updateNickname(@RequestBody UserNicknameDto dto);
	
}
