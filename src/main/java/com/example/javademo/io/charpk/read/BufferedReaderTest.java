package com.example.javademo.io.charpk.read;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author liuf
 * @create 2019-03-20 22:41
 */
public class BufferedReaderTest {

    public static void main(String[] args) throws Exception{
        read1();
    }

    public static void read1() throws Exception{
        try (BufferedReader bur = new BufferedReader(new FileReader("E:/123.txt"))){
            String line = "";
            while ((line=bur.readLine())!=null){
                System.out.println(line);
            }
        }
    }
}
