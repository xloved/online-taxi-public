package com.hgx.apipassenger.controller;

import com.hgx.apipassenger.service.OrdersService;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.OrdersRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 乘客下订单功能
 * @Author huogaoxu
 * @Date 2023-06-12 9:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Resource
    private OrdersService ordersService;

    @PostMapping("/add")
    public ResponseResult addOrder(@RequestBody OrdersRequest ordersRequest){

        System.out.println(ordersRequest.toString());
        return  ordersService.addOrders(ordersRequest);

    }
}
