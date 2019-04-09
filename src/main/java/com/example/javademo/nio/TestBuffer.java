package com.example.javademo.nio;


import java.nio.ByteBuffer;

/**
 * @author liuf
 * @create 2019-04-06 15:46
 *
/*
 * 一、缓冲区（Buffer）：在 Java NIO 中负责数据的存取。缓冲区就是数组。用于存储不同数据类型的数据
 *
 * 根据数据类型不同（boolean 除外），提供了相应类型的缓冲区：
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 *
 * 上述缓冲区的管理方式几乎一致，通过 allocate() 获取缓冲区
 *
 * 二、缓冲区存取数据的两个核心方法：
 * put() : 存入数据到缓冲区中
 * get() : 获取缓冲区中的数据
 *
 * 三、缓冲区中的四个核心属性：
 * capacity : 容量，表示缓冲区中最大存储数据的容量。一旦声明不能改变。
 * limit : 界限，表示缓冲区中可以操作数据的大小。（limit 后数据不能进行读写）
 * position : 位置，表示缓冲区中正在操作数据的位置。
 *
 * mark : 标记，表示记录当前 position 的位置。可以通过 reset() 恢复到 mark 的位置
 *
 * 0 <= mark <= position <= limit <= capacity
 *
 * 四、直接缓冲区与非直接缓冲区：
 * 非直接缓冲区：通过 allocate() 方法分配缓冲区，将缓冲区建立在 JVM 的内存中
 * 直接缓冲区：通过 allocateDirect() 方法分配直接缓冲区，将缓冲区建立在物理内存中。可以提高效率
 */
public class TestBuffer {

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    public static void test1(){
        //1、分配一个指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        System.out.println("---------allocate()--------");
        System.out.println(buf.capacity());
        System.out.println(buf.limit());
        System.out.println(buf.position());

        //2、利用put()放入数据到缓冲区中
        System.out.println("---------------put()--------------");
        String string = "abcde";
        buf.put(string.getBytes());
        System.out.println(buf.capacity());
        System.out.println(buf.limit());
        System.out.println(buf.position());

        //3、切换读取数据模式
        System.out.println("-----------flip()--------");
        buf.flip();
        System.out.println(buf.capacity());
        System.out.println(buf.limit());
        System.out.println(buf.position());

        //4、利用get()读取缓冲区中的数据
        byte[] bytes = new byte[buf.limit()];
        buf.get(bytes);
        System.out.println(new String(bytes,0,buf.limit()));

        System.out.println("-----------get()--------");
        System.out.println(buf.capacity());
        System.out.println(buf.limit());
        System.out.println(buf.position());

        //5、rewind() : 可重复读
        buf.rewind();
        System.out.println("-----------rewind()--------");
        System.out.println(buf.capacity());
        System.out.println(buf.limit());
        System.out.println(buf.position());

        //6、clear()：清空缓冲区，但是缓冲区中的数据依然存在，但是处于被"被遗忘状态"
        System.out.println(buf.capacity());
        System.out.println(buf.limit());
        System.out.println(buf.position());
    }

    public static void test2(){
        String str = "abcde";
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(str.getBytes());
        buffer.flip();
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes,0,2);
        System.out.println(new String(bytes,0,2));
        System.out.println(buffer.position());

        //mark()：表记
        buffer.mark();

        buffer.get(bytes,2,2);
        System.out.println(new String(bytes,2,2));
        System.out.println(buffer.position());

        //reset()：恢复到mark的位置
        buffer.reset();
        System.out.println(buffer.position());

        //判断缓冲区是否还有可操作的数据，以position为判断依据
        if(buffer.hasRemaining()){
            //输出缓冲区可供操作的数据的个数
            System.out.println(buffer.remaining());
        }
    }

    public static void test3(){
        //分配直接缓冲区
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        //判断是否是直接缓冲区
        System.out.println(buffer.isDirect());
    }
}
