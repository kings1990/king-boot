package com.kingboot.basic.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties (prefix = "dev")
@Component
@Getter
@Setter
public class DevConfig {
    
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