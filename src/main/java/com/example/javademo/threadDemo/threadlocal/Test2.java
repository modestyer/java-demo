package com.example.javademo.threadDemo.threadlocal;

/**
 * @author liuf
 * @create 2019-03-02 17:14
 */
public class Test2 {
    private static ThreadLoaclExt threadLocal = new ThreadLoaclExt();

    public static void main(String[] args) {
        if(threadLocal.get()==null){
            System.out.println("没有放值");
            threadLocal.set("aa");
        }
        System.out.println(threadLocal.get());
        System.out.println(threadLocal.get());
    }

    static public class ThreadLoaclExt extends ThreadLocal{
        @Override
        protected Object initialValue() {
            return "我是默认值 第一次get不再为null";
        }
    }
}
