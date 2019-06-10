package com.kingboot.common.config.norepeat;


import com.kingboot.common.config.redis.KingRedisManager;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

@Configuration
@AutoConfigureAfter ({KingRedisManager.class, JedisPool.class})
public class NoRepeatSubmitConfig {
	
	@Bean
	public NoRepeatSubmitAop noRepeatSubmitAop() {
		return new NoRepeatSubmitAop();
	}
}
