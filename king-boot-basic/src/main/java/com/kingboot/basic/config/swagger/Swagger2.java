package com.kingboot.basic.config.swagger;

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

@Configuration
@EnableSwagger2
public class Swagger2 {
	
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.kingboot.basic.api")).paths(path()).build().securityContexts(Lists.newArrayList(securityContext())).securitySchemes(Lists.newArrayList(apiKey())).host("king.ws.com");
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Kings Rest Api Center").description("api文档中心")
			//.termsOfServiceUrl("http://blog.didispace.com/")
			.contact(new Contact("Kings", "https://gitee.com/kings", "963987632@qq.com")).version("1.0").build();
	}
	
	@Bean
	public Docket createThirdRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.kingboot.basic.api")).paths(pathThird()).build().groupName("third").securityContexts(Lists.newArrayList(securityContextThird())).securitySchemes(Lists.newArrayList(apiKey())).host("king.ws.com");
	}
	
	private Predicate<String> path() {
		return PathSelectors.regex("/api/.*");
	}
	
	private Predicate<String> pathThird() {
		return PathSelectors.regex("/thirdapi/.*");
	}
	
	private SecurityContext securityContext() {
		return SecurityContext.builder().forPaths(path()).build();
	}
	
	private SecurityContext securityContextThird() {
		return SecurityContext.builder().forPaths(pathThird()).build();
	}
	
	private ApiKey apiKey() {
		return new ApiKey("Token", "Token", "header");
	}
}