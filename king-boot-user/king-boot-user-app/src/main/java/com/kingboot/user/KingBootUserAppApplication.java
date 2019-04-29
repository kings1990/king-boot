package com.kingboot.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class KingBootUserAppApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(KingBootUserAppApplication.class, args);
	}
	
	// @Bean
	// public IRule ribbonRule() {
	//     //这里配置策略，和配置文件对应
	//     return new WeightedResponseTimeRule();
	// }
}
