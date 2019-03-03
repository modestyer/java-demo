package com.example.javademo.threadDemo.lockdemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuf
 * @create 2019-03-02 18:56
 */
public class UseMoreConditionWaitNotify {

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();

        MyThread1 t1 = new MyThread1(myService);

        t1.start();

        MyThread2 t2 = new MyThread2(myService);

        t2.start();

        Thread.sleep(3000);

        System.out.println("开始唤醒");

        myService.sinalAll_A();
    }
}


class MyService{
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();


    public void waiteA(){
        lock.lock();

        try {
            System.out.println("waiteA开始等待："+System.currentTimeMillis());
            conditionA.await();
            System.out.println("waiteA等待结束："+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void waiteB(){
        lock.lock();
        try {
            System.out.println("waiteB开始等待："+System.currentTimeMillis());
            conditionB.await();
            System.out.println("waiteB结束等待："+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void sinalAll_A(){
        lock.lock();
        try {
            conditionA.signalAll();
            Thread.sleep(3000);
            System.out.println("ConditionA唤醒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void sinallAll_B(){
        lock.lock();
        try {
            conditionB.signalAll();
            Thread.sleep(3000);
            System.out.println("ConditionB唤醒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}

class MyThread1 extends Thread{
    private MyService myService;

    public MyThread1(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.waiteA();
    }
}
class MyThread2 extends Thread{
    private MyService myService;

    public MyThread2(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.waiteB();
    }
}

