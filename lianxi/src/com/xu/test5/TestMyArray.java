/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TestMyArray
 * Author:   旭哥
 * Date:     2019/3/7 20:06
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xu.test5;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 旭哥
 * @create 2019/3/7
 * @since 1.0.0
 */
public class TestMyArray {
    public static void main(String[] args) {
        MyArray ma=new MyArray(4);
        ma.add(1);
        ma.add(2);
        ma.add(3);
        ma.add(4);
       // ma.add(5);
        //ma.add(6);
        ma.showarray();
        int index = ma.getIndex(0);
       System.out.println(index+"根据下标查找");
        //int i = ma.find(1);
        System.out.println("5645897");

         ma.del(2);
         ma.showarray();
        ma.update(3,10);
        ma.showarray();
        //System.out.println(ma.find(4)+"qqqqqs");
        //System.out.println( ma.getLength());
       //System.out.println(del+"---");
        //System.out.println(ma.update(12,15));
    }
}



