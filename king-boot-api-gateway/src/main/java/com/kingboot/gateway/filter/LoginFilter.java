package com.kingboot.gateway.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * <p class="detail">
 * 功能:登录过滤器
 * </p>
 * @author Kings
 * @ClassName LoginFilter
 * @Version V1.0.
 * @date 2019.07.30 11:50:48
 */
@Component
public class LoginFilter extends ZuulFilter {
	
	/** Pattern. */
	private static Pattern  pattern = Pattern.compile("/apigateway/u/user/detail/(.*)");
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
		boolean match = pattern.matcher(request.getRequestURI()).matches();
		return match;
	}
	
	
	@Override
	public Object run() throws ZuulException {
		//jwt
		
		return "1";
	}
}
