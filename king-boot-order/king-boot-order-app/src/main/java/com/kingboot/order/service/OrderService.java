package com.kingboot.order.service;


import com.kingboot.order.entity.Order;

/**
 * <p class="detail">
 * 功能:订单接口
 * </p>
 * @author Kings
 * @ClassName OrderService
 * @Version V1.0.
 * @date 2019.07.30 11:04:11
 */
public interface OrderService {
	
	/**
	 * <p class="detail">
	 * 功能:根据id查询订单
	 * </p>
	 * @param id :id
	 *
	 * @return order
	 * @author Kings
	 * @date 2019.07.30 11:04:11
	 */
	Order findById(Integer id);
}
