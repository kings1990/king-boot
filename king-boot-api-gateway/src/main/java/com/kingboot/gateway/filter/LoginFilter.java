package com.kingboot.gateway.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

@Component
public class LoginFilter extends ZuulFilter {
	
	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}
	
	
	@Override
	public int filterOrder() {
		return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
	}
	
	
	@Override
	public boolean shouldFilter() {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		boolean match = Pattern.compile("/apigateway/u/user/detail/(.*)").matcher(request.getRequestURI()).matches();
		return match;
	}
	
	
	@Override
	public Object run() throws ZuulException {
		//jwt
		
		return "1";
	}
}
