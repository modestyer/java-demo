package com.example.javademo.io.bytepk.write;

import com.example.javademo.io.bytepk.read.FileInputStreamTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author liuf
 * @create 2019-03-17 18:09
 */
public class ObjectOutputStreamTest {

    public static void main(String[] args) throws Exception{
        write();
        read();
    }


    public static void write() throws Exception{
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("E:/987.txt"))){
            oos.writeObject(new FileInputStreamTest());
        }
    }

    public static void read() throws Exception{
        try (ObjectInputStream oin = new ObjectInputStream(new FileInputStream("E:/987.txt"))){
            FileInputStreamTest fint = (FileInputStreamTest) oin.readObject();
            System.out.println("-----------");
        }
    }
}
