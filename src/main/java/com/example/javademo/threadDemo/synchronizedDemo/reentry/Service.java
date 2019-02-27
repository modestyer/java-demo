package com.example.javademo.threadDemo.synchronizedDemo.reentry;

/**
 * @author liuf
 * @create 2019-02-27 21:44
 * 可重入锁：自己可以再次获取自己的内部锁。比如一个线程获得了某个对象的锁，此时这个对象锁还没有释放，
 * 当其再次想要获取这个对象的锁的时候还是可以获取的，如果不可锁重入的话，就会造成死锁。
 * 验证：
 * service1()执行时持有当前对象锁，执行过程中调用service2()，service2()也会持有当前对象锁，
 * 而此时service1()还没有释放当前对象锁，所以说synchronized是可重入锁，如果不可重入，会造成死锁
 */
public class Service {

    public synchronized void service1(){
        System.out.println("service1");
        service2();

    }

    public synchronized void service2(){
        System.out.println("service2");
        service3();
    }

    public synchronized void service3(){
        System.out.println("service3");

    }
}
