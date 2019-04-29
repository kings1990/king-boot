package com.kingboot.gateway.filter;


import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

@Component
public class RateLimiterFilter extends ZuulFilter {
	
	//限流
	//没秒产生1000个令牌
	public static final RateLimiter RATE_LIMITER = RateLimiter.create(1000);
	
	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}
	
	
	@Override
	public int filterOrder() {
		return - 4;
	}
	
	
	@Override
	public boolean shouldFilter() {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		boolean match = Pattern.compile("/apigateway/b/order/(.*)").matcher(request.getRequestURI()).matches();
		return match;
	}
	
	
	@Override
	public Object run() throws ZuulException {
		RequestContext currentContext = RequestContext.getCurrentContext();
		if (RATE_LIMITER.tryAcquire()) {
			currentContext.setSendZuulResponse(false);
			currentContext.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
		}
		return null;
	}
}
