package com.kingboot.mplus.config.tenant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * <p class="detail">
 * 功能:多租户配置
 * </p>
 * @author oathsign
 * @ClassName Tenant config properties.
 * @Version V1.0.
 * @date 2019.07.10 09:31:12
 */
@Data
@Configuration
@ConfigurationProperties (prefix = "kings.tenant")
public class TenantConfigProperties {
	
	/**
	 * 维护租户列名称
	 */
	private String column="tenant_id";
	
	/**
	 * 多租户的数据表集合
	 */
	private List<String> tables;
	
	public TenantConfigProperties() {
		System.out.println(1);
	}
}
