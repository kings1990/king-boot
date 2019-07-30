package com.kingboot.mplus.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kingboot.mplus.entity.Country;
import org.apache.ibatis.annotations.Param;

/**
 * <p class="detail">
 * 功能:国家mapper
 * </p>
 * @author Kings
 * @ClassName CountryMapper
 * @Version V1.0.
 * @date 2019.07.30 11:19:21
 */
public interface CountryMapper extends BaseMapper<Country> {
	
	/**
	 * <p class="detail">
	 * 功能:
	 * </p>
	 * @param page   :分页对象
	 * @param letter :字母
	 *
	 * @return page
	 * @author Kings
	 * @date 2019.07.30 11:19:21
	 */
	Page<Country> selectByTheFirstLetterPage(Page page ,@Param("letter") String letter);
}
