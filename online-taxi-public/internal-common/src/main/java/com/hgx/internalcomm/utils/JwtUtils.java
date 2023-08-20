package com.hgx.internalcomm.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hgx.internalcomm.dto.TokenResult;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成jwt方法
 */
public class JwtUtils {

    //设置盐

    private  static final String SING = "CFsnm#@.*!";

    //定义传入的key
    private static final String JWT_KEY_PHONE = "passengerPhone";//手机号
    private static final String JWT_KEY_IDENTITY = "identity";//身份唯一识别
    //定义token类型
    private static final String JWT_KEY_TOKEN_TYPE = "tokenType";

    //定义token的过期时间
    private static final String JWT_KEY_TOKEN_DATE = "tokenDateTime";

    //生成token
    public static String generatorToken(String phone,String identity,String tokenType){

        Map<String,String> map  = new HashMap<>();
        map.put(JWT_KEY_PHONE,phone);
        map.put(JWT_KEY_IDENTITY,identity);
        map.put(JWT_KEY_TOKEN_TYPE,tokenType);
//        System.out.println(phone);
         //防止生成的token一样
        map.put(JWT_KEY_TOKEN_DATE,Calendar.getInstance().getTime().toString());

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

    /**
     * 校验token，判断token是否异常
     * @param token
     * @return
     */
    public static TokenResult checkToken(String token){
        TokenResult tokenResult = null;
        try {
            tokenResult = JwtUtils.parseToken(token);//获取token
        }catch (Exception e){
        }

        return tokenResult;
    }

    //测试
    public static void main(String[] args) {
        /*Map<String,String> map = new HashMap<>();
        map.put("name","zhangsan");
        map.put("age","18");*/
         String s = generatorToken("17890653409","1","accessToken");
         System.out.println("生成的token："+s);
        TokenResult tokenResult = parseToken(s);
        System.out.println("手机号："+tokenResult.getPassengerPhone());
        System.out.println("ID："+tokenResult.getIdentity());

    }

}
