package com.example.designpattern.factory.simplefactory;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/3/7
 **/
public class CarFactory {

    public static Car createCar(String carName){
        if(carName.equals("SUV")){
            return new SuvCar("红色",4);
        }else if(carName.equals("small")){
            return new SmallCar("黑色",3);
        }else{
            return null;
        }
    }
}
