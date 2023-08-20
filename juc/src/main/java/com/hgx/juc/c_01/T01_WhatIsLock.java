package com.hgx.juc.c_01;

public class T01_WhatIsLock {
    private static Object object = new Object();

    public static void main(String[] args) {
        Runnable runnable = () -> {
            synchronized (object) {//加上锁让执行结果变得有序
                System.out.println(Thread.currentThread().getName() + "start>>>>");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "end>>>>>");
            }
        };

        for(int i = 0; i < 4; i++){
            new Thread(runnable).start();
        }
    }

}
