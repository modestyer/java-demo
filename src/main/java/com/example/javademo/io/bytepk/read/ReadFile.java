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


    public static void inputStreamRead() throws Exception{
        File file = new File("E:\\密码.txt");
        String result="";
        try (InputStream in = new FileInputStream(file);) {
            byte[] bytes = new byte[(int) file.length()];
            in.read(bytes);
            result = new String(bytes,"gbk");
            System.out.println(result);
        }

    }

    public static void main(String[] args) {

        try {
            inputStreamRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
