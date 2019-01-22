package com.kingboot.basic.dao.boot.mapper;

import com.kingboot.basic.config.mybatis.mapper.BaseMapper;
import com.kingboot.basic.dao.boot.entity.Country;

import java.util.List;

public interface CountryMapper extends BaseMapper<Country> {
    
    List<Country> findAll();
}