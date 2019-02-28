package com.example.javademo.threadDemo.volatiledemo;

/**
 * @author liuf
 * @create 2019-02-28 22:11
 */
public class MyThreadRun {

    public static void main(String[] args) {
        MyThread[] myThreads = new MyThread[100];
        for (int i = 0; i < 100; i++) {
            myThreads[i] = new MyThread();
        }

        for (int i = 0; i < myThreads.length; i++) {
            myThreads[i].start();
        }

    }

}
