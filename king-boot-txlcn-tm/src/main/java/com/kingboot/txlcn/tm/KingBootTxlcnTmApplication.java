package com.kingboot.txlcn.tm;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableTransactionManagerServer
public class KingBootTxlcnTmApplication {

	public static void main(String[] args) {
		SpringApplication.run(KingBootTxlcnTmApplication.class, args);
	}

}
