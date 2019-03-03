package com.example.javademo.threadDemo.lockdemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuf
 * @create 2019-03-02 19:16
 * 使用condition实现顺序打印
 */
public class ConditionSeqExec {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();

        /*Thread[] aArray = new Thread[5];
        Thread[] bArray = new Thread[5];
        Thread[] cArray = new Thread[5];
        Thread[] dArray = new Thread[5];*/

        Thread1 t1 = new Thread1(orderService);
        Thread2 t2 = new Thread2(orderService);
        Thread3 t3 = new Thread3(orderService);
        Thread4 t4 = new Thread4(orderService);
        /*for (int i = 0; i < 5; i++) {
            aArray[i] = new Thread(t1);
            bArray[i] = new Thread(t2);
            cArray[i] = new Thread(t3);
            dArray[i] = new Thread(t4);


            aArray[i].start();
            aArray[i].setName("A");
            bArray[i].start();
            bArray[i].setName("B");
            cArray[i].start();
            cArray[i].setName("C");
            dArray[i].start();
            dArray[i].setName("D");
        }*/

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

}

class OrderService{
    private volatile static int nextNum = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private Condition condition4 = lock.newCondition();

    public void startMethod(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.lock();
        try {
            if (nextNum!=1){
                System.out.println(Thread.currentThread().getName()+"开始等待");
                condition1.await();
                System.out.println(Thread.currentThread().getName()+"开始唤醒");
            }
            System.out.println(Thread.currentThread().getName()+"：A");
            System.out.println(Thread.currentThread().getName()+"：B");
            System.out.println(Thread.currentThread().getName()+"：C");

            nextNum=2;
            condition2.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void awaite1(){
        lock.lock();
        try {
            if(nextNum!=2){
                System.out.println(Thread.currentThread().getName()+"开始等待");
                condition2.await();
                System.out.println(Thread.currentThread().getName()+"开始唤醒");
            }
            System.out.println(Thread.currentThread().getName()+"：A");
            System.out.println(Thread.currentThread().getName()+"：B");
            System.out.println(Thread.currentThread().getName()+"：C");

            nextNum=3;
            condition3.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void awaite2(){
        lock.lock();
        try {
            if(nextNum!=3){
                System.out.println(Thread.currentThread().getName()+"开始等待");
                condition3.await();
                System.out.println(Thread.currentThread().getName()+"开始唤醒");
            }
            System.out.println(Thread.currentThread().getName()+"：A");
            System.out.println(Thread.currentThread().getName()+"：B");
            System.out.println(Thread.currentThread().getName()+"：C");

            nextNum=4;
            condition4.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void awaite3(){
        lock.lock();
        try {
            if(nextNum!=4){
                System.out.println(Thread.currentThread().getName()+"开始等待");
                condition4.await();
                System.out.println(Thread.currentThread().getName()+"开始唤醒");
            }
            System.out.println(Thread.currentThread().getName()+"：A");
            System.out.println(Thread.currentThread().getName()+"：B");
            System.out.println(Thread.currentThread().getName()+"：C");
            nextNum=1;
            condition1.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}


class Thread1 extends Thread{
    private OrderService orderService;

    public Thread1(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void run() {
        orderService.startMethod();
    }
}

class Thread2 extends Thread{
    private OrderService orderService;

    public Thread2(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void run() {
        orderService.awaite1();
    }
}

class Thread3 extends Thread{
    private OrderService orderService;

    public Thread3(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void run() {
        orderService.awaite2();
    }
}

class Thread4 extends Thread{
    private OrderService orderService;

    public Thread4(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void run() {
        orderService.awaite3();
    }
}


