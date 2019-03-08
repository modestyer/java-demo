package com.example.designpattern.factory.simplefactory;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/3/7
 **/
public class SmallCar extends Car{


    public SmallCar(String color, int tyre) {
        super(color, tyre);
    }

    @Override
    public void run() {
        System.out.println(super.getColor()+"颜色的小轿车"+"用"+super.getTyre()+"个轮子"+"再跑");
    }
}
