package com.example.javademo.threadDemo.synchronizedDemo.synchronizedchunk;

/**
 * @author liuf
 * @create 2019-02-28 21:28
 */
public class ThreadC extends Thread{

    private Service service;

    public ThreadC(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.printC();
    }
}
