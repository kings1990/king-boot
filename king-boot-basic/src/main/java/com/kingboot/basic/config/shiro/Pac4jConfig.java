package com.kingboot.basic.config.shiro;

import io.buji.pac4j.context.ShiroSessionStore;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.cas.config.CasProtocol;
import org.pac4j.core.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gongtao
 * @version 2018-07-06 9:35
 * @update 2018-08-29 升级 pac4j 版本到 4.0.0
 **/
@Configuration
public class Pac4jConfig {
    
    /** 地址为：cas地址 */
    @Value ("${cas.server.url}")
    private String casServerUrl;
    
    @Value ("${cas.server.login.url}")
    private String casLoginUrl;
    
    /** 相当于一个标志，可以随意 */
    @Value ("${cas.client-name}")
    private String clientName;
    
    @Value ("${cas.callback.url}")
    private String callbackUrl;
    
    @Value ("${basic.login.url}")
    private String loginUrl;
    
    /**
     * pac4j配置
     * @param casClient
     * @param shiroSessionStore
     *
     * @return
     */
    @Bean ("authcConfig")
    public Config config(CasClient casClient, ShiroSessionStore shiroSessionStore) {
        Config config = new Config(casClient);
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
    
    /**
     * cas 客户端配置
     * @param casConfig
     *
     * @return
     */
    @Bean
    public CasClient casClient(CasConfiguration casConfig) {
        CasClient casClient = new CasClient(casConfig);
        //客户端回调地址
        casClient.setCallbackUrl(callbackUrl);
        casClient.setName(clientName);
        return casClient;
    }
    
    /**
     * 请求cas服务端配置
     * @param
     */
    @Bean
    public CasConfiguration casConfig() {
        final CasConfiguration configuration = new CasConfiguration();
        //CAS server登录地址
        configuration.setLoginUrl(loginUrl);
        //CAS 版本，默认为 CAS30，我们使用的是 CAS20
        configuration.setProtocol(CasProtocol.CAS30);
        configuration.setAcceptAnyProxy(true);
        configuration.setPrefixUrl(casServerUrl + "/");
        return configuration;
    }
    
    
}