package com.kingboot.user;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@tk.mybatis.spring.annotation.MapperScan (basePackages = "com.kingboot.user.mapper")
@EnableTransactionManagement
@EnableDistributedTransaction
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
