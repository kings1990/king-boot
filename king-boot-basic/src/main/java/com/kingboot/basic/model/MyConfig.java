package com.kingboot.basic.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties (prefix = "local")
@Getter
@Setter
public class MyConfig {
    private String host;
    
    private List<String> domain = new ArrayList<String>();
    
    
    @Override
    public String toString() {
        StringBuilder domains = new StringBuilder();
        this.domain.stream().forEach(q -> domains.append(q).append(","));
        domains.deleteCharAt(domains.length() - 1);
        return "host:" + this.host + "\tdomain:[" + domains.toString() + "]";
    }
}
