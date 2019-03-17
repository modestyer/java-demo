package com.example.javademo.io.bytepk.write;

import java.io.*;

/**
 * @author liuf
 * @create 2019-03-17 17:10
 */
public class DataOutputStreamTest {

    public static void main(String[] args) throws Exception {
        write();
        read();
    }

    public static void write() throws Exception{
        String[] str = new String[]{"衬衣","手套","围巾"};
        float[] floats = {98.3f,30.3f,50.5f};
        int[] number = {3,2,1};


        try (DataOutputStream dou = new DataOutputStream(new FileOutputStream("E:/789.txt"))){
            for (int i = 0; i < str.length; i++) {
                dou.writeChars(str[i]);
                dou.writeChar('\t');
                dou.writeFloat(floats[i]);
                dou.writeChar('\t');
                dou.writeInt(number[i]);
                dou.writeChar('\n');
            }
        }
    }

    public static void read() throws Exception{
        String name="";
        float f = 0f;
        int number =0;
        char[] tmp = null;
        int len =0;
        char c = 0;
        DataInputStream din = null;
        try{
            din = new DataInputStream(new FileInputStream("E:/789.txt"));
            //直接以文件大小作为条件循环会报错，文件大小大于要读取的大小
//            while (length < new File("E:/789.txt").length())
            while (true){
                len=0;
                tmp = new char[200];
                while ((c=din.readChar())!='\t'){
                    tmp[len] = c;
                    len++;
                }
                name = new String(tmp,0,len);

                f = din.readFloat();

                din.readChar();

                number = din.readInt();

                din.readChar();
                System.out.printf("名称：%s，价格：%sf，数量：%s\n",name,f,number);
            }
        } catch (Exception e) {
            din.close();
        }
    }
}
