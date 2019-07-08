package com.kingboot.mplus.api;


import com.kingboot.common.model.RestResponse;
import com.kingboot.mplus.entity.User;
import com.kingboot.mplus.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping ("/test")
@Api (tags = "测试api")
public class TestApi {
	
	@Autowired
	private UserService userService;
	
	@GetMapping ("/version")
	@ApiOperation (value = "乐观锁测试[@Kings]")
	public RestResponse<Integer> versionTest() {
		int id = 1;
		int version = 2;
		
		User u = new User();
		u.setId(id);
		u.setVersion(version);
		u.setName("aa");
		
		if(userService.updateById(u)){
			System.out.println("Update successfully");
		}else{
			System.out.println("Update failed due to modified by others");
		}
	    return new RestResponse<>(1);
	}
}

