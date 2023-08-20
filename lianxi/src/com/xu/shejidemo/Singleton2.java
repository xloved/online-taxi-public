/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Singleton2
 * Author:   旭哥
 * Date:     2019/5/12 15:00
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
 * 饿汉模式
 */
public class Singleton2 {
        private static Singleton2 singleton2=new Singleton2();
        private  Singleton2(){}
        public static  Singleton2 getInstance(){
            System.out.println();
            return singleton2;
        }
}

