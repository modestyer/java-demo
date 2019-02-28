package com.example.javademo.threadDemo.volatiledemo;

/**
 * @author liuf
 * @create 2019-02-28 21:58
 */
public class Run {

    public static void main(String[] args) throws InterruptedException {
        RunThread runThread = new RunThread();
        runThread.start();
        Thread.sleep(1000);
        runThread.setRunning(false);
        System.out.println("已修改为false");
    }
}
