package com.example.javademo.io.charpk.read;

import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @author liuf
 * @create 2019-03-20 22:48
 */
public class InputStreamReaderTest {

    public static void main(String[] args) throws Exception{
        read1();
    }

    public static void read1() throws Exception{
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream("E:/123.txt"))){
            char[] chars = new char[2048];
            int len = 0;
            StringBuffer stringBuffer = new StringBuffer();
            while ((len = isr.read(chars))!=-1){
                stringBuffer.append(new String(chars,0,len));
            }
            System.out.println(stringBuffer.toString());
        }
    }
}
