/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: InsertSort
 * Author:   旭哥
 * Date:     2019/3/12 11:30
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
 * @create 2019/3/12
 * @since 1.0.0
 * 插入排序
 */
public class InsertSort {
              public static int[] sort(int[] array){
                  int j;
                  //从下标为1的元素开始选择合适的位置插入，下标为0的元素只有一个默认为有序
                     for(int i=0;i<array.length;i++){
                         //记录要插入的数据
                               int temp=array[i];
                               j=i;
                               //从已经排序的最右面开始比较，找到比其小的数
                               while(j>0 && temp<array[j-1]){
                                   //向后挪动
                                  array[j]=array[j-1];
                                  j--;
                               }
                               //存在比其小的数，插入
                               array[j]=temp;
                     }
                  return array;
              }
              //遍历数组
              public static void shows(int[] array){
                   for (int i=0;i<array.length;i++){
                       System.out.print(array[i]+" ");
                   }
                  System.out.println();
              }

    public static void main(String[] args) {
            int[] array={1,0,5,4,6,3,7,2,9,8};
            System.out.println("未排序前的数组");
            shows(array);
        System.out.println("-------------");
             array=sort(array);
        System.out.println("---------");
        System.out.println("排序后的数组");
            shows(array);
    }
}

