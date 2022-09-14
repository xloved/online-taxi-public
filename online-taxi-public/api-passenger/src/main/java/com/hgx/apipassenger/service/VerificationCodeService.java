package com.hgx.apipassenger.service;

import com.hgx.apipassenger.remote.ServiceVerificationCodeClient;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

//验证码服务
@Service
public class VerificationCodeService {

    @Resource
    ServiceVerificationCodeClient serviceVerificationCodeClient;

    private String verificationCodePrefix = "passgenger-verfication-code-";

    @Resource
    StringRedisTemplate stringRedisTemplate;

    //定义乘客验证码的前缀
    public ResponseResult generatorCode(String passengerPhone){

        //调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");
        ResponseResult<NumberCodeResponse> numberCoderesponse = serviceVerificationCodeClient.getNumberCode(6);
        int numberCode = numberCoderesponse.getData().getNumberCode();

        //存入redis
        //定义key
        String key = verificationCodePrefix + passengerPhone;
        //存入redis，设置过期时间为2分钟
        stringRedisTemplate.opsForValue().set(key,numberCode+"",2, TimeUnit.MINUTES);

        //通过短信服务商将验证码发送到手机上

        return  ResponseResult.success("");
    }
}
