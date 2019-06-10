package com.kingboot.common.config.norepeat;

import com.kingboot.common.config.redis.KingRedisManager;
import com.kingboot.common.model.RestResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
public class NoRepeatSubmitAop {
	
	private Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private KingRedisManager kingRedisManager;
	
	@Around("@annotation(com.kingboot.common.config.norepeat.NoRepeatSubmit) && ( (@annotation(org.springframework.web.bind.annotation.DeleteMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping) ||@annotation(org.springframework.web.bind.annotation.PutMapping)))")
	public Object arround(ProceedingJoinPoint pjp) {
		try {
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();
			HttpServletRequest request = attributes.getRequest();
			String key = sessionId + "-" + request.getServletPath();
			if (kingRedisManager.get(key) == null) {// 如果缓存中有这个url视为重复提交
				Object o = pjp.proceed();
				kingRedisManager.setex(key,"0",2);
				return o;
			} else {
				logger.error("重复提交");
				return new RestResponse<>(HttpStatus.BAD_REQUEST,"验证重复提交时出现未知异常",null);
			}
		} catch (Throwable e) {
			logger.error("redis异常!");
			return new RestResponse<>(HttpStatus.BAD_REQUEST,"redis异常",null);
		}
		
	}
	
}