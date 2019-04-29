package com.kingboot.basic.config.shiro.service.impl;

import com.kingboot.basic.config.redis.KingRedisManager;
import com.kingboot.basic.config.shiro.service.SessionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class SessionServiceImpl implements SessionService {
	private static final String ACCOUNT = "Account";
	@Value ("${shiro.timeout.seconds}")
	private Integer shiroTimeoutSeconds;
	@Autowired
	private KingRedisManager kingRedisManager;
	
	@Override
	public Map<String, Object> getExtraAttributes(String account) {
		Map<String, Object> result = new HashMap<>();
		//todo set some account info
		return result;
	}
	
	@Override
	public void saveAccountToSessionIdMapping(String account, String sid) {
		if (StringUtils.isBlank(account)) {
			return;
		}
		kingRedisManager.hset(ACCOUNT, account, sid);
		kingRedisManager.expire(ACCOUNT, shiroTimeoutSeconds);
	}
}

