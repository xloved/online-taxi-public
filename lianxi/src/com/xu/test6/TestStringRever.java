/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TestStringRever
 * Author:   旭哥
 * Date:     2019/3/13 10:07
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xu.test6;

import java.util.Stack;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 旭哥
 * @create 2019/3/13
 * @since 1.0.0
 * 利用栈实现字符串逆序
 */
public class TestStringRever {

    public void test() {
        Stack stack = new Stack();
        String str = "hellow world";
        char[] cha = str.toCharArray();
        for (char c : cha) {
            stack.push(c);
        }
        while (!stack.empty()) {
            System.out.print(stack.pop());
        }
    }

    public static void main(String[] args) {
           TestStringRever ts=new TestStringRever();
           ts.test();
    }
}

