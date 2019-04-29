package com.kingboot.basic.model;

import lombok.Data;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@ConfigurationProperties (prefix = "person.emperor")
@Component
@Setter
@Data
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	private String firstName;
	
	private String lastName;
	
	private String fullName;
	
	
}
