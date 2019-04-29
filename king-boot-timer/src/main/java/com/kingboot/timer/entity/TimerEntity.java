package com.kingboot.timer.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * <p class="detail">
 * 功能:定时器基本信息
 * </p>
 * @author Kings
 * @ClassName Topic info.
 * @Version V1.0.
 * @date 2019.02.04 10:39:32
 */
@Getter
@Setter
public class TimerEntity {
	
	private String _id;
	
	/**
	 * 唯一标示(不可更改，就是kafka topic，格式timer_xxx)
	 */
	private String topic;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 描述
	 */
	private String describe;
	/**
	 * cron表达式
	 */
	private String corn;
	/**
	 * kafka message
	 */
	private String message;
	/**
	 * 是否启用（true：启用 false：停用）
	 */
	private Boolean status;
	
	/**
	 * 是否删除 （true：删除 false：未删除）
	 */
	private Boolean delStatus = false;
	
	/**
	 * 创建日期
	 */
	private String createTime;
	
	
}
