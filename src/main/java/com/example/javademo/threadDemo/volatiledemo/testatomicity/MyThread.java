package com.example.javademo.threadDemo.volatiledemo.testatomicity;

/**
 * @author liuf
 * @create 2019-03-02 15:03
 */
public class MyThread extends Thread{
    private int i=0;

    @Override
    public void run() {
        super.run();
        addMethod();
    }

    public void addMethod(){
        while(i<10){
            i++;
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"线程："+i);
        }
    }
}
