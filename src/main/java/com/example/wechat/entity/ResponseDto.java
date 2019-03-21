package com.example.wechat.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/3/18
 **/
public class ResponseDto {

    private Object baseResp;

    private Object result;

    private Object total;

    private int appMsgCnt;

    private Object appMsgList;


    @JSONField(name = "base_resp")
    public Object getBaseResp() {
        return baseResp;
    }

    @JSONField(name = "base_resp")
    public void setBaseResp(Object baseResp) {
        this.baseResp = baseResp;
    }

    @JSONField(name = "list")
    public Object getResult() {
        return result;
    }

    @JSONField(name = "list")
    public void setResult(Object result) {
        this.result = result;
    }

    @JSONField(name = "total")
    public Object getTotal() {
        return total;
    }

    @JSONField(name = "total")
    public void setTotal(Object total) {
        this.total = total;
    }

    @JSONField(name = "app_msg_cnt")
    public int getAppMsgCnt() {
        return appMsgCnt;
    }

    @JSONField(name = "app_msg_cnt")
    public void setAppMsgCnt(int appMsgCnt) {
        this.appMsgCnt = appMsgCnt;
    }

    @JSONField(name = "app_msg_list")
    public Object getAppMsgList() {
        return appMsgList;
    }

    @JSONField(name = "app_msg_list")
    public void setAppMsgList(Object appMsgList) {
        this.appMsgList = appMsgList;
    }
}
