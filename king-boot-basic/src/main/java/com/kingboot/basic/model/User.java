package com.kingboot.basic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
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
    
    @ApiModelProperty ("用户id")
    private Integer accountId;
    
    @ApiModelProperty ("年龄")
    private int age;
    @JsonIgnore
    private String password;
    
    @JsonProperty ("account")
    @JsonInclude (JsonInclude.Include.NON_NULL)//空字段不返回
    @ApiModelProperty ("账号")
    private String phone;
    
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GTM+8")
    @ApiModelProperty ("创建时间")
    private Date createTime;
    
    
}