/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TestReach2
 * Author:   旭哥
 * Date:     2019/3/3 18:29
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xu.test3;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 旭哥
 * @create 2019/3/3
 * @since 1.0.0
 */
public class TestReach2 {

    public static void main(String[] args) {
                 //定义一个数组
             int[] arr=new int[]{1,2,3,4,5,6,7,8,9,0};
             //目标元素
             int tar=9;
             //开始位置
             int a=0;
             //结束位置
             int b=arr.length-1;
             //中间位置
             int c=(a+b)/2;
             //目标位置
             int index=-1;
             //循环查找
             while(true){
                   //判断中间元素是不是要查找的元素
                  if(arr[c]==tar){

                        index=c;
                        break;
                  }else{
                      //判断中间元素是不是比目标元素大
                      if(arr[c]>tar){
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
        System.out.println("index:"+index);
    }
}

