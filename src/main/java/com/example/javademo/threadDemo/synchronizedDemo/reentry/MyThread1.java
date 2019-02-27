package com.example.javademo.threadDemo.synchronizedDemo.reentry;

/**
 * @author liuf
 * @create 2019-02-27 22:19
 */
public class MyThread1 extends Thread{

    private Main main;

    public MyThread1(Main main) {
        this.main = main;
    }

    @Override
    public void run() {
        super.run();
        main.operateIMainMethod();
    }
}
