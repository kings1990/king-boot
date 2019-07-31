package com.kingboot.common.config.feign;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * <p class="detail">
 * 功能:feign序列化配置
 * </p>
 * @author Kings
 * @ClassName CustomFeignConfig
 * @Version V1.0.
 * @date 2019.07.31 14:09:46
 */
@Configuration
public class CustomFeignConfig {
	/**
	 * <p class="detail">
	 * 功能:反序列化
	 * </p>
	 * @return decoder
	 * @author Kings
	 * @date 2019.07.31 14:09:46
	 */
	@Bean
    public Decoder feignDecoder() {
        HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(customObjectMapper());
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
        return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
    }
	
	/**
	 * <p class="detail">
	 * 功能:序列化
	 * </p>
	 * @return encoder
	 * @author Kings
	 * @date 2019.07.31 14:09:46
	 */
	@Bean
    public Encoder feignEncoder(){
        HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(customObjectMapper());
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
        return new SpringEncoder(objectFactory);
    }
	
	/**
	 * <p class="detail">
	 * 功能:自定义mapper
	 * </p>
	 * @return object mapper
	 * @author Kings
	 * @date 2019.07.31 14:09:46
	 */
	private ObjectMapper customObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        //Customize as much as you want 允许传null
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        return objectMapper;
    }
}