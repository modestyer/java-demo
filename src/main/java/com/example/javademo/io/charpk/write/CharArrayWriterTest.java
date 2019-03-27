package com.example.javademo.io.charpk.write;

import java.io.CharArrayWriter;
import java.io.FileReader;

/**
 * @author liuf
 * @create 2019-03-24 18:18
 */
public class CharArrayWriterTest {

    public static void main(String[] args) throws Exception{
        write1();
//        System.out.println(2<<2);
    }

    private static void write1() throws Exception {
        try (FileReader fileReader = new FileReader("E:/123.txt");
             CharArrayWriter charArrayWriter = new CharArrayWriter()){
            char[] chars = new char[1024];
            int len=0;
            while ((len = fileReader.read(chars))!=-1){
                charArrayWriter.write(chars);
            }

            charArrayWriter.append('\n');
            charArrayWriter.append('b');
            System.out.println(charArrayWriter.toCharArray());
        }
    }
}
