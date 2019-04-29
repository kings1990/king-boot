package com.kingboot.basic.config.common;


import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserEnv {
	
	public String userName() {
		Map<String, Object> pac4jPrincipal = (Map) SecurityUtils.getSubject().getPrincipal();
		if (pac4jPrincipal != null) {
			return pac4jPrincipal.get("account").toString();
		}
		return null;
	}
	
}
