package com.kingboot.basic;

import com.kingboot.basic.config.listener.MyApplicationStartingEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KingBootBasicApplication {
    
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(KingBootBasicApplication.class);
        app.addListeners(new MyApplicationStartingEventListener());
        app.run(args);
    }
}
