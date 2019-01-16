package com.kingboot.basic.config.advice;


import com.kingboot.basic.config.common.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * <p class="detail">
 * 功能:全局rest控制器异常
 * </p>
 * @author Kings
 * @ClassName Rest controller advice.
 * @Version V1.0.
 * @date 2019.01.02 17:33:44
 */
@ControllerAdvice (annotations = RestController.class)
public class RestControllerAdvice {
    /**
     * <p class="detail">
     * 功能:500异常
     * </p>
     * @param e :
     *
     * @return rest response
     * @author Kings
     * @date 2019.01.02 17:33:44
     */
    @ExceptionHandler (Exception.class)
    @ResponseStatus (HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public RestResponse<String> handleUnauthorizedException(Exception e) {
        return new RestResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), "");
    }
}

