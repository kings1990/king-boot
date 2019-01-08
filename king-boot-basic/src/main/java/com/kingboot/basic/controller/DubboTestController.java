package com.kingboot.basic.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.kingboot.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequestMapping ("/dubbo")
@Controller
public class DubboTestController {
    
    @Reference
    private HelloService helloService;
    
    @RequestMapping (name = "dubbo测试",value = "/1", method = GET)
    @ResponseBody
    public String test1() {
        String result = helloService.hello();
        String result1 = helloService.hi();
        String result2 = helloService.hehe();
        System.out.println(result);
        System.out.println(result1);
        System.out.println(result2);
        return result;
    }
    
}

