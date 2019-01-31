/*
 * ahhahah
 */

package com.kingboot.basic.config;

import com.kingboot.basic.config.converter.TelephoneConverter;
import com.kingboot.basic.config.interceptor.RequestTimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    
    @Autowired
    private TelephoneConverter telephoneConverter;
    
    @Override
    public void addFormatters(FormatterRegistry registry) {
        //添加自定义converter
        //registry.addConverter(telephoneConverter);
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestTimeInterceptor()).addPathPatterns("/**").order(1);
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //registry.addMapping("/api/**");由nginx代替
    }
    
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(responseBodyConverter());
        converters.add(jacksonConverter());
    }
    
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }
    
    @Bean
    public MappingJackson2HttpMessageConverter jacksonConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        return converter;
    }
    
    //@Configuration
    // public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
    //     @Override
    //     protected void configure(HttpSecurity http) throws Exception {
    //         http.authorizeRequests().anyRequest().permitAll()
    //                 .and().csrf().disable();
    //     }
    // }
}

