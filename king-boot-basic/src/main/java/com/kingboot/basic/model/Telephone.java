package com.kingboot.basic.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Telephone {
	
	private String areaCode;
	
	private String phoneNum;
	
	public Telephone() {
	}
	
	public Telephone(String areaCode, String phoneNum) {
		this.areaCode = areaCode;
		this.phoneNum = phoneNum;
	}
}
