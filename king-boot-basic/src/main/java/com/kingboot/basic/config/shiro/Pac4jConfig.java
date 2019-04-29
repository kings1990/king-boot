package com.kingboot.basic.config.shiro;

import io.buji.pac4j.context.ShiroSessionStore;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gongtao
 * @version 2018-07-06 9:35
 * @update 2018-08-29 升级 pac4j 版本到 4.0.0
 **/
@Configuration
public class Pac4jConfig {
	
	
	/**
	 * pac4j配置
	 * @param kingClients
	 * @param shiroSessionStore
	 *
	 * @return
	 */
	@Bean ("authcConfig")
	public Config config(Clients kingClients, ShiroSessionStore shiroSessionStore) {
		Config config = new Config();
		config.setClients(kingClients);
		config.setSessionStore(shiroSessionStore);
		return config;
	}
	
	/**
	 * 自定义存储
	 * @return
	 */
	@Bean
	public ShiroSessionStore shiroSessionStore() {
		return new ShiroSessionStore();
	}
	
	
}