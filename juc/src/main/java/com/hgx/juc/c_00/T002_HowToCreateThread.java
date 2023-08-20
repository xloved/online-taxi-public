package com.hgx.juc.c_00;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class T002_HowToCreateThread {
    public static void main(String[] args) {
            new myThread().start();
            new Thread(new mYRun()).start();
            new Thread(()-> {
                    System.out.println("hello Lambda");
        }).start();
            //创建 Callable 接口实现类的对象
            myCall call = new myCall();
            //将此 Callable 接口实现类的对象作为传递到 FutureTask 构造器中，创建 FutureTask 的对象
            FutureTask future = new FutureTask(call);
            // 将 FutureTask 的对象作为参数传递到 Thread 类的构造器中，创建 Thread 对象，并调用 start()
            new Thread(future).start();
    }

    static class myThread extends Thread{
          public void run(){
              System.out.println("hello myThread");
          }
    }

    static class mYRun implements Runnable{

        @Override
        public void run() {
            System.out.println("hello nyRun");
        }
    }
    // 需要借助 FutureTask 类，比如获取返回结果
    static class myCall implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println("hello myCall");
            return "success";
        }
    }
}
