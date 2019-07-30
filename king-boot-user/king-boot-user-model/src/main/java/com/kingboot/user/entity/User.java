package com.kingboot.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * <p class="detail">
 * 功能:用户
 * </p>
 * @author Kings
 * @ClassName UserNicknameDto
 * @Version V1.0.
 * @date 2019.07.30 10:54:13
 */
@Data
@Table(name = "user")
public class User {
    /**
     * 主键
     */
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 年龄
     */
    private Byte age;

    /**
     * 性别【1-男 2-女 3-未知】
     */
    private Byte sex;

    /**
     * 盐
     */
    private String salt;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 登录名
     */
    @Column (name = "login_name")
    private String loginName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;
	
	@JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
	@JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
	@JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    
}