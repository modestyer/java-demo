package com.example.javademo.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

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
public class TestNonBlokingNio {

    @Test
    public void client() throws Exception{
        //开启通道
        SocketChannel socketChannel = SocketChannel.open();

        //建立连接
        socketChannel.connect(new InetSocketAddress("127.0.0.1",5555));

        //调整为非阻塞模式
        socketChannel.configureBlocking(false);

        //缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //缓冲区存入数据
        buffer.put(new Date().toString().getBytes());

        //切换读取数据模式
        buffer.flip();

        //将缓冲区写入通道，发送数据给服务端
        socketChannel.write(buffer);

        //清空缓冲区
        buffer.clear();

        //关闭连接
        socketChannel.close();
    }

    @Test
    public void client2() throws Exception{
        //开启通道
        SocketChannel socketChannel = SocketChannel.open();

        //建立连接
        socketChannel.connect(new InetSocketAddress("127.0.0.1",5555));

        //调整为非阻塞模式
        socketChannel.configureBlocking(false);

        //缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            //缓冲区存入数据
            buffer.put((new Date().toString()+"\n"+scanner.next()).getBytes());

            //切换读取数据模式
            buffer.flip();

            //将缓冲区写入通道，发送数据给服务端
            socketChannel.write(buffer);

            //清空缓冲区
            buffer.clear();
        }



        //关闭连接
        socketChannel.close();
    }

    @Test
    public void server() throws IOException {

        //开启服务端通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //绑定连接
        serverSocketChannel.bind(new InetSocketAddress(5555));

        //切换非阻塞模式
        serverSocketChannel.configureBlocking(false);

        //获取选择器
        Selector selector = Selector.open();

        //将通道注册到选择器中，并且指定监听接收事件选择键
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

        //轮询获取选择器上“准备就绪”的事件
        while (selector.select()>0){
            //获取监听事件的选择键集合
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            //循环选择键集合，
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                //判断选择键
                if(selectionKey.isAcceptable()){
                    //获取客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();

                    socketChannel.configureBlocking(false);
                    //将客户端通道注册到选择器中
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if(selectionKey.isReadable()){
                    //获取读就绪的事件，并读取客户端传来的数据
                    SocketChannel channel = (SocketChannel) selectionKey.channel();

                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len=0;
                    //注意：这里使用大于0，猜测是因为client2的套接字通道没有关闭，
                    //但此时服务端仍然从通道中读取数据，通道中此时没有数据，所以返回0
                    while ((len = channel.read(buffer))>0){
                        System.out.println(len);
                        buffer.flip();
                        System.out.println(new String(buffer.array(),0,len));
                        buffer.clear();
                    }
                }
                //取消已迭代的选择键SelectionKey
                iterator.remove();
            }
        }
    }
}
