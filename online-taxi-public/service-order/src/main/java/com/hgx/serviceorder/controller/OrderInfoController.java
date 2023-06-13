package com.hgx.serviceorder.controller;


import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.OrdersRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  创建订单
 * </p>
 *
 * @author huogaoxu
 * @since 2023-06-12
 */
@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderInfoController {

    @PostMapping("/add")
    public ResponseResult addOrders(@RequestBody OrdersRequest ordersRequest){
        System.out.println(ordersRequest.toString());
        log.info("订单信息：", ordersRequest.toString());
        return null;
    }

}
