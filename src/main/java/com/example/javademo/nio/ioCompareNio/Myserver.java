package com.example.javademo.nio.ioCompareNio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:多线程下的IO机制
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/2/12
 **/
public class Myserver {

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    private static class HandleMsg implements Runnable{
        /**
         * 创建一个客户端
         */
        Socket client;

        public HandleMsg(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            //创建字符缓存输入流
            BufferedReader bufferedReader = null;
            //创建字符写入流
            PrintWriter printWriter = null;

            try {
                //获取客户端的输入流
                bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));

                //获取客户端的输出流，true是随时刷新
                printWriter = new PrintWriter(client.getOutputStream(),true);

                String inputLine = null;

                long startTime = System.currentTimeMillis();
                while((inputLine = bufferedReader.readLine())!=null){
                    printWriter.println(inputLine);
                }
                long endTime = System.currentTimeMillis();
                System.out.println("此线程共花费："+(endTime-startTime)+"秒");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    printWriter.close();
                    bufferedReader.close();
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8686);

        Socket client = null;

        //循环监听
        while (true){
            //服务端监听客户端请求
            client=serverSocket.accept();
            System.out.println(client.getRemoteSocketAddress()+"地址的客户端连接成功!");
            //将该客户端请求通过线程池放入HandlMsg线程中进行处理
            executorService.submit(new HandleMsg(client));
        }
    }
}
