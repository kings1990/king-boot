package com.kingboot.common.config.redis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <p class="detail">
 * 功能:redis配置
 * </p>
 * @author Kings
 * @ClassName RedisPoolConfig
 * @Version V1.0.
 * @date 2019.07.30 11:43:03
 */
@Getter
@Setter
@Configuration
public class RedisPoolConfig {
	
	/** Host. */
	@Value ("${spring.redis.host}")
	private String host;
	
	/** Port. */
	@Value ("${spring.redis.port}")
	private int port;
	
	/** Timeout. */
	@Value ("${spring.redis.timeout}")
	private int timeout;
	
	/** Max idle. */
	@Value ("${spring.redis.jedis.pool.max-idle}")
	private int maxIdle;
	
	/** Max wait millis. */
	@Value ("${spring.redis.jedis.pool.max-wait}")
	private long maxWaitMillis;
	
	/** Password. */
	@Value ("${spring.redis.password}")
	private String password;
	
	/**
	 * <p class="detail">
	 * 功能:
	 * </p>
	 * @return jedis pool
	 * @author Kings
	 * @date 2019.07.30 11:43:03
	 */
	@Bean
	public JedisPool redisPoolFactory() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
		return jedisPool;
	}
	
}