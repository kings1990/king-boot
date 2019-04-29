package com.kingboot.basic.config.shiro;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kingboot.basic.config.common.RestResponse;
import io.buji.pac4j.filter.SecurityFilter;
import org.springframework.http.HttpStatus;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class KingsSecurityFilter extends SecurityFilter {
	private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();
	
	private String loginUrl;
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		super.doFilter(servletRequest, servletResponse, filterChain);
		if (response.getStatus() == HttpStatus.FOUND.value()) {
			if (isAjaxOrMobileRequest(servletRequest)) {
				//todo  add service
				//String loginUrl = ((CasClient) (this.getConfig().getClients().findClient(getClients()))).getConfiguration().getLoginUrl();
				
				RestResponse<String> restResponse = new RestResponse<>(HttpStatus.UNAUTHORIZED, "Unauthenticated user", "", loginUrl);
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				OutputStream outputStream = servletResponse.getOutputStream();
				outputStream.write(GSON.toJson(restResponse).getBytes());
			}
		}
	}
	
	private boolean isAjaxOrMobileRequest(ServletRequest servletRequest) {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		return request.getServletPath().startsWith("/thirdapi") || request.getServletPath().startsWith("/api") || "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
	}
	
	public String getLoginUrl() {
		return loginUrl;
	}
	
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
}
