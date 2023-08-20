package com.hgx.juc.c_01;

import java.lang.ref.SoftReference;

public class T02_SoftReference {

    public static void main(String[] args) {
        SoftReference<byte[]>  softReference = new SoftReference<>(new byte[1024*1024*10]);
        System.out.println(softReference.get()+"----------1");
        System.gc();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(softReference.get()+"-------------2");

        byte[] b = new byte[1024*1024*10];
        System.out.println(softReference.get()+"33333333333");
    }
}
