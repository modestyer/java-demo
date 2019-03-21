/*
package com.example.wechat.util;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

*/
/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/3/18
 **//*

public class SpiderModel implements PageProcessor {


    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
// TODO Auto-generated method stub
        String content = page.getHtml().xpath("//div[@id='js_content']").get();
        //存在和谐文章 此处做判定如果有直接删除记录或设置表示位表示文章被和谐
        if(content == null){
            System.out.println("文章已和谐！");
            //postMapper.deleteByPrimaryKey(post.getId());
            return;
        }
        String contentSnap = content.replaceAll("data-src", "src").replaceAll("preview.html", "player.html");//快照
        String contentTxt = HtmlToWord.stripHtml(content);//纯文本内容

        Selectable metaContent = page.getHtml().xpath("//div[@id='meta_content']");
        String pubTime = null;
        String wxname = null;
        String author = null;
        if(metaContent != null){
            pubTime = metaContent.xpath("//em[@id='post-date']").get();
            if(pubTime != null){
                pubTime = HtmlToWord.stripHtml(pubTime);//文章发布时间
            }
            wxname = metaContent.xpath("//a[@id='post-user']").get();
            if(wxname != null){
                wxname = HtmlToWord.stripHtml(wxname);//公众号名称
            }
            author = metaContent.xpath("//em[@class='rich_media_meta rich_media_meta_text' and @id!='post-date']").get();
            if(author != null){
                author = HtmlToWord.stripHtml(author);//文章作者
            }
        }

//        System.out.println("发布时间:"+pubTime);
//        System.out.println("公众号名称:"+wxname);
//        System.out.println("文章作者:"+author);

        String title = post.getTitle().replaceAll("&nbsp;", "");//文章标题
        String digest = post.getDigest();//文章摘要
        int likeNum = post.getLikenum();//文章点赞数
        int readNum = post.getReadnum();//文章阅读数
        String contentUrl = post.getContentUrl();//文章链接

        WechatInfoBean wechatBean = new WechatInfoBean();
        wechatBean.setTitle(title);
        wechatBean.setContent(contentTxt);//纯文本内容
        wechatBean.setSourceCode(contentSnap);//快照
        wechatBean.setLikeCount(likeNum);
        wechatBean.setViewCount(readNum);
        wechatBean.setAbstractText(digest);//摘要
        wechatBean.setUrl(contentUrl);
        wechatBean.setPublishTime(pubTime);
        wechatBean.setSiteName(wxname);//站点名称 公众号名称
        wechatBean.setAuthor(author);
        wechatBean.setMediaType("微信公众号");//来源媒体类型

        WechatStorage.saveWechatInfo(wechatBean);

        //标示文章已经被爬取
        post.setIsSpider(1);
        postMapper.updateByPrimaryKey(post);

    }

    @Override
    public Site getSite() {
        return this.site;
    }
}
*/
