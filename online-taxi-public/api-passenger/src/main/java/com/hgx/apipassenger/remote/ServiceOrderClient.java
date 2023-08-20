package com.hgx.apipassenger.remote;

import com.hgx.internalcomm.dto.OrderInfo;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.OrdersRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.GET,value = "/test-real-time-order/{orderId}")
    public String dispatchRealTimeOrder(@PathVariable("orderId") long orderId);

    @RequestMapping(method = RequestMethod.POST, value = "/order/cancel")
    public ResponseResult cancel(@RequestParam Long orderId , @RequestParam String identity);

    @RequestMapping(method = RequestMethod.GET, value = "/order/detail")
    public ResponseResult<OrderInfo> detail(@RequestParam Long orderId);

    @RequestMapping(method = RequestMethod.GET, value = "/order/current")
    public ResponseResult current(@RequestParam String phone ,@RequestParam String identity);
}
