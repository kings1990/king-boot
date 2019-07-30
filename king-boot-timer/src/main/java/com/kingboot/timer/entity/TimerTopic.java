package com.kingboot.timer.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * <p class="detail">
 * 功能:定时器topic
 * </p>
 * @author Kings
 * @ClassName Timer topic.
 * @Version V1.0.
 * @date 2019.07.30 10:39:03
 */
@Getter
@Setter
public class TimerTopic {
	
	private String _id;
	
	
	private String topic;
	
	private String createTime;
	
	/**
	 * 是否删除 （true：删除 false：未删除）
	 */
	private Boolean delStatus = false;
	
	
}
