package com.example.javademo.io.charpk.write;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;

/**
 * @author liuf
 * @create 2019-03-24 17:09
 */
public class OutputStreamWriterTest {

    public static void main(String[] args) throws Exception {
//        write1();
        write2();
    }

    public static void write1() throws Exception {
        try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("E:/writer.txt"));
             StringReader stringReader = new StringReader("测试字符串输出流")){
            char[] chars = new char[1024];
            int len =0;
            while ((len = stringReader.read(chars))!=-1){
                osw.write(chars,0,len);
            }

        }
    }

    public static void write2() throws Exception{
        try(OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("E:/writer.txt"))){
            String str = "测试字符串输出流输出字符串";
            osw.write(str,0,str.length());
        }
    }
}
