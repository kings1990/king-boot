package com.kingboot.basic.model;

import lombok.Data;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.io.Serializable;

@ConfigurationProperties (prefix = "my.contact")
@Component
@Setter
@Data
@Validated
public class MyContact implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Telephone tel;
	private String phone;
	@Email
	private String email;
	
}
