package com.example.javademo.jdk8special.lambdademo;

/**
 * @author liuf
 * @create 2019-05-08 21:49
 */
public interface Converter<F,T> {

    T convert(F from);
}
