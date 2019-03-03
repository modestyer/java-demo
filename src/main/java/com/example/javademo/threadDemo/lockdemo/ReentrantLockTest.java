package com.example.javademo.threadDemo.lockdemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuf
 * @create 2019-03-02 18:15
 * ReentrantLock（排他锁）具有完全互斥排他的效果 同一时刻只允许一个线程访问
 */
public class ReentrantLockTest {

    public static void main(String[] args) {
        Service service = new Service();
        MyThreadA myThreadA = new MyThreadA(service);
        MyThreadB myThreadB = new MyThreadB(service);
        MyThreadC myThreadC = new MyThreadC(service);
        MyThreadD myThreadD = new MyThreadD(service);
        MyThreadE myThreadE = new MyThreadE(service);
        MyThreadF myThreadF = new MyThreadF(service);

        myThreadA.start();
        myThreadB.start();
        myThreadC.start();
        myThreadD.start();
        myThreadE.start();
        myThreadF.start();


    }
}

class Service {
    private Lock lock = new ReentrantLock();

    public void addMethode() {
        lock.lock();
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "：" + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lock.unlock();
    }
}

class MyThreadA extends Thread {
    private Service service;

    public MyThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.addMethode();
    }
}

class MyThreadB extends Thread {
    private Service service;

    public MyThreadB(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.addMethode();
    }
}

class MyThreadC extends Thread {
    private Service service;

    public MyThreadC(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.addMethode();
    }
}

class MyThreadD extends Thread {
    private Service service;

    public MyThreadD(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.addMethode();
    }
}

class MyThreadE extends Thread {
    private Service service;

    public MyThreadE(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.addMethode();
    }
}

class MyThreadF extends Thread {
    private Service service;

    public MyThreadF(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.addMethode();
    }
}