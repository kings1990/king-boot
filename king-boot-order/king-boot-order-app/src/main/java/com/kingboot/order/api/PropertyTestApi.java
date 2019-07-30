package com.kingboot.order.api;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p class="detail">
 * 功能:刷新配置测试控制器  刷新配置 访问POST http://localhost:8302/actuator/bus-refresh刷新
 * </p>
 * @author Kings
 * @ClassName PropertyTestApi
 * @Version V1.0.
 * @date 2019.07.30 11:05:07
 */
@SuppressWarnings ("ALL")
@RestController
@RequestMapping ("/prop")
@Api (tags = "属性测试")
@RefreshScope
public class PropertyTestApi {
	@Value ("${a}")
	
	private Integer a;
	
	@RequestMapping (value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public Integer test() {
		
		return a;
	}
}

