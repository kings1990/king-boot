package com.kingboot.service.impl;

import com.kingboot.service.HelloService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class HelloServiceImpl implements HelloService {
	@Override
	public String hello() {
		return "hello ws";
	}
	
	@Override
	public String hi() {
		return "hi ws";
	}
	
	@Override
	public String hehe() {
		return "hehe";
	}
}