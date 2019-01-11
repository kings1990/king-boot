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

/**
 * <p class="detail">
 * 功能:字段验证错误消息
 * </p>
 * @author Kings
 * @ClassName Error message.
 * @Version V1.0.
 * @date 2016.08.03 17:49:37
 */
public class ErrorMessage {
    
    /** 字段名 */
    private String fieldName;
    /** 错误提示. */
    private String message;
    
    /**
     * Instantiates a new Error message.
     * @param fieldName the field name
     * @param message   the message
     */
    public ErrorMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
    
    /**
     * Gets field name.
     * @return the field name
     */
    public String getFieldName() {
        return fieldName;
    }
    
    /**
     * Gets message.
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    
    @Override
    public String toString() {
        return "{\"fieldName\":\""+fieldName+"\",\"message\":\""+message+"\"}";
    }
}