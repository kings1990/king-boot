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

/**
 * <p class="detail">
 * 功能:
 * </p>
 * @author Kings
 * @ClassName Country api.
 * @Version V1.0.
 * @date 2019.07.08 14:40:56
 */
@RequestMapping ("/api/countrys")
@RestController
public class CountryApi {
	
	/** Country mapper. */
	@Autowired
	private CountryMapper countryMapper;
	
	
	/**
	 * <p class="detail">
	 * 功能:
	 * </p>
	 * @return rest response
	 * @author Kings
	 * @date 2019.07.08 14:40:56
	 */
	@RequestMapping (value = "/list", method = RequestMethod.GET)
	public RestResponse<List<Country>> listAll() {
		return new RestResponse<>(countryMapper.selectList(Wrappers.query()));
	}
	
	/**
	 * <p class="detail">
	 * 功能:
	 * </p>
	 * @param page :
	 *
	 * @return rest response
	 * @author Kings
	 * @date 2019.07.08 14:40:56
	 */
	@RequestMapping (value = "/page", method = RequestMethod.GET)
	public RestResponse<IPage<Country>> page(Page<Country> page) {
		page.setCurrent(2);
		return new RestResponse<>(countryMapper.selectPage(page,Wrappers.query()));
	}
	
	/**
	 * <p class="detail">
	 * 功能:
	 * </p>
	 * @param page :
	 *
	 * @return rest response
	 * @author Kings
	 * @date 2019.07.08 14:40:56
	 */
	@RequestMapping (value = "/page/by/letter", method = RequestMethod.GET)
	public RestResponse<IPage<Country>> pageByLetter(Page<Country> page) {
		return new RestResponse<>(countryMapper.selectByTheFirstLetterPage(page,"A"));
	}
}

