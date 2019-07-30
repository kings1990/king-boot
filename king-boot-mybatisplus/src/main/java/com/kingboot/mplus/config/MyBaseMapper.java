package com.kingboot.mplus.config;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p class="detail">
 * 功能:自定义basemapper
 * </p>
 * @param <T> the type parameter
 *
 * @author Kings
 * @ClassName MyBaseMapper
 * @Version V1.0.
 * @date 2019.07.30 11:21:10
 */
public interface MyBaseMapper<T> extends BaseMapper<T> {
	
	
	/**
	 * <p class="detail">
	 * 功能:自定义全局方法
	 * </p>
	 * @param entity :实体
	 *
	 * @return int
	 * @author Kings
	 * @date 2019.07.30 11:21:10
	 */
	int deleteByIdWithFill(T entity);
}
