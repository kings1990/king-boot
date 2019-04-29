package com.kingboot.basic.config.shiro.service;

import java.util.Map;

public interface SessionService {
	Map<String, Object> getExtraAttributes(String account);
	
	void saveAccountToSessionIdMapping(String account, String sid);
}
