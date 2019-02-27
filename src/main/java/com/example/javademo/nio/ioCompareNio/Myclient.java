package com.example.javademo.nio.ioCompareNio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @Description IO客户端
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/2/12
 **/
public class Myclient {

    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;


        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress("localhost",8686));
            Thread.sleep(10000);
            printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println("hello");
            printWriter.flush();

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("来自服务器的消息是："+bufferedReader.readLine());


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
            try {
                bufferedReader.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
