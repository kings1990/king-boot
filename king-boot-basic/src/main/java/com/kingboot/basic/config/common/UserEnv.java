package com.kingboot.basic.config.common;


import io.buji.pac4j.subject.Pac4jPrincipal;
import org.apache.shiro.SecurityUtils;
import org.pac4j.core.profile.CommonProfile;
import org.springframework.stereotype.Component;

@Component
public class UserEnv {
    public CommonProfile userProfile() {
        Pac4jPrincipal pac4jPrincipal = (Pac4jPrincipal) SecurityUtils.getSubject().getPrincipal();
        return pac4jPrincipal.getProfile();
    }
    
    public String userName() {
        Pac4jPrincipal pac4jPrincipal = (Pac4jPrincipal) SecurityUtils.getSubject().getPrincipal();
        if (pac4jPrincipal != null) {
            return pac4jPrincipal.getProfile().getId();
        }
        return null;
    }
    
}
