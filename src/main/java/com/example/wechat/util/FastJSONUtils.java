package com.example.wechat.util;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

public class FastJSONUtils {
    /***
     *
     * 将对象序列化为JSON文本
     */
   public static String toJSONString(Object object) {
        return JSON.toJSONString(object);
    }

    /***
     * 将JSON文本反序列化到对象
     */
    public static <T> T toBean(String jsonString, Class<T> bean) {
        T t = (T) JSON.parseObject(jsonString, bean);
        return t;
    }

    /***
     * 将JSON文本反序列化到对象的list
     */
    public static <T> List<T> toArray(String jsonString, Class<T> bean) {
        List<T> t = JSON.parseArray(jsonString, bean);
        return t;
    }
    public static Map<String,Object> json2Map(String json){
        return JSON.parseObject(json, Map.class);
    }
    /***
     * 将对象序列化为JSON文本的二进制数组
     */
    public static byte[] toJSONByteArray(Object object) {
        return JSON.toJSONString(object).getBytes();
    }

    /***
     * 将SON文本的二进制数组反序列化到对象
     */
    public static <T> T toBeanFromByteArray(byte[] bytes, Class<T> bean) {
        String jsonString = new String(bytes);
        T t = (T) JSON.parseObject(jsonString, bean);
        return t;
    }

    /***
     * 将SON文本的二进制数组反序列化到对象的list
     */
    public static <T> List<T> toArrayFromByteArray(byte[] bytes, Class<T> bean) {
        String jsonString = new String(bytes);
        List<T> t = JSON.parseArray(jsonString, bean);
        return t;
    }


}
