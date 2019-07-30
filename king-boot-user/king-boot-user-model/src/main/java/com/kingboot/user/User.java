package com.kingboot.user;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p class="detail">
 * 功能:用户,不在推荐使用
 * </p>
 * @author Kings
 * @ClassName UserNicknameDto
 * @Version V1.0.
 * @date 2019.07.30 10:54:13
 */
@Getter
@Setter
@Deprecated
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	
	private String name;
	
	
}
