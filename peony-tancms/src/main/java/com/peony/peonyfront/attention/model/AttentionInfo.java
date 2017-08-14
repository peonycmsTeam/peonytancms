package com.peony.peonyfront.attention.model;

import java.util.Date;

import com.peony.core.base.pojo.BasePojo;

public class AttentionInfo extends BasePojo {

    private static final long serialVersionUID = 1L;

    private Integer           attentionInfoId;

    private Integer           attentionId;

    private String            title;

    private Integer           type;

    private String            website;

    private String            url;

    private Date              ptime;

    private Date              publishdate;

    private Integer           visitcount;

    private Integer           reply;

    private String            summary;

    private String            pageId;

    private Integer           forwardcount;

    private Integer           polarity;

    private Integer           isRead;

    private int               pageSize;

    private String            selectDate;           // 选择显示天数

    private String            beginDate;            // 开始时间

    private String            time;

    private Integer           newsLevel;            // 区分定制和境外舆情

    public Integer getNewsLevel() {
        return this.newsLevel;
    }

    public void setNewsLevel(Integer newsLevel) {
        this.newsLevel = newsLevel;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getAttentionInfoId() {
        return this.attentionInfoId;
    }

    public void setAttentionInfoId(Integer attentionInfoId) {
        this.attentionInfoId = attentionInfoId;
    }

    public Integer getAttentionId() {
        return this.attentionId;
    }

    public void setAttentionId(Integer attentionId) {
        this.attentionId = attentionId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getPtime() {
        return this.ptime;
    }

    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }

    public Date getPublishdate() {
        return this.publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public Integer getVisitcount() {
        return this.visitcount;
    }

    public void setVisitcount(Integer visitcount) {
        this.visitcount = visitcount;
    }

    public Integer getReply() {
        return this.reply;
    }

    public void setReply(Integer reply) {
        this.reply = reply;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPageId() {
        return this.pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public Integer getForwardcount() {
        return this.forwardcount;
    }

    public void setForwardcount(Integer forwardcount) {
        this.forwardcount = forwardcount;
    }

    public Integer getPolarity() {
        return this.polarity;
    }

    public void setPolarity(Integer polarity) {
        this.polarity = polarity;
    }

    public Integer getIsRead() {
        return this.isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSelectDate() {
        return this.selectDate;
    }

    public void setSelectDate(String selectDate) {
        this.selectDate = selectDate;
    }

    public String getBeginDate() {
        return this.beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

}