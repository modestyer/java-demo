package com.example.javademo.threadDemo.waitandnotify;

/**
 * @author liuf
 * @create 2019-03-02 16:28
 * wait/notify方法在使用前要先获得锁，此，wait() 与  notify/notifyAll() 经常与synchronized搭配使用，
 * 即在synchronized修饰的同步代码块或方法里面调用wait() 与  notify/notifyAll()方法。
 */
public class Run {
    public static void main(String[] args) {
        Object object = new Object();
        MyThreadA myThreadA = new MyThreadA(object);
        myThreadA.start();

        MyThreadB myThreadB = new MyThreadB(object);
        myThreadB.start();
    }
}
