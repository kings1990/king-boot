package com.kingboot.basic.config.shiro;


import com.google.common.base.Strings;
import com.kingboot.basic.config.common.Const;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.web.session.mgt.DefaultWebSessionContext;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class KingsSessionManager extends DefaultWebSessionManager {
	//private static final long mobileExpireInMs = 7776000L * 1000;
	@Override
	protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
		String id;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		// 先从header拿
		id = httpServletRequest.getHeader(Const.TOKEN_HEADER);
		if (! Strings.isNullOrEmpty(id)) {
			return id;
		}
		
		// 再从请求参数拿
		id = httpServletRequest.getParameter(Const.TOKEN_PARAM);
		if (! Strings.isNullOrEmpty(id)) {
			return id;
		}
		
		// 最后从cookie拿
		return super.getSessionId(request, response);
	}
	
	@Override
	public Session start(SessionContext context) {
		Session session = super.start(context);
		DefaultWebSessionContext webSessionContext = (DefaultWebSessionContext) context;
		ServletRequest request = webSessionContext.getServletRequest();
		// if (Const.LOGIN_PLATFORM_MOBILE.equals(request.getParameter(Const.LOGIN_PLATFORM))) {
		//     session.setTimeout(mobileExpireInMs);
		// }
		return session;
	}
	
	
}
