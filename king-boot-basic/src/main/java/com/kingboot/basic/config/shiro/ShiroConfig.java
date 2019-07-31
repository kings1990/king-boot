package com.kingboot.basic.config.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import io.buji.pac4j.filter.LogoutFilter;
import io.buji.pac4j.subject.Pac4jSubjectFactory;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.pac4j.core.config.Config;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * <p class="detail">
 * 功能:shiro配置
 * </p>
 * @author Kings
 * @ClassName ShiroConfig
 * @Version V1.0.
 * @date 2019.07.31 16:12:25
 */
@Configuration
@PropertySource ("classpath:config/cas.properties")
public class ShiroConfig {
	
	
	/** Cas login url. */
	@Value ("${cas.server.login.url}")
	private String casLoginUrl;
	
	/** Cas logout success url. */
	@Value ("${basic.logout.success.url}")
	private String casLogoutSuccessUrl;
	
	/** Basic login url. */
	@Value ("${basic.login.url}")
	private String basicLoginUrl;
	
	// @Value ("${cas.client-name}")
	// private String clientName;
	
	/** 客户端名称 */
	@Value ("${shiro.timeout.seconds}")
	private Integer shiroTimeoutSeconds;
	
	/** Login url. */
	@Value ("${basic.login.url}")
	private String loginUrl;
	
	/** Callback url. */
	@Value ("${cas.callback.url}")
	private String callbackUrl;
	
	/**
	 * <p class="detail">
	 * 功能:
	 * </p>
	 * @return lifecycle bean post processor
	 * @author Kings
	 * @date 2019.07.31 16:12:25
	 */
	@Bean
	public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	
	/**
	 * <p class="detail">
	 * 功能:
	 * </p>
	 * @param subjectFactory :
	 * @param sessionManager :
	 * @param casRealm       :
	 * @param cacheManager   :
	 *
	 * @return default web security manager
	 * @author Kings
	 * @date 2019.07.31 16:12:25
	 */
	@Bean ("securityManager")
	public DefaultWebSecurityManager securityManager(Pac4jSubjectFactory subjectFactory, SessionManager sessionManager, CasRealm casRealm, CacheManager cacheManager) {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		casRealm.setCredentialsMatcher(new TinyCredentialsMatcher());
		manager.setRealm(casRealm);
		manager.setSubjectFactory(subjectFactory);
		manager.setSessionManager(sessionManager);
		manager.setCacheManager(cacheManager);
		
		return manager;
	}
	
	/**
	 * <p class="detail">
	 * 功能:
	 * </p>
	 * @return cas realm
	 * @author Kings
	 * @date 2019.07.31 16:12:25
	 */
	@Bean
	public CasRealm casRealm() {
		CasRealm realm = new CasRealm();
		// 使用自定义的realm
		realm.setCachingEnabled(false);
		//暂时不使用缓存
		realm.setAuthenticationCachingEnabled(false);
		realm.setAuthorizationCachingEnabled(false);
		//realm.setAuthenticationCacheName("authenticationCache");
		//realm.setAuthorizationCacheName("authorizationCache");
		return realm;
	}
	
	/**
	 * 使用 pac4j 的 subjectFactory
	 * @return 4 j subject factory
	 * @author Kings
	 * @date 2019.07.31 16:12:25
	 */
	@Bean
	public Pac4jSubjectFactory subjectFactory() {
		return new Pac4jSubjectFactory();
	}
	
	/**
	 * 下面的代码是添加注解支持
	 * @return default advisor auto proxy creator
	 * @author Kings
	 * @date 2019.07.31 16:12:25
	 */
	@Bean
	@DependsOn ("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		// 强制使用cglib，防止重复代理和可能引起代理出错的问题
		// https://zhuanlan.zhihu.com/p/29161098
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		defaultAdvisorAutoProxyCreator.setUsePrefix(true);
		return defaultAdvisorAutoProxyCreator;
	}
	
