package com.kingboot.order.service.impl;

import com.kingboot.order.entity.Order;
import com.kingboot.order.service.OrderService;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl implements OrderService {
    
    @Override
    public Order findById(Integer id) {
        Order user = new Order();
        user.setId(id);
        user.setOrderId("OD-123");
        return user;
    }
}
