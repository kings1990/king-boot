package com.kingboot.basic.model;

import java.util.ArrayList;
import java.util.List;

public class AnotherConfig {
    
    private String host;
    
    private List<String> domain = new ArrayList<String>();
    
    public List<String> getDomain() {
        return this.domain;
    }
    
    public void setDomain(List<String> domain) {
        this.domain = domain;
    }
    
    public String getHost() {
        return host;
    }
    
    public void setHost(String host) {
        this.host = host;
    }
    
    @Override
    public String toString() {
        StringBuilder domains = new StringBuilder();
        this.domain.stream().forEach(q->domains.append(q).append(","));
        domains.deleteCharAt(domains.length()-1);
        return "host:"+this.host+"\tdomain:["+domains.toString()+"]";
    }
}