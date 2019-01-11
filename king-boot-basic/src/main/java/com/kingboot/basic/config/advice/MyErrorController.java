package com.kingboot.basic.config.advice;

import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.util.WebUtils.ERROR_STATUS_CODE_ATTRIBUTE;

@Controller
public class MyErrorController extends BasicErrorController {
    
    public MyErrorController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }
    
    @Override
    public String getErrorPath() {
        return "/error";
    }
    
    /**
     * 覆盖默认的HTML响应
     */
    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        //请求的状态
        HttpStatus status = getStatus(request);
        response.setStatus(getStatus(request).value());
        
        String viewName = "";
        Integer statusCode = (Integer) request.getAttribute(ERROR_STATUS_CODE_ATTRIBUTE);
        if (statusCode == 401) {
            viewName = "thymeleaf/error/401";
        } else if (statusCode == 404) {
            viewName = "thymeleaf/error/404";
        } else if (statusCode == 403) {
            viewName = "thymeleaf/error/403";
        } else {
            viewName = "thymeleaf/error/500";
        }
        
        Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        ModelAndView modelAndView = resolveErrorView(request, response, status, model);
        //指定自定义的视图
        return (modelAndView == null ? new ModelAndView(viewName, model) : modelAndView);
    }
    
    /**
     * 覆盖默认的Json响应
     */
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);
        
        //输出自定义的Json格式
        Map<String, Object> map = new HashMap<>();
        
        map.put("code", status.value());
        map.put("message", status.getReasonPhrase());
        String message = (String) body.get("message");
        if (status.equals(HttpStatus.BAD_REQUEST)) {
            Map<String, Object> errorMessage = (Map<String, Object>) JSONObject.parse(message);
            map.put("data", errorMessage.get("data"));
        } else {
            map.put("data", message);
        }
        return new ResponseEntity<>(map, status);
    }
    
    @RequestMapping ("/error")
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(ERROR_STATUS_CODE_ATTRIBUTE);
        if (statusCode == 401) {
            return "thymeleaf/error/401";
        } else if (statusCode == 404) {
            return "thymeleaf/error/404";
        } else if (statusCode == 403) {
            return "thymeleaf/error/403";
        } else {
            return "thymeleaf/error/500";
        }
    }
}