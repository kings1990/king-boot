package com.kingboot.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication (scanBasePackages = {"com.kingboot.user.client", "com.kingboot.order"})
@EnableFeignClients ("com.kingboot.user.client")
@EnableEurekaClient
public class KingBootOrderAppApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(KingBootOrderAppApplication.class, args);
	}
	
	// @Bean
	// public IRule ribbonRule() {
	//     //这里配置策略，和配置文件对应
	//     return new WeightedResponseTimeRule();
	// }
}
