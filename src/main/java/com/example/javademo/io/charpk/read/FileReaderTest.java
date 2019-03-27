package com.example.javademo.io.charpk.read;

import java.io.FileReader;

/**
 * @author liuf
 * @create 2019-03-20 23:04
 */
public class FileReaderTest {

    public static void main(String[] args) throws Exception {
        read1();
    }

    public static void read1() throws Exception{
        try (FileReader fileReader = new FileReader("E:/123.txt")){
            char[] chars = new char[1024];
            int len = 0;
            StringBuffer stringBuffer = new StringBuffer();
            while((len = fileReader.read(chars))!=-1){
                stringBuffer.append(new String(chars,0,len));
            }
            System.out.println(stringBuffer.toString());
        }
    }
}
