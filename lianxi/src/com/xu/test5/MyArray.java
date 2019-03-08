/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MyArray
 * Author:   旭哥
 * Date:     2019/3/7 19:58
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
public class MyArray {
                 //定义数组
               private int[] myarray;
               //数组的有效长度
               private int ments;
               //实际长度
               private int length;
                //构造一个长度为60的数组
                 public MyArray(){
                     ments=0;
                     length=60;
                     myarray=new int[length];

                 }
                 //构造函数 初始化length长度
                 public MyArray(int length){
                      ments=0;
                      this.length=length;
                      myarray=new int[length];
                 }

                  //获取数组有效长度
                 public int getLength(){
                     return ments;
                 }

                   //遍历数组
                 public void showarray(){
                     for (int i=0;i<ments;i++){
                         System.out.println(myarray[i]);
                     }
                     System.out.println();
                 }

                  //添加元素
                  public boolean add(int num){
                       if(ments==length){
                           return false;
                       }else{
                           myarray[ments]=num;
                           ments++;
                       }
                       return true;

                  }
                  //根据下标获取元素
                  public int getIndex(int index){
                     //当下标为-1时或者下标大于数组长度，下标越界
                        if(index<0 || index>ments){
                                  throw new RuntimeException("下标越界");
                        }
                        return myarray[index];
                  }
                     //查找元素
                  public int find(int serach){
                        int i=0;
                        //循环数组长度
                       for( i=0;i<ments;i++){
                           //元素==数组中的一个元素则跳出循环
                             if(myarray[i]==serach){
                                    break;
                             }
                           if(i==ments){
                               return -1;
                           }
                       }

                      return i;
                  }
                  //删除元素
                  public boolean del(int nn){
                     int d=find(nn);
                      if(d == -1){
                          System.out.println("*********************");
                          return false;
                      }else{
                          if(d==ments-1){
                              ments--;
                          }else {
                              for (int i = d; i < ments - 1; i++) {
                                  myarray[i] = myarray[i + 1];
                              }
                              ments--;
                          }
                         return true;
                      }

                  }
                     //修改元素
                  public boolean update(int old,int news){
                          int i=find(old);
                          if(i==-1){
                              System.out.println("修改的值不存在");
                               return false;
                          }else{
                              myarray[i]=news;
                              return true;
                          }

                  }
}

