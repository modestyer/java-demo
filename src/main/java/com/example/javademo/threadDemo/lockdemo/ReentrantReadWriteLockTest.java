package com.example.javademo.threadDemo.lockdemo;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author liuf
 * @create 2019-03-03 16:06
 * ReentrantReadWriteLock 读写锁
 * 读写锁维护了两个锁，一个是读操作相关的锁也成为共享锁，一个是写操作相关的锁 也称为排他锁。
 * 通过分离读锁和写锁，其并发性比一般排他锁有了很大提升。
 *
 * 多个读锁之间不互斥，读锁与写锁互斥，写锁与写锁互斥（只要出现写操作的过程就是互斥的。）。
 * 在没有线程Thread进行写入操作时，进行读取操作的多个Thread都可以获取读锁，
 * 而进行写入操作的Thread只有在获取写锁后才能进行写入操作。即多个Thread可以同时进行读取操作，
 * 但是同一时刻只允许一个Thread进行写入操作。

 */
public class ReentrantReadWriteLockTest {

    public static void main(String[] args) {
        ReadAndWriteService service = new ReadAndWriteService();
        Thread thread = new Thread(){
            @Override
            public void run() {
                service.read();
            }
        };

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                service.write();
            }
        };


        Thread[] array1 = new Thread[3];
        for (int i = 0; i < array1.length; i++) {
            array1[i] = new Thread(thread);
        }

        Thread[] array2 = new Thread[3];
        for (int i = 0; i < array2.length; i++) {
            array2[i] = new Thread(thread1);
        }

        for (int i = 0; i < array1.length; i++) {
            array1[i].start();
        }

        for (int i = 0; i < array2.length; i++) {
            array2[i].start();
        }

    }
}

class ReadAndWriteService{

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void read(){
        readWriteLock.readLock().lock();
        System.out.println(Thread.currentThread().getName()+"获取读锁："+System.currentTimeMillis());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void write(){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"获取写锁"+System.currentTimeMillis());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }
}
