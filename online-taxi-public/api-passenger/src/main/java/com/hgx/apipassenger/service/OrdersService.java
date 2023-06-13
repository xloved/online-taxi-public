package com.hgx.apipassenger.service;

import com.hgx.apipassenger.remote.ServiceOrderClient;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.OrdersRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description com.hgx.apipassenger.service
 * @Author huogaoxu
 * @Date 2023-06-12 9:14
 * @Version 1.0
 **/
@Service
public class OrdersService {

    @Resource
    private ServiceOrderClient serviceOrderClient;
    public ResponseResult addOrders(OrdersRequest ordersRequest) {

        serviceOrderClient.addOrders(ordersRequest);
        return ResponseResult.success();
    }
}
