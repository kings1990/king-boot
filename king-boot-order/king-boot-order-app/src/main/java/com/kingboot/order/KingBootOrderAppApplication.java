package com.kingboot.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication (scanBasePackages = {"com.kingboot.user.client", "com.kingboot.order"})
@EnableFeignClients ("com.kingboot.user.client")
@EnableEurekaClient
@tk.mybatis.spring.annotation.MapperScan (basePackages = "com.kingboot.order.mapper")
@EnableTransactionManagement
public class KingBootOrderAppApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(KingBootOrderAppApplication.class, args);
	}
	
	
}
