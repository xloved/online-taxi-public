package com.hgx.apidriver.service;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.hgx.apidriver.remote.ServiceDriverUserClients;
import com.hgx.apidriver.remote.ServiceVerificationClients;
import com.hgx.internalcomm.constant.CommonStatusEnum;
import com.hgx.internalcomm.constant.DriverCarConstants;
import com.hgx.internalcomm.constant.IdentityConstantEnum;
import com.hgx.internalcomm.constant.TokenConstantEnum;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.response.DriverUserExistsResponse;
import com.hgx.internalcomm.response.NumberCodeResponse;
import com.hgx.internalcomm.response.TokenResponse;
import com.hgx.internalcomm.utils.JwtUtils;
import com.hgx.internalcomm.utils.RedisPrefixUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Description 司机接收验证码
 * @Author huogaoxu
 * @Date 2023-06-03 21:55
 * @Version 1.0
 **/
@Service
@Slf4j
public class VerificationCodeService {
    @Resource
    ServiceDriverUserClients serviceDriverUserClients;

    @Resource
    ServiceVerificationClients verificationClients;

    @Resource
    StringRedisTemplate stringRedisTemplate;


    public ResponseResult verificationCode(String driverPhone ){

        // 查询 service-Driver-User服务该手机号是否存在
        ResponseResult<DriverUserExistsResponse> userByPhone = serviceDriverUserClients.getUserByPhone(driverPhone);
        DriverUserExistsResponse data = userByPhone.getData();
        int ifExists = data.getIfExists();
        if(ifExists == DriverCarConstants.DRIVER_NOT_EXISTS) {
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXITS.getCode(),
                    CommonStatusEnum.DRIVER_NOT_EXITS.getValue());
        }
        log.info(driverPhone+"的司机存在");
        // 获取验证码
        ResponseResult<NumberCodeResponse> result = verificationClients.numberCode(6);
        NumberCodeResponse resultData = result.getData();
        int numberCode = resultData.getNumberCode();
        log.info("验证码"+numberCode);
        // 调用第三方发送验证码，如阿里，腾讯
        // 存入Redis key,value 过期时间
        String key = RedisPrefixUtils.getByPassengerPhone(driverPhone, IdentityConstantEnum.IDENTITY_DRIVER);
        stringRedisTemplate.opsForValue().set(key, numberCode + "",2, TimeUnit.MINUTES);

        return ResponseResult.success("");
    }


    public ResponseResult checkCode(String driverPhone,String verificationCode){

        //根据手机号去redis读取验证码
        String key = RedisPrefixUtils.getByPassengerPhone(driverPhone,IdentityConstantEnum.IDENTITY_DRIVER);
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


        //发送密码令牌,使用常量或者枚举来进行用户/司机的身份检验
        String  accessToken = JwtUtils.generatorToken(driverPhone, IdentityConstantEnum.IDENTITY_DRIVER, TokenConstantEnum.ACCESS_TOKEN_TYPE);
        String  refreshToken = JwtUtils.generatorToken(driverPhone,IdentityConstantEnum.IDENTITY_DRIVER,TokenConstantEnum.REFRESH_TOKEN_TYPE);

        //响应token
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);

        //把token存储到redis中,设定过期时间为30天
        String accessTokenKey = RedisPrefixUtils.getByToken(driverPhone,IdentityConstantEnum.IDENTITY_DRIVER,TokenConstantEnum.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenKey,accessToken,30,TimeUnit.DAYS);


        String refreshTokenKey = RedisPrefixUtils.getByToken(driverPhone,IdentityConstantEnum.IDENTITY_DRIVER,TokenConstantEnum.REFRESH_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(refreshTokenKey,refreshToken,31,TimeUnit.DAYS);


        return ResponseResult.success(tokenResponse);
    }

}
