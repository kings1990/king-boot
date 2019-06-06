package com.kingboot.common.config.mybatis.mapper;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;
import tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper;

/**
 * <p class="detail">
 * 功能：Mysql数据库的Mapper需要继承此Mapper
 * </p>
 * @param <T>
 *
 * @author Kings
 * @version V1.0
 * @ClassName: MybatisMSMapper
 * @date 2016年2月22日
 */

@RegisterMapper
public interface MybatisMysqlMapper<T> extends AbstractCommonMapper<T>, InsertMapper<T>, InsertSelectiveMapper<T> {
}
