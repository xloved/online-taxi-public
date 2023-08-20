/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: DiguiTest
 * Author:   旭哥
 * Date:     2019/4/1 11:06
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xu.digui;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 旭哥
 * @create 2019/4/1
 * @since 1.0.0
 * 递归算法
 */
public class DiguiTest {

    public static void main(String[] args) {
            getFor(4);
    }

    public static int getFor(int n){
         /*int temp=1;
          if(n>=0){
              for (int i = 1; i <=n; i++) {
                     temp=temp*i;
                  System.out.println(temp);
              }
          }else{
              return -1;
          }
          return temp;*/
         if(n>=0){
               if(n==0){
                   System.out.println(n+"!=1");
                    return 1;
               }else{
                   System.out.println(n);

                   int temp=n*getFor(n-1);
                   System.out.println("n = "+n+" e " +getFor(n-1) + " j "+temp);
                   return  temp;
               }
         }
         return -1;
    }


}

