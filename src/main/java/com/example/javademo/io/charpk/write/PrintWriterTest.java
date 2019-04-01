package com.example.javademo.io.charpk.write;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/3/29
 **/
public class PrintWriterTest {

    public static void main(String[] args) throws Exception{
        test1();
    }

    private static void test1() throws FileNotFoundException {
        try (PrintWriter printWriter = new PrintWriter("F:/iotest.txt")){
            printWriter.format("测试printWriter[%s]","测试三十");
        }
    }

    private static void test2() throws Exception{
        PrintWriter out
                = new PrintWriter(new BufferedWriter(new FileWriter("foo.out")));
    }
}
