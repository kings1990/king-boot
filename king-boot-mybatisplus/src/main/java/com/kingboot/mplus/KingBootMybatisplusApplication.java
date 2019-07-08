package com.kingboot.mplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan (basePackages={"com.kingboot.mplus.mapper","com.kingboot.mplus.generator.mapper"})
public class KingBootMybatisplusApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(KingBootMybatisplusApplication.class, args);
	}
	
}
