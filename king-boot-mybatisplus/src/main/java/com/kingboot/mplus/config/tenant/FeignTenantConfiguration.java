package com.kingboot.mplus.config.tenant;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p class="detail">
 * 功能:租户信息拦截
 * </p>
 * @author waylen.chi
 * @ClassName Feign tenant configuration.
 * @Version V1.0.
 * @date 2018 /9/14 feign
 */
@Configuration
public class FeignTenantConfiguration {
	/**
	 * <p class="detail">
	 * 功能:
	 * </p>
	 * @return request interceptor
	 * @author Kings
	 * @date 2019.07.10 09:31:43
	 */
	@Bean
	public RequestInterceptor jcwlFeignTenantInterceptor() {
		return new FeignTenantInterceptor();
	}
}
