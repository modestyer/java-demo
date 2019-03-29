package com.example.javademo.io.charpk.write;

import java.io.FileWriter;

/**
 * @author liuf
 * @create 2019-03-24 17:31
 */
public class FileWriterTest {
    public static void main(String[] args) throws Exception{
        write1();
    }

    public static void write1() throws Exception{
        try (FileWriter writer = new FileWriter("E:/wreter.txt")){
            String str = "FileWriter输出流测试";
            writer.write(str,0,str.length());
        }
    }
}
