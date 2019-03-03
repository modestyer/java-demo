package com.example.javademo.threadDemo.volatiledemo.testatomicity;

/**
 * @author liuf
 * @create 2019-03-02 15:05
 */
public class Run {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        myThread.addMethod();

    }
}
