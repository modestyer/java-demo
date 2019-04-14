package com.example.javademo.nio;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author liuf
 * @create 2019-04-10 21:34
 * 一、使用 NIO 完成网络通信的三个核心：
 *
 * 1. 通道（Channel）：负责连接
 *
 * 	   java.nio.channels.Channel 接口：
 * 			|--SelectableChannel
 * 				|--SocketChannel
 * 				|--ServerSocketChannel
 * 				|--DatagramChannel
 *
 * 				|--Pipe.SinkChannel
 * 				|--Pipe.SourceChannel
 *
 * 2. 缓冲区（Buffer）：负责数据的存取
 *
 * 3. 选择器（Selector）：是 SelectableChannel 的多路复用器。用于监控 SelectableChannel 的 IO 状况
 *
 */
public class TestBlokingNio {

    /**
     * 服务端
     * @throws Exception
     */
    @Test
    public void server() throws Exception{
        //1、获取连接
        ServerSocketChannel channel = ServerSocketChannel.open();

        FileChannel fileChannel = FileChannel.open(Paths.get("E:/8.jpg"),StandardOpenOption.WRITE,StandardOpenOption.CREATE);

        //2、绑定端口
        channel.bind(new InetSocketAddress(5555));

        SocketChannel acceptChannel = channel.accept();

        //3、创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (acceptChannel.read(byteBuffer)!=-1){
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        //关闭连接
        acceptChannel.close();
        fileChannel.close();
        channel.close();
    }


    @Test
    public void client() throws Exception{
        //1、建立通道
        SocketChannel socketChannel = SocketChannel.open();

        //2、建立连接
        socketChannel.connect(new InetSocketAddress("127.0.0.1",5555));

        FileChannel fileChannel = FileChannel.open(Paths.get("E:/1.jpg"),StandardOpenOption.READ);

        //3、创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //客户端读取本地数据发送到服务端
        while(fileChannel.read(buffer)!=-1){
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }

        //关闭连接
        fileChannel.close();
        socketChannel.close();
    }

}
