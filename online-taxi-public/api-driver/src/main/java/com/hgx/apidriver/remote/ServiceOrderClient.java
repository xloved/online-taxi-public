package com.hgx.apidriver.remote;

import com.hgx.internalcomm.dto.OrderInfo;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.OrdersRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.POST, value = "/order/cancel")
    public ResponseResult cancel(@RequestParam Long orderId, @RequestParam String identity);

    @PostMapping("/order/push-pay-info")
    public ResponseResult pushPayInfo(@RequestBody OrdersRequest orderRequest);

    @RequestMapping(method = RequestMethod.GET, value = "/order/detail")
    public ResponseResult<OrderInfo> detail(@RequestParam Long orderId);

    @RequestMapping(method = RequestMethod.GET, value = "/order/current")
    public ResponseResult current(@RequestParam String phone ,@RequestParam String identity);
}