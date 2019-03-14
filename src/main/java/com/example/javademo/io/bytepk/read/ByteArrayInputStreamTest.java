package com.example.javademo.io.bytepk.read;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author liuf
 * @create 2019-03-13 22:15
 */
public class ByteArrayInputStreamTest {
    public static void main(String[] args) throws Exception{
        byte[] bytes = "11111".getBytes();
        InputStream inputStream = new ByteArrayInputStream(bytes);
        byte[] pos = new byte[bytes.length];
        int len=0;
        inputStream.read(pos);
        System.out.println(new String(pos));
    }
}
