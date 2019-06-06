package com.kingboot.order.service.impl;

import com.kingboot.common.config.mybatis.mapper.BaseCRUDServiceImpl;
import com.kingboot.order.entity.Items;
import com.kingboot.order.service.ItemsService;
import org.springframework.stereotype.Service;


@Service
public class ItemsServiceImpl extends BaseCRUDServiceImpl<Items> implements ItemsService {
}

