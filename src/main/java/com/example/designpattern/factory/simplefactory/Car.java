package com.example.designpattern.factory.simplefactory;

import lombok.Data;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/3/7
 **/
@Data
public abstract class Car {

    /**
     * 颜色
     **/
    private String color;

    /**
     * 轮胎
     **/
    private int tyre;

    public Car(String color, int tyre) {
        this.color = color;
        this.tyre = tyre;
    }

    public abstract void run();
}
