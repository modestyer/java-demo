package com.example.javademo.threadDemo.waitandnotify;

/**
 * @author liuf
 * @create 2019-03-02 16:25
 */
public class MyThreadB extends Thread{

    private Object object;

    public MyThreadB(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        synchronized (object){
            for (int i = 0; i < 10; i++) {
                MyList.add();
                if(MyList.getSize()==5){
                    object.notify();
                    System.out.println("唤醒等待");
                }
                System.out.println("添加第"+i+"个元素");
            }
        }
    }
}
