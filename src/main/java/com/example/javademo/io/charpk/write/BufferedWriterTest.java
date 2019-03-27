package com.example.javademo.io.charpk.write;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * @author liuf
 * @create 2019-03-27 22:06
 */
public class BufferedWriterTest {

    public static void main(String[] args) throws Exception{
        write1();
    }

    public static void write1() throws Exception {
        StringBuffer str = new StringBuffer();
        str.append("测试");
        str.append("\t");
        str.append("换行");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("E:/BufferedWriterTest.txt"))){
            for (int i = 0; i < str.length(); i++) {
                if(str.charAt(i)=='\t'){
                    bw.newLine();
                }else {
                    bw.write(str.charAt(i));
                }
            }
        }
    }
}
