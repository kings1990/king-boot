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
 * @author gongtao
 * @version 2018-03-30 10:49
 * @update 2018-08-29 升级 pac4j 版本到 4.0.0
 **/
@Configuration
@PropertySource ("classpath:config/cas.properties")
public class ShiroConfig {
    
    
    @Value ("${cas.server.login.url}")
    private String casLoginUrl;
    
    @Value ("${basic.logout.success.url}")
    private String casLogoutSuccessUrl;
    
    @Value ("${basic.login.url}")
    private String basicLoginUrl;
    
    /** 客户端名称 */
    // @Value ("${cas.client-name}")
    // private String clientName;
    
    @Value ("${shiro.timeout.seconds}")
    private Integer shiroTimeoutSeconds;
    
    @Value ("${basic.login.url}")
    private String loginUrl;
    
    @Value ("${cas.callback.url}")
    private String callbackUrl;
    
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    
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
     * @return
     */
    @Bean
    public Pac4jSubjectFactory subjectFactory() {
        return new Pac4jSubjectFactory();
    }
    
    /**
     * 下面的代码是添加注解支持
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
     * @param shiroFilterFactoryBean
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
     * @param securityManager
     * @param config
     *
     * @return
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
    
    @Bean
    public SessionDAO sessionDAO() {
        return new MemorySessionDAO();
    }
    
    /**
     * 自定义cookie名称
     * @return
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
    
    @Bean KingsSessionManager sessionManager(SimpleCookie sessionIdCookie, SessionDAO sessionDAO) {
        KingsSessionManager sessionManager = new KingsSessionManager();
        sessionManager.setSessionIdCookie(sessionIdCookie);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setGlobalSessionTimeout(shiroTimeoutSeconds * 1000);
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        return sessionManager;
    }
    
    
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
    
    @Bean (name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
    
    @Bean
    public RedisCacheManager cacheManager(RedisManager redisManager) {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        return redisCacheManager;
    }
    
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