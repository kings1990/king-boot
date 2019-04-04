package com.kingboot.order.api;


import com.kingboot.common.model.RestResponse;
import com.kingboot.order.entity.User;
import com.kingboot.order.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping ("/user")
@Api (description = "用户API")
@Slf4j
public class UserApi {
    
    @Autowired
    private UserService userService;
    
    @GetMapping (value = "/detail/{id}",name = "根据id查询")
    @ApiOperation (value = "根据id查询用户",notes = "根据id查询用户【Kings】")
    public RestResponse<User> findById (@PathVariable @ApiParam Integer id) throws Exception {
        log.error("1234");
        //TimeUnit.SECONDS.sleep(2);
        return new RestResponse<>(userService.findById(id));
    }
}

