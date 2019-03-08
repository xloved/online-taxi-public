/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MyDuilie
 * Author:   旭哥
 * Date:     2019/3/4 11:57
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
public class MyDuilie {
                 int ments[];

                 public MyDuilie(){
                     ments=new int[0];
                 }
           public void add(int ment){
               int[] newarr=new int[ments.length+1];
               for(int i=0;i<ments.length;i++){
                   newarr[i]=ments[i];
               }
               newarr[ments.length]=ment;
               ments=newarr;
           }

           //出队
           public int pop(){
                     //把第0个元素取出来
                   int ment=ments[0];
                   int[] arrs=new int[ments.length-1];
                   //复制原数组中的元素到新数组
                    for(int i=0;i<arrs.length;i++){
                         arrs[i]=ments[i+1];

                    }
                    ments=arrs;
                    return ment;
           }
           public boolean isEmpty(){
                     return ments.length==0;
           }
}

