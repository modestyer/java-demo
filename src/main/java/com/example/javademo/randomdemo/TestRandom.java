package com.example.javademo.randomdemo;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author liuf
 * @create 2019-03-03 10:40
 */
public class TestRandom {

    public static void main(String[] args) {
// 另外，直接使用Random无法避免生成重复的数字，如果需要生成不重复的随机数序列，需要借助数组和集合类
        ArrayList list=getDiffNum(10);
        System.out.println();
        System.out.println("产生的n个不同的随机数："+list);
        getRandomCoin();
    }

    public static ArrayList<Integer> getDiffNum(int n){
        // 生成 [0-n) 个不重复的随机数
        // list 用来保存这些随机数
        ArrayList list = new ArrayList();
        Random rand = new Random();
        boolean[] bool = new boolean[n];
        int num = 0;
        for (int i = 0; i < n; i++) {
            do {
                // 如果产生的数相同继续循环
                num = rand.nextInt(n);
            } while (bool[num]);
            bool[num] = true;
            list.add(num);
        }
        return list;
    }

    public static void getRandomCoin(){
        Random random = new Random();
        while(true){
            int i = random.nextInt(2);
            System.out.println(i);
        }
    }
}
