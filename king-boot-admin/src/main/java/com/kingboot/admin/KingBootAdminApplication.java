package com.kingboot.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class KingBootAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(KingBootAdminApplication.class, args);
	}

}

