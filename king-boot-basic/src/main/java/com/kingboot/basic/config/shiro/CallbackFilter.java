package com.kingboot.basic.config.shiro;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class CallbackFilter extends io.buji.pac4j.filter.CallbackFilter {
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.err.println(request.getRequestURL());
        super.doFilter(servletRequest, servletResponse, filterChain);
    }
    
}