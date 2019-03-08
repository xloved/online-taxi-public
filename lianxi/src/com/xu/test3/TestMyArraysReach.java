/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TestMyArraysReach
 * Author:   旭哥
 * Date:     2019/3/4 10:00
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xu.test3;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 旭哥
 * @create 2019/3/4
 * @since 1.0.0
 */
public class TestMyArraysReach {
    public static void main(String[] args) {
              MyArrays arr=new MyArrays();
              arr.add(1);
              arr.add(2);
              arr.add(3);
              arr.add(4);
       /* int index = arr.serteach(3);
        System.out.println(index);*/
         //二分法查找
        int sertreach2 = arr.sertreach2(5);
        System.out.println(sertreach2);
    }
}

