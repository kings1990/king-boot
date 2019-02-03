package com.kingboot.basic.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping ("/profile")
@RestController
public class ProfileController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);
    
    @Value ("${test.version}")
    private String version;
    
    @GetMapping (name = "spring.profiles.include测试配置", value = "/1")
    public String test1() {
        return version;
    }
}

