package com.hgx.apidriver.service;

import com.hgx.apidriver.remote.ServiceOrderClient;
import com.hgx.apidriver.remote.ServiceSsePushClient;
import com.hgx.internalcomm.constant.IdentityConstantEnum;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.OrdersRequest;
import com.hgx.internalcomm.request.PushRequest;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayService {

    @Autowired
    ServiceSsePushClient serviceSsePushClient;

    @Autowired
    ServiceOrderClient serviceOrderClient;

    public ResponseResult pushPayInfo(Long orderId, String price,Long passengerId){
        // 封装消息
        JSONObject message = new JSONObject();
        message.put("price",price);
        message.put("orderId",orderId);

        // 修改订单状态
        OrdersRequest orderRequest = new OrdersRequest();
        orderRequest.setOrderId(orderId);
        serviceOrderClient.pushPayInfo(orderRequest);

        PushRequest pushRequest = new PushRequest();
        pushRequest.setContent(message.toString());
        pushRequest.setUserId(passengerId);
        pushRequest.setIdentity(IdentityConstantEnum.IDENTITY_PASSENGER);

        // 推送消息
//        serviceSsePushClient.push(passengerId, IdentityConstantEnum.IDENTITY_PASSENGER,message.toString());
        serviceSsePushClient.push(pushRequest);

        return ResponseResult.success();
    }
}