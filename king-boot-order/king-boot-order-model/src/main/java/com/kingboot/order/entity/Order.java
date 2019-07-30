package com.kingboot.order.entity;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p class="detail">
 * 功能:订单
 * </p>
 * @author Kings
 * @ClassName Order
 * @Version V1.0.
 * @date 2019.07.30 11:07:04
 */
@Getter
@Setter
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	
	private String orderId;
	
	
}
