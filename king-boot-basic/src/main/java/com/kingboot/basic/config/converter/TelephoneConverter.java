package com.kingboot.basic.config.converter;

import com.kingboot.basic.model.Telephone;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component//必须要加入次注解,不要无法和properties文件中的属性绑定
@ConfigurationPropertiesBinding//必须加入 不要无法和properties文件中的属性绑定
public class TelephoneConverter implements Converter<String, Telephone> {
	@Override
	public Telephone convert(String source) {
		if (source.matches("\\d{3,4}-\\d{7,8}")) {
			String[] telephoneArray = source.split("-");
			return new Telephone(telephoneArray[0], telephoneArray[1]);
		}
		return null;
	}
}