package com.example.javademo.threadDemo.lockdemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuf
 * @create 2019-03-02 18:34
 */
public class UseSingleConditionWaitNotify {

    public static void main(String[] args) throws InterruptedException {
        ServiceWaiteAndNotify service = new ServiceWaiteAndNotify();

        MyThread myThread = new MyThread(service);

        myThread.start();

        Thread.sleep(3000);

        System.out.println("开始唤醒");

        service.notifyMethond();
    }
}


class ServiceWaiteAndNotify{
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void waite(){
        lock.lock();

        try {
            System.out.println("waite开始时间："+System.currentTimeMillis());
            condition.await();
            System.out.println("waite结束时间："+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void notifyMethond(){
        lock.lock();
        try {
            System.out.println("signal时间为" + System.currentTimeMillis());
            condition.signal();
            Thread.sleep(3000);
            System.out.println("这是condition.signal()方法之后的语句");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}

class MyThread extends Thread{
    ServiceWaiteAndNotify service = new ServiceWaiteAndNotify();

    public MyThread(ServiceWaiteAndNotify service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.waite();
    }
}