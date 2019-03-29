package com.example.arima;

import org.rosuda.REngine.Rserve.RConnection;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/3/29
 **/
public class RserveDemo {

    public static void main(String[] args) {
        RConnection connection = null;
        System.out.println("平均值");
        try {
            connection = new RConnection();
            String vetor = "c(1,2,3,4)";
            connection.eval("meanVal<-mean(" + vetor +")");
            double mean = connection.eval("meanVal").asDouble();
            System.out.println("the mean of given vector is=" + mean);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("执行脚本");
        try {
            connection.eval("source('F:/myAdd.R')");// 此处路径也可以这样写D:\\\\myAdd.R
            int num1 = 20;
            int num2 = 10;
            int sum = connection.eval("myAdd(" + num1 + "," + num2 + ")").asInteger();
            System.out.println("the sum=" + sum);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        connection.close();
    }

}
