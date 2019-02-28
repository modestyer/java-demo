package com.example.javademo.threadDemo.volatiledemo;

import lombok.Data;

/**
 * @author liuf
 * @create 2019-02-28 21:56
 */
@Data
public class RunThread extends Thread{

    private boolean isRunning = true;
    int m;

    @Override
    public void run() {
        super.run();
        System.out.println("进入run了");
        while (isRunning){
            int a=2;
            int b=3;
            int c=a+b;
            m=c;
//            System.out.println(m);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(m);
        System.out.println("线程被停止了！");
    }
}
