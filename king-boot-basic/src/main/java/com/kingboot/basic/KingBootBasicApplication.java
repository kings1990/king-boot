package com.kingboot.basic;

import com.kingboot.basic.config.listener.MyApplicationStartingEventListener;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@tk.mybatis.spring.annotation.MapperScan (basePackages = "com.kingboot.basic.dao.boot.mapper", sqlSessionFactoryRef = "sqlSessionFactory-boot")
@EnableScheduling//定时任务
@EnableAsync//异步任务
@EnableDubbo
@EnableEurekaClient
public class KingBootBasicApplication {
    
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(KingBootBasicApplication.class);
        app.addListeners(new MyApplicationStartingEventListener());
        app.run(args);
    }
}
