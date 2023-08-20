/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ChoiceSort
 * Author:   旭哥
 * Date:     2019/3/12 10:43
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
 * 选择排序
 */
public class ChoiceSort {

         public static int[] sort(int[] array){
                  //总共要经过array.length-1轮比较
                for(int i=0;i<array.length-1;i++){
                       int min=i;
                       //每轮需要比较的次数
                     for(int j=i+1; j<array.length;j++){
                           if(array[j]<array[min]){
                               //记录目前能找到的最小元素的下标
                               min=j;
                           }
                     }
                     //将找到的最小值与i所在位置的值进行交换
                     if(i!=min){
                          int temp=array[i];
                          array[i]=array[min];
                          array[min]=temp;

                     }
                    System.out.print("第"+(i+1)+"排序后的顺序");
                     shows(array);
                }
                return array;
         }
            //遍历数组
         public static void shows(int[] array){
                    for(int i=0;i< array.length;i++){
                        System.out.print(array[i]+" ");
                    }
             System.out.println();
         }

    public static void main(String[] args) {
            int[] array={1,5,6,3,4,2,8,7,9,0};
        System.out.println("未排序前的数组");
        shows(array);
        System.out.println("----------------");
        array=sort(array);
        System.out.println("----------------");
        System.out.println("排序后的数组顺序");
        shows(array);
    }
}

