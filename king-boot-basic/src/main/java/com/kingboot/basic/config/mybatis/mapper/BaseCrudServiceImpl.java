package com.kingboot.basic.config.mybatis.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p class="detail">
 * 功能:base crud service
 * </p>
 * @param <T> the type parameter
 *
 * @author Kings
 * @ClassName Base crud service.
 * @Version V1.0.
 * @date 2016.02.29 08:53:52
 */
@SuppressWarnings ("SpringAutowiredFieldsWarningInspection")
@Service
public abstract class BaseCrudServiceImpl<T> extends AbstractBaseCrudServiceImpl<T> implements BaseCrudService<T> {
	/** Mapper. */
	@Autowired
	protected MybatisMysqlMapper<T> mapper;

	@Override
	public AbstractCommonMapper<T> getMapper() {
		return mapper;
	}

	/**
	 * <p class="detail">
	 * 功能：插入一条数据
	 * </p>
	 * @param record:实体
	 *
	 * @return int
	 * @author Kings
	 * @date 2015年8月4日
	 */
	@Override
	public int insert(T record) {
		return mapper.insert(record);
	}

	/**
	 * <p class="detail">
	 * 功能：插入一条数据,只插入不为null的字段,不会影响有默认值的字段
	 * </p>
	 * @param record:实体
	 *
	 * @return int
	 * @author Kings
	 * @date 2015年8月4日
	 */
	@Override
	public int insertSelective(T record) {
		return mapper.insertSelective(record);
	}


}