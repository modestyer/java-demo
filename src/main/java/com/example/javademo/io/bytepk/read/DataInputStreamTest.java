package com.example.javademo.io.bytepk.read;

import com.example.javademo.util.EncodingDetect;

import java.io.*;

/**
 * @author liuf
 * @create 2019-03-07 21:54
 * 适合读取八种基本数据类型或是由DataOutputStream生成的文件
 */
public class DataInputStreamTest {


    public static void writeAndRead() throws Exception{
        try(DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("E:/456.txt"));
            DataInputStream inputStream = new DataInputStream(new FileInputStream("E:/456.txt"))) {
            outputStream.writeUTF(read2());

            String s = inputStream.readUTF();
            System.out.println("dataoutputstream读取");
            System.out.println(s);
        }
    }

    public static String read2() throws Exception{
        File file = new File("E:/123.txt");
        byte[] bytes = new byte[(int) file.length()];
        try(InputStream in = new FileInputStream(file)) {
            in.read(bytes);
        }
        return new String(bytes,EncodingDetect.detect("E:/123.txt"));
    }
    public static void main(String[] args) throws Exception{
        writeAndRead();
    }
}
