package com.hgx.ariths;

/**
 * @Description 计算数组元素的平均值
 * @Author huogaoxu
 * @Date 2023-07-26 16:42
 * @Version 1.0
 **/

/**
 * 编写一个Java函数，接受一个整数数组作为参数，并计算数组中所有元素的平均值。
 * 函数签名：public static double calculateAverage(int[] array)
 * 输入参数：
 *
 * array：一个整数数组，长度不超过100，数组元素取值范围在-100到100之间。
 * 输出：
 * 返回一个double类型的平均值，保留两位小数。
 * 示例：
 * 输入：[5, 10, 15, 20, 25]
 * 输出：15.00
 *
 * 输入：[0, -10, 10, 0, -10]
 * 输出：-2.00
 *
 * 提示：
 *
 *   使用一个变量来存储数组元素的总和，然后除以数组的长度即可得到平均值。
 *   注意处理数组为空的情况，可以返回0或者抛出异常。
 *   注意处理除以0的情况，可以返回0或者抛出异常。
 */
public class Demo01 {

    public static void main(String[] args) {
      int[] array = {5, 10, 15, 20, 25};
        double average = calculateAverage(array);
        System.out.println(average);
    }
    public static double calculateAverage(int[] array){
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }
        int sum = 0;
        for (int num : array) {
            sum += num; // 累加集合中所有元素的值

        }
        return (double) sum / array.length; // sum除以总长度得到平均值
    }
}
