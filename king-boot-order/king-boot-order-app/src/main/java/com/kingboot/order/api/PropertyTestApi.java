package com.kingboot.order.api;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping ("/prop")
@Api (description = "")
public class PropertyTestApi {
    @Value("${a}")
    private Integer a;
    
    @RequestMapping (value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public Integer test() {
        
        return a;
    }
}

