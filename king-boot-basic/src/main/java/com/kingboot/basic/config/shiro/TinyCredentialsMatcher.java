package com.kingboot.basic.config.shiro;

import io.buji.pac4j.token.Pac4jToken;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

//@Component
public class TinyCredentialsMatcher extends SimpleCredentialsMatcher {
	
	@Override
	public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo info) {
		// info来自数据库，token来自用户输入
		Pac4jToken token = (Pac4jToken) authenticationToken;
		
		Object realPassword = token.getCredentials();
		Object tokenPassword = info.getCredentials();
		if (! super.equals(realPassword, tokenPassword)) {
			throw new IncorrectCredentialsException("用户不存在或密码错误");
		}
		return true;
	}
	
}