package com.kingboot.basic.config.shiro;

import io.buji.pac4j.context.ShiroSessionStore;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * <p class="detail">
 * 功能:pac4j配置
 * </p>
 * @author Kings
 * @ClassName Pac4jConfig
 * @Version V1.0.
 * @date 2019.07.31 16:11:29
 */
@Configuration
public class Pac4jConfig {
	
	/**
	 * <p class="detail">
	 * 功能:配置
	 * </p>
	 * @param kingClients       :
	 * @param shiroSessionStore :
	 *
	 * @return config
	 * @author Kings
	 * @date 2019.07.31 16:11:49
	 */
	@Bean ("authcConfig")
	public Config config(Clients kingClients, ShiroSessionStore shiroSessionStore) {
		Config config = new Config();
		config.setClients(kingClients);
		config.setSessionStore(shiroSessionStore);
		return config;
	}
	
	
	/**
	 * <p class="detail">
	 * 功能:自定义存储
	 * </p>
	 * @return shiro session store
	 * @author Kings
	 * @date 2019.07.31 16:12:09
	 */
	@Bean
	public ShiroSessionStore shiroSessionStore() {
		return new ShiroSessionStore();
	}
	
	
}