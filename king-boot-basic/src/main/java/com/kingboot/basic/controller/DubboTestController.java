package com.kingboot.basic.controller;


import com.kingboot.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping ("/dubbo")
@Controller
public class DubboTestController {
    
    @Reference
    private HelloService helloService;
    
    @GetMapping (name = "dubbo测试", value = "/1")
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

