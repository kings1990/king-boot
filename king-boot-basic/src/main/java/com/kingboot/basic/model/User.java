package com.kingboot.basic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int age;
    @JsonIgnore
    private String password;
    @JsonProperty ("account")
    @JsonInclude (JsonInclude.Include.NON_NULL)//空字段不返回
    private String phone;
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh",timezone = "GTM+8")
    private Date createTime;
    
    
}