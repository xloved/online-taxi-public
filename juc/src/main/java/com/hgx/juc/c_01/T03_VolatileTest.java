package com.hgx.juc.c_01;

import java.util.concurrent.TimeUnit;

public class T03_VolatileTest {
         volatile boolean running = true;
          void m(){
              System.out.println("m start......");
              while(running){
              }
              System.out.println("m end!");
          }
    public static void main(String[] args) {
            T03_VolatileTest t = new T03_VolatileTest();

            new Thread(t::m,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
           t.running = false;
    }
}
