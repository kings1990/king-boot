package com.kingboot.basic;

import com.kingboot.basic.config.listener.MyApplicationStartingEventListener;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages={"com.kingboot.user.client","com.kingboot.basic"},exclude = {DataSourceAutoConfiguration.class},excludeName = "org.apache.dubbo.spring.boot.autoconfigure.DubboRelaxedBinding2AutoConfiguration")
@EnableTransactionManagement
//@tk.mybatis.spring.annotation.MapperScan (basePackages = "com.kingboot.basic.dao.boot.mapper", sqlSessionFactoryRef = "sqlSessionFactory-boot")
@tk.mybatis.spring.annotation.MapperScan (basePackages = "com.kingboot.basic.dao.boot.mapper")
@EnableScheduling//定时任务
@EnableAsync//异步任务
@EnableDubbo
@EnableEurekaClient
@EnableFeignClients("com.kingboot.user.client")
@EnableHystrix
public class KingBootBasicApplication {
    
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(KingBootBasicApplication.class);
        app.addListeners(new MyApplicationStartingEventListener());
        app.run(args);
    }
}
