package com.kingboot.order.api;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping ("/prop")
@Api (description = "")
@RefreshScope//刷新配置 访问POST http://localhost:8302/actuator/bus-refresh刷新
public class PropertyTestApi {
    @Value("${a}")
    
    private Integer a;
    
    @RequestMapping (value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public Integer test() {
        
        return a;
    }
}

