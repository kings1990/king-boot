package com.kingboot.order.service;


import com.kingboot.order.entity.Order;

public interface OrderService {
	
	Order findById(Integer id);
}
