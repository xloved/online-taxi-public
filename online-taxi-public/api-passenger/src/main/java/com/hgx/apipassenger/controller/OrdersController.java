package com.hgx.apipassenger.controller;

import com.hgx.apipassenger.service.OrdersService;
import com.hgx.internalcomm.constant.CommonStatusEnum;
import com.hgx.internalcomm.constant.IdentityConstantEnum;
import com.hgx.internalcomm.dto.OrderInfo;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.dto.TokenResult;
import com.hgx.internalcomm.request.OrdersRequest;
import com.hgx.internalcomm.utils.JwtUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description 乘客下订单功能
 * @Author huogaoxu
 * @Date 2023-06-12 9:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Resource
    private OrdersService ordersService;

    @PostMapping("/add")
    public ResponseResult addOrder(@RequestBody OrdersRequest ordersRequest){

        System.out.println(ordersRequest.toString());
        return  ordersService.addOrders(ordersRequest);

    }

    /**
     * 乘客取消订单
     * @param orderId
     * @return
     */
    @PostMapping("/cancel")
    public ResponseResult cancel(@RequestParam Long orderId){
        return ordersService.cancel(orderId);
    }

    @GetMapping("/detail")
    public ResponseResult<OrderInfo> detail(Long orderId){
        return ordersService.detail(orderId);
    }

    @GetMapping("/current")
    public ResponseResult<OrderInfo> currentOrder(HttpServletRequest httpServletRequest){
        String authorization = httpServletRequest.getHeader("Authorization");
        TokenResult tokenResult = JwtUtils.parseToken(authorization);
        String identity = tokenResult.getIdentity();
        if (!identity.equals(IdentityConstantEnum.IDENTITY_PASSENGER)){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        String phone = tokenResult.getPhone();

        return ordersService.currentOrder(phone,IdentityConstantEnum.IDENTITY_PASSENGER);
    }
}
