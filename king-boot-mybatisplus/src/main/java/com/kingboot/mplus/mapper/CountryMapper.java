package com.kingboot.mplus.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kingboot.mplus.entity.Country;
import org.apache.ibatis.annotations.Param;

public interface CountryMapper extends BaseMapper<Country> {
	
	Page<Country> selectByTheFirstLetterPage(Page page ,@Param("letter") String letter);
}
