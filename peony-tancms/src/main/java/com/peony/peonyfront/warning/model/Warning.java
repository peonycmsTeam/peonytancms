package com.peony.peonyfront.warning.model;

import java.util.Date;

import com.peony.core.base.pojo.BasePojo;

public class Warning extends BasePojo {
    private static final long serialVersionUID = 1L;

    private String            warningId;

    private String            title;

    private Date              warnTime;

    private String            pageId;

    private String            url;

    private String            summary;

    private Integer           type;

    private Integer           userId;

    private String            isRead;

    private String            website;

    private Integer           waringType;

    private Integer           clickcount;

    private Integer           replycount;

    private Integer           forwardcount;

    private Date              publishdate;

    private Integer           polarity;

    private Date              downloaddate;

    private String            selectDate;           // 选择显示天数

    private String            beginDate;            // 开始时间

    private int               pageSize;             // 每页显示条数

    private String            btime;                // 开始时间

    private String            etime;                // 结束时间

    private String            time;                 // 排序时间

    private Integer           newsLevel;            // 区分定制和境外舆情

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWarningId() {
        return warningId;
    }

    public void setWarningId(String warningId) {
        this.warningId = warningId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getWarnTime() {
        return warnTime;
    }

    public void setWarnTime(Date warnTime) {
        this.warnTime = warnTime;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getWaringType() {
        return waringType;
    }

    public void setWaringType(Integer waringType) {
        this.waringType = waringType;
    }

    public Integer getClickcount() {
        return clickcount;
    }

    public void setClickcount(Integer clickcount) {
        this.clickcount = clickcount;
    }

    public Integer getReplycount() {
        return replycount;
    }

    public void setReplycount(Integer replycount) {
        this.replycount = replycount;
    }

    public Integer getForwardcount() {
        return forwardcount;
    }

    public void setForwardcount(Integer forwardcount) {
        this.forwardcount = forwardcount;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public Integer getPolarity() {
        return polarity;
    }

    public void setPolarity(Integer polarity) {
        this.polarity = polarity;
    }

    public Date getDownloaddate() {
        return downloaddate;
    }

    public void setDownloaddate(Date downloaddate) {
        this.downloaddate = downloaddate;
    }

    public String getSelectDate() {
        return selectDate;
    }

    public void setSelectDate(String selectDate) {
        this.selectDate = selectDate;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getBtime() {
        return btime;
    }

    public void setBtime(String btime) {
        this.btime = btime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public Integer getNewsLevel() {
        return newsLevel;
    }

    public void setNewsLevel(Integer newsLevel) {
        this.newsLevel = newsLevel;
    }

}