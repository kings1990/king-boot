package com.kingboot.mplus.generator.entity;

import lombok.Data;

/**
 * @author waylen.chi
 * @date 2018/07/29
 * 列属性： https://blog.csdn.net/lkforce/article/details/79557482
 */
@Data
public class ColumnEntity {
	/**
	 * 列表
	 */
	private String columnName;
	/**
	 * 数据类型
	 */
	private String dataType;
	/**
	 * 备注
	 */
	private String comments;

	/**
	 * 驼峰属性
	 */
	private String caseAttrName;
	/**
	 * 普通属性
	 */
	private String lowerAttrName;
	/**
	 * 属性类型
	 */
	private String attrType;
	/**
	 * 其他信息
	 */
	private String extra;
}
