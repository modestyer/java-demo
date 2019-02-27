package com.example.javademo.threadDemo.synchronizedDemo.dirtyread;

/**
 * @author liuf
 * @create 2019-02-27 21:21
 */
public class Test {

    public static void main(String[] args) {
        PublicVar publicVar = new PublicVar();

        ThreadA threadA = new ThreadA(publicVar);
        threadA.start();
        try {
            Thread.sleep(200);
            publicVar.getValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
