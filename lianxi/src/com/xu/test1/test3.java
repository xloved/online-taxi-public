/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: test3
 * Author:   旭哥
 * Date:     2019/2/27 14:41
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xu.test1;

import java.util.Random;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 旭哥
 * @create 2019/2/27
 * @since 1.0.0
 */
public class test3 {
    public static void main(String[] args) {

      /*  for(int i=1;i<21;i++){
            Users u=new Users();
            int x=1+(int)(Math.random()*50);
            int a=1+(int)(Math.random()*10);

            System.out.println("name"+i+" "+"年龄="+x);
        }
        System.out.println();*/

         Users[] u=new Users[20];
         Random  random=new Random();
         String[]  citys={"上海","广州","深圳","北京"};
         for(int i=0;i<u.length;i++){
             //实例化实体类
               u[i]=new Users();
               u[i].setName("name"+(i+1));
               u[i].setAge(random.nextInt(150));
               u[i].setGander(random.nextInt()%2==0 ? "男":"女");
               u[i].setCity(citys[random.nextInt(4)]);

         }
         //创建一个空对象
         Users us=null;
         //冒泡排序
          for(int i=1;i<u.length;i++){
             for(int j=0;j<u.length-i;j++){
                      if(u[j].getAge()>u[j+1].getAge()){
                               us=u[j];
                               u[j]=u[j+1];
                               u[j+1]=us;

                      }
             }
          }
          //遍历
        for (Users user:u) {
            System.out.println(user.getName()+ " "+user.getAge()+ " "+user.getCity()+ " "+user.getCity());
        } 
    }
}

