package com.kingboot.basic.config.mybatis.mapper;


import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@RegisterMapper
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
