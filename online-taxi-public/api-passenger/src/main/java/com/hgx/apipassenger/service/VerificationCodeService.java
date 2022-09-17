package com.hgx.apipassenger.service;

import com.hgx.apipassenger.remote.ServiceVerificationCodeClient;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.response.NumberCodeResponse;
import com.hgx.internalcomm.response.TokenResponse;
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
    //定义乘客验证码的前缀
    private String verificationCodePrefix = "passgenger-verfication-code-";

    @Resource
    StringRedisTemplate stringRedisTemplate;

    /**
     * 获取手机号
     * @param passengerPhone
     * @return
     */
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


    public ResponseResult checkCode(String passsengerPhone,String verificationCode){

        System.out.println("根据手机号去redis读取验证码");
        System.out.println("校验验证码");
        System.out.println("判断原来是否有用户，然后进行相应处理");
        System.out.println("发送密码令牌");

        //响应token
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token value");

        return ResponseResult.success(tokenResponse);
    }
}
