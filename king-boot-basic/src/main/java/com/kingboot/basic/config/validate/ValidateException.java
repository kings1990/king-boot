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

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p class="detail">
 * 功能:验证异常
 * </p>
 * @author Kings
 * @ClassName Validate exception.
 * @Version V1.0.
 * @date 2016.08.09 14:45:58
 */
@ResponseStatus (value = HttpStatus.BAD_REQUEST, code = HttpStatus.BAD_REQUEST)
public class ValidateException extends RuntimeException {
	
	/**
	 * Instantiates a new Validate exception.
	 * @param message the message
	 */
	public ValidateException(String message) {
		super(message);
	}
}
