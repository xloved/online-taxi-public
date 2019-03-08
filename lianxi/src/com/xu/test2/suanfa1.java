/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: suanfa1
 * Author:   旭哥
 * Date:     2019/3/1 19:56
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xu.test2;

import java.util.Arrays;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 旭哥
 * @create 2019/3/1
 * @since 1.0.0
 * 改变数组长度
 */
public class suanfa1 {
    public static void main(String [] args) {
        //原数组长度
               int arr1[]=new int[]{1,2,3};
        System.out.println(Arrays.toString(arr1));
        //定义目标元素
             int a=4;
             //创建新数组，在原数组的基础上＋1
             int arr2[]=new int[arr1.length+1];
             //循环原数组
             for (int i=0;i<arr1.length;i++){
                 //把原数组赋值给新数组
                   arr2[i]=arr1[i];
             }
             //把目标元素放在新数组中
            arr2[arr1.length]=a;
             //把新数组替换原数组
             arr1=arr2;

        System.out.println(Arrays.toString(arr1));
    }
}

