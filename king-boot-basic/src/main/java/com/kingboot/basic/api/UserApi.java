package com.kingboot.basic.api;


import com.kingboot.basic.config.common.RestResponse;
import com.kingboot.basic.model.User;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequestMapping ("/api/user")
@Api (description = "用户api")
public class UserApi {
    @GetMapping (value = "/1",name = "api测试")
    public RestResponse<User> getUser() {
        return new RestResponse<>(new User(11,"1234","15258297405",new Date()));
    }
      
}

