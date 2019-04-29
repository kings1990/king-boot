package com.kingboot.basic.config.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.servlet.FilterConfig;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p class="detail">
 * 功能:脚本过滤
 * </p>
 * @author Kings
 * @ClassName Script filter.
 * @Version V1.0.
 * @date 2019.01.04 20:33:45
 */
@Component
public class ScriptFilter implements Filter {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScriptFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.debug(ScriptFilter.class.getCanonicalName() + " init...");
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		boolean filterFlag = "POST".equalsIgnoreCase(HttpMethod.POST.name()) || "PUT".equalsIgnoreCase(HttpMethod.PUT.name()) || "DELETE".equalsIgnoreCase(HttpMethod.DELETE.name());
		if (filterFlag) {
			request = new KingRequest(request, request.getParameterMap());
		}
		chain.doFilter(request, res);
	}
	
	@Override
	public void destroy() {
	
	}
}