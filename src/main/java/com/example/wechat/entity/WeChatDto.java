package com.example.wechat.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Description:微信搜索接口返回的文章list实体类
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/3/18
 **/
public class WeChatDto {

    private String fakeId;

    private String nickname;

    private String alias;

    private String roundHeadImg;

    private String serviceType;

    @JSONField(name = "fakeid")
    public String getFakeId() {
        return fakeId;
    }
    @JSONField(name = "fakeid")
    public void setFakeId(String fakeId) {
        this.fakeId = fakeId;
    }
    @JSONField(name = "nickname")
    public String getNickname() {
        return nickname;
    }
    @JSONField(name = "nickname")
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    @JSONField(name = "alias")
    public String getAlias() {
        return alias;
    }
    @JSONField(name = "alias")
    public void setAlias(String alias) {
        this.alias = alias;
    }
    @JSONField(name = "round_head_img")
    public String getRoundHeadImg() {
        return roundHeadImg;
    }
    @JSONField(name = "round_head_img")
    public void setRoundHeadImg(String roundHeadImg) {
        this.roundHeadImg = roundHeadImg;
    }
    @JSONField(name = "service_type")
    public String getServiceType() {
        return serviceType;
    }
    @JSONField(name = "service_type")
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
}
