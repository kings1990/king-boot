package com.kingboot.basic.config.shiro;

import com.kingboot.basic.config.shiro.service.SessionService;
import io.buji.pac4j.realm.Pac4jRealm;
import io.buji.pac4j.subject.Pac4jPrincipal;
import io.buji.pac4j.token.Pac4jToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.crazycake.shiro.RedisManager;
import org.pac4j.core.profile.CommonProfile;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 认证与授权
 * @author gongtao
 * @version 2018-03-30 13:55
 **/
public class CasRealm extends Pac4jRealm {
	
	
	@Autowired
	private RedisManager redisManager;
	
	@Autowired
	private SessionService sessionService;
	
	/**
	 * 认证
	 * @param authenticationToken
	 *
	 * @return
	 * @throws
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		final Pac4jToken pac4jToken = (Pac4jToken) authenticationToken;
		final List<CommonProfile> commonProfileList = pac4jToken.getProfiles();
		final CommonProfile commonProfile = commonProfileList.get(0);
		
		System.out.println("单点登录返回的信息" + commonProfile.toString());
		//todo
		final Pac4jPrincipal principal = new Pac4jPrincipal(commonProfileList, getPrincipalNameAttribute());
		final PrincipalCollection principalCollection = new SimplePrincipalCollection(principal, getName());
		
		//todo set info to redis
		Map<String, Object> attributes = new HashMap<>(16);
		String account = principal.getProfile().getId();
		attributes.put("account", account);
		Map<String, Object> extraAttributes = sessionService.getExtraAttributes(account);
		attributes.putAll(extraAttributes);
		String sid = (String) SecurityUtils.getSubject().getSession().getId();
		sessionService.saveAccountToSessionIdMapping(account, sid);
		
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(attributes, commonProfileList.hashCode(), getName());
		return simpleAuthenticationInfo;
	}
	
	
	/**
	 * 授权/验权（todo 后续有权限在此增加）
	 * @param principals
	 *
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
		authInfo.addStringPermission("user");
		return authInfo;
	}
	
	
}