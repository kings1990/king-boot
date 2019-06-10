package com.kingboot.user.api;


import com.kingboot.common.model.RestResponse;
import com.kingboot.user.dto.UserNicknameDto;
import com.kingboot.user.entity.User;
import com.kingboot.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping ("/api/user")
@Api (description = "用户API")
@Slf4j
public class UserApi {
	
	@Autowired
	private UserService userService;
	
	@GetMapping (value = "/detail/{id}", name = "根据id查询")
	@ApiOperation (value = "根据id查询用户", notes = "根据id查询用户【Kings】")
	@Deprecated
	public RestResponse<com.kingboot.user.User> findById(@PathVariable @ApiParam Integer id) throws Exception {
		log.error("1234");
		//TimeUnit.SECONDS.sleep(2);
		return new RestResponse<>(userService.findById(id));
	}
	
	@GetMapping (value = "/{id}", name = "根据id查询")
	@ApiOperation (value = "根据id查询用户", notes = "根据id查询用户【Kings】")
	public RestResponse<User> findDetailById(@PathVariable @ApiParam Integer id) throws Exception {
		return new RestResponse<>(userService.selectByPrimaryKey(id));
	}
	
	@PostMapping (value = "", name = "根据id更新nickname")
	@ApiOperation (value = "根据id更新nickname", notes = "根据id更新nickname【Kings】")
	public RestResponse<Integer> updateNickname(@RequestBody UserNicknameDto dto) {
		return new RestResponse<>(userService.updateNickName(dto));
	}
	
	
}

