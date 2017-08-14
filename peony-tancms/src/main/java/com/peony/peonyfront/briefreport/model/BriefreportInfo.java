package com.peony.peonyfront.briefreport.model;

import java.util.Date;

import com.peony.core.base.pojo.BasePojo;

public class BriefreportInfo extends BasePojo {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer           briefreportInfoId;

    private Integer           briefreportId;

    private String            title;

    private Integer           type;

    private String            website;

    private String            url;

    private Date              ptime;

    private Date              publishdate;

    private Integer           visitcount;

    private Integer           reply;

    private String            summary;

    private Integer           polarity;

    private String            pageId;

    private Integer           forwardcount;

    private String            selectDate;           // 选择显示天数

    private String            beginDate;            // 开始时间

    private int               pageSize;             // 每页显示条数

    private String            time;

    private Integer           eventId;              // 行业舆情节点id

    private Integer           subjectId;            // 定制舆情节点id

    private Integer           newsLevel;            // 区分定制和境外舆情

    public Integer getNewsLevel() {
        return newsLevel;
    }

    public void setNewsLevel(Integer newsLevel) {
        this.newsLevel = newsLevel;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getBriefreportInfoId() {
        return briefreportInfoId;
    }

    public void setBriefreportInfoId(Integer briefreportInfoId) {
        this.briefreportInfoId = briefreportInfoId;
    }

    public Integer getBriefreportId() {
        return briefreportId;
    }

    public void setBriefreportId(Integer briefreportId) {
        this.briefreportId = briefreportId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public Date getPtime() {
        return ptime;
    }

    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }

    public Integer getVisitcount() {
        return visitcount;
    }

    public void setVisitcount(Integer visitcount) {
        this.visitcount = visitcount;
    }

    public Integer getReply() {
        return reply;
    }

    public void setReply(Integer reply) {
        this.reply = reply;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getPolarity() {
        return polarity;
    }

    public void setPolarity(Integer polarity) {
        this.polarity = polarity;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public Integer getForwardcount() {
        return forwardcount;
    }

    public void setForwardcount(Integer forwardcount) {
        this.forwardcount = forwardcount;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

}