package com.kingboot.order.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * <p class="detail">
 * 功能:订单数据实体
 * </p>
 * @author Kings
 * @ClassName Orders
 * @Version V1.0.
 * @date 2019.07.30 11:07:04
 */
@Data
public class Orders implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 下单用户id
     */
    @Column (name = "user_id")
    private Integer userId;

    /**
     * 订单号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 创建订单时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 备注
     */
    private String note;

    private static final long serialVersionUID = 1L;
}