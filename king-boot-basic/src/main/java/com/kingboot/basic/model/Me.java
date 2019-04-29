package com.kingboot.basic.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@ConfigurationProperties (prefix = "my")
@Component
@Getter
@Setter
@Validated
public class Me {
	private MyFruilt fruit;
	@Valid
	private MyContact contact;
}
