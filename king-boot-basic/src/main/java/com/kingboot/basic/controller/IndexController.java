package com.kingboot.basic.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequestMapping ("/login")
@Controller
public class IndexController {
    
    @RequestMapping (name="登录",value = "", method = GET)
    public String login() {
        
        return "redirect:thymeleaf/mappings";
    }
}

