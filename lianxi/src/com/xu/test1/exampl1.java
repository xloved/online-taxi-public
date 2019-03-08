/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: exampl1
 * Author:   旭哥
 * Date:     2019/2/22 10:47
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xu.test1;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 旭哥
 * @create 2019/2/22
 * @since 1.0.0
 */
public class exampl1 {

    public static void main(String[] args) {
           int num1=10;
           int num2=20;
           swap(num1,num2);
        System.out.println("num1="+num1);
        System.out.println("num2="+num2);
    }
    public static void swap(int a,int b){
         int temp=a;
         a=b;
         b=temp;
        System.out.println("a="+a);
        System.out.println("b="+b);
    }
}

