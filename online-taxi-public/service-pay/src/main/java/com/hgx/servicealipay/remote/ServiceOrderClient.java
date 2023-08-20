package com.hgx.servicealipay.remote;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.OrdersRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-order")
public interface ServiceOrderClient {

    @RequestMapping(method = RequestMethod.POST, value = "/order/pay")
    public ResponseResult pay(@RequestBody OrdersRequest orderRequest);
}