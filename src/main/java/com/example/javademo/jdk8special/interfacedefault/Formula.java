package com.example.javademo.jdk8special.interfacedefault;

/**
 * @author liuf
 * @create 2019-05-08 21:05
 * Java 8使我们能够通过使用 default 关键字向接口添加非抽象方法实现
 */
public interface Formula {

    double calculate(int a);

    default double sqrt(int a){
        return Math.sqrt(a);
    }
}
