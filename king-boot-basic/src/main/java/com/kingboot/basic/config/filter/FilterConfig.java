package com.kingboot.basic.config.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;

@Configuration
public class FilterConfig {
	@Autowired
	private ThymeleafFilter thymeleafFilter;
	
	@Autowired
	private ScriptFilter scriptFilter;
	
	@Bean
	public FilterRegistrationBean setScriptFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(scriptFilter);
		filterRegistrationBean.addUrlPatterns("/*");
		//order越小优先级越高
		filterRegistrationBean.setOrder(1);
		return filterRegistrationBean;
	}
	
	@Bean
	public FilterRegistrationBean setThymeleafFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(thymeleafFilter);
		filterRegistrationBean.addUrlPatterns("/thymeleaf/*");
		filterRegistrationBean.setOrder(2);
		return filterRegistrationBean;
	}
	
	@Bean
	public FilterRegistrationBean eurekaFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new CustomUrlRewriteFilter());
		registrationBean.addUrlPatterns("/eureka/*", "/hystrix/*");
		registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
		return registrationBean;
	}
	
}
