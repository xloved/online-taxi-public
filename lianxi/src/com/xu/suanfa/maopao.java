/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: maopao
 * Author:   旭哥
 * Date:     2019/5/12 15:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xu.suanfa;

import java.util.Arrays;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 旭哥
 * @create 2019/5/12
 * @since 1.0.0
 * 冒泡排序
 */
public class maopao {

    public static void main(String[] args) {
        int[] array={2,9,4,8,5,3,7,6};
        bubble(array);
        //System.out.println(Arrays.toString(array));
        System.out.println("**********");

    }

    public static int[] bubble(int[] array){

        if(array.length==0){

            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length-1-i; j++) {
                    if(array[j+1]<array[j]){
                        int temp=array[j+1];
                        array[j+1]=array[j];
                        array[j]=temp;
                        System.out.print(temp+"------");
                    }
                    return array;
                }
            }
        }
        return array;
    }
}

