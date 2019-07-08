package com.kingboot.mplus.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kingboot.mplus.entity.Country;

import java.util.List;

public interface CountryMapper extends BaseMapper<Country> {
	List<Country> selectUserList(Page page, Integer state);
	
}
