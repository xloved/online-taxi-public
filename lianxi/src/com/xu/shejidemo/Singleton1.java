/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Singleton1
 * Author:   旭哥
 * Date:     2019/5/12 14:51
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xu.shejidemo;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 旭哥
 * @create 2019/5/12
 * @since 1.0.0
 * 饿汉式
 */
public class Singleton1 {
      private static Singleton1 singleton=new Singleton1();
      private Singleton1(){}

      public static Singleton1 getInstance(){
          return singleton;
      }

}

