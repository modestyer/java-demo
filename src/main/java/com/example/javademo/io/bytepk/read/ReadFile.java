package com.example.javademo.io.bytepk.read;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/2/13
 **/
public class ReadFile {


    public static void inputStreamRead(){

        InputStream inputStream = null;

        File file = new File("D:\\GprsFlow_20181101.txt");

        try {
            inputStream = new FileInputStream(file);

            byte[] b = new byte[(int) file.length()];

            inputStream.read(b);
            inputStream.close();
            System.out.println("文件长度为："+file.length());
            System.out.println(new String(b));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        inputStreamRead();
    }
}
