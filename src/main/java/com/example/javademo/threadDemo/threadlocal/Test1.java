package com.example.javademo.threadDemo.threadlocal;

/**
 * @author liuf
 * @create 2019-03-02 17:12
 * ThreadLocal:
 * hreadLocal类主要解决的就是让每个线程绑定自己的值，
 * 可以将ThreadLocal类形象的比喻成存放数据的盒子，盒子中可以存储每个线程的私有数据。
 */
public class Test1 {
    private static ThreadLocal<String> t = new ThreadLocal<>();

    public static void main(String[] args) {
        if(t.get()==null){
            t.set("aa");
        }
        System.out.println(t.get());
        System.out.println(t.get());
    }
}
