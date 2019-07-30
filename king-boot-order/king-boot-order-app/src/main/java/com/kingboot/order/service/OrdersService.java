package com.kingboot.order.service;

import com.kingboot.common.config.mybatis.mapper.BaseCRUDService;
import com.kingboot.order.entity.Orders;

/**
 * <p class="detail">
 * 功能:
 * </p>
 * @author Kings
 * @ClassName OrdersService
 * @Version V1.0.
 * @date 2019.07.30 11:04:30
 */
public interface OrdersService extends BaseCRUDService<Orders> {
	
	/**
	 * <p class="detail">
	 * 功能:保存订单测试
	 * </p>
	 * @param userId :用户id
	 * @param note   :备注
	 *
	 * @return integer
	 * @author Kings
	 * @date 2019.07.30 11:04:31
	 */
	Integer saveOrder(Integer userId,String note);
}
