package com.hgx.serviceorder.controller;


import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.OrdersRequest;
import com.hgx.serviceorder.service.OrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    public ResponseResult addOrders(@RequestBody OrdersRequest ordersRequest, HttpServletRequest httpRequest){
        // 获取设备请求头 ，使用apifox测试通过，通过header获取deviceCode
//        String header = httpRequest.getHeader(HeaderParamConstant.DEVICE_CODE);
//        ordersRequest.setDeviceCode(header);
        System.out.println(ordersRequest.toString());
        log.info("订单信息：", ordersRequest.toString());
        return orderInfoService.addOrder(ordersRequest);
    }

}
