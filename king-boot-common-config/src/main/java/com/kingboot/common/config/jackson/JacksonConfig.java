package com.kingboot.common.config.jackson;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;


/**
 * <p class="detail">
 * 功能:JacksonConfig
 * </p>
 * @author Kings
 * @ClassName JacksonConfig
 * @Version V1.0.
 * @date 2019.07.31 16:03:24
 */
@Configuration
@ConditionalOnClass (ObjectMapper.class)
@AutoConfigureBefore (JacksonAutoConfiguration.class)
public class JacksonConfig {
	/**
	 * <p class="detail">
	 * 功能:自定义JacksonObjectMapperBuilder
	 * </p>
	 * @return jackson 2 object mapper builder customizer
	 * @author Kings
	 * @date 2019.07.31 16:03:24
	 */
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer customizer() {
		return builder -> {
			builder.locale(Locale.CHINA);
			builder.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
			builder.simpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
			builder.modules(new KingsJavaTimeModule());
		};
	}
}
