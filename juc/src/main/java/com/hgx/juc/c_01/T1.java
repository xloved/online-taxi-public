package com.hgx.juc.c_01;

public class T1 {
      private int count = 10;
      private  Object object = new Object();

      public void m(){
          synchronized (this) {
              count--;
              System.out.println(Thread.currentThread().getName() + "count=" + count);
          }
      }

    public static void main(String[] args) {
           T1 t1 = new T1();
           t1.m();

    }

}
