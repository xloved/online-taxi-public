package com.hgx.apidriver.remote;

import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.OrdersRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-order")
public interface ServiceOrderClient {

    @RequestMapping(method = RequestMethod.POST, value = "/order/to-pick-up-passenger")
    public ResponseResult toPickUpPassenger(@RequestBody OrdersRequest orderRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/order/arrived-departure")
    public ResponseResult arrivedDeparture(@RequestBody OrdersRequest orderRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/order/pick-up-passenger")
    public ResponseResult pickUpPassenger(@RequestBody OrdersRequest orderRequest);

    @RequestMapping(method = RequestMethod.POST,value ="/order/passenger-getoff")
    public ResponseResult passengerGetoff(@RequestBody OrdersRequest orderRequest);
}