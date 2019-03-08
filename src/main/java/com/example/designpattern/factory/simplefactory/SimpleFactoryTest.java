package com.example.designpattern.factory.simplefactory;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/3/7
 **/
public class SimpleFactoryTest {

    public static void main(String[] args) {
        Car car = CarFactory.createCar("SUV");
        car.run();

        Car car1 = CarFactory.createCar("small");
        car1.run();
    }
}
