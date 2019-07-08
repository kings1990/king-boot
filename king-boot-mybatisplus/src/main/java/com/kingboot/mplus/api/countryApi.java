package com.kingboot.mplus.api;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kingboot.common.model.RestResponse;
import com.kingboot.mplus.entity.Country;
import com.kingboot.mplus.mapper.CountryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping ("/api/countrys")
@RestController
public class countryApi {
	
	@Autowired
	private CountryMapper countryMapper;
	
	
	@RequestMapping (value = "/list", method = RequestMethod.GET)
	public RestResponse<List<Country>> listAll() {
		return new RestResponse<>(countryMapper.selectList(Wrappers.query()));
	}
	
	@RequestMapping (value = "/page", method = RequestMethod.GET)
	public RestResponse<IPage<Country>> listAll(Page<Country> page) {
		page.setCurrent(2);
		return new RestResponse<>(countryMapper.selectPage(page,Wrappers.query()));
	}
}

