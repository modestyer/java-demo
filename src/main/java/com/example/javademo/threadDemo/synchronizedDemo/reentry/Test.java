package com.example.javademo.threadDemo.synchronizedDemo.reentry;

/**
 * @author liuf
 * @create 2019-02-27 21:45
 */
public class Test {

    public static void main(String[] args) {

        //验证子父类是否是同一把锁，传入同一个对象即可 存在疑问？
        Sub sub = new Sub();

        MyThread myThread = new MyThread(sub);
        myThread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MyThread1 myThread1 = new MyThread1(sub);
        myThread1.start();
    }
}
