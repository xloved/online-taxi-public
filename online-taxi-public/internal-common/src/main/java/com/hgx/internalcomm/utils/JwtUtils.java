package com.hgx.internalcomm.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hgx.internalcomm.dto.TokenResult;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    //设置盐

    private  static final String SING = "CFsnm#@.*!";

    //定义传入的key
    private static final String JWT_KEY_PHONE = "passengerPhone";//手机号
    private static final String JWT_KEY_IDENTITY = "identity";//身份唯一识别

    //生成token
    public static String generatorToken(String passengerPhone,String identity){

        Map<String,String> map  = new HashMap<>();
        map.put(JWT_KEY_PHONE,passengerPhone);
        map.put(JWT_KEY_IDENTITY,identity);
        System.out.println(passengerPhone);
        //获取当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,30);//设置过期时间
        //Date date = calendar.getTime();//具体的到期时间点时间
        JWTCreator.Builder builder = JWT.create();//生成builder
        //使用lamde表达式迭代map
        map.forEach((k,v)-> {
                builder.withClaim(k,v);
        });

        //整合过期时间
        //builder.withExpiresAt(date);

        //生成token
        String singer = builder.sign(Algorithm.HMAC256(SING));

        return singer;

    }


    //解析token
    public static TokenResult  parseToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        System.out.println(verify);
        String passengerPhone  = verify.getClaim(JWT_KEY_PHONE).asString();
        String identity  = verify.getClaim(JWT_KEY_IDENTITY).asString();
        TokenResult tokenResult = new TokenResult();
        tokenResult.setPassengerPhone(passengerPhone);
        tokenResult.setIdentity(identity);

        return tokenResult;
    }

    //测试
    public static void main(String[] args) {
        /*Map<String,String> map = new HashMap<>();
        map.put("name","zhangsan");
        map.put("age","18");*/
         String s = generatorToken("17890653409","1");
         System.out.println("生成的token："+s);
        TokenResult tokenResult = parseToken(s);
        System.out.println("手机号："+tokenResult.getPassengerPhone());
        System.out.println("ID："+tokenResult.getIdentity());

    }

}
