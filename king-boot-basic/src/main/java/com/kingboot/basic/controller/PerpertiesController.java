package com.kingboot.basic.controller;


import com.kingboot.basic.model.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping ("/prop")
@Controller
@PropertySource ("classpath:config/custom.properties")
@EnableConfigurationProperties (MyConfig.class)
@AllArgsConstructor
public class PerpertiesController {
	
	@Value ("${my.lucknum}")
	private String luckNum;
	
	@Value ("${my.name}")
	private String name;
	
	@Value ("${my.firstName}")
	private String firstName;
	
	@Autowired
	private DevConfig devConfig;
	
	@Autowired
	private MyConfig myConfig;
	
	@Autowired
	private Person yinzheng;
	
	@Autowired
	private AnotherConfig anotherConfig;
	
	@Autowired
	private MyFruilt myFruilt;
	
	@Autowired
	private MyContact myContact;
	
	@Autowired
	private Me me;
	
	@GetMapping (name = "ConfigurationProperties的使用(集合属性)", value = "/3")
	@ResponseBody
	public String test3() {
		return myConfig.toString();
	}
	
	@GetMapping (name = "ConfigurationProperties的使用", value = "/4")
	@ResponseBody
	public String test4() {
		return devConfig.toString();
	}
	
	/*************************************************************************************************************/
	@Bean
	@ConfigurationProperties (prefix = "another")
	public AnotherConfig getAnotherConfig() {
		return new AnotherConfig();
	}
	
	@GetMapping (name = "第三方组件绑定", value = "/5")
	@ResponseBody
	public String test5() {
		return anotherConfig.toString();
	}
	
	/*************************************************************************************************************/
	
	@GetMapping (name = "宽松绑定绑定(忽略 1.大小写 2.-(横杠) 3._(下划线)【建议全使用大写】)", value = "/6")
	@ResponseBody
	public Person test6() {
		return yinzheng;
	}
	
	@GetMapping (name = "Map、List绑定", value = "/7")
	@ResponseBody
	public MyFruilt test7() {
		return myFruilt;
	}
	
	@GetMapping (name = "自定义converter", value = "/8", params = {"areaCode=0574", "phoneNum=12345678"})
	@ResponseBody
	public Telephone test8(Telephone tel) {
		return tel;
	}
	
	@GetMapping (name = "@ConfigurationProperties验证", value = "/9", params = {"areaCode=05", "phoneNum=12345678"})
	@ResponseBody
	public MyContact test9() {
		return myContact;
	}
	
	@GetMapping (name = "嵌套绑定", value = "/10")
	@ResponseBody
	public Me test10() {
		return me;
	}
	
	
}

