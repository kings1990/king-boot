package com.king.timer.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequestMapping ("/")
@Controller
public class IndexController {
    @RequestMapping (value = "", method = GET)
    public String index() {
        return "forward:/timer/list";
    }
}

