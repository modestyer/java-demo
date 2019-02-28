package com.example.javademo.threadDemo.volatiledemo;

/**
 * @author liuf
 * @create 2019-02-28 22:08
 */
public class MyThread extends Thread{

    private volatile static int count;

    private static void addCount(){
        for (int i = 0; i < 100; i++) {
            count=i;
        }
        System.out.println(count);
    }

    @Override
    public void run() {
        super.run();
        addCount();
    }

}
