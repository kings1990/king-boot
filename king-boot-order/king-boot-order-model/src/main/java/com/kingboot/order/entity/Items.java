package com.kingboot.order.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Items implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品定价
     */
    private BigDecimal price;

    /**
     * 商品图片
     */
    @Column (name = "pic_url")
    private String picUrl;

    /**
     * 创建订单时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 商品描述
     */
    private String remark;

    private static final long serialVersionUID = 1L;
}