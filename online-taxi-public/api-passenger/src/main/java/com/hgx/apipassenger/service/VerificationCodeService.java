package com.hgx.apipassenger.service;

import com.hgx.apipassenger.remote.ServiceVerificationCodeClient;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

//验证码服务
@Service
public class VerificationCodeService {

    @Resource
    ServiceVerificationCodeClient serviceVerificationCodeClient;

    public String generatorCode(String passengerPhone){

        //调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");
        String  code = "111111";
        ResponseResult<NumberCodeResponse> numberCoderesponse = serviceVerificationCodeClient.getNumberCode();
        int numberCode = numberCoderesponse.getData().getNumberCode();

        System.out.println("remote number code："+numberCode);
        //假设存入redis
        System.out.println("存入redis");

        //返回值，验证码返回给乘客
        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("message","success");
        return result.toString();
    }
}
