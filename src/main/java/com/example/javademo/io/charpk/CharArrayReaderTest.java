package com.example.javademo.io.charpk;

import java.io.CharArrayReader;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/3/21
 **/
public class CharArrayReaderTest {

    public static void main(String[] args) throws Exception {
        read1();
    }

    public static void read1() throws Exception{
        String string = "测试字符数组输入流";
        char[] chars =string.toCharArray();
        char[] chars1 = new char[20];
        try (CharArrayReader charArrayReader = new CharArrayReader(chars)){
            charArrayReader.read(chars1,0 , chars1.length);
            System.out.println(String.valueOf(chars1));
        }
    }
}
