package com.kingboot.timer.config;

public enum Topics {
	TEST("test"), DAILY_TIMER("daily_timer"), MINUTE_TIMER("minute_timer"), DAILY_TIMER_TEST("daily_timer_test"), DAILY_TIMER_8OCLOCK("daily_timer_8oclock");
	
	private String value;
	
	private Topics(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}