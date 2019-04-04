package com.kingboot.order.api;


import com.kingboot.common.model.RestResponse;
import com.kingboot.order.entity.Order;
import com.kingboot.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping ("/order")
@Api (description = "订单API")
@Slf4j
public class OrderApi {
    
    @Autowired
    private OrderService orderService;
    
    @GetMapping (value = "/detail/{id}",name = "根据id查询")
    @ApiOperation (value = "根据id查询用户",notes = "根据id查询用户【Kings】")
    public RestResponse<Order> findById (@PathVariable @ApiParam Integer id) throws Exception {
        return new RestResponse<>(orderService.findById(id));
    }
}

