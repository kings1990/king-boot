package com.kingboot.basic.config.advice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.util.WebUtils.ERROR_STATUS_CODE_ATTRIBUTE;

/**
 * <p class="detail">
 * 功能:视图异常配置
 * </p>
 * @author Kings
 * @ClassName View error controller.
 * @Version V1.0.
 * @date 2019.01.02 17:33:28
 */
@Controller
public class ViewErrorController implements ErrorController {
    /** The constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAdvice.class);
    
    
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
    
    /**
     * Returns the path of the error page.
     * @return the error path
     */
    @Override
    public String getErrorPath() {
        return "/error";
    }
}

