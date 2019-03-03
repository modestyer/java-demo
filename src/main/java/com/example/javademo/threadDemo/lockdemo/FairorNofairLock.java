package com.example.javademo.threadDemo.lockdemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuf
 * @create 2019-03-03 15:48
 * lock分为公平所和非公平所
 * new ReentrantLock(boolean) true为公平锁，false为非公平锁
 * 公平锁表示线程获取锁的顺序是按照线程加锁的顺序来分配的，即先来先得的FIFO先进先出顺序。
 * 而非公平锁就是一种获取锁的抢占机制，是随机获取锁的，和公平锁不一样的就是先来的不一定先的到锁，
 * 这样可能造成某些线程一直拿不到锁，结果也就是不公平的了。
 */
public class FairorNofairLock {

    public static void main(String[] args) {

        ServiceLock serviceLock = new ServiceLock(true);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"运行了");
                serviceLock.serviceMethond();
            }
        };

        Thread[] array = new Thread[10];
        for (int i = 0; i < 10; i++) {
            array[i] = new Thread(runnable);
        }

        for (int i = 0; i < array.length; i++) {
            array[i].start();
        }


    }
}

class ServiceLock{

    private Lock lock;

    public ServiceLock(boolean flag) {
        this.lock = new ReentrantLock(flag);
    }

   public void serviceMethond(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"获取锁");
        }finally {
            lock.unlock();
        }
   }
}