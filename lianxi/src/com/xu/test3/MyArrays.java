/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MyArrays
 * Author:   旭哥
 * Date:     2019/3/2 11:33
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xu.test3;

import java.util.Arrays;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 旭哥
 * @create 2019/3/2
 * @since 1.0.0
 */
public class MyArrays {
     /*
       创建存储数据的数组
      */
    private int[] ments;

    public MyArrays(){
         ments=new int[0];

    }
       /*
         检查数组长度
        */
    public int getsize(){
        return ments.length;
    }
      //在元素组末尾加上一个元素
    public void add(int ment){
        //创建一个新数组
          int[] nArr=new int[ments.length+1];
          //循环原数组
          for (int i=0;i<ments.length;i++){
                nArr[i]=ments[i];
          }
          nArr[ments.length]=ment;
          ments=nArr;
    }

    //打印数组有多少元素
    public void myprint(){

        System.out.println(Arrays.toString(ments));
    }
    //删除一个元素
    public void del(int index){
        //判断下标是否越界
         if(index<0 || index>ments.length-1){
             throw new RuntimeException("下标越界");
         }
           //创建新数组
           int[] arr=new int[ments.length-1];
            for(int i=0;i<arr.length;i++){
                 if(i<index){
                       arr[i]=ments[i];
                 }else{
                     arr[i]=ments[i+1];
                 }
            }
            ments=arr;
    }
        //获取指定下标的元素
       public int get(int index){
           //判断下标是否越界
           if(index<0 || index>ments.length-1){
               throw new RuntimeException("下标越界");
           }
             return ments[index];
       }

       //插入元素到指定位置
    public void insert(int index,int ment){
        //创建一个新的数组
        int[] a2=new int[ments.length+1];
        //循环原数组
         for (int i=0;i<ments.length;i++){
             //判断下标，
                if(i<index){
                    //元素在指定下标之前
                      a2[i]=ments[i];
                }else{
                    //元素在指定下标之后
                    a2[i+1]=ments[i];
                }
         }
         //把原数组复制给新数组
         a2[index]=ment;
         //新数组替换原数组
         ments=a2;

    }
    //替换元素到指定位置
    public void getwei(int index,int ment){
        //判断下标是否越界
        if(index<0 || index>ments.length-1){
            throw new RuntimeException("下标越界");
        }
          ments[index]=ment;
    }

       //线性查找
    public int serteach(int tar){
        for (int i=0;i<ments.length;i++){
            //如果i=目标元素,则输出目标元素所在下标
            if(ments[i]==tar){
                return i;

            }
        }
        return -1;
    }

 public int sertreach2(int tar){
     //开始位置
     int a=0;
     //结束位置
     int b=ments.length-1;
     //中间位置
     int c=(a+b)/2;

     //循环查找
     while(true){
         if(a>=b){
               return -1;
         }
         //判断中间元素是不是要查找的元素
         if(ments[c]==tar){
           return c;
         }else{
             //判断中间元素是不是比目标元素大
             if(ments[c]>tar){
                 //把结束位置调整到中间位置前一个位置
                 b=c-1;
             }else{
                 //把开始位置调整到中间位置后一个元素
                 a=c+1;
             }
             //重新查找中间元素
             c=(a+b)/2;
         }
     }
 }
}

