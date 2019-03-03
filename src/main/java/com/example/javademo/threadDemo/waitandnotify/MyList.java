package com.example.javademo.threadDemo.waitandnotify;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuf
 * @create 2019-03-02 16:19
 */
public class MyList {

    private static List<String> list = new ArrayList<>();

    public static void add(){
        list.add("anything");
    }

    public static int getSize(){
        return list.size();
    }
}
