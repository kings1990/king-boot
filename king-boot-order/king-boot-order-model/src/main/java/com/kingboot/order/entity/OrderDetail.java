package com.kingboot.order.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p class="detail">
 * 功能:订单详情
 * </p>
 * @author Kings
 * @ClassName OrderDetail
 * @Version V1.0.
 * @date 2019.07.30 11:07:04
 */
@Data
@Table (name = "order_detail")
public class OrderDetail implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单id
     */
    @Column (name = "orders_id")
    private Integer ordersId;

    /**
     * 商品id
     */
    @Column(name = "items_id")
    private Integer itemsId;

    /**
     * 商品购买数量
     */
    @Column(name = "items_num")
    private Integer itemsNum;

    private static final long serialVersionUID = 1L;
}