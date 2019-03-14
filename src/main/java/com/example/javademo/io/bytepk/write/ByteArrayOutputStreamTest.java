package com.example.javademo.io.bytepk.write;

import java.io.ByteArrayOutputStream;

/**
 * @author liuf
 * @create 2019-03-14 20:31
 */
public class ByteArrayOutputStreamTest {

    /**
     *对应英文字母“abcddefghijklmnopqrsttuvwxyz”
     */
    private static final byte[] ArrayLetters = {
            0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F,
            0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7A
    };

    public static void main(String[] args) throws Exception{

        ByteArrayOutputStreamAPITest();

    }


    /**
     * ByteArrayOutputStreamAPI测试
     */
    public static void ByteArrayOutputStreamAPITest() throws Exception{
        String temp = new String(ArrayLetters);
        System.out.println("temp："+temp);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


        //write(int b) 将指定的字节写入此字节数组输出流。
        outputStream.write(0x61);
        outputStream.write(0x62);
        outputStream.write(0x63);
        System.out.println("out："+outputStream);
        System.out.println("out："+outputStream.toString());


        //write(byte[] b, int off, int len)
        // 从指定的字节数组写入 len字节，从偏移量为 off开始，输出到这个字节数组输出流。
        outputStream.write(ArrayLetters,3,7);
        System.out.println("out："+outputStream.toString());

        //size()返回缓冲区的当前大小。
        int size = outputStream.size();
        System.out.println("当前缓冲区的大小："+size);

        //toByteArray()创建一个新分配的字节数组。
        byte[] bytes = outputStream.toByteArray();
        System.out.println("bytes："+new String(bytes));

        //writeTo(OutputStream out)
        // 将此字节数组输出流的完整内容写入指定的输出流参数
        ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
        outputStream.writeTo(bos2);
        System.out.println("bos2："+bos2.toString());
    }

}

