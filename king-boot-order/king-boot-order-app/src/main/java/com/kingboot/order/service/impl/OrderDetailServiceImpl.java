package com.kingboot.order.service.impl;

import com.kingboot.common.config.mybatis.mapper.BaseCrudServiceImpl;
import com.kingboot.order.entity.Orders;
import com.kingboot.order.service.OrdersService;
import com.kingboot.user.client.UserClient;
import com.kingboot.user.dto.UserNicknameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class OrderDetailServiceImpl extends BaseCrudServiceImpl<Orders> implements  OrdersService {

	@Autowired
	private UserClient userClient;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer saveOrder(Integer userId, String note) {
		Orders orders4i = new Orders();
		orders4i.setUserId(userId);
		orders4i.setNote(note);
		mapper.insertSelective(orders4i);

		UserNicknameDto dto = new UserNicknameDto();
		dto.setId(userId);
		dto.setNickname("King's");
		userClient.updateNickname(dto);

		//制造异常使得feign接口回滚
		if(userId == 1){
			throw new IllegalStateException("by exFlag");
		}
		return 1;
	}
}

