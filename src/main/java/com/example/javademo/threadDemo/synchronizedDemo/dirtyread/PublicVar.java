package com.example.javademo.threadDemo.synchronizedDemo.dirtyread;

/**
 * @author liuf
 * @create 2019-02-27 21:16
 */
public class PublicVar {
    private String userName="A";

    private String password="AA";

    public synchronized void setValue(String userName,String password){
        try {
            this.userName=userName;
            Thread.sleep(5000);
            this.password=password;

            System.out.println("setValue method thread name="
                    + Thread.currentThread().getName() + " username="
                    + userName + " password=" + password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /*public void setValue(String userName,String password){
        synchronized (this){
            try {
                this.userName=userName;
                Thread.sleep(5000);
                this.password=password;

                System.out.println("setValue method thread name="
                        + Thread.currentThread().getName() + " username="
                        + userName + " password=" + password);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }*/

    /*public void setValue(String userName,String password){
        synchronized (Object.class){
            try {
                this.userName=userName;
                Thread.sleep(5000);
                this.password=password;

                System.out.println("setValue method thread name="
                        + Thread.currentThread().getName() + " username="
                        + userName + " password=" + password);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }*/

    /**
     * 线程不安全的方法 脏读
     */
    /*public void getValue(){
        System.out.println("getValue method thread name="
                + Thread.currentThread().getName() + " username=" + userName
                + " password=" + password);
    }*/

    /**
     * 线程安全的方法
     * 原因：因为synchronized关键字持有的是对象锁，
     * 当一个线程先持有该对象的锁时，其他线程进入等待获取锁的状态(前提是同一对象的锁)
     */
    public synchronized void getValue(){
        System.out.println("getValue method thread name="
                + Thread.currentThread().getName() + " username=" + userName
                + " password=" + password);
    }
}
