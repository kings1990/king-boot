package com.kingboot.order;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication (scanBasePackages = {"com.kingboot.user.client", "com.kingboot.order"})
@EnableFeignClients ("com.kingboot.user.client")
@EnableEurekaClient
@tk.mybatis.spring.annotation.MapperScan (basePackages = "com.kingboot.order.mapper")
@EnableDistributedTransaction
public class KingBootOrderAppApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(KingBootOrderAppApplication.class, args);
	}
	
	
	
}
