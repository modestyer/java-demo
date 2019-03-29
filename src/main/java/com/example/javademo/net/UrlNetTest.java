package com.example.javademo.net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.URL;

/**
 * @author liuf
 * @create 2019-03-24 15:13
 */
public class UrlNetTest {

    public static void main(String[] args) throws Exception{
        URL url = new URL("https://mmbiz.qpic.cn/mmbiz_jpg/R3InYSAIZkGC9Rwek9KQ6YRlKt7XOuAqSm593NWL87tbHqzgA0jvzbU8uM1M8E3QibRiaUZlCp1ibuJgGR00BUbGw/640?wx_fmt=jpeg");

        try(BufferedInputStream bif = new BufferedInputStream(url.openStream());
            FileOutputStream fos = new FileOutputStream("E:/wx.jpg");
            BufferedOutputStream bof = new BufferedOutputStream(fos)){
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = bif.read(bytes))!=-1){
                bof.write(bytes,0,len);
            }

        }

    }
}
