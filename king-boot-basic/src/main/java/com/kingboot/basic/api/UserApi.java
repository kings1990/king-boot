package com.kingboot.basic.api;


import com.kingboot.basic.config.common.RestResponse;
import com.kingboot.basic.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequestMapping ("/api/user")
@Api (description = "用户api")
public class UserApi {
    @GetMapping (value = "/{id}",name = "api测试")
    @ApiOperation(value = "根据id查询用户",notes = "根据id查询用户【Kings】")
    public RestResponse<User> getUser(@PathVariable @ApiParam("用户id") Integer id) {
        return new RestResponse<>(new User(11,"1234","15258297405",new Date()));
    }
}

