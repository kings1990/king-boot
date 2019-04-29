package com.kingboot.basic.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping ("/jsp")
@Controller
public class TestJspController {
	
	
	@GetMapping (name = "返回jsp页面", value = "/1")
	public String test2(Model model) {
		model.addAttribute("a", "haha");
		return "jsp/a";
	}
}

