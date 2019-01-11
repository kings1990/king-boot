/*
 * 版权所有(C) 浙江大道网络科技有限公司2011-2020
 * Copyright 2009-2020 Zhejiang GreatTao Factoring Co., Ltd.
 *   
 * This software is the confidential and proprietary information of
 * Zhejiang GreatTao Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Zhejiang GreatTao
 */
package com.kingboot.basic.config.validate;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;


/**
 * <p class="detail">
 * 功能:验证切面
 * </p>
 * @author Kings
 * @ClassName Validate handel aspect.
 * @Version V1.0.
 * @date 2016.08.03 17:51:42
 */
@Aspect
@Component
public class ValidateHandelAspect {
    /**judge is all property error need to be export*/
    private boolean outputAllPropError = false;
    
    /**
     * <p class="detail">
     * 功能:验证输出结果
     * </p>
     * @param pjp :
     *
     * @return object
     * @throws Throwable the throwable
     * @author Kings
     * @date 2016.08.03 17:51:42
     */
    @Around ("validatePointcut()")
    public Object validateAround(ProceedingJoinPoint pjp) throws Throwable  {
        Object[] args =  pjp.getArgs();
        BindingResult bindingResult = null;
        if (args != null) {
            for (Object obj : args) {
                if (obj instanceof BindingResult) {
                    bindingResult = (BindingResult) obj;
                    //noinspection BreakStatement
                    break;
                }
            }
        }
        
        if ( bindingResult != null && bindingResult.hasErrors() ){//异常输出
            ErrorHelper errorHelper = new ErrorHelper(); 
            throw new ValidateException(errorHelper.converBindError2AjaxError(bindingResult,outputAllPropError).toString());
            //return errorHelper.converBindError2AjaxError(bindingResult,outputAllPropError);
        } else {//正常输出
            return pjp.proceed(args);
        }
    }
    
    /**
     * <p class="detail">
     * 功能:切点
     * </p>
     * @author Kings
     * @date 2016.08.03 17:51:42
     */
    @Pointcut ("@annotation(com.kingboot.basic.config.validate.ValidateMethod)")
    public void validatePointcut() {
        
    }
    
    public void setOutputAllPropError(boolean outputAllPropError) {
        this.outputAllPropError = outputAllPropError;
    }
}
