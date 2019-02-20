package com.kingboot.dubbo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping ("/")
@Controller
public class IndexController {
    
    @RequestMapping (value = "", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        
        return "success";
    }
}

