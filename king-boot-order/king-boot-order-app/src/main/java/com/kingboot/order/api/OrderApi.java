package com.kingboot.order.api;


import com.kingboot.common.config.norepeat.NoRepeatSubmit;
import com.kingboot.common.model.RestResponse;
import com.kingboot.order.entity.Order;
import com.kingboot.order.entity.Orders;
import com.kingboot.order.service.OrderService;
import com.kingboot.order.service.OrdersService;
import com.kingboot.user.User;
import com.kingboot.user.client.UserClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p class="detail">
 * 功能:订单测试api
 * </p>
 * @author Kings
 * @ClassName OrderApi
 * @Version V1.0.
 * @date 2019.07.30 11:09:47
 */
@SuppressWarnings ( {"ALL"})
@RestController
@RequestMapping ("/api/order")
@Api (tags = "订单API")
@Slf4j
public class OrderApi {
	
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private UserClient userClient;
	
	@GetMapping (value = "/detail/{id}", name = "根据id查询")
	@ResponseBody
	@ApiOperation (value = "根据id查询用户", notes = "根据id查询用户【Kings】")
	public RestResponse<Order> findById(@PathVariable @ApiParam Integer id) {
		RestResponse<User> u = userClient.findById(1);
		System.out.println(u);
		return new RestResponse<>(orderService.findById(id));
	}
	
	@PostMapping (value = "/test", name = "重复提交测试")
	@ResponseBody
	@NoRepeatSubmit
	@ApiOperation (value = "重复提交测试", notes = "重复提交测试【Kings】")
	public RestResponse<Integer> noRepeatTest() {
		return new RestResponse<>(1);
	}
	
	@PostMapping (value = "/{id}", name = "根据id查询")
	@ResponseBody
	@ApiOperation (value = "根据id查询订单", notes = "根据id查询订单【Kings】")
	public RestResponse<Orders> findByDetailId(@PathVariable @ApiParam Integer id) {
		return new RestResponse<>(ordersService.selectByPrimaryKey(id));
	}
	
	
	@PostMapping("")
	@ApiOperation (value = "保存订单[@Kings]")
	public RestResponse<Integer> saveOrder() {
	    return new RestResponse<>(ordersService.saveOrder(1,"测试"));
	}
	
	
}

