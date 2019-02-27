package com.example.javademo.threadDemo;

/**
 * @author liuf
 * @create 2019-02-26 21:50
 */
public class MyThread extends Thread{

    private int count = 5;

    public MyThread(){

    }

    public MyThread(String name) {
        this.setName(name);
    }

    @Override
    public void run() {
        System.out.println("我是多线程");
//        method1();
//        method2();
//        method3();
//        method4();
        method5();
    }


    /**
     * 线程安全方法测试
     */
    private void method1(){
        while (count>0){
            count--;
            System.out.println("由"+Thread.currentThread().getName()+" 计算，count="+count);
        }
    }

    /**
     * 线程中断方法测试
     */
    private void method2(){
        for (int i = 0; i < 5000000; i++) {
            System.out.println("i=" + (i + 1));
        }
    }

    /**
     * 线程中断方法测试
     */
    private void method3(){
        for (int i = 0; i < 5000000; i++) {
            if(this.isInterrupted()){
                System.out.println("已经是停止状态了!我要退出了!");
                break;
            }
            System.out.println("i=" + (i + 1));
        }
        System.out.println("看到这句话说明线程并未终止------");
    }

    /**
     * 线程中断方法测试
     */
    private void method4(){
        for (int i = 0; i < 5000000; i++) {
            if(this.isInterrupted()){
                System.out.println("已经是停止状态了!我要退出了!");
                return;
            }
            System.out.println("i=" + (i + 1));
        }
        System.out.println("看到这句话说明线程并未终止------");
    }

    /**
     * 守护线程方法测试
     */
    private void method5(){
        int i=0;
        while (true) {
            i++;
            System.out.println("i=" + (i));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 线程安全方法
     */
    private static void safeThread(){
        MyThread myThread = new MyThread("A");
        MyThread myThread1 = new MyThread("B");
        MyThread myThread2 = new MyThread("C");
        MyThread myThread3 = new MyThread("D");
        myThread.start();
        myThread1.start();
        myThread2.start();
        myThread3.start();
    }


    /**
     * 线程不安全
     */
    private static void shareThread(){
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread,"A");
        Thread thread1 = new Thread(myThread,"B");
        Thread thread2 = new Thread(myThread,"C");
        Thread thread3 = new Thread(myThread,"D");
        Thread thread4 = new Thread(myThread,"E");
        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    /**
     * 线程中断
     */
    private static void interruptThread(){
        MyThread myThread = new MyThread("A");
        myThread.start();
        try {
            Thread.sleep(2000);
            myThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 守护线程
     */
    private static void daemonThread(){
        try {
            MyThread myThread = new MyThread("B");
            myThread.setDaemon(true);
            myThread.start();
            Thread.sleep(5000);
            System.out.println("已设置守护线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
//        safeThread();
//        shareThread();
//        interruptThread();
        daemonThread();
    }

}
