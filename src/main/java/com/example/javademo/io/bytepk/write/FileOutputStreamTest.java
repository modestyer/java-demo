package com.example.javademo.io.bytepk.write;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author liuf
 * @create 2019-03-14 21:42
 */
public class FileOutputStreamTest {
    public static void main(String[] args) throws Exception {
//        write1();
        write2();
    }


    /**
     * write(byte[] b, int off, int len)
     * 将 len字节从位于偏移量 off的指定字节数组写入此文件输出流。
     */
    public static void write1() throws Exception{

        try (FileInputStream fin = new FileInputStream("E:/123.txt");
             FileOutputStream fon = new FileOutputStream("E:/789.txt");){

            byte[] bytes = new byte[1024];

            int len = 0;
            while ((len = fin.read(bytes))!=-1){
                fon.write(bytes,0,len);
            }
        }
    }

    /**
     * write(int b)
     * 将指定的字节写入此文件输出流。
     * @throws Exception
     */
    public static void write2() throws Exception{
        try (FileInputStream fin = new FileInputStream("E:/123.txt");
             FileOutputStream fon = new FileOutputStream("E:/789.txt")){

            byte[] bytes = new byte[1024];
            int len=0;
            while ((len = fin.read(bytes))!=-1){
                System.out.println("读取的字节大小位："+len);
                String str = new String(bytes,0,len,"utf-8");
                //此种方式中文不乱码
                for (int i = 0; i < len; i++) {
                    int b = bytes[i];
                    fon.write(b);
                }
                //此种方式中文乱码
                System.out.println(str);
                for (int i = 0; i < str.length(); i++) {
                    int b = str.charAt(i);
                    fon.write(b);
                }
            }
        }
    }
}
