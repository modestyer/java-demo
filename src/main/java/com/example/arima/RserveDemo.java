package com.example.arima;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/3/29
 **/
public class RserveDemo {

    public static void main(String[] args) throws Exception{
        /*RConnection connection = null;
        System.out.println("平均值");
        try {
            connection = new RConnection();
            String vetor = "c(1,2,3,4)";
            connection.eval("meanVal<-mean(" + vetor +")");
            double mean = connection.eval("meanVal").asDouble();
            String clipboard = "clipboard";
            String ML = "ML";
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("X=read.table("+clipboard+",header=F)");
            stringBuffer.append("airts <- ts(X,start=1,frequency=24)");
            stringBuffer.append("plot.ts(airts)");
            stringBuffer.append("airdiff <- diff(airts, differences=1)");
            stringBuffer.append("airdiff <- diff(airts, differences=2)");
            stringBuffer.append("plot.ts(airdiff)");
            stringBuffer.append("acf(airdiff, lag.max=30)");
            stringBuffer.append("acf(airdiff, lag.max=30,plot=FALSE)");
            stringBuffer.append("pacf(airdiff, lag.max=30)");
            stringBuffer.append("pacf(airdiff, lag.max=30,plot=FALSE)");
            stringBuffer.append("auto.arima(airts,trace=T)");
            stringBuffer.append("airarima1 <- arima(airts,order=c(2,0,3),seasonal=list(order=c(1,0,2),period=24),method="+ML+")");
            stringBuffer.append("airforecast <- forecast(airarima1,h=240,level=c(99.5))");
            stringBuffer.append("plot(airforecast)");
            connection.eval(stringBuffer.toString());
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

        *//*connection = new RConnection();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("X=read.table(\"clipboard\",header=F)");
        stringBuffer.append("airts <- ts(X,start=1,frequency=24)");
        stringBuffer.append("plot.ts(airts)");
        stringBuffer.append("airdiff <- diff(airts, differences=1)");
        stringBuffer.append("airdiff <- diff(airts, differences=2)");
        stringBuffer.append("plot.ts(airdiff)");
        stringBuffer.append("acf(airdiff, lag.max=30)");
        stringBuffer.append("acf(airdiff, lag.max=30,plot=FALSE)");
        stringBuffer.append("pacf(airdiff, lag.max=30)");
        stringBuffer.append("pacf(airdiff, lag.max=30,plot=FALSE)");
        stringBuffer.append("auto.arima(airts,trace=T)");
        stringBuffer.append("airarima1 <- arima(airts,order=c(2,0,3),seasonal=list(order=c(1,0,2),period=24),method=\"ML\")");
        stringBuffer.append("airforecast <- forecast(airarima1,h=240,level=c(99.5))");
        stringBuffer.append("plot(airforecast)");
        connection.eval(stringBuffer.toString());*/

        test1();
    }


    public static void test1() throws Exception{
        RConnection connection = new RConnection();
        REXP x =connection.eval("R.version.string");
        System.out.println(x.asString());
        String clipboard = "clipboard";
        String ML = "ML";
//        connection.eval("library(forecast)");
        connection.eval("X=read.table('clipboard',header=F)");
        connection.eval("airts <- ts(X,start=1,frequency=24)");
        connection.eval("plot.ts(airts)");
        connection.eval("airdiff <- diff(airts, differences=1)");
        connection.eval("airdiff <- diff(airts, differences=2)");
        connection.eval("plot.ts(airdiff)");
        connection.eval("acf(airdiff, lag.max=30)");
        connection.eval("acf(airdiff, lag.max=30,plot=FALSE)");
        connection.eval("pacf(airdiff, lag.max=30)");
        connection.eval("pacf(airdiff, lag.max=30,plot=FALSE)");
        connection.eval("auto.arima(airts,trace=T)");
        connection.eval("airarima1 <- arima(airts,order=c(2,0,3),seasonal=list(order=c(1,0,2),period=24),method='ML')");
        connection.eval("airforecast <- forecast(airarima1,h=240,level=c(99.5))");
        connection.eval("jpeg('f://test-9.jpg')");
        connection.eval("plot(airforecast)");
        connection.eval("dev.off()");

    }
}
