package com.peony.peonyfront.topic.model;

import java.util.Date;

import com.peony.core.base.pojo.BasePojo;

public class TopicPage extends BasePojo {
    private String  id;

    private Integer topicid;

    private String  pageid;

    private Date    updatetime;

    private String  url;

    private String  website;

    private Date    downloaddate;

    private String  title;

    private String  summary;

    private Integer clickcount;

    private Integer replycount;

    private Integer forwardcount;

    private Integer type;

    private Date    publishdate;

    private Integer newslevel;

    private Integer polarity;

    private Integer userid;

    private String  stime;       // 查询开始时间

    private String  etime;       // 查询结束时间

    private String  daytime;     // 当天时间

    private String  exportStime; // 导出开始时间

    private String  exportEtime; // 导出结束时间

    public String getExportStime() {
        return exportStime;
    }

    public void setExportStime(String exportStime) {
        this.exportStime = exportStime;
    }

    public String getExportEtime() {
        return exportEtime;
    }

    public void setExportEtime(String exportEtime) {
        this.exportEtime = exportEtime;
    }

    public String getDaytime() {
        return daytime;
    }

    public void setDaytime(String daytime) {
        this.daytime = daytime;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public Integer getTopicid() {
        return topicid;
    }

    public void setTopicid(Integer topicid) {
        this.topicid = topicid;
    }

    public String getPageid() {
        return pageid;
    }

    public void setPageid(String pageid) {
        this.pageid = pageid;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Date getDownloaddate() {
        return downloaddate;
    }

    public void setDownloaddate(Date downloaddate) {
        this.downloaddate = downloaddate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public Integer getNewslevel() {
        return newslevel;
    }

    public void setNewslevel(Integer newslevel) {
        this.newslevel = newslevel;
    }

    public Integer getPolarity() {
        return polarity;
    }

    public void setPolarity(Integer polarity) {
        this.polarity = polarity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @param id
     * @param topicid
     * @param pageid
     * @param updatetime
     * @param url
     * @param website
     * @param downloaddate
     * @param title
     * @param summary
     * @param clickcount
     * @param replycount
     * @param forwardcount
     * @param type
     * @param publishdate
     * @param newslevel
     * @param polarity
     * @param userid
     * @param stime
     * @param etime
     */
    public TopicPage(String id, Integer topicid, String pageid, Date updatetime, String url, String website, Date downloaddate, String title, String summary, Integer clickcount, Integer replycount, Integer forwardcount, Integer type, Date publishdate, Integer newslevel, Integer polarity, Integer userid, String stime, String etime) {
        super();
        this.id = id;
        this.topicid = topicid;
        this.pageid = pageid;
        this.updatetime = updatetime;
        this.url = url;
        this.website = website;
        this.downloaddate = downloaddate;
        this.title = title;
        this.summary = summary;
        this.clickcount = clickcount;
        this.replycount = replycount;
        this.forwardcount = forwardcount;
        this.type = type;
        this.publishdate = publishdate;
        this.newslevel = newslevel;
        this.polarity = polarity;
        this.userid = userid;
        this.stime = stime;
        this.etime = etime;
    }

    /**
     * 
     */
    public TopicPage() {
        super();
    }
}