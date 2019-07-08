package com.kingboot.mplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@TableId(type = IdType.AUTO)
	private Long id;
	private String countryname;
	private String countrycode;
	
}