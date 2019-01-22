package com.kingboot.basic.dao.boot.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Id;
import java.io.Serializable;


@Getter
@Setter
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ColumnType (jdbcType = JdbcType.BIGINT)
    private Long id;
    private String countryname;
    private String countrycode;
    
}