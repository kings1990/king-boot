package com.kingboot.basic.service.impl;

import com.kingboot.basic.config.mybatis.mapper.BaseCRUDServiceImpl;
import com.kingboot.basic.dao.boot.entity.Country;
import com.kingboot.basic.service.CountryService;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl extends BaseCRUDServiceImpl<Country> implements CountryService {
    
}
