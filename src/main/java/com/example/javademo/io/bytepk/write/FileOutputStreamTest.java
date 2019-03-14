package com.example.javademo.io.bytepk.write;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author liuf
 * @create 2019-03-14 21:42
 */
public class FileOutputStreamTest {
    public static void main(String[] args) throws Exception {
        write1();
    }


    /**
     * write(byte[] b, int off, int len)
     * 将 len字节从位于偏移量 off的指定字节数组写入此文件输出流。
     */
    public static void write1() throws Exception{

        try (FileInputStream fin = new FileInputStream("E:/密码.txt");
             FileOutputStream fon = new FileOutputStream("E:/789.txt");){

            byte[] bytes = new byte[70];

            int len = 0;
            while ((len = fin.read(bytes))!=-1){
                fon.write(bytes,0,len);
            }
        }




    }
}
