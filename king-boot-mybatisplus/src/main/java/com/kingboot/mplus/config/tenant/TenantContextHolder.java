package com.kingboot.mplus.config.tenant;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.experimental.UtilityClass;


/**
 * <p class="detail">
 * 功能:租户工具类
 * </p>
 * @author Kings
 * @ClassName Tenant context holder.
 * @Version V1.0.
 * @date 2019.07.10 09:30:57
 */
@UtilityClass
public class TenantContextHolder {
	
	/** Thread local tenant. */
	private final ThreadLocal<String> THREAD_LOCAL_TENANT = new TransmittableThreadLocal<>();
	
	
	/**
	 * TTL 设置租户ID
	 * @param tenantId the tenant id
	 */
	void setTenantId(String tenantId) {
		THREAD_LOCAL_TENANT.set(tenantId);
	}
	
	/**
	 * 获取TTL中的租户ID
	 * @return tenant id
	 */
	public String getTenantId() {
		return THREAD_LOCAL_TENANT.get();
	}
	
	
	public void clear() {
		THREAD_LOCAL_TENANT.remove();
	}
}
