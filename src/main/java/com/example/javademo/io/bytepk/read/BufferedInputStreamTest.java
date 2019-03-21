package com.example.javademo.io.bytepk.read;

import com.example.javademo.util.EncodingDetect;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author liuf
 * @create 2019-03-07 23:07
 */
public class BufferedInputStreamTest {
    public static void main(String[] args) {

        try (BufferedInputStream buf = new BufferedInputStream(new FileInputStream("E:/123.txt"),512)){
            StringBuffer str = new StringBuffer();
            byte[] bytes = new byte[1024];
            int len = 0;
            buf.read();
            while((len=buf.read(bytes))!=-1){
                str.append(new String(bytes,0,len,EncodingDetect.detect("E:/123.txt")));
            }

            System.out.println(str.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
