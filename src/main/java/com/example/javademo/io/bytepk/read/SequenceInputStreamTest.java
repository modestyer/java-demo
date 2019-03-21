package com.example.javademo.io.bytepk.read;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

/**
 * @author liuf
 * @create 2019-03-13 21:15
 * 合并流
 * 若需要在每次读完一个文件做一些处理，目前有两种想法：
 * 1、可以考虑read()方法一个一个字节读取，直到读取到-1代表一个文件读完
 * 2、继承SequenceInputStream类，重写read(byte[] b, int off, int len)方法，
 * 在执行nextStream()方法前返回标识做处理
 */
public class SequenceInputStreamTest {

    public static void main(String[] args) throws Exception{
        twoInput();
        manyInput();
    }

    /**
     * 合并两个输入流
     * @throws Exception
     */
    public static void twoInput() throws Exception{
        try (InputStream inputStream =
                     new SequenceInputStream(
                             new BufferedInputStream
                                     (new FileInputStream("E:/123.txt")),new BufferedInputStream(new FileInputStream("E:/123.txt")))){

            byte[] bytes = new byte[2048];
            int len = 0;
            StringBuffer stringBuffer = new StringBuffer();
            while ((len=inputStream.read(bytes))!=-1){
                stringBuffer.append(new String(bytes,0,len,"utf-8"));
            }
            System.out.println(stringBuffer.toString());
        }
    }

    /**
     * 合并多个输入流
     * @throws Exception
     */
    public static void manyInput() throws Exception{
        Vector<InputStream> v = new Vector<>(3);
        v.addElement(new BufferedInputStream (new FileInputStream("E:/123.txt")));
        v.addElement(new BufferedInputStream (new FileInputStream("E:/123.txt")));
        v.addElement(new BufferedInputStream (new FileInputStream("E:/123.txt")));

        Enumeration<InputStream> e = v.elements();
        try (InputStream inputStream = new SequenceInputStream(e)){
            byte[] bytes = new byte[2048];
            int len = 0;
            StringBuffer stringBuffer = new StringBuffer();
            while ((len=inputStream.read(bytes))!=-1){
                stringBuffer.append(new String(bytes,0,len,"utf-8"));
            }
            System.out.println(stringBuffer.toString());
        }
    }
}
