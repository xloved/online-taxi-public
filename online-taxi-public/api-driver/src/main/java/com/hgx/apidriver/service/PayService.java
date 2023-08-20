package com.hgx.apidriver.service;

import com.hgx.apidriver.remote.ServiceSsePushClient;
import com.hgx.internalcomm.constant.IdentityConstantEnum;
import com.hgx.internalcomm.dto.ResponseResult;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayService {

    @Autowired
    ServiceSsePushClient serviceSsePushClient;

    public ResponseResult pushPayInfo(String orderId, String price, Long passengerId){
        // 封装消息
        JSONObject message = new JSONObject();
        message.put("price",price);
        message.put("orderId",orderId);

        // 推送消息
        serviceSsePushClient.push(passengerId, IdentityConstantEnum.IDENTITY_PASSENGER,message.toString());

        return ResponseResult.success();
    }
}