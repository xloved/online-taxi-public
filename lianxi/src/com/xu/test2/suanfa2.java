/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: suanfa2
 * Author:   旭哥
 * Date:     2019/3/2 10:32
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
 * @create 2019/3/2
 * @since 1.0.0\
 *
 * 删除数组
 */
public class suanfa2 {
    public static void main(String[] args) {
        //目标数组
            int arr[]=new int[]{1,2,3,4,5,6};
        System.out.println(Arrays.toString(arr));
        //要删除的元素下标
          int a=2;
          //新数组
          int b[]=new int[arr.length-1];
          //循环新数组
          for(int i=0;i<b.length;i++){
              //判断元素下标
                 if(i<a){
                      b[i]=arr[i];
                 }else{
                     //删除下标之后的元素
                     b[i]=arr[i+1];

                 }
                 //arr[i]=b[i];

          }
          //把新数组替换原来的数组
          arr=b;
        System.out.println(Arrays.toString(arr));
    }
}

