package com.example.javademo.jdk8special.lambdademo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author liuf
 * @create 2019-05-08 21:17
 * lambda表达式排列字符串
 *
 * 概念
 * 1、函数式接口：是指仅仅只包含一个抽象方法,但是可以有多个非抽象方法(也就是上面提到的默认方法)的接口。
 *               像这样的接口，可以被隐式转换为lambda表达式。java.lang.Runnable 与 java.util.concurrent.Callable 是函数式接口最典型的两个例子。
 *               Java 8增加了一种特殊的注解@FunctionalInterface,但是这个注解通常不是必须的(某些情况建议使用)，只要接口只包含一个抽象方法，
 *               虚拟机会自动判断该接口为函数式接口。一般建议在接口上使用@FunctionalInterface 注解进行声明，这样的话，
 *               编译器如果发现你标注了这个注解的接口有多于一个抽象方法的时候会报错的
 */
public class StringSort {
    public static void main(String[] args) {
        //旧版排序方式
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        /*Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });*/

        //jdk8 lambda表达式排序
        //第一种方式
        Collections.sort(names,(String a,String b) -> {
            return a.compareTo(b);
        });
        System.out.println(names);

        //第二种方式 如果方法体中只有一行代码，可省略大括号及return关键字
        Collections.sort(names,(String a,String b) -> a.compareTo(b));
        System.out.println(names);

        //第三种方式
        names.sort((a,b) -> a.compareTo(b));
        System.out.println(names);

    }




}
