/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TestReach
 * Author:   旭哥
 * Date:     2019/3/3 18:15
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
 * 算法之线性查找
 */
public class TestReach {
    public static void main(String[] args) {
            int[] arr=new int[]{1,2,3,4,5,6,7,8,9};
             //目标元素
            int tar=5;
            //元素所在下标
            int index=-1;
            for (int i=0;i<arr.length;i++){
                //如果i=目标元素,则输出目标元素所在下标
                   if(arr[i]==tar){
                        index=i;
                        break;
                   }
            }
        System.out.println(index);
    }
}

