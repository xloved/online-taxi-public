package com.hgx.internalcomm.utils;

import java.math.BigDecimal;

/**
 * @Description
 * @Author huogaoxu
 * @Date 2023-01-14 21:53
 **/
public class BigDecimalUtils {

    /**
     * 加法
     * @param dou1
     * @param dou2
     * @return
     */
    public static double add(double dou1, double dou2){
        BigDecimal num1 = BigDecimal.valueOf(dou1);
        BigDecimal num2 = BigDecimal.valueOf(dou2);
        return num1.add(num2).doubleValue();
    }

    /**
     * 减法
     * @param v1
     * @param v2
     * @return
     */
    public static double substract(double v1, double v2){
        BigDecimal num1 = BigDecimal.valueOf(v1);
        BigDecimal num2 = BigDecimal.valueOf(v2);
        return num1.subtract(num2).doubleValue();
    }

    /**
     * 乘法
     * @param v1
     * @param v2
     * @return
     */
    public static double multiply(double v1,double v2){
        BigDecimal num1 = BigDecimal.valueOf(v1);
        BigDecimal num2 = BigDecimal.valueOf(v2);
        return num1.multiply(num2).doubleValue();
    }
    /**
     * 除法
     * @param v1
     * @param v2
     * @return
     */
    public static double divide(int v1, int v2){
        if (v2 <= 0) {
            throw  new IllegalArgumentException("除数异常");
        }
        BigDecimal num1 = BigDecimal.valueOf(v1);
        BigDecimal num2 = BigDecimal.valueOf(v2);
        return num1.divide(num2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
