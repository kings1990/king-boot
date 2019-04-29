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

import com.kingboot.basic.config.common.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class ErrorHelper {
	private static Logger logger = LoggerFactory.getLogger(ErrorHelper.class);
	
	public RestResponse converBindError2AjaxError(BindingResult result, boolean validAllPropeerty) {
		
		try {
			RestResponse res = new RestResponse(HttpStatus.BAD_REQUEST, "validate error!");
			
			List<ErrorMessage> errorMesages = new ArrayList<>();
			List<ObjectError> objectErrors = result.getAllErrors();
			String objErrorStr;
			for (ObjectError objError : objectErrors) {
				//去掉 @Pattern中错误信息的双引号
				objErrorStr = objError.getDefaultMessage().replaceAll("\"", "");
				if (objError instanceof FieldError) {
					FieldError objectError = (FieldError) objError;
					errorMesages.add(new ErrorMessage(objectError.getField(), objErrorStr));
				} else {
					errorMesages.add(new ErrorMessage(objError.getCode(), objErrorStr));
				}
				if (! validAllPropeerty) {
					//noinspection BreakStatement
					break;//just one error object
				}
			}
			res.setData(errorMesages);
			return res;
		} catch (Exception e) {
			logger.error("com.gttown.common.support.web.validate.ErrorHelper error", e);
		}
		return null;
	}
}
