package com.kingboot.basic.dao.boot.mapper;

import com.kingboot.basic.config.mybatis.mapper.MybatisMysqlMapper;
import com.kingboot.basic.dao.boot.entity.Country;

import java.util.List;

public interface CountryMapper extends MybatisMysqlMapper<Country> {
	
	List<Country> findAll();
}