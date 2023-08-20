package com.hgx.apidriver.controller;

import com.hgx.apidriver.service.ApiDriverOrderInfoService;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.OrdersRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    ApiDriverOrderInfoService apiDriverOrderInfoService;

    /**
     * 去接乘客
     * @param orderRequest
     * @return
     */
    @PostMapping("/to-pick-up-passenger")
    public ResponseResult changeStatus(@RequestBody OrdersRequest orderRequest){

        return apiDriverOrderInfoService.toPickUpPassenger(orderRequest);
    }

    /**
     * 到达乘客上车点
     * @param orderRequest
     * @return
     */
    @PostMapping("/arrived-departure")
    public ResponseResult arrivedDeparture(@RequestBody OrdersRequest orderRequest){
        return apiDriverOrderInfoService.arrivedDeparture(orderRequest);
    }

    /**
     * 司机接到乘客
     * @param orderRequest
     * @return
     */
    @PostMapping("/pick-up-passenger")
    public ResponseResult pickUpPassenger(@RequestBody OrdersRequest orderRequest){
        return apiDriverOrderInfoService.pickUpPassenger(orderRequest);
    }

    /**
     * 乘客到达目的地，行程终止
     * @param orderRequest
     * @return
     */
    @PostMapping("/passenger-getoff")
    public ResponseResult passengerGetoff(@RequestBody OrdersRequest orderRequest){
        return apiDriverOrderInfoService.passengerGetoff(orderRequest);
    }

    @PostMapping("/cancel")
    public ResponseResult cancel(@RequestParam Long orderId){
        return apiDriverOrderInfoService.cancel(orderId);
    }

}