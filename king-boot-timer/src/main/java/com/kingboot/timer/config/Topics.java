package com.kingboot.timer.config;

/**
 * <p class="detail">
 * 功能:topic枚举
 * </p>
 * @author Kings
 * @ClassName Topics.
 * @Version V1.0.
 * @date 2019.07.30 10:39:47
 */
public enum Topics {
	//测试
	TEST("test"),
	//每日
	DAILY_TIMER("daily_timer"),
	//每分钟
	MINUTE_TIMER("minute_timer"),
	//每日测试
	DAILY_TIMER_TEST("daily_timer_test"),
	//每日8点
	DAILY_TIMER_8OCLOCK("daily_timer_8oclock");
	
	private String value;
	
	private Topics(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}