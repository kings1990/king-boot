package com.kingboot.user.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserNicknameDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nickname;
	
	private Integer id;
}
