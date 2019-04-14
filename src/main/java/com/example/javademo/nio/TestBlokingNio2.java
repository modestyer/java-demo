package com.example.javademo.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author liuf
 * @create 2019-04-10 22:07
 */
public class TestBlokingNio2 {

    @Test
    public void client() throws IOException {
        //1、建立通道
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.connect(new InetSocketAddress("127.0.0.1",5555));

//        2、创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        FileChannel inChannel = FileChannel.open(Paths.get("E:/1.jpg"),StandardOpenOption.READ);

        //3、读取本地文件发送到服务端
        while (inChannel.read(buffer)!=-1){
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }

        //4、关闭连接
        socketChannel.shutdownOutput();

        //5、得到服务端的反馈
        int len=0;
        while ((len = socketChannel.read(buffer))!=-1){
            buffer.flip();
            System.out.println(new String(buffer.array(),0,len));
        }

        inChannel.close();
        socketChannel.close();
    }

    @Test
    public void server() throws IOException {
//        1、建立通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(5555));

//        2、创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

//        3、得到客户端
        SocketChannel accept = serverSocketChannel.accept();

        FileChannel fileChannel = FileChannel.open(Paths.get("E:/9.jpg"),StandardOpenOption.WRITE,StandardOpenOption.CREATE);

        //4、读取客户端数据写入本地
        while (accept.read(buffer)!=-1){
            buffer.flip();
            fileChannel.write(buffer);
            buffer.clear();
        }

        //5、向客户端反馈结果
        buffer.put("服务端接收成功！".getBytes());
        buffer.flip();
        accept.write(buffer);

        fileChannel.close();
        accept.close();
        serverSocketChannel.close();
    }
}
