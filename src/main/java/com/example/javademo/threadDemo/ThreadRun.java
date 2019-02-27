package com.example.javademo.threadDemo;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author liuf
 * @create 2019-02-26 21:51
 */
public class ThreadRun {

    public static void main(String[] args) {
        MyThread myThread = new MyThread("A");
        myThread.start();

        System.out.println("thread 运行结束");

        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("runnable 运行结束");


        MyCallable myCallable = new MyCallable();
        //接收返回值，在接收到返回值之前线程阻塞
        FutureTask<String> result = new FutureTask<>(myCallable);
        new Thread(result).start();

        try {
            String str = result.get();
            System.out.println(str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
