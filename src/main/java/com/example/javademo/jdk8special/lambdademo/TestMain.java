package com.example.javademo.jdk8special.lambdademo;

/**
 * @author liuf
 * @create 2019-05-08 21:50
 */
public class TestMain {

    public static void main(String[] args) {
        Converter<String,Integer> converter = (form) -> Integer.valueOf(form);

        Integer integer = converter.convert("123");

        System.out.println(integer.getClass());
    }
}
