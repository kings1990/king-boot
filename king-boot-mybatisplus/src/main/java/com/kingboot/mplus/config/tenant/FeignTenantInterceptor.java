package com.kingboot.mplus.config.tenant;

import com.kingboot.mplus.generator.constant.CommonConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * <p class="detail">
 * 功能:feign拦截器
 * </p>
 * @author waylen.chi
 * @ClassName Feign tenant interceptor.
 * @Version V1.0.
 * @date 2018 /9/14
 */
@Slf4j
public class FeignTenantInterceptor implements RequestInterceptor {
	@Override
	public void apply(RequestTemplate requestTemplate) {
		if (TenantContextHolder.getTenantId() == null) {
			log.error("TTL 中的 租户ID为空，feign拦截器 >> 增强失败");
			return;
		}
		requestTemplate.header(CommonConstants.TENANT_ID, TenantContextHolder.getTenantId().toString());
	}
}
