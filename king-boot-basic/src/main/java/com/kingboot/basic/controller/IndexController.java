package com.kingboot.basic.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class IndexController {
    
    @RequestMapping (name = "登录", value = {"/login", "/"}, method = GET)
    public String login() {
        return "redirect:thymeleaf/mappings";
    }
}

