package com.kingboot.common.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p class="detail">
 * 功能:redis总管理配置
 * </p>
 * @author Kings
 * @ClassName KingRedisConfig
 * @Version V1.0.
 * @date 2019.07.30 11:42:43
 */
@Configuration
public class KingRedisConfig {
	
	/**
	 * <p class="detail">
	 * 功能:生产kingRedisManager
	 * </p>
	 * @return king redis manager
	 * @author Kings
	 * @date 2019.07.30 11:42:43
	 */
	@Bean
	public KingRedisManager kingRedisManager() {
		return new KingRedisManager();
	}
	
	
	
}
