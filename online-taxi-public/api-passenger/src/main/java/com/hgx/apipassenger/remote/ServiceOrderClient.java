package com.hgx.apipassenger.remote;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.OrdersRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description com.hgx.apipassenger.remote
 * @Author huogaoxu
 * @Date 2023-06-12 11:29
 * @Version 1.0
 **/
@FeignClient("service-order")
public interface ServiceOrderClient {

    @RequestMapping(method = RequestMethod.POST,value = "/orders/add")
    public ResponseResult addOrders(@RequestBody OrdersRequest ordersRequest);
}
