package com.example.javademo.threadDemo.waitandnotify;

/**
 * @author liuf
 * @create 2019-03-02 16:21
 */
public class MyThreadA extends Thread{

    private Object object;

    public MyThreadA(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        synchronized (object){
            if(MyList.getSize()!=5){
                try {
                    System.out.println(System.currentTimeMillis()+"开始等待");
                    object.wait();
                    System.out.println(System.currentTimeMillis()+"等待结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
