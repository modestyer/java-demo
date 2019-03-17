package com.example.wechat.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author liuf
 * @create 2019-03-17 12:09
 */
public class HttpClientUtil {

    /**
     * 将参数拼接上GET请求
     *
     * @return
     */
    public static String setUrlByMap(StringBuffer url, Map<String, String> map) {
        List<String> paramNames = new ArrayList<String>(map.size());
        paramNames.addAll(map.keySet());
        Collections.sort(paramNames);
        int i = 0;
        for (String paramName : paramNames) {
            if (i == 0) {
                url.append("?");
            } else {
                url.append("&");
            }
            url.append(paramName);
            url.append("=");
            String paramValue = map.get(paramName);
            if (StringUtils.contains(paramValue, " ")) {
                paramValue = StringUtils.replace(paramValue, " ", "%20");
            }
            url.append(paramValue);
            i = 1;
        }
        return url.toString();
    }
}
