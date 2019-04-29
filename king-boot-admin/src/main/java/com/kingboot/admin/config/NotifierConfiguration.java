package com.kingboot.admin.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.notify.CompositeNotifier;
import de.codecentric.boot.admin.server.notify.Notifier;
import de.codecentric.boot.admin.server.notify.RemindingNotifier;
import de.codecentric.boot.admin.server.notify.filter.FilteringNotifier;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

@Configuration
public class NotifierConfiguration {
	
	
	@Configuration
	public static class NotifierConfig {
		private final InstanceRepository repository;
		private final ObjectProvider<List<Notifier>> otherNotifiers;
		
		public NotifierConfig(InstanceRepository repository, ObjectProvider<List<Notifier>> otherNotifiers) {
			this.repository = repository;
			this.otherNotifiers = otherNotifiers;
		}
		
		@Bean
		public FilteringNotifier filteringNotifier() {
			CompositeNotifier delegate = new CompositeNotifier(otherNotifiers.getIfAvailable(Collections :: emptyList));
			return new FilteringNotifier(delegate, repository);
		}
		
		@Primary
		@Bean (initMethod = "start", destroyMethod = "stop")
		public RemindingNotifier remindingNotifier() {
			RemindingNotifier notifier = new RemindingNotifier(filteringNotifier(), repository);
			notifier.setReminderPeriod(Duration.ofMinutes(10));
			notifier.setCheckReminderInverval(Duration.ofSeconds(60));
			return notifier;
		}
	}
	
	@Configuration
	public static class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
		private final String adminContextPath;
		
		public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
			this.adminContextPath = adminServerProperties.getContextPath();
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
			successHandler.setTargetUrlParameter("redirectTo");
			successHandler.setDefaultTargetUrl(adminContextPath + "/");
			
			http.authorizeRequests().antMatchers(adminContextPath + "/assets/**").permitAll().antMatchers(adminContextPath + "/login").permitAll().anyRequest().authenticated().and().formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and().logout().logoutUrl(adminContextPath + "/logout").and().httpBasic().and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).ignoringAntMatchers(adminContextPath + "/instances/**", adminContextPath + "/actuator/**");
			// @formatter:on
		}
	}
}