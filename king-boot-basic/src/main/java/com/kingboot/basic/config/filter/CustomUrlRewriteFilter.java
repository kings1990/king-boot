package com.kingboot.basic.config.filter;


import lombok.extern.slf4j.Slf4j;
import org.tuckey.web.filters.urlrewrite.Conf;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;
import org.tuckey.web.filters.urlrewrite.UrlRewriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.InputStream;

@Slf4j
public class CustomUrlRewriteFilter extends UrlRewriteFilter {
	
	private UrlRewriter urlRewriter;
	
	@Override
	protected void loadUrlRewriter(javax.servlet.FilterConfig filterConfig) throws ServletException {
		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("urlrewrite.xml");
			Conf conf = new Conf(filterConfig.getServletContext(), inputStream, "urlrewrite.xml", "");
			urlRewriter = new UrlRewriter(conf);
		} catch (Exception e) {
			log.error("load urlrewrite.xml error");
		}
	}
	
	@Override
	public void destroyUrlRewriter() {
		if (urlRewriter != null) {
			urlRewriter.destroy();
		}
	}
	
	@Override
	public UrlRewriter getUrlRewriter(ServletRequest request, ServletResponse response, FilterChain chain) {
		return urlRewriter;
	}
}