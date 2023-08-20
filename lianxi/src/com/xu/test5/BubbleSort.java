/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BubbleSort
 * Author:   旭哥
 * Date:     2019/3/8 11:04
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
 * @create 2019/3/8
 * @since 1.0.0
 * 冒泡排序
 */
public class BubbleSort {

                public static int[] sort(int[] arr){
                           for(int i=1;i<arr.length;i++){
                               //做标记使用，若是没动排序，则说明排序已经完成
                                boolean flag=true;
                                //对无序数组进行排序，在逐渐的缩小范围
                                for(int j=0;j<arr.length-1;j++){
                                     if(arr[j]>arr[j+1]){
                                         int temp=arr[j];
                                         arr[j]=arr[j+1];
                                         arr[j+1]=temp;
                                         flag=false;
                                     }
                                }
                                if(flag){
                                    break;
                                }
                               System.out.print("第"+i+"排序:");
                                showarr(arr);
                           }
                           return arr;
                }
                  //遍历数组
                public static  void  showarr(int[] arr){
                        for (int i=0;i<arr.length;i++){
                            System.out.print(arr[i]+"");
                        }
                    System.out.println();
                }

    public static void main(String[] args) {
             int[] arr={5,4,2,3,8,6,7,9,1};
              /* showarr(arr);
        System.out.println("*************");*/
               sort(arr);
        System.out.println("****************");
               //showarr(arr);
    }
}

