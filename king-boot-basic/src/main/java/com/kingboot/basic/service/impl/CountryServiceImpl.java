package com.kingboot.basic.service.impl;

import com.kingboot.basic.config.mybatis.mapper.BaseCrudServiceImpl;
import com.kingboot.basic.dao.boot.entity.Country;
import com.kingboot.basic.service.CountryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CountryServiceImpl extends BaseCrudServiceImpl<Country> implements CountryService {

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveCountry() throws Exception {
		Country country = new Country();
		country.setCountryname("test");
		country.setCountrycode("TE");
		mapper.insertSelective(country);
		//手动制造异常
		throw new Exception("error");
	}
}
