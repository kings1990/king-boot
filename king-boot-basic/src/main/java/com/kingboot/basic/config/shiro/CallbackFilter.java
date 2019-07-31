package com.kingboot.basic.config.shiro;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kingboot.basic.config.common.RestResponse;
import org.apache.shiro.web.servlet.ShiroHttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;


/**
 * <p class="detail">
 * 功能:回调过滤器
 * </p>
 * @author Kings
 * @ClassName CallbackFilter
 * @Version V1.0.
 * @date 2019.07.31 16:13:21
 */
public class CallbackFilter extends io.buji.pac4j.filter.CallbackFilter {
	/** The constant GSON. */
	private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();
	
	/** The constant PLATFORM. */
	private static final String PLATFORM = "platform";
	
	/** The constant PLATFORM_MOBILE. */
	private static final String PLATFORM_MOBILE = "m";
	
	/**
	 * <p class="detail">
	 * 功能:
	 * </p>
	 * @param request :
	 *
	 * @return boolean
	 * @author Kings
	 * @date 2019.07.31 16:13:21
	 */
	private static boolean isMobileLogin(HttpServletRequest request) {
		String loginPlatform = request.getParameter(PLATFORM);
		return ! Strings.isNullOrEmpty(loginPlatform) && loginPlatform.equals(PLATFORM_MOBILE);
	}
	
	/**
	 * <p class="detail">
	 * 功能:
	 * </p>
	 * @param response     :
	 * @param restResponse :
	 *
	 * @throws Exception the exception
	 * @author Kings
	 * @date 2019.07.31 16:13:21
	 */
	private static void doReturnSession(ServletResponse response, RestResponse<?> restResponse) throws Exception {
		ShiroHttpServletResponse httpServletResponse = (ShiroHttpServletResponse) response;
		httpServletResponse.addHeader("Content-Type", MediaType.APPLICATION_JSON.toString());
		httpServletResponse.setStatus(restResponse.getCode());
		try {
			OutputStream outputStream = httpServletResponse.getOutputStream();
			outputStream.write(GSON.toJson(restResponse).getBytes());
		} catch (IOException e) {
			PrintWriter writer = httpServletResponse.getWriter();
			writer.write(GSON.toJson(restResponse));
		}
	}
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		System.err.println(request.getRequestURL());
		if (isMobileLogin(request)) {
			try {
				super.doFilter(servletRequest, servletResponse, filterChain);
				doReturnSession(response, new RestResponse<>(HttpStatus.OK, request.getSession().getId()));
				return;
			} catch (Exception e) {
				super.doFilter(servletRequest, servletResponse, filterChain);
			}
		}
		super.doFilter(servletRequest, servletResponse, filterChain);
	}
	
}