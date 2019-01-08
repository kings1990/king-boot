package com.kingboot.basic.model;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties (prefix = "local")
public class MyConfig {
    private String host;
    
    private List<String> domain = new ArrayList<String>();
    
    public String getHost() {
        return host;
    }
    
    public void setHost(String host) {
        this.host = host;
    }
    
    public List<String> getDomain() {
        return domain;
    }
    
    public void setDomain(List<String> domain) {
        this.domain = domain;
    }
    
    @Override
    public String toString() {
        StringBuilder domains = new StringBuilder();
        this.domain.stream().forEach(q->domains.append(q).append(","));
        domains.deleteCharAt(domains.length()-1);
        return "host:"+this.host+"\tdomain:["+domains.toString()+"]";
    }
}
