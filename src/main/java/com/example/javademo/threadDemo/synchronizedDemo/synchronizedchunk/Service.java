package com.example.javademo.threadDemo.synchronizedDemo.synchronizedchunk;

/**
 * @author liuf
 * @create 2019-02-28 21:25
 */
public class Service {

    public void printA(){
        synchronized (Service.class){
            try {
                System.out.println("线程名称为："+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"进入printA");
                Thread.sleep(3000);
                System.out.println("线程名称为："+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"离开printA");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void printB(){
        System.out.println("线程名称为："+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"进入printB");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程名称为："+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"离开printB");

    }

    public synchronized static void printC(){
        System.out.println("线程名称为："+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"进入printC");
        System.out.println("线程名称为："+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"离开printC");

    }
}
