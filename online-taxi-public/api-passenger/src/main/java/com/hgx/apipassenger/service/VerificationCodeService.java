package com.hgx.apipassenger.service;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.hgx.apipassenger.remote.ServicePassengerUserClient;
import com.hgx.apipassenger.remote.ServiceVerificationCodeClient;
import com.hgx.internalcomm.constant.CommonStatusEnum;
import com.hgx.internalcomm.constant.IdentityConstantEnum;
import com.hgx.internalcomm.constant.TokenConstantEnum;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.VerificationCodeDTO;
import com.hgx.internalcomm.response.NumberCodeResponse;
import com.hgx.internalcomm.response.TokenResponse;
import com.hgx.internalcomm.utils.JwtUtils;
import com.hgx.internalcomm.utils.RedisPrefixUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

//验证码服务
@Service
public class VerificationCodeService {

    @Resource
    private ServiceVerificationCodeClient serviceVerificationCodeClient;

    @Resource
    private ServicePassengerUserClient servicePassengerUserClient;

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
        String key = RedisPrefixUtils.getByPassengerPhone(passengerPhone,IdentityConstantEnum.IDENTITY_PASSENGER);
        System.out.println("phone------------------"+key);
        //存入redis，设置过期时间为2分钟
        stringRedisTemplate.opsForValue().set(key,numberCode+"",2, TimeUnit.MINUTES);

        //通过短信服务商将验证码发送到手机上

        return  ResponseResult.success("");
    }



    public ResponseResult checkCode(String passengerPhone,String verificationCode){

        //根据手机号去redis读取验证码
        String key = RedisPrefixUtils.getByPassengerPhone(passengerPhone,IdentityConstantEnum.IDENTITY_PASSENGER);
        System.out.println("手机号============"+key);
        //获取key
        String redisCode = stringRedisTemplate.opsForValue().get(key);
        System.out.println("从redis中获取的值："+redisCode);

        //校验验证码
        if(StringUtils.isBlank(redisCode)){//判断验证码是否为NULL
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERRROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERRROR.getValue());
        }
        //判断用户填写的验证码与redis中取到的验证码是否相等
        if(!verificationCode.trim().equals(redisCode.trim())){//删除字符串的头尾空白符
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERRROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERRROR.getValue());
        }

        //判断原来是否有用户，然后进行相应处理"
        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();
        verificationCodeDTO.setPassengerPhone(passengerPhone);
        servicePassengerUserClient.loginOrRegister(verificationCodeDTO);

        //发送密码令牌,使用常量或者枚举来进行用户/司机的身份检验
        String  accessToken = JwtUtils.generatorToken(passengerPhone, IdentityConstantEnum.IDENTITY_PASSENGER,TokenConstantEnum.ACCESS_TOKEN_TYPE);
        String  refreshToken = JwtUtils.generatorToken(passengerPhone,IdentityConstantEnum.IDENTITY_PASSENGER,TokenConstantEnum.REFRESH_TOKEN_TYPE);

        //响应token
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);

        //把token存储到redis中,设定过期时间为30天
        String accessTokenKey = RedisPrefixUtils.getByToken(passengerPhone,IdentityConstantEnum.IDENTITY_PASSENGER,TokenConstantEnum.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenKey,accessToken,30,TimeUnit.DAYS);


        String refreshTokenKey = RedisPrefixUtils.getByToken(passengerPhone,IdentityConstantEnum.IDENTITY_PASSENGER,TokenConstantEnum.REFRESH_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(refreshTokenKey,refreshToken,31,TimeUnit.DAYS);


        return ResponseResult.success(tokenResponse);
    }
}
