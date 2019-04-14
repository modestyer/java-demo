package com.example.javademo.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author liuf
 * @create 2019-04-08 22:28
 * 一、通道（Channel）：用于源节点与目标节点的连接。在 Java NIO 中负责缓冲区中数据的传输。Channel 本身不存储数据，因此需要配合缓冲区进行传输。
 *
 * 二、通道的主要实现类
 * 	java.nio.channels.Channel 接口：
 * 		|--FileChannel
 * 		|--SocketChannel
 * 		|--ServerSocketChannel
 * 		|--DatagramChannel
 *
 * 三、获取通道
 * 1. Java 针对支持通道的类提供了 getChannel() 方法
 * 		本地 IO：
 * 		FileInputStream/FileOutputStream
 * 		RandomAccessFile
 *
 * 		网络IO：
 * 		Socket
 * 		ServerSocket
 * 		DatagramSocket
 *
 * 2. 在 JDK 1.7 中的 NIO.2 针对各个通道提供了静态方法 open()
 * 3. 在 JDK 1.7 中的 NIO.2 的 Files 工具类的 newByteChannel()
 *
 * 四、通道之间的数据传输
 * transferFrom()
 * transferTo()
 *
 * 五、分散(Scatter)与聚集(Gather)
 * 分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中
 * 聚集写入（Gathering Writes）：将多个缓冲区中的数据聚集到通道中
 *
 * 六、字符集：Charset
 * 编码：字符串 -> 字节数组
 * 解码：字节数组  -> 字符串
 *
 */
public class TestChannel {

    public static void main(String[] args) throws Exception{
//        test1();
//        test2();
//        test3();
//        test4();
        test5();
    }


    /**
     * 编码，解码
     */
    public static void test5() throws Exception{
        Charset charset = Charset.forName("UTF-8");
        //编码
        CharsetEncoder encoder = charset.newEncoder();
        //解码
        CharsetDecoder decoder = charset.newDecoder();

        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("测试编码解码");

        charBuffer.flip();

        ByteBuffer byteBuffer = encoder.encode(charBuffer);

        for(int i=0;i<byteBuffer.limit();i++){
            System.out.println(i+"========"+byteBuffer.get());
        }

        byteBuffer.flip();

        CharBuffer decode = decoder.decode(byteBuffer);
        System.out.println(decode.toString());

    }

    /**
     *
     * 分散读取，聚集写入
     */
    public static void test4() throws Exception{
        //分散读取
        FileChannel channel = FileChannel.open(Paths.get("E:/123.txt"), StandardOpenOption.READ);

        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(2048);

        ByteBuffer[] bufs = {buf1,buf2};

        channel.read(bufs);

        for(ByteBuffer buffer : bufs){
            buffer.flip();
        }

        System.out.println(new String(bufs[0].array(),0,bufs[0].limit()));
        System.out.println("----------------------");
        System.out.println(new String(bufs[1].array(),0,bufs[1].limit()));


        //聚集写入
        FileChannel fileChannel = FileChannel.open(Paths.get("E:/channel.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        fileChannel.write(bufs);

        channel.close();
        fileChannel.close();
    }

    /**
     * 通道之间的数据传输（直接缓冲区）
     * @throws Exception
     */
    public static void test3() throws Exception{
        FileChannel inChannel = FileChannel.open(Paths.get("E:/1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("E:/4.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
        FileChannel outChannel2 = FileChannel.open(Paths.get("E:/5.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
        FileChannel outChannel3 = FileChannel.open(Paths.get("E:/6.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        //输入通道写出到输出通道
        inChannel.transferTo(0,inChannel.size(),outChannel);

        //输出通道的数据来自输入通道
        outChannel2.transferFrom(inChannel,0,inChannel.size());

        //输出通道的数据来自输出通道
        outChannel2.transferTo(0,outChannel.size(),outChannel3);

        inChannel.close();
        outChannel.close();
        outChannel2.close();
    }

    /**
     *  使用直接缓冲区完成文件的复制(内存映射文件)
     * @throws Exception
     */
    public static void test2() throws Exception{
        FileChannel inChannel = FileChannel.open(Paths.get("E:/1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("E:/3.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ,StandardOpenOption.CREATE);

        //内存映射文件
        MappedByteBuffer inmap = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outmap = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //直接对缓冲区进行操作
        byte[] bytes = new byte[inmap.limit()];
        inmap.get(bytes);
        outmap.put(bytes);

        inChannel.close();
        outChannel.close();

    }


    /**
     *  1、利用通道完成文件的复制(非直接缓冲区)
     */
    public static void test1() throws Exception{
        try(FileInputStream fis = new FileInputStream("E:/1.jpg");
            FileOutputStream fos = new FileOutputStream("E:/2.jpg");
            //获取通道
            FileChannel inChannel = fis.getChannel();
            FileChannel outChannel = fos.getChannel()) {

            //创建缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            //将通道中的数据存入缓冲区中
            while (inChannel.read(buf)!=-1){
                //转换模式，从头开始读缓冲区中的数据
                buf.flip();
                //将缓冲区中的数据写入通道中
                outChannel.write(buf);
                //清空缓冲区
                buf.clear();
            }

        }





    }
}
