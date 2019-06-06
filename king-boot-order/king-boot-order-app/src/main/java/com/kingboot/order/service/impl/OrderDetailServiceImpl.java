package com.kingboot.order.service.impl;

import com.kingboot.common.config.mybatis.mapper.BaseCRUDServiceImpl;
import com.kingboot.order.entity.Orders;
import com.kingboot.order.service.OrdersService;
import org.springframework.stereotype.Service;


@Service
public class OrderDetailServiceImpl extends BaseCRUDServiceImpl<Orders> implements  OrdersService {
}

