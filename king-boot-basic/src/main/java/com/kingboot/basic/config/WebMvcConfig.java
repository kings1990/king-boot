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
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    
    @Autowired
    private TelephoneConverter telephoneConverter;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
    }
    
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(responseBodyConverter());
        converters.add(new MappingJackson2XmlHttpMessageConverter());//支持返回xml
        converters.add(jacksonConverter());
        
    }
    
    @Override
    public void addFormatters(FormatterRegistry registry) {
        //添加自定义converter
        //registry.addConverter(telephoneConverter);
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestTimeInterceptor()).addPathPatterns("/**").order(1);
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
    
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        //支持.xml结尾的请求
        Map<String, MediaType> mediaTypeMap = new HashMap<>();
        mediaTypeMap.put("xml", MediaType.APPLICATION_XML);
        mediaTypeMap.put("json", MediaType.APPLICATION_JSON);
        mediaTypeMap.put("html", MediaType.TEXT_HTML);
        configurer.favorParameter(false)//是否支持format=json参数
                .favorPathExtension(true).useJaf(false)//是否支持.json .xml等扩展名
                .ignoreAcceptHeader(true)//忽视Accept请求头
                .defaultContentType(MediaType.APPLICATION_JSON)//默认返回的context-type
                .mediaTypes(mediaTypeMap);
    }
    
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**");
    }
}

