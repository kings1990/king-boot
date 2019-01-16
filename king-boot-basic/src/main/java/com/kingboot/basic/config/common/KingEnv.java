package com.kingboot.basic.config.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class KingEnv {
    @Autowired
    private Environment environment;
    
    public String key(String key) {
        return environment.getProperty(key);
    }
}
