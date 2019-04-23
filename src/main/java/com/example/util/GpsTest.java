package com.example.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/4/22
 **/
public class GpsTest {

    public static String connectURL(String dest_url, String commString) {
        String rec_string = "";
        URL url = null;
        HttpURLConnection urlconn = null;
        OutputStream out = null;
        BufferedReader rd = null;
        try {
            url = new URL(dest_url);
            urlconn = (HttpURLConnection) url.openConnection();
            urlconn.setReadTimeout(1000 * 30);
            //urlconn.setRequestProperty("content-type", "text/html;charset=UTF-8");
            urlconn.setRequestMethod("GET");
            urlconn.setDoInput(true);
            urlconn.setDoOutput(true);
            out = urlconn.getOutputStream();
            out.write(commString.getBytes("UTF-8"));
            out.flush();
            out.close();
            rd = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            int ch;
            while ((ch = rd.read()) > -1)
                sb.append((char) ch);
            rec_string = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (urlconn != null) {
                    urlconn.disconnect();
                }
                if (rd != null) {
                    rd.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rec_string;
    }

    public static void main(String[] args) throws Exception {
        SnCal snCal = new SnCal();


        /*Map paramsMap = new LinkedHashMap<String, String>();
        paramsMap.put("coords", "118.493791,31.543626");
        paramsMap.put("from", "1");
        paramsMap.put("to", "5");
        paramsMap.put("ak", "H0FsbKNX2UvxeNVKtGpqzEXxLsDKc2Gc");

        String paramsStr = snCal.toQueryString(paramsMap);

        String wholeStr = new String("/geocoder/v1/?" + paramsStr + "ldkQdwl31jNj0UVGfpNYkiteOe933e4a");

        // 对上面wholeStr再作utf8编码
        String tempStr = URLEncoder.encode(wholeStr, "UTF-8");

        // 调用下面的MD5方法得到最后的sn签名7de5a22212ffaa9e326444c75a58f9a0
        String sn = snCal.MD5(tempStr);*/


        Map paramsMap = new LinkedHashMap<String, String>();
        paramsMap.put("address", "百度大厦");
        paramsMap.put("output", "json");
        paramsMap.put("ak", "H0FsbKNX2UvxeNVKtGpqzEXxLsDKc2Gc");

        // 调用下面的toQueryString方法，对LinkedHashMap内所有value作utf8编码，拼接返回结果address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourak
        String paramsStr = snCal.toQueryString(paramsMap);

        // 对paramsStr前面拼接上/geocoder/v2/?，后面直接拼接yoursk得到/geocoder/v2/?address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourakyoursk
        String wholeStr = new String("/geocoder/v2/?" + paramsStr + "ldkQdwl31jNj0UVGfpNYkiteOe933e4a");

        // 对上面wholeStr再作utf8编码
        String tempStr = URLEncoder.encode(wholeStr, "UTF-8");

        // 调用下面的MD5方法得到最后的sn签名7de5a22212ffaa9e326444c75a58f9a0
        String sn = snCal.MD5(tempStr);
        System.out.println(sn);


        //  System.out.println(System.getProperty("file.encoding"));
//        String result =connectURL("http://api.map.baidu.com/geoconv/v1/?coords=118.493791,31.543626&from=1&to=5&ak=H0FsbKNX2UvxeNVKtGpqzEXxLsDKc2Gc&sn="+sn,"");
        String result = connectURL("http://api.map.baidu.com/geocoder/v2/?address=百度大厦&output=json&ak=H0FsbKNX2UvxeNVKtGpqzEXxLsDKc2Gc&sn=" + sn, "");
        // 算得sn后发送get请求
        /*HttpClient client = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://api.map.baidu.com/geocoder/v2/?address=百度大厦&output=json&ak=XWnIC5k1cguL71ClpinbhmozUsBWY4Xr&sn=" + sn);
        HttpResponse response = client.execute(httpget);
        InputStream is = response.getEntity().getContent();
        String result = inStream2String(is);
        // 打印响应内容
        System.out.println(result);

        try {
            System.out.println(result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/


//        String coords = "106.6519570767,26.6245856997";
//        String result =connectURL("http://api.map.baidu.com/geoconv/v1/?coords="+coords+"&from=1&to=5&output=json&ak=***你的ak***","");
//        System.out.println(result);
    }

}
