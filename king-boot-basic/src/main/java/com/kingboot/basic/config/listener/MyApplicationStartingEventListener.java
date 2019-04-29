package com.kingboot.basic.config.listener;


import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * 监听类型
 * ApplicationStartingEvent
 * ApplicationEnvironmentPreparedEvent
 * ApplicationPreparedEvent
 * ApplicationStartedEvent
 * ApplicationReadyEvent
 * ApplicationFailedEvent
 */
public class MyApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent> {
	
	@Override
	public void onApplicationEvent(ApplicationStartingEvent event) {
		System.err.println("....启动开始.....");
	}
}
