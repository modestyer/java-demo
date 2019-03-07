package com.example.javademo.io.bytepk.read;


import com.example.javademo.util.EncodingDetect;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/2/13
 **/
public class FileInputStreamTest {


    /**
     * 指定大小读取文件,若文件过大则读不完
     */
    public static void read1() throws Exception{
        try(InputStream in = new FileInputStream("E:/123.txt")) {
            byte[] bytes = new byte[1024];
            in.read(bytes);
            System.out.println(new String(bytes,EncodingDetect.detect("E:/123.txt")));

        }

    }


    /**
     * 使用文件大小作为缓冲数组的长度读取文件
     */
    public static void read2() throws Exception{
        File file = new File("E:/123.txt");
        try(InputStream in = new FileInputStream(file)) {
            byte[] bytes = new byte[(int) file.length()];
            in.read(bytes);
            System.out.println(new String(bytes,EncodingDetect.detect("E:/123.txt")));
        }
    }

    /**
     * 当无法获取文件大小时，使用循环read(byte)!=-1来确保读到文件结尾
     */
    public static void read3() throws Exception{
        try(InputStream in = new FileInputStream("E:/123.txt")){
            byte[] bytes = new byte[2048];
            int len = 0;
            StringBuffer stringBuffer = new StringBuffer();
            while ((len=in.read(bytes))!=-1){
                stringBuffer.append(new String(bytes,0,len,EncodingDetect.detect("E:/123.txt")));
            }
            System.out.println(stringBuffer.toString());
        }
    }

    public static void main(String[] args) {

        try {
//            inputStreamRead();
//            read1();
//            read2();
            read3();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
