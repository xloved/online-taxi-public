package com.hgx.juc.c_00;

public class T003_ThreadMethod {


    static class T<object> extends Thread{
        @Override
        public void run() {
            System.out.println(this.getState());
            for(int i = 0; i < 5; i++){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }
    }
    public static void main(String[] args) {
              Thread thread = new T();
        System.out.println(thread.getState());
              thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
