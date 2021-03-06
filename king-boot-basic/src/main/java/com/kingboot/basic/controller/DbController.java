package com.kingboot.basic.controller;


import com.github.pagehelper.PageInfo;
import com.kingboot.basic.config.common.RestResponse;
import com.kingboot.basic.dao.boot.entity.Country;
import com.kingboot.basic.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping ("/api/db")
@Controller
public class DbController {
	@Autowired
	private CountryService countryService;
	
	@GetMapping (name = "国家分页查询", value = "/country/page")
	@ResponseBody
	public RestResponse<PageInfo> countryPage(PageInfo pageInfo) throws Exception {
		countryService.selectPage(pageInfo, new Country());
		return new RestResponse<>(pageInfo);
	}
	
	@GetMapping (name = "国家事务测试", value = "/country/test/1")
	@ResponseBody
	public RestResponse<Integer> transactionalTest() throws Exception {
		countryService.saveCountry();
		return new RestResponse<>(1);
	}
	
}

