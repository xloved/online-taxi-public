package com.hgx.apipassenger.service;

import com.hgx.apipassenger.interceptor.JwtInterceptor;
import com.hgx.internalcomm.constant.CommonStatusEnum;
import com.hgx.internalcomm.constant.TokenConstantEnum;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.dto.TokenResult;
import com.hgx.internalcomm.response.TokenResponse;
import com.hgx.internalcomm.utils.JwtUtils;
import com.hgx.internalcomm.utils.RedisPrefixUtils;
import lombok.val;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 生成双token
 */
@Service
public class TokenService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult refreshToken(String refreshTokenSrc){

        //解析refreshtoken
        TokenResult tokenResult = JwtUtils.checkToken(refreshTokenSrc);

        if(tokenResult == null){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        String passengerPhone = tokenResult.getPassengerPhone();
        String identity = tokenResult.getIdentity();
        //读取redis中的refreshtoken
        String refreshTokenKey = RedisPrefixUtils.getByToken(passengerPhone, identity, TokenConstantEnum.REFRESH_TOKEN_TYPE);
        String refreshTokenRedis = stringRedisTemplate.opsForValue().get(refreshTokenKey);
        //校验refreshtoken
        if((StringUtils.isBlank(refreshTokenRedis)) || (!refreshTokenRedis.trim().equals(refreshTokenRedis.trim()))){
           return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        //生成双token
        String refreshToken = JwtUtils.generatorToken(passengerPhone, identity, TokenConstantEnum.REFRESH_TOKEN_TYPE);
        String accessToken = JwtUtils.generatorToken(passengerPhone,identity,TokenConstantEnum.ACCESS_TOKEN_TYPE);

        String accessTokenKey = RedisPrefixUtils.getByToken(passengerPhone,identity,TokenConstantEnum.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(refreshTokenKey,refreshToken,30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(accessTokenKey,accessToken,31,TimeUnit.DAYS);

        TokenResponse  tokenResponse = new TokenResponse();
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setAccessToken(accessToken);


        return ResponseResult.success(tokenResponse);
    }
}
