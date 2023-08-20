/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: shuTest1
 * Author:   旭哥
 * Date:     2019/3/18 10:53
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
 * @create 2019/3/18
 * @since 1.0.0
 * 改变原数组长度
 */
public class shuTest1 {

    public static void main(String[] args) {
             int[] arr=new int[]{1,2,3,4,5,6};
        System.out.println(Arrays.toString(arr));
         /*    int a=7;
             int newarr[]=new int[arr.length+1];
             for(int i=0;i<arr.length;i++){
                   newarr[i]=arr[i];
             }
             newarr[arr.length]=a;
             arr=newarr;
        System.out.println(Arrays.toString(arr));*/
         //要删除下标的元素
         int b=3;

         int arr2[]=new int[arr.length-1];
         //循环新数组
          for(int i=0;i<arr2.length;i++){
                 if(i<b){
                      arr2[i]=arr[i];
                 }else{
                     arr2[i]=arr[i+1];
                 }
          }
          arr=arr2;
        System.out.println(Arrays.toString(arr));
    }
}

