package com.kingboot.order.config;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p class="detail">
 * 功能:swagger2配置
 * </p>
 * @author Kings
 * @ClassName Swagger2
 * @Version V1.0.
 * @date 2019.07.30 11:10:27
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
	
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.kingboot.order.api")).paths(path()).build().securityContexts(Lists.newArrayList(securityContext())).securitySchemes(Lists.newArrayList(apiKey()));
		//.host("king.ws.com");
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Kings Order Api Center").description("api文档中心")
			//.termsOfServiceUrl("http://blog.didispace.com/")
			.contact(new Contact("Kings", "https://gitee.com/kings", "963987632@qq.com")).version("1.0").build();
	}
	
	
	private Predicate<String> path() {
		return PathSelectors.regex("/api/.*");
	}
	
	private SecurityContext securityContext() {
		return SecurityContext.builder().forPaths(path()).build();
	}
	
	
	private ApiKey apiKey() {
		return new ApiKey("Token", "Token", "header");
	}
}