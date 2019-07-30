package com.kingboot.order.client;

import com.kingboot.common.model.RestResponse;
import com.kingboot.order.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p class="detail">
 * 功能:订单feign接口
 * </p>
 * @author Kings
 * @ClassName OrderClient
 * @Version V1.0.
 * @date 2019.07.30 11:10:04
 */
@SuppressWarnings ("ALL")
@FeignClient (value = "king-boot-order", fallback = OrderClientImpl.class)
public interface OrderClient {
	
	/**
	 * <p class="detail">
	 * 功能:
	 * </p>
	 * @param id :
	 *
	 * @return rest response
	 * @author Kings
	 * @date 2019.07.30 11:10:04
	 */
	@GetMapping (value = "/api/order/detail/{id}", name = "根据id查询")
	RestResponse<Order> findById(@PathVariable Integer id);
}
