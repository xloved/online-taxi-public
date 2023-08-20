/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Test1
 * Author:   旭哥
 * Date:     2019/3/28 11:07
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xu.iotest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 旭哥
 * @create 2019/3/28
 * @since 1.0.0
 */
public class Test1 {
    public static void main(String[] args) {
                String path="F:"+ File.separator+"test.txt";
                readFile(path);
                readFIle2(path);
    }

        public  static void readFile(String path){
            FileInputStream inputStream = null;
            try {
                inputStream=new FileInputStream(path);
                System.out.println("单个字节开始============");
                int ch=0;
                while((ch=inputStream.read())!=-1){
                    System.out.print((char)ch);
                }
                System.out.println();
                System.out.println("单个字节结束=====================");
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(inputStream!=null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        public static void readFIle2(String path){
              FileInputStream inputStream=null;
            try {
                inputStream=new FileInputStream(path);
                System.out.println("循环读取开始======================");
                int num=1024;
                byte buffer[]=new byte[1024];
                while((inputStream.read(buffer,0,num)!=-1 && (num>0))){
                    System.out.print(new String(buffer));
                }
                System.out.println();
                System.out.println("循环结束=======================");
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                 if (inputStream!=null){
                     try {
                         inputStream.close();
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }
            }
        }
}

