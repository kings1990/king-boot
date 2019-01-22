package com.kingboot.basic;

import com.kingboot.basic.config.listener.MyApplicationStartingEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@tk.mybatis.spring.annotation.MapperScan(basePackages = "com.kingboot.basic.dao.boot.mapper",sqlSessionFactoryRef = "sqlSessionFactory-boot")
public class KingBootBasicApplication {
    
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(KingBootBasicApplication.class);
        app.addListeners(new MyApplicationStartingEventListener());
        app.run(args);
    
        
    }
}
