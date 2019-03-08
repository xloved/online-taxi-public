/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Testce2
 * Author:   旭哥
 * Date:     2019/3/1 19:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xu.test2;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 旭哥
 * @create 2019/3/1
 * @since 1.0.0
 */
public class Testce2 {
   /* private void myprint(int i){
        System.out.println(i);
    }*/
    public static void main(String[] args) {
        int m=100;
        for(int i=2;i<Math.sqrt(m);i++){
            for (int j=2*i;j<m;j+=i){
                 int a=i*2;
                 int b=i;
                setd(a,b);
                System.out.println();
            }
        }


    }
    public static void setd(int a,int b){
        if(a<100){
            System.out.print(a+" ");
            setd(a+b,b);
        }

    }
}

