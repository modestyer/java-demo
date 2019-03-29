package com.example.javademo.io.charpk.read;

import java.io.IOException;
import java.io.StringReader;

/**
 * @author liuf
 * @create 2019-03-24 16:41
 */
public class StringReaderTest {

    public static void main(String[] args) throws IOException {
        read1();
    }

    public static void read1() throws IOException {
        try (StringReader stringReader = new StringReader("测试字符串输入流")){

            char[] chars = new char[1024];
            int len = 0;
            StringBuffer stringBuffer = new StringBuffer();
            while ((len = stringReader.read(chars))!=-1){
                stringBuffer.append(new String(chars,0,len));
            }
            System.out.println(stringBuffer.toString());
        }
    }
}
