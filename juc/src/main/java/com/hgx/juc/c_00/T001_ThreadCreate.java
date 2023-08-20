package com.hgx.juc.c_00;

import java.util.concurrent.TimeUnit;

public class T001_ThreadCreate {

    private static class T1 extends Thread{

        @Override
        public void run() {
            for(int i = 0; i < 10; i++){
                try {
                    TimeUnit.MILLISECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1");
            }
        }
    }

    public static void main(String[] args) {
        new T1().start();
        for (int i = 0; i < 10; i++){
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }
    }
}
