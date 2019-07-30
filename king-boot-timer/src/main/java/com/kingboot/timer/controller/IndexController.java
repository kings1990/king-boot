package com.kingboot.timer.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * <p class="detail">
 * 功能:首页控制器
 * </p>
 * @author Kings
 * @ClassName Index controller.
 * @Version V1.0.
 * @date 2019.07.30 10:35:14
 */
@RequestMapping ("/")
@Controller
public class IndexController {
	/**
	 * <p class="detail">
	 * 功能:首页
	 * </p>
	 * @return string
	 * @author Kings
	 * @date 2019.07.30 10:35:14
	 */
	@RequestMapping (value = "", method = GET)
	public String index() {
		return "forward:/timer/list";
	}
}

