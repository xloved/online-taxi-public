/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MyQueTest
 * Author:   旭哥
 * Date:     2019/3/17 21:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xu.test6;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 旭哥
 * @create 2019/3/17
 * @since 1.0.0
 */
public class MyQueTest {
    public static void main(String[] args) {
        MyQueue que=new MyQueue(5);
             que.insert(1);
             que.insert(2);
             que.insert(3);
             que.insert(4);
             que.insert(5);
        System.out.println(que.peekFront());
           que.remove();
       System.out.println(que.peekFront());
        que.insert(4);
        que.insert(5);


    }



}

