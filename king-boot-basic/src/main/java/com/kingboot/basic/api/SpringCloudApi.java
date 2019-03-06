package com.kingboot.basic.api;


import com.kingboot.common.model.RestResponse;
import com.kingboot.user.client.UserClient;
import com.kingboot.user.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping ("/cloud")
@Api (description = "cloud应用")
public class SpringCloudApi {
    
    @Autowired
    private UserClient userClient;
    
    @HystrixCommand(fallbackMethod = "findByUserIdFail")
    @GetMapping (value = "/feign",name = "feign负载均衡")
    public RestResponse<User> findByUserId() {
        return userClient.findById(1);
    }
    
    //熔断异常处理
    private RestResponse<User> findByUserIdFail(){
        return new RestResponse<>(-1,"服务异常",null);
    }
   
}

