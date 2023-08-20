/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MyQueue
 * Author:   旭哥
 * Date:     2019/3/17 20:29
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xu.test6;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 旭哥
 * @create 2019/3/17
 * @since 1.0.0
 * 单端队列
 */
public class MyQueue {

    private Object[] array;
    private int maxSize;
    private int rera;
    private int front;
    private int intems;

    public MyQueue(int s){

          maxSize=s;
          array=new Object[maxSize];
          front=0;
          rera=-1;
          intems=0;

    }

    public void insert(int value){

           if(isFull()){
               System.out.println("队列已满");
           }else{
                if(rera == maxSize-1){
                   rera=-1;
                }
                array[++rera]=value;
                intems++;
           }

    }

    public Object remove() {
        Object removeValue = null;
        if (!isEmpty()) {
            removeValue = array[front];
            array[front] = null;
            front++;
            if (front == maxSize) {
                front = 0;
            }
            intems--;
            return removeValue;
        }
         return removeValue;
    }

    public Object peekFront(){
        return array[front];
    }

    public boolean isFull(){
        return (intems==maxSize);
    }


    public boolean isEmpty(){
        return (intems==0);
    }
    public int getSize(){
        return  intems;
    }
}

