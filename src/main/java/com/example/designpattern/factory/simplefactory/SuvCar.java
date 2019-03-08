package com.example.designpattern.factory.simplefactory;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/3/7
 **/
public class SuvCar extends Car{


    public SuvCar(String color,int tyre) {
        super(color,tyre);
    }

    @Override
    public void run() {
        System.out.println(super.getColor()+"颜色的SUV车"+"用"+super.getTyre()+"个轮子"+"再跑");
    }
}
