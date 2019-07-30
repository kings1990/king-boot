package com.kingboot.timer.config;


import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * <p class="detail">
 * 功能:定时器配置
 * </p>
 * @author Kings
 * @ClassName Timer config.
 * @Version V1.0.
 * @date 2019.07.30 10:38:45
 */
@Configuration
public class TimerConfig {
	
	@Bean
	public ZkClient zkClient() {
		return new ZkClient("localhost:2181", 30000, 0x7fffffff, new BytesPushThroughSerializer());
	}
	
	@Bean
	public SchedulerFactoryBean scheduler() {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		return schedulerFactoryBean;
	}
	
	
}
