/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TestMyStack
 * Author:   旭哥
 * Date:     2019/3/4 10:36
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xu.test4;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 旭哥
 * @create 2019/3/4
 * @since 1.0.0
 */
public class TestMyStack {
    public static void main(String[] args) {
            MyStack m=new MyStack();
            m.push(1);
            m.push(2);
            m.push(3);
        /*    System.out.println(m.pop());
            System.out.println(m.pop());
            System.out.println(m.pop());*/
            System.out.println(m.lock());
            m.pop();
        System.out.println(m.lock());
    }
}

