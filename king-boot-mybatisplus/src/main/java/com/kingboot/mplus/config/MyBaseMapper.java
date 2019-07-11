package com.kingboot.mplus.config;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface MyBaseMapper<T> extends BaseMapper<T> {
	
	/**
	 * 自定义全局方法
	 */
	int deleteByIdWithFill(T entity);
}
