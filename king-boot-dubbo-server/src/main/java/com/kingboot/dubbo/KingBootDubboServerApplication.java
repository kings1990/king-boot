package com.kingboot.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class KingBootDubboServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(KingBootDubboServerApplication.class, args);
    }
    
}

