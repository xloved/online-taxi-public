/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MyStack
 * Author:   旭哥
 * Date:     2019/3/4 10:31
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
 * 栈，先进后出
 */
public class MyStack  {
         int[] ments;
         public MyStack(){
             ments=new int[0];

         }

          //压入元素
         public void push(int ment){
               int[] newarr=new int[ments.length+1];
               for(int i=0;i<ments.length;i++){
                   newarr[i]=ments[i];
               }
               newarr[ments.length]=ment;
               ments=newarr;
         }
          //取出栈顶元素
         public int pop(){
             //判断元素是否为空
             if(ments.length==0){
                  throw new RuntimeException("为空");
             }
              int ment=ments[ments.length-1];
              int[] arr=new int[ments.length-1];
              for(int i=0;i<ments.length-1;i++){
                    arr[i]=ments[i];
              }
              ments=arr;

             return ment;
         }
          //查看栈顶元素
         public int lock(){
             //判断元素是否为空
             if(ments.length==0){
                 throw new RuntimeException("为空");
             }
             return ments[ments.length-1];
         }

         //判断栈是否为空
         public boolean ipemptoy(){
             return ments.length==0;
         }
}