	/**
	 * <p class="detail">
	 * 功能:
	 * </p>
	 * @return filter registration bean
	 * @author Kings
	 * @date 2019.07.31 16:12:25
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
		//  该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
		filterRegistration.addInitParameter("targetFilterLifecycle", "true");
		filterRegistration.setEnabled(true);
		filterRegistration.addUrlPatterns("/*");
		filterRegistration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD);
		
		filterRegistration.addInitParameter("redirectAfterValidation", "true");
		
		filterRegistration.setOrder(- 1);
		return filterRegistration;
	}
	
	/**
	 * 加载shiroFilter权限控制规则（从数据库读取然后配置）
	 * @param shiroFilterFactoryBean :
	 *
	 * @author Kings
	 * @date 2019.07.31 16:12:25
	 */
	private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean) {
		/*下面这些规则配置最好配置到配置文件中 */
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/callback", "callbackFilter");
		filterChainDefinitionMap.put("/logout", "logoutFilter");
		filterChainDefinitionMap.put("/", "anon");
		filterChainDefinitionMap.put("/swagger/api", "anon");
		filterChainDefinitionMap.put("/eureka/**", "anon");
		filterChainDefinitionMap.put("/hystrix/**", "anon");
		
		filterChainDefinitionMap.put("/error", "anon");
		filterChainDefinitionMap.put("/thymeleaf/**", "anon");
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/actuator/**", "anon");
		filterChainDefinitionMap.put("/**", "securityFilter");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
	}
	
	/**
	 * shiroFilter
	 * @param securityManager :
	 * @param config          :
	 *
	 * @return filter factory bean
	 * @author Kings
	 * @date 2019.07.31 16:12:25
	 */
	@Bean ("shiroFilter")
	public ShiroFilterFactoryBean factory(DefaultWebSecurityManager securityManager, Config config) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//登录url kings   cas地址f
		shiroFilterFactoryBean.setLoginUrl(loginUrl);
		
		//shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		// 添加casFilter到shiroFilter中
		loadShiroFilterChain(shiroFilterFactoryBean);
		Map<String, Filter> filters = new HashMap<>(3);
		
		//cas 认证后回调拦截器
		CallbackFilter callbackFilter = new CallbackFilter();
		callbackFilter.setConfig(config);
		callbackFilter.setDefaultUrl(basicLoginUrl);
		
		filters.put("callbackFilter", callbackFilter);
		// 注销 拦截器
		LogoutFilter logoutFilter = new LogoutFilter();
		logoutFilter.setConfig(config);
		logoutFilter.setDefaultUrl(casLogoutSuccessUrl);
		logoutFilter.setCentralLogout(true);
		logoutFilter.setLocalLogout(true);
		filters.put("logoutFilter", logoutFilter);
		shiroFilterFactoryBean.setFilters(filters);
		
		//cas 资源认证拦截器
		KingsSecurityFilter securityFilter = new KingsSecurityFilter();
		securityFilter.setClients("casClient,jwtClient,restClient");
		securityFilter.setLoginUrl(loginUrl);
		securityFilter.setConfig(config);
		filters.put("securityFilter", securityFilter);
		return shiroFilterFactoryBean;
	}
	
	/**
	 * <p class="detail">
	 * 功能:
	 * </p>
	 * @return session dao
	 * @author Kings
	 * @date 2019.07.31 16:12:25
	 */
	@Bean
	public SessionDAO sessionDAO() {
		return new MemorySessionDAO();
	}
	
	/**
	 * 自定义cookie名称
	 * @return cookie
	 * @author Kings
	 * @date 2019.07.31 16:12:25
	 */
	@Bean
	public SimpleCookie sessionIdCookie() {
		SimpleCookie cookie = new SimpleCookie("sid");
		cookie.setMaxAge(- 1);
		cookie.setPath("/");
		cookie.setDomain("ws.com");
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		return cookie;
	}
	
	// @Bean
	// public DefaultWebSessionManager sessionManager(SimpleCookie sessionIdCookie, SessionDAO sessionDAO) {
	//     DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
	//     sessionManager.setSessionIdCookie(sessionIdCookie);
	//     sessionManager.setSessionIdCookieEnabled(true);
	//     sessionManager.setGlobalSessionTimeout(shiroTimeoutSeconds * 1000);
	//     sessionManager.setSessionDAO(sessionDAO);
	//     sessionManager.setDeleteInvalidSessions(true);
	//     sessionManager.setSessionValidationSchedulerEnabled(true);
	//     return sessionManager;
	// }
	
	/**
	 * <p class="detail">
	 * 功能:
	 * </p>
	 * @param sessionIdCookie :
	 * @param sessionDAO      :
	 *
	 * @return kings session manager
	 * @author Kings
	 * @date 2019.07.31 16:12:26
	 */
	@Bean
	KingsSessionManager sessionManager(SimpleCookie sessionIdCookie, SessionDAO sessionDAO) {
		KingsSessionManager sessionManager = new KingsSessionManager();
		sessionManager.setSessionIdCookie(sessionIdCookie);
		sessionManager.setSessionIdCookieEnabled(true);
		sessionManager.setGlobalSessionTimeout(shiroTimeoutSeconds * 1000);
		sessionManager.setSessionDAO(sessionDAO);
		sessionManager.setDeleteInvalidSessions(true);
		sessionManager.setSessionValidationSchedulerEnabled(true);
		return sessionManager;
	}
	
	
	/**
	 * <p class="detail">
	 * 功能:
	 * </p>
	 * @param securityManager :
	 *
	 * @return authorization attribute source advisor
	 * @author Kings
	 * @date 2019.07.31 16:12:26
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}
	
	/**
	 * <p class="detail">
	 * 功能:
	 * </p>
	 * @return shiro dialect
	 * @author Kings
	 * @date 2019.07.31 16:12:26
	 */
	@Bean (name = "shiroDialect")
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}
	
	/**
	 * <p class="detail">
	 * 功能:
	 * </p>
	 * @param redisManager :
	 *
	 * @return redis cache manager
	 * @author Kings
	 * @date 2019.07.31 16:12:26
	 */
	@Bean
	public RedisCacheManager cacheManager(RedisManager redisManager) {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager);
		return redisCacheManager;
	}
	
	/**
	 * <p class="detail">
	 * 功能:redis manager
	 * </p>
	 * @param host     :
	 * @param port     :
	 * @param timeout  :
	 * @param password :
	 *
	 * @return redis manager
	 * @author Kings
	 * @date 2019.07.31 16:12:26
	 */
	@Bean
	public RedisManager redisManager(@Value ("${spring.redis.host}") String host, @Value ("${spring.redis.port}") int port, @Value ("${spring.redis.timeout}") int timeout, @Value ("${spring.redis.password}") String password) {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost(host);
		redisManager.setPort(port);
		redisManager.setTimeout(timeout);
		redisManager.setPassword(password);
		return redisManager;
	}
}