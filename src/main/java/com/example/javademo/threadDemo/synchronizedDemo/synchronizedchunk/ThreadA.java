package com.example.javademo.threadDemo.synchronizedDemo.synchronizedchunk;

/**
 * @author liuf
 * @create 2019-02-28 21:28
 */
public class ThreadA extends Thread{

    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.printA();
    }
}
