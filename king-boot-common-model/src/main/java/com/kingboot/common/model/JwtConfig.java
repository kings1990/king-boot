package com.kingboot.common.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

/**
 * <p class="detail">
 * 功能:jwt配置
 * </p>
 * @author Kings
 * @ClassName JwtConfig
 * @Version V1.0.
 * @date 2019.07.30 11:35:22
 */
@Getter
@ToString
public class JwtConfig {
	
	@Value ("${security.jwt.uri:/auth/**}")
	private String uri;
	
	@Value ("${security.jwt.header:Authorization}")
	private String header;
	
	@Value ("${security.jwt.prefix:Bearer }")
	private String prefix;
	
	@Value ("${security.jwt.expiration:#{24*60*60}}")
	private int expiration;
	
	@Value ("${security.jwt.secret:JwtSecretKey}")
	private String secret;
	
	public String getUri() {
		return uri;
	}
	
	public String getHeader() {
		return header;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public int getExpiration() {
		return expiration;
	}
	
	public String getSecret() {
		return secret;
	}
	
}