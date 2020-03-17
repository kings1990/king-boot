package com.kingboot.basic.service;


import com.kingboot.basic.config.mybatis.mapper.BaseCrudService;
import com.kingboot.basic.dao.boot.entity.Country;

public interface CountryService extends BaseCrudService<Country> {
	void saveCountry() throws Exception;
}

