package com.example.javademo.threadDemo.threadlocal;

/**
 * @author liuf
 * @create 2019-03-02 17:20
 * TODO:验证线程变量间的隔离性
 */
public class Test3 {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
//            System.out.println(Thread.currentThread().getName()+"："+Tool2.object);
            System.out.println(Thread.currentThread().getName()+"："+Tool.t.get());
            Thread.sleep(100);
        }
        Tool.t.set("bb");
        Thread.sleep(5000);
        ThreadA threadA = new ThreadA();
        threadA.start();
    }

    static public class Tool2{
        public static Object object = System.currentTimeMillis();
    }

    static public class Tool{
        public static ThreadLocalExt t = new ThreadLocalExt();
    }

    static public class ThreadLocalExt extends ThreadLocal{
        @Override
        protected Object initialValue() {
            return System.currentTimeMillis();
        }
    }

    static public class ThreadA extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
//                System.out.println(Thread.currentThread().getName()+"："+Tool.t.get());
                System.out.println(Thread.currentThread().getName()+"："+Tool2.object);
            }
        }
    }
}
