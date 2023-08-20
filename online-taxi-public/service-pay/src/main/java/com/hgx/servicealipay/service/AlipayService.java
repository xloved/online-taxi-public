package com.hgx.servicealipay.service;

import com.hgx.internalcomm.request.OrdersRequest;
import com.hgx.servicealipay.remote.ServiceOrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlipayService {

    @Autowired
    ServiceOrderClient serviceOrderClient;

    public void pay(Long orderId){
        OrdersRequest orderRequest = new OrdersRequest();
        orderRequest.setOrderId(orderId);
        serviceOrderClient.pay(orderRequest);

    }
}