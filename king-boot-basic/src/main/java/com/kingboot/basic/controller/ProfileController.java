package com.kingboot.basic.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping ("/profile")
@RestController
public class ProfileController {
    
    @Value ("${test.version}")
    private String version;
    
    @GetMapping (name = "spring.profiles.include测试配置", value = "/1")
    public String test1() {
        return version;
    }
}

