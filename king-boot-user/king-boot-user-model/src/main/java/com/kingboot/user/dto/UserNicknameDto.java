package com.kingboot.user.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p class="detail">
 * 功能:用户Nickname姓名dto
 * </p>
 * @author Kings
 * @ClassName UserNicknameDto
 * @Version V1.0.
 * @date 2019.07.30 10:54:13
 */
@Data
public class UserNicknameDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nickname;
	
	private Integer id;
}
