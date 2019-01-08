package com.kingboot.basic.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Getter@Setter
@ConfigurationProperties(prefix = "my.fruit")
@Component
public class MyFruilt {
    private List<String> like;
    
    private Map<String,String> pineapple;
    
    
}
