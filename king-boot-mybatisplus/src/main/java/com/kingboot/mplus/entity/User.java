package com.kingboot.mplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户
 * @author Kings
 * @date 2019-07-08 14:24:50
 */
@Data
@TableName ("user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 *
	 */
	@TableId
	private Integer id;
	/**
	 * 用户名
	 */
	private String name;
	/**
	 *
	 */
	private LocalDateTime createTime;
	/**
	 * 版本
	 */
	@Version
	private Integer version;
	
	@TableField (fill = FieldFill.UPDATE)
	@JsonFormat (shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd", timezone="GMT+8")
	private LocalDateTime lastUpdatedTime;
	
	/**
	 * 删除标记  1-未删除 0-已删除
	 */
	@TableLogic
	private Boolean deleteFlag;
	
	@TableField (fill = FieldFill.UPDATE)
	private Integer syncFlag;
	/**
	 * 租户id 1-浙江 2-上海
	 */
	private Integer tenantId;
}
