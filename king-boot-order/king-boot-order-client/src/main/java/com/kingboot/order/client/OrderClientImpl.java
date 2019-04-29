package com.kingboot.order.client;


import com.kingboot.common.model.RestResponse;
import com.kingboot.order.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderClientImpl implements OrderClient {
	@Override
	public RestResponse<Order> findById(Integer id) {
		log.error("findById 异常");
		return new RestResponse<>(- 1, "服务异常", null);
	}
}
