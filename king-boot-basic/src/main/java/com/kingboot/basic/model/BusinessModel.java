package com.kingboot.basic.model;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BusinessModel {
	
	@NotBlank
	@Email
	private String email;
	
	@NotNull
	private Integer type;
	
}
