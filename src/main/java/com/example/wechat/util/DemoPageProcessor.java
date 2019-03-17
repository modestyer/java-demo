package com.example.wechat.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/3/15
 **/
public class DemoPageProcessor{

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoPageProcessor.class);

    private static final String ACCOUNT_NAME = "hjxu@dunoinfo.com";

    private static final String PASSWORD = "dunoinfo111";

    private static final String URL = "https://mp.weixin.qq.com/";

    private static final String SEARCHURL = "https://mp.weixin.qq.com/cgi-bin/searchbiz?";

    private static final String HEADER = "";

    /**
     * 正则匹配token
     */
    private static Pattern TOKEN_PATTERN = Pattern.compile("(?<=\"&token=).*?(?=\",)");

    static  CookieStore cookieStore = new BasicCookieStore();

//    private Site site = Site.me().setRetryTimes(5).setTimeOut(3000).setSleepTime(1000);

    /**
     * @Description: 用来存储cookie信息
     **/
    private static Set<Cookie> cookies;

    public static void wechatLogin() throws InterruptedException {
        LOGGER.info("启动浏览器，打开微信公众号登录界面");
        //设置驱动程序路径
        //Chrome
        System.setProperty("webdriver.chrome.driver","C:/Users/ABC/AppData/Local/Google/Chrome/Application/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(URL);
        Thread.sleep(3000);
        LOGGER.info("正在输入微信公众号登录账号和密码......");
        //寻找账号编辑框
        driver.findElement(By.name("account")).clear();
        driver.findElement(By.name("account")).sendKeys(ACCOUNT_NAME);

        Thread.sleep(1000);
        //寻找密码编辑框
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(PASSWORD);
        Thread.sleep(1000);
        LOGGER.info("请在登录界面点击:记住账号");
        driver.findElement(By.className("frm_checkbox_label")).click();
        Thread.sleep(5000);
        LOGGER.info("点击登陆");
        driver.findElement(By.className("btn_login")).click();
        LOGGER.info("请拿手机扫码二维码登录公众号");
        Thread.sleep(10000);
        LOGGER.info("登录成功");

        cookies  = driver.manage().getCookies();

//        driver.quit();
    }

    public static void getWeChatContent() throws Exception{
        wechatLogin();

        Iterator<Cookie> itr = cookies.iterator();

        while (itr.hasNext()) {
            Cookie cookie = itr.next();
            BasicClientCookie bcco = new BasicClientCookie(cookie.getName(), cookie.getValue());
            bcco.setDomain(cookie.getDomain());
            bcco.setPath(cookie.getPath());
            cookieStore.addCookie(bcco);
        }

        HttpGet httpGet = new HttpGet(URL);

        CloseableHttpClient httpClient = null;

        // 创建默认的httpClient实例.
        PublicSuffixMatcher publicSuffixMatcher = PublicSuffixMatcherLoader.load(new URL(httpGet.getURI().toString()));
        DefaultHostnameVerifier hostnameVerifier = new DefaultHostnameVerifier(publicSuffixMatcher);
        httpClient = HttpClients.custom().setSSLHostnameVerifier(hostnameVerifier).setDefaultCookieStore(cookieStore).build();

        HttpResponse httpResponse = httpClient.execute(httpGet);
        String result= EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        Matcher matcher = TOKEN_PATTERN.matcher(result);
        String token="";
        while(matcher.find()){
            token = matcher.group();
        }

        HttpGet httpGetSearch = new HttpGet(SEARCHURL);

        Map<String,Object> map = new HashMap<>();
        map.put("action","search_biz");
        map.put("token",token);
        map.put("random",Math.random());
        httpGetSearch.addHeader("HOST","mp.weixin.qq.com");
        httpGetSearch.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");

        PublicSuffixMatcher publicSuffixMatcher1 = PublicSuffixMatcherLoader.load(new URL(httpGetSearch.getURI().toString()));
        DefaultHostnameVerifier hostnameVerifier1 = new DefaultHostnameVerifier(publicSuffixMatcher1);
        httpClient = HttpClients.custom().setSSLHostnameVerifier(hostnameVerifier1).setDefaultCookieStore(cookieStore).build();

        HttpResponse httpResponse1 = httpClient.execute(httpGetSearch);
        System.out.println(token);
    }

    public static void main(String[] args) throws Exception{
//        wechatLogin();
        getWeChatContent();
        /*String str = "param: [\"&token=1254205642\", '&lang=zh_CN'].join(\"\")";

        Pattern p = Pattern.compile("(?<=\"&token=).*?(?=\",)");
        Matcher matcher = p.matcher(str);
        while(matcher.find()){
            System.out.println(matcher.group());
        }*/
    }
}
