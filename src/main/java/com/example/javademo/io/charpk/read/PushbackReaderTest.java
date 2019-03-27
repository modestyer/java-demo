package com.example.javademo.io.charpk.read;

import java.io.FileReader;
import java.io.PushbackReader;

/**
 * @author liuf
 * @create 2019-03-24 16:11
 */
public class PushbackReaderTest {

    public static void main(String[] args) throws Exception {
        read1();
    }

    public static void read1() throws Exception{
        try (PushbackReader pr = new PushbackReader(new FileReader("E:/密码.txt"),2048)){
            char[] chars = new char[1024];
            int len =0;
            StringBuffer str = new StringBuffer();
            /*while ((len = pr.read(chars))!=-1){
//                str.append(String.valueOf(chars));
            }*/
            pr.unread(chars,0,1024);
            char[] chars1 = new char[1024];
            len=0;
            while ((len = pr.read(chars1))!=-1){
                str.append(String.valueOf(chars1));
            }
            System.out.println(str.toString());
        }
    }
}
