package com.kingboot.service.impl;

import com.kingboot.service.HelloService;
import org.springframework.stereotype.Component;

@Component
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = HelloService.class)
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello() {
        return "hello ws";
    }
    
    @Override
    public String hi() {
        return "hi ws";
    }
    
    @Override
    public String hehe() {
        return "hehe";
    }
}