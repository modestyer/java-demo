package com.example.wechat.util;

import com.example.util.DateTools;
import com.example.wechat.entity.ResponseDto;
import com.example.wechat.entity.WeChatArticleDto;
import com.example.wechat.entity.WeChatDto;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.impl.client.*;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
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

    private static final String ACCOUNT_NAME = "liuf9410@163.com";

    private static final String PASSWORD = "liufei56789";

    private static final String URL = "https://mp.weixin.qq.com/";

    private static final String SEARCHURL = "https://mp.weixin.qq.com/cgi-bin/searchbiz";

    private static final String QUERY = "java";

    private static final String APPMSGURL = "https://mp.weixin.qq.com/cgi-bin/appmsg";

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
        Set<Cookie> cookies = new HashSet<>();
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
        Thread.sleep(5000);
        while(cookies.size()!=17){
            cookies  = driver.manage().getCookies();
        }

        List<Cookie> list = new ArrayList<>(cookies);
        for(Cookie cookie : list){
            if(cookie.getName().equals("remember_acct")){
                Date date = cookie.getExpiry();
            }
        }
        Cookie cookie = list.get(0);
        Date date = cookie.getExpiry();



        LOGGER.info("登录成功");


        driver.quit();
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



        Map<String,String> map = new HashMap<>();
        map.put("action","search_biz");
        map.put("token",token);
        map.put("lang", "zh_CN");
        map.put("f", "json");
        map.put("ajax", "1");
        map.put("random",Double.toString(Math.random()));
        map.put("query", QUERY);
        map.put("begin","0");
        map.put("count", "5");
        StringBuffer urlbuf = new StringBuffer(SEARCHURL);
        String requestUrl = HttpClientUtil.setUrlByMap(urlbuf, map);

        HttpGet httpGetSearch = new HttpGet(requestUrl);
        httpGetSearch.addHeader("HOST","mp.weixin.qq.com");
        httpGetSearch.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");

        PublicSuffixMatcher publicSuffixMatcher1 = PublicSuffixMatcherLoader.load(new URL(httpGetSearch.getURI().toString()));
        DefaultHostnameVerifier hostnameVerifier1 = new DefaultHostnameVerifier(publicSuffixMatcher1);
        httpClient = HttpClients.custom().setSSLHostnameVerifier(hostnameVerifier1).setDefaultCookieStore(cookieStore).build();


        HttpResponse httpResponse1 = httpClient.execute(httpGetSearch);

        String result1= EntityUtils.toString(httpResponse1.getEntity(),"utf-8");

        ResponseDto responseDto = FastJSONUtils.toBean(result1, ResponseDto.class);

        List<WeChatDto> weChatDtoList = FastJSONUtils.toArray(FastJSONUtils.toJSONString
                (responseDto.getResult()), WeChatDto.class);

        Map<String,String> map1 = new HashMap<>();

        map1.put("token",token);
        map1.put("lang", "zh_CN");
        map1.put("f", "json");
        map1.put("ajax", "1");
        map1.put("random",Double.toString(Math.random()));
        map1.put("action","list_ex");
        map1.put("begin","0");
        map1.put("count", "5");
        map1.put("query", "");
        map1.put("fakeid", weChatDtoList.get(0).getFakeId());
        map1.put("type", "9");
        StringBuffer urlbuf1 = new StringBuffer(APPMSGURL);
        String requestUrl1 = HttpClientUtil.setUrlByMap(urlbuf1, map1);

        HttpGet httpGetArticle = new HttpGet(requestUrl1);
        httpGetArticle.addHeader("HOST","mp.weixin.qq.com");
        httpGetArticle.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");

        PublicSuffixMatcher publicSuffixMatcher2 = PublicSuffixMatcherLoader.load(new URL(httpGetArticle.getURI().toString()));
        DefaultHostnameVerifier hostnameVerifier2 = new DefaultHostnameVerifier(publicSuffixMatcher2);
        httpClient = HttpClients.custom().setSSLHostnameVerifier(hostnameVerifier2).setDefaultCookieStore(cookieStore).build();

        HttpResponse httpResponse2 = httpClient.execute(httpGetArticle);

        String result2= EntityUtils.toString(httpResponse2.getEntity(),"utf-8");

        ResponseDto responseDto1 = FastJSONUtils.toBean(result2, ResponseDto.class);


        int begin =0;
        int seq = 0;
        int num = responseDto1.getAppMsgCnt()%5==0 ? responseDto1.getAppMsgCnt()/5 : (responseDto1.getAppMsgCnt()/5)+1;

        while(num>0){
            map1.put("begin", Integer.toString(begin));

            StringBuffer urlbuf2 = new StringBuffer(APPMSGURL);
            String requestUrl2 = HttpClientUtil.setUrlByMap(urlbuf2, map1);

            HttpGet httpGetArticle1 = new HttpGet(requestUrl2);
            httpGetArticle1.addHeader("HOST","mp.weixin.qq.com");
            httpGetArticle1.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");

            PublicSuffixMatcher publicSuffixMatcher3 = PublicSuffixMatcherLoader.load(new URL(httpGetArticle1.getURI().toString()));
            DefaultHostnameVerifier hostnameVerifier3 = new DefaultHostnameVerifier(publicSuffixMatcher3);
            httpClient = HttpClients.custom().setSSLHostnameVerifier(hostnameVerifier3).setDefaultCookieStore(cookieStore).build();

            HttpResponse httpResponse3 = httpClient.execute(httpGetArticle1);

            String result3= EntityUtils.toString(httpResponse3.getEntity(),"utf-8");

            ResponseDto responseDto2 = FastJSONUtils.toBean(result3, ResponseDto.class);

            List<WeChatArticleDto> weChatArticleDtoList = FastJSONUtils.toArray(FastJSONUtils.toJSONString
                    (responseDto2.getAppMsgList()), WeChatArticleDto.class);

            for(WeChatArticleDto weChatArticleDto : weChatArticleDtoList){

                String link = weChatArticleDto.getLink();

                HttpGet articleUrl = new HttpGet(link);

                httpClient = HttpClientBuilder.create().setRetryHandler(new DefaultHttpRequestRetryHandler(0, false)).build();

                HttpResponse response = httpClient.execute(articleUrl);

                String html = EntityUtils.toString(response.getEntity(),"utf-8");

                String realHtml = html.replace("data-src", "src");

                BufferedOutputStream buf = new BufferedOutputStream(new FileOutputStream("F:/文档/wechat/article/"+weChatArticleDto.getTitle()+".html"));

                buf.write(realHtml.getBytes());

            }





            num--;
            begin = begin+5;

            if(begin == 15){
                break;
            }

        }

    }


    public static void getArticle(String url){

    }

    public static void main(String[] args) throws Exception{
//        wechatLogin();
//        getWeChatContent();
        wechatLogin();
        /*String str = "param: [\"&token=1254205642\", '&lang=zh_CN'].join(\"\")";

        Pattern p = Pattern.compile("(?<=\"&token=).*?(?=\",)");
        Matcher matcher = p.matcher(str);
        while(matcher.find()){
            System.out.println(matcher.group());
        }*/
        /*int c = 86%5 == 0 ? (86/5) : (86/5)+1;
        System.out.println(c);*/

        String url="https://mp.weixin.qq.com/s?__biz=MzU1NzU3NDQzMg==&mid=2247484399&idx=1&sn=4fc6bd947bbe09a9a094fee011f55774&chksm=fc32f3a0cb457ab6bcb032bad0873be4dc6b1b6c884e99621e7c18d97ea2dd58a2356d7dc7bf#rd";

        /*java.net.URL url1 = new URL(url);

        //通过URL的openStrean方法获取URL对象所表示的自愿字节输入流
        InputStream is = url1.openStream();
        BufferedInputStream bif = new BufferedInputStream(is);

        byte[] bytes = new byte[1024];

        int len = 0;

        BufferedOutputStream buf = new BufferedOutputStream(new FileOutputStream("F:/文档/wechat/123.html"));
        while ((len = bif.read(bytes))!=-1){
            buf.write(bytes,0 ,len );
        }*/

       /* Document doc= Jsoup.parse(new URL(url),(2000));

        String html = doc.toString();

        BufferedOutputStream buf = new BufferedOutputStream(new FileOutputStream("F:/文档/wechat/123.html"));

        buf.write(html.getBytes());
*/

      /*  HttpHtmlUtils httpUtils = HttpHtmlUtils.getInstance();
        httpUtils.setTimeout(30000);
        httpUtils.setWaitForBackgroundJavaScript(30000);
        try {
            String htmlPageStr = httpUtils.getHtmlPageResponse(url);

            BufferedOutputStream buf = new BufferedOutputStream(new FileOutputStream("F:/文档/wechat/123.html"));

            buf.write(htmlPageStr.getBytes());
            //TODO
            System.out.println(htmlPageStr);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        String date = DateTools.timeStamp2Date("1552865400","yyyy-MM-dd");
        String date1 = DateTools.getMonthFirstDay();
        System.out.println(date1);
    }

}
