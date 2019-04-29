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

package com.kingboot.basic.config.common;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;


/**
 * <p class="detail">
 * 功能:REST接口标准容器
 * </p>
 * @param <T> the type parameter
 *
 * @author Kings
 * @ClassName Rest response.
 * @Version V1.0.
 * @date 2019.01.02 17:17:46
 */
public class RestResponse<T> {
	/**
	 * The constant VOID_REST_RESPONSE.
	 */
	public static final RestResponse<Void> VOID_REST_RESPONSE = new RestResponse<>(null);
	
	/** Code. */
	@ApiModelProperty ("Code码")
	private int code;
	
	/** Message. */
	@ApiModelProperty ("消息")
	private String message;
	
	@ApiModelProperty ("下一个需要跳转的地址")
	private String redirectUrl;
	
	/** Data. */
	@ApiModelProperty ("返回实体数据")
	private T data = null;
	
	public RestResponse(int code, String message, T data) {
		this.code = code;
		this.message = message;
		// this.data = data;
		//this.data = data;
		// if (data != null && data.getClass().equals(PageInfo.class)) {
		//     Map<String, Object> map = new HashMap<String, Object>();
		//     map.put("pageInfo", data);
		//     this.data = (T) map;
		// } else {
		this.data = data;
		//}
		
	}
	
	/**
	 * Instantiates a new Rest response.
	 * @param code    the code
	 * @param message the message
	 * @param data    the data
	 */
	public RestResponse(int code, String message, T data, String redirectUrl) {
		this.code = code;
		this.message = message;
		// this.data = data;
		//this.data = data;
		// if (data != null && data.getClass().equals(PageInfo.class)) {
		//     Map<String, Object> map = new HashMap<String, Object>();
		//     map.put("pageInfo", data);
		//     this.data = (T) map;
		// } else {
		this.data = data;
		this.redirectUrl = redirectUrl;
		//}
		
	}
	
	/**
	 * Instantiates a new Rest response.
	 * @param status the status
	 * @param data   the data
	 */
	public RestResponse(HttpStatus status, T data) {
		this(status.value(), status.getReasonPhrase(), data, "");
	}
	
	public RestResponse(HttpStatus status, String message, T data, String redirectUrl) {
		this(status.value(), status.getReasonPhrase(), data, redirectUrl);
	}
	
	/**
	 * Instantiates a new Rest response.
	 * @param data the data
	 */
	public RestResponse(T data) {
		this(HttpStatus.OK.value(), "OK", data, "");
	}
	
	/**
	 * Gets data.
	 * @return the data
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * Sets data.
	 * @param data the data
	 */
	public void setData(T data) {
		this.data = data;
	}
	
	/**
	 * Gets code.
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	
	/**
	 * Sets code.
	 * @param code the code
	 */
	public void setCode(int code) {
		this.code = code;
	}
	
	/**
	 * Gets message.
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Sets message.
	 * @param message the message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getRedirectUrl() {
		return redirectUrl == null ? "" : redirectUrl;
	}
	
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	
	@Override
	public String toString() {
		return "{\"code\":" + code + ",\"message\":\"" + message + "\",\"data\":" + data + ",\"redirectUrl\":\"" + getRedirectUrl() + "\"}";
	}
}
