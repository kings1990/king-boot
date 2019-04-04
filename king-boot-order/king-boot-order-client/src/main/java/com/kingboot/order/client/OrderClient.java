package com.kingboot.order.client;

import com.kingboot.common.model.RestResponse;
import com.kingboot.order.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (value = "king-boot-order",fallback = OrderClientImpl.class)
public interface OrderClient {
    
    @GetMapping (value = "/order/detail/{id}",name = "根据id查询")
    RestResponse<Order> findById (@PathVariable Integer id);
}
