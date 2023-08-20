/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Singleton
 * Author:   旭哥
 * Date:     2019/5/12 14:42
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
 * 懒汉式（线程不安全）
 */
public class Singleton {
    private static Singleton singleton;

    private Singleton() {
    }

    /*public static  Singleton getInstance(){
        if (singleton==null){
            singleton=new Singleton();
        }
        return singleton;
    }*/

   //懒汉式（线程安全的）
  /*  public static  synchronized Singleton getInstance(){
        if(singleton==null){
             singleton=new Singleton();
        }
        return singleton;
    }*/
   //双重校验锁
    public static Singleton getInstance(){
          synchronized (Singleton.class){
            if(singleton==null){
                singleton=new Singleton();
            }
          }
          return singleton;
    }


}

