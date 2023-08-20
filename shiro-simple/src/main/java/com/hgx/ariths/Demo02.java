package com.hgx.ariths;

/**
 * @Description 查找数组中的最大值和最小值
 * @Author huogaoxu
 * @Date 2023-07-26 16:50
 * @Version 1.0
 **/

import java.util.Arrays;

/**
 * 编写一个Java函数，接受一个整数数组作为参数，并返回数组中的最大值和最小值。
 * 函数签名：public static int[] findMinMax(int[] array)
 * 输入参数：
 *
 * array：一个整数数组，长度不超过100，数组元素取值范围在-100到100之间。
 * 输出：
 * 返回一个包含两个整数的数组，第一个元素是最小值，第二个元素是最大值。
 * 示例：
 * 输入：[5, 10, 15, 20, 25]
 * 输出：[5, 25]
 *
 * 输入：[0, -10, 10, 0, -10]
 * 输出：[-10, 10]
 */
public class Demo02 {

    public static void main(String[] args) {
        int[] array = {5, 10, 15, 20, 25};
        int[] minMax = findMinMax(array);
        System.out.println("最小值："+minMax[0]);
        System.out.println("最大值："+minMax[1]);
    }

    public static int[] findMinMax(int[] array) {
        if (array == null || array.length == 0) {
            throw  new IllegalArgumentException("数据不能为空");
        }
        int minIndex = array[0];
        int maxIndex = array[0];
        for (int j : array) {
            if (j < minIndex) {
                minIndex = j;
            }
            if (j > maxIndex) {
                maxIndex = j;
            }
        }
        return new int[]{minIndex,maxIndex};
    }
}
