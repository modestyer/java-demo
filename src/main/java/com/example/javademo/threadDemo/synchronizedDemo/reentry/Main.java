package com.example.javademo.threadDemo.synchronizedDemo.reentry;

/**
 * @author liuf
 * @create 2019-02-27 21:53
 */
public class Main {
    public int i = 10;

    public synchronized void operateIMainMethod(){
        System.out.println(this);
        try {
            i--;
            System.out.println("main print i="+i);
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
