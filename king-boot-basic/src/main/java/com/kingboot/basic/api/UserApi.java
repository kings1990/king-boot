package com.kingboot.basic.api;


import com.kingboot.basic.config.common.KingParam;
import com.kingboot.basic.config.common.RestResponse;
import com.kingboot.basic.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping ("/api/user")
@Api (description = "用户api")
@Slf4j
public class UserApi {
    @GetMapping (value = "/{id}",name = "api测试(查询)")
    @ApiOperation(value = "根据id查询用户",notes = "根据id查询用户【Kings】")
    public RestResponse<User> getUser(@PathVariable @ApiParam("用户id") Integer id) {
        return new RestResponse<>(new User(1,11,"1234","15258297405",new Date()));
    }
    
    @PostMapping (value = "",name = "api测试(提交)")
    @ApiOperation(value = "保存用户信息",notes = "保存用户信息(0-失败 1-成功)【Kings】")
    public RestResponse<Integer> saveUser(@RequestBody @ApiParam("用户信息") User user) {
        return new RestResponse<>(1);
    }
    
    @PutMapping (value = "/{accountId}",name = "api测试(更新)",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "根据账号id更新用户年龄",notes = "根据账号id更新用户年龄(0-失败 1-成功)【【Kings】")
    @KingParam ("{\"accountId\":1,\"age\":22}")
    public RestResponse<Integer> getUser(@ApiParam(value = "年龄") Integer age, @ApiParam("账号id")@PathVariable Integer accountId) {
        return new RestResponse<>(1);
    }
    
    @DeleteMapping (value = "/{id}",name = "api测试(删除)")
    @ApiOperation(value = "根据用户id删除",notes = "根据用户id删除(0-失败 1-成功)【Kings】")
    @KingParam ("{\"id\":1}")
    public RestResponse<Integer> deleteUser(@PathVariable @ApiParam("用户id") Integer id) {
        return new RestResponse<>(1);
    }
}

