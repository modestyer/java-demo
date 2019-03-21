package com.example.javademo.io.bytepk.write;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author liuf
 * @create 2019-03-17 16:25
 */
public class BufferedOutputStreamTest {

    public static void main(String[] args) throws Exception {
        write1();

    }

    public static void write1() throws Exception{
        try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream("E:/123.txt"));
             BufferedOutputStream bon = new BufferedOutputStream(new FileOutputStream("E:/789.txt"))){

            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = bin.read(bytes))!=-1){
                bon.write(bytes,0,len);
            }

        }
    }

}
