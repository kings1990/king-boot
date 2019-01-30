/*
 * ahhahah
 */

/*
 * ahhahah
 */

package com.kingboot.basic.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping ("/test")
@Controller
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
    
    @GetMapping (name = "测试1", value = "/1")
    @ResponseBody
    public String test1() {
        LOGGER.error("TEST");
        return "1";
    }
    
    
}

