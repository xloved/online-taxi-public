/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: exampl2
 * Author:   旭哥
 * Date:     2019/2/22 11:09
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xu.test1;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 旭哥
 * @create 2019/2/22
 * @since 1.0.0
 */
public class exampl2 {

    public static void main(String[] args) {

           int[] arr={1,2,3,4,5,6};
        System.out.println(arr[0]+"====");
            change(arr);
        System.out.println(arr[0]+"+++++");
    }
    public static void change(int[] arry){
           arry[0]=0;
    }
}

