package com.example.javademo.threadDemo.synchronizedDemo.synchronizedchunk;

/**
 * @author liuf
 * @create 2019-02-28 21:30
 */
public class Run {

    public static void main(String[] args) {
        Service service = new Service();

        ThreadA threadA = new ThreadA(service);
        threadA.setName("a");
        threadA.start();

        ThreadB threadB = new ThreadB(service);
        threadB.setName("b");
        threadB.start();

        ThreadC threadC = new ThreadC(service);
        threadC.setName("c");
        threadC.start();
    }
}
