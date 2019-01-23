package com.kingboot.basic.config.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(RequestTimeInterceptor.class);
    private static final String REQUEST_TIME = "reqTime";
    private static final String POST_HANDLE_TIME = "postHandleTime";
    private static final String ERROR = "/error";
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(REQUEST_TIME, System.currentTimeMillis());
        return super.preHandle(request, response, handler);
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long begTime = (Long) request.getAttribute(REQUEST_TIME);
        long postHandleTime = System.currentTimeMillis();
        request.setAttribute(POST_HANDLE_TIME, postHandleTime);
        if (!request.getRequestURI().startsWith(request.getContextPath()+"/static")) {
            logger.info("{} postHandle cost {} ms", request.getRequestURL(), postHandleTime - begTime);
        }
        super.postHandle(request, response, handler, modelAndView);
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        
        Long begTime = (Long) request.getAttribute(REQUEST_TIME);
        Long completeTime = (Long) request.getAttribute(POST_HANDLE_TIME);
        long afterCompletionTime = System.currentTimeMillis();
        
        if (begTime != null && completeTime != null) {
            long renderTime = afterCompletionTime - completeTime;
            if (!request.getRequestURI().startsWith(request.getContextPath()+"/static")) {
                logger.info("{} afterCompletion render view cost {} ms, totally cost {} ms", request.getRequestURL(), renderTime, afterCompletionTime - begTime);
            }
            
        }
        super.afterCompletion(request, response, handler, ex);
    }
    
    
}
