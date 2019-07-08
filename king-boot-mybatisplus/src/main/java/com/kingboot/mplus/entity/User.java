package com.kingboot.mplus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户
 *
 * @author Kings
 * @date 2019-07-08 14:24:50
 */
@Data
@TableName("user")
public class User implements Serializable {
private static final long serialVersionUID = 1L;

  /**
   *
   */
  @TableId
  private Integer id;
  /**
   * 用户名
   */
  private String name;
  /**
   *
   */
  private LocalDateTime createTime;
  /**
   * 版本
   */
  @Version
  private Integer version;

}
