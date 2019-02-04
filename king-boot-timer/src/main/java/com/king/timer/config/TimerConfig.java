package com.king.timer.config;


import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

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
