package com.kingboot.basic.controller;


import com.kingboot.basic.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping ("/json")
@RestController
public class TestJsonController {
	
	@GetMapping (value = "/1", name = "json框架使用 ")
	public User getUser() {
		
		return new User(1, 1, "123456", null, new Date());
	}
}

