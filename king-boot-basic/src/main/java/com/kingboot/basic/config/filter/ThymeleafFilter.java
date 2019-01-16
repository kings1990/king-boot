package com.kingboot.basic.config.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.FilterConfig;
import javax.servlet.*;
import java.io.IOException;

@Component
public class ThymeleafFilter implements Filter {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ThymeleafFilter.class);
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug(ThymeleafFilter.class.getCanonicalName() + " init...");
    }
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.debug("do some operation for thymeleaf request");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
