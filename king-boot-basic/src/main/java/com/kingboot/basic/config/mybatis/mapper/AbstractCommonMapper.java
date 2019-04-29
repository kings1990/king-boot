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
package com.kingboot.basic.config.mybatis.mapper;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.base.BaseDeleteMapper;
import tk.mybatis.mapper.common.base.BaseSelectMapper;
import tk.mybatis.mapper.common.base.BaseUpdateMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * <p class="detail">
 * 功能:抽象公共mapper
 * </p>
 * @param <T> the type parameter
 *
 * @author Kings
 * @ClassName Abstract Common mapper.
 * @Version V1.0.
 * @date 2016.07.19 16:22:24
 */
@RegisterMapper
public interface AbstractCommonMapper<T> extends BaseDeleteMapper<T>, BaseSelectMapper<T>, BaseUpdateMapper<T>, ExampleMapper<T>, CustomSelectMapper<T>, CustomDeleteMapper<T>, InsertListMapper<T> {
}
