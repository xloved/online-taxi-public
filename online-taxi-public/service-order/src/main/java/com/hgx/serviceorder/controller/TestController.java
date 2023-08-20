package com.hgx.serviceorder.controller;

import com.hgx.internalcomm.dto.OrderInfo;
import com.hgx.serviceorder.mapper.OrderInfoMapper;
import com.hgx.serviceorder.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description com.hgx.serviceorder.controller
 * @Author huogaoxu
 * @Date 2023-06-12 10:55
 * @Version 1.0
 **/
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "service-order";
    }
    @Value("${server.port}")
    String port;



    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private OrderInfoMapper orderInfoMapper;

    /**
     * 测试派单逻辑
     * @param orderId
     * @return
     */
    @GetMapping("/test-real-time-order/{orderId}")
    public String dispatchRealTimeOrder(@PathVariable("orderId") long orderId){
        System.out.println("service-order 端口："+ port+" 并发测试：orderId："+orderId);
        OrderInfo orderInfo = orderInfoMapper.selectById(orderId);
        orderInfoService.dispatchRealTimeOrder(orderInfo);
        return "test-real-time-order success";
    }
}
