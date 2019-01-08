package com.kingboot.basic.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequestMapping ("/jsp")
@Controller
public class TestJspController {
    
    
    @RequestMapping (name="返回jsp页面",value = "/1", method = GET)
    public String test2(Model model) {
        model.addAttribute("a", "haha");
        return "jsp/a";
    }
}

