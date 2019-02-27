package com.example.javademo.threadDemo;

import java.util.concurrent.Callable;

/**
 * @author liuf
 * @create 2019-02-26 21:56
 */
public class MyCallable implements Callable {

    @Override
    public String call() throws Exception {
        System.out.println("MyCallable");
        return "MyCallable运行成功";
    }
}
