package com.kingboot.basic.config.mybatis.mapper;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;
import tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper;

/**
 *
 * <p class="detail">
 * 功能：Mysql数据库的Mapper需要继承此Mapper
 * </p>
 * @ClassName: MybatisMSMapper
 * @version V1.0
 * @date 2016年2月22日
 * @author Kings
 * @param <T>
 */

@RegisterMapper
public interface MybatisMysqlMapper<T> extends AbstractCommonMapper<T>, InsertMapper<T>, InsertSelectiveMapper<T> {
}
