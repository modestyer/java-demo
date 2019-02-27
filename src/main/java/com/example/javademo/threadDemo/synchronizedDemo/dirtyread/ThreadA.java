package com.example.javademo.threadDemo.synchronizedDemo.dirtyread;

/**
 * @author liuf
 * @create 2019-02-27 21:19
 */
public class ThreadA extends Thread{

    private PublicVar publicVar;

    public ThreadA(PublicVar publicVar) {
        this.publicVar = publicVar;
    }

    @Override
    public void run() {
        super.run();
        publicVar.setValue("B","BB");
    }
}
