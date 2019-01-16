package com.kingboot.basic.controller;


import com.kingboot.basic.config.common.KingParam;
import com.kingboot.basic.config.common.RestResponse;
import com.kingboot.basic.config.validate.ValidateMethod;
import com.kingboot.basic.model.BusinessModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@RequestMapping ("/business")
@Controller
public class BusinessController {
    @RequestMapping (name = "validate验证", value = "/1", method = RequestMethod.POST)
    @ResponseBody
    @KingParam ("{\"email\":\"123\",\"type\":1}")
    @ValidateMethod
    public RestResponse<String> test1(@Valid BusinessModel businessModel, BindingResult bindingResult) {
        
        return new RestResponse<>("1");
    }
}

