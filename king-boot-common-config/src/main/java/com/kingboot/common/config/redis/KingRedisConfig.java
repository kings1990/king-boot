package com.kingboot.common.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KingRedisConfig {
	
	@Bean
	public KingRedisManager kingRedisManager() {
		return new KingRedisManager();
	}
	
	
	
}
