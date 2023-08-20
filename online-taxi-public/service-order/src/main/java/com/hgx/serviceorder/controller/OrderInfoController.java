package com.hgx.serviceorder.controller;


import com.hgx.internalcomm.dto.OrderInfo;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.OrdersRequest;
import com.hgx.serviceorder.service.OrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  订单服务
 * </p>
 *
 * @author huogaoxu
 * @since 2023-06-12
 */
@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;

    /**
     * 创建订单
     * @param ordersRequest
     * @return
     */
    @PostMapping("/add")
    public ResponseResult addOrders(@RequestBody OrdersRequest ordersRequest){// HttpServletRequest httpRequest
        // 获取设备请求头 ，使用apifox测试通过，通过header获取deviceCode
//        String header = httpRequest.getHeader(HeaderParamConstant.DEVICE_CODE);
//        ordersRequest.setDeviceCode(header);
//        System.out.println(ordersRequest.toString());
        log.info("订单信息：", ordersRequest.toString());
        return orderInfoService.addOrder(ordersRequest);
    }

    /**
     * 去接乘客
     * @param orderRequest
     * @return
     */
    @PostMapping("/to-pick-up-passenger")
    public ResponseResult changeStatus(@RequestBody OrdersRequest orderRequest){

        return orderInfoService.toPickUpPassenger(orderRequest);
    }

    /**
     * 到达乘客上车点
     * @param orderRequest
     * @return
     */
    @PostMapping("/arrived-departure")
    public ResponseResult arrivedDeparture(@RequestBody OrdersRequest orderRequest){
        return orderInfoService.arrivedDeparture(orderRequest);
    }

    /**
     * 司机接到乘客
     * @param orderRequest
     * @return
     */
    @PostMapping("/pick_up_passenger")
    public ResponseResult pickUpPassenger(@RequestBody OrdersRequest orderRequest){
        return orderInfoService.pickUpPassenger(orderRequest);
    }

    /**
     * 乘客到达目的地，行程终止
     * @param orderRequest
     * @return
     */
    @PostMapping("/passenger-getoff")
    public ResponseResult passengerGetoff(@RequestBody OrdersRequest orderRequest){
        return orderInfoService.passengerGetoff(orderRequest);
    }

    /**
     * 支付完成
     * @param orderRequest
     * @return
     */
    @PostMapping("/pay")
    public ResponseResult pay(@RequestBody OrdersRequest orderRequest){

        return orderInfoService.pay(orderRequest);
    }

    /**
     * 订单取消
     * @param orderId
     * @param identity
     * @return
     */
    @PostMapping("/cancel")
    public ResponseResult cancel(Long orderId, String identity){

        return orderInfoService.cancel(orderId,identity);
    }

    /**
     * 司机发起收款
     * @param orderRequest
     * @return
     */
    @PostMapping("/push-pay-info")
    public ResponseResult pushPayInfo(@RequestBody OrdersRequest orderRequest){
        return orderInfoService.pushPayInfo(orderRequest);
    }

    /**
     * 订单详情
     * @param orderId
     * @return
     */
    @GetMapping("/detail")
    public ResponseResult<OrderInfo> detail(Long orderId){
        return orderInfoService.detail(orderId);
    }

    @GetMapping("/current")
    public ResponseResult current(String phone , String identity){
        return orderInfoService.current(phone , identity);
    }

}
