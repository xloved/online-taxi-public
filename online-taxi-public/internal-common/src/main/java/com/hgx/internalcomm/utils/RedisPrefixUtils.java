package com.hgx.internalcomm.utils;

public class RedisPrefixUtils {

    //定义乘客验证码的前缀
    public static String verificationCodePrefix = "passgenger-verfication-code-";

    //定义token的前缀
    public static  String tokenPrefix = "tokenBy-";

    /**
     * 根据手机号生成key
     * @param passengerPhone
     * @return
     */
    public static String getByPassengerPhone(String passengerPhone){

        return verificationCodePrefix + passengerPhone;
    }

    /**
     * 根据手机号和身份标识生成token的key
     * @param passengerPhone
     * @param identity
     * @return
     */
    public static String getByToken(String passengerPhone,String identity,String tokenType){
        return "tokenPrefix"+passengerPhone+"-"+identity +"-"+tokenType;

    }
}
