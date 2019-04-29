package com.kingboot.basic.config.shiro;


import org.joda.time.DateTime;
import org.pac4j.cas.client.rest.CasRestFormClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.cas.config.CasProtocol;
import org.pac4j.core.client.Clients;
import org.pac4j.http.client.direct.HeaderClient;
import org.pac4j.jwt.config.encryption.SecretEncryptionConfiguration;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureBefore (ShiroConfig.class)
public class KingClientsConfig {
	/** 地址为：cas地址 */
	@Value ("${cas.server.url}")
	private String casServerUrl;
	
	@Value ("${cas.server.login.url}")
	private String casLoginUrl;
	
	/** 相当于一个标志，可以随意 */
	// @Value ("${cas.client-name}")
	// private String clientName;
	
	@Value ("${cas.callback.url}")
	private String callbackUrl;
	
	@Value ("${basic.login.url}")
	private String loginUrl;
	
	@Value ("${jwt.salt}")
	private String salt;
	
	@Bean
	protected Clients kingClients() {
		//可以设置默认client
		Clients clients = new Clients();
		HeaderClient jwtClient = new HeaderClient("jToken", jwtAuthenticator());
		jwtClient.setName("jwtClient");
		//支持的client全部设置进去
		clients.setClients(casClient(), casRestFormClient(), jwtClient);
		return clients;
	}
	
	/**
	 * cas 客户端配置
	 */
	@Bean
	public CasClient casClient() {
		CasClient casClient = new CasClient(casConfiguration());
		//客户端回调地址
		casClient.setCallbackUrl(callbackUrl);
		casClient.setName("casClient");
		return casClient;
	}
	
	/**
	 * 请求cas服务端配置
	 */
	@Bean
	public CasConfiguration casConfiguration() {
		final CasConfiguration configuration = new CasConfiguration();
		//CAS server登录地址
		configuration.setLoginUrl(casLoginUrl);
		configuration.setProtocol(CasProtocol.CAS30);
		configuration.setAcceptAnyProxy(true);
		configuration.setPrefixUrl(casServerUrl + "/");
		return configuration;
	}
	
	/**
	 * JWT校验器，也就是目前设置的ParameterClient进行的校验器，是rest/或者前后端分离的核心校验器
	 */
	@Bean
	protected JwtAuthenticator jwtAuthenticator() {
		JwtAuthenticator jwtAuthenticator = new JwtAuthenticator(new SecretSignatureConfiguration(salt), new SecretEncryptionConfiguration(salt));
		jwtAuthenticator.setExpirationTime(DateTime.now().plusMinutes(5).toDate());
		return jwtAuthenticator;
	}
	
	/**
	 * 通过rest接口可以获取tgt，获取service ticket，甚至可以获取CasProfile
	 */
	@Bean
	protected CasRestFormClient casRestFormClient() {
		CasRestFormClient casRestFormClient = new CasRestFormClient();
		casRestFormClient.setConfiguration(casConfiguration());
		casRestFormClient.setName("restClient");
		return casRestFormClient;
	}
}
