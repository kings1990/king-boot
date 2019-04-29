package com.kingboot.basic.service.impl;

import com.kingboot.basic.config.mybatis.mapper.BaseCRUDServiceImpl;
import com.kingboot.basic.dao.boot.entity.Country;
import com.kingboot.basic.service.CountryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CountryServiceImpl extends BaseCRUDServiceImpl<Country> implements CountryService {
	
	@Override
	@Transactional
	public void saveCountry() throws Exception {
		Country country = new Country();
		country.setCountryname("test");
		country.setCountrycode("TE");
		mapper.insertSelective(country);
		if (1 == 1) {
			throw new Exception("error");
		}
	}
}
