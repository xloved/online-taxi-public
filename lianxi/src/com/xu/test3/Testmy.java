/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Testmy
 * Author:   旭哥
 * Date:     2019/3/2 11:38
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
 * @create 2019/3/2
 * @since 1.0.0
 */
public class Testmy {
    public static void main(String[] args) {
           MyArrays ma=new MyArrays();
           int getsize = ma.getsize();
           System.out.println(getsize);
           ma.add(2);
           ma.add(3);
           ma.add(4);
        System.out.println( ma.getsize());
          ma.myprint();
          ma.del(1);
          ma.myprint();
          //int ment=ma.get(1);
        ///System.out.println(ment+"***************");
        System.out.println(ma.get(1)+"****");
          ma.insert(1,8);
          ma.myprint();
          ma.getwei(0,3);
          ma.myprint();
    }
}

