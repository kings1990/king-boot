package com.kingboot.order.service.impl;

import com.kingboot.common.config.mybatis.mapper.BaseCRUDServiceImpl;
import com.kingboot.order.entity.OrderDetail;
import com.kingboot.order.service.OrderDetailService;
import org.springframework.stereotype.Service;


@Service
public class OrdersServiceImpl extends BaseCRUDServiceImpl<OrderDetail> implements OrderDetailService {
}

