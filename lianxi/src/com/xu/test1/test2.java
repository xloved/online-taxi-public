/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: test2
 * Author:   旭哥
 * Date:     2019/2/22 11:28
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
public class test2 {
        public static void main(String[] args) {
            String a = new String("ab"); // a 为一个引用
            String b = new String("ab"); // b为另一个引用,对象的内容一样
            String aa = "ab"; // 放在常量池中
            String bb = "ab"; // 从常量池中查找
            if (aa == bb) { // true
                System.out.println("aa==bb");
            }
            if (a == b) { // false，非同一对象
                System.out.println("a==b");
            }
            if (a.equals(b)) { // true
                System.out.println("aEQb");
            }
            if (42 == 42.0) { // true
                System.out.println("true");
            }
        }
    }



