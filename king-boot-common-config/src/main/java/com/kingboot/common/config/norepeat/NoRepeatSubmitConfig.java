package com.kingboot.common.config.norepeat;


import com.kingboot.common.config.redis.KingRedisManager;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * <p class="detail">
 * 功能:防重复提交配置
 * </p>
 * @author Kings
 * @ClassName NoRepeatSubmitConfig
 * @Version V1.0.
 * @date 2019.07.30 11:46:28
 */
@Configuration
@AutoConfigureAfter ({KingRedisManager.class, JedisPool.class})
public class NoRepeatSubmitConfig {
	
	/**
	 * <p class="detail">
	 * 功能:创建单例
	 * </p>
	 * @return no repeat submit aop
	 * @author Kings
	 * @date 2019.07.30 11:46:28
	 */
	@Bean
	public NoRepeatSubmitAop noRepeatSubmitAop() {
		return new NoRepeatSubmitAop();
	}
}
