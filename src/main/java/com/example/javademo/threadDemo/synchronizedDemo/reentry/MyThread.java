package com.example.javademo.threadDemo.synchronizedDemo.reentry;

/**
 * @author liuf
 * @create 2019-02-27 21:46
 */
public class MyThread extends Thread{

    private Sub sub;

    public MyThread(Sub sub) {
        this.sub=sub;
    }

    @Override
    public void run() {
        super.run();
        /*Service service = new Service();
        service.service1();*/

        System.out.println(sub);
        sub.operateISubMethod();

    }
}


