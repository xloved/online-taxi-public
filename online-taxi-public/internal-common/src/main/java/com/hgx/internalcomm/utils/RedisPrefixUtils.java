package com.hgx.internalcomm.utils;

/**
 * redis通用方法
 */
public class RedisPrefixUtils {

    //定义乘客验证码的前缀
    public static String verificationCodePrefix = "verfication-code-";

    //定义token的前缀
    public static  String tokenPrefix = "tokenBy-";

    /**
     * 黑名单设备号
     */
    public static String blackDeviceCodePrefix = "black-device-";
    /**
     * 根据手机号生成key
     * @param phone,identity
     * @return
     */
    public static String getByPassengerPhone(String phone,String identity){

        return verificationCodePrefix + identity + "-" + phone;
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
