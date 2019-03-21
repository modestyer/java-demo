package com.example.wechat.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/3/18
 **/

public class WeChatArticleDto {


    private String aid;
    private String appmsgId;
    private String cover;
    private String digest;
    private String itemShowType;
    private String itemidx;
    private String link;
    private String title;
    private String updateTime;


    @JSONField(name = "aid")
    public String getAid() {
        return aid;
    }
    @JSONField(name = "aid")
    public void setAid(String aid) {
        this.aid = aid;
    }
    @JSONField(name = "appmsgid")
    public String getAppmsgId() {
        return appmsgId;
    }
    @JSONField(name = "appmsgid")
    public void setAppmsgId(String appmsgId) {
        this.appmsgId = appmsgId;
    }
    @JSONField(name = "cover")
    public String getCover() {
        return cover;
    }
    @JSONField(name = "cover")
    public void setCover(String cover) {
        this.cover = cover;
    }
    @JSONField(name = "digest")
    public String getDigest() {
        return digest;
    }
    @JSONField(name = "digest")
    public void setDigest(String digest) {
        this.digest = digest;
    }
    @JSONField(name = "item_show_type")
    public String getItemShowType() {
        return itemShowType;
    }
    @JSONField(name = "item_show_type")
    public void setItemShowType(String itemShowType) {
        this.itemShowType = itemShowType;
    }
    @JSONField(name = "itemidx")
    public String getItemidx() {
        return itemidx;
    }
    @JSONField(name = "itemidx")
    public void setItemidx(String itemidx) {
        this.itemidx = itemidx;
    }
    @JSONField(name = "link")
    public String getLink() {
        return link;
    }
    @JSONField(name = "link")
    public void setLink(String link) {
        this.link = link;
    }
    @JSONField(name = "title")
    public String getTitle() {
        return title;
    }
    @JSONField(name = "title")
    public void setTitle(String title) {
        this.title = title;
    }
    @JSONField(name = "update_time")
    public String getUpdateTime() {
        return updateTime;
    }
    @JSONField(name = "update_time")
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
