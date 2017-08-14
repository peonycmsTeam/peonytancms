package com.peony.peonyfront.focus.model;

import java.util.Date;

import com.peony.core.base.pojo.BasePojo;

public class FocusPage extends BasePojo {
    /**
     * 
     */
    private static final long serialVersionUID = 5869683454591804334L;

    private String            id;

    private String            focusid;

    private String            pageid;

    private String            url;

    private String            website;

    private Date              downloaddate;

    private String            title;

    private String            summary;

    private Integer           clickcount;

    private Integer           replycount;

    private Integer           forwardcount;

    private Integer           type;

    private Date              publishdate;

    private Integer           newslevel;

    private Integer           polarity;

    private Integer           groupcount;

    private String            groupseedid;

    private String            isMaininfo;

    private String            stime;

    private String            etime;

    private String            daytime;                                // 当天时间

    private String            exportStime;                            // 导出开始时间

    private String            exportEtime;                            // 导出结束时间

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFocusid() {
        return focusid;
    }

    public void setFocusid(String focusid) {
        this.focusid = focusid;
    }

    public String getPageid() {
        return pageid;
    }

    public void setPageid(String pageid) {
        this.pageid = pageid;
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

    public Integer getGroupcount() {
        return groupcount;
    }

    public void setGroupcount(Integer groupcount) {
        this.groupcount = groupcount;
    }

    public String getGroupseedid() {
        return groupseedid;
    }

    public void setGroupseedid(String groupseedid) {
        this.groupseedid = groupseedid;
    }

    public String getIsMaininfo() {
        return isMaininfo;
    }

    public void setIsMaininfo(String isMaininfo) {
        this.isMaininfo = isMaininfo;
    }

    /**
     * @param id
     * @param focusid
     * @param pageid
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
     * @param groupcount
     * @param groupseedid
     * @param isMaininfo
     */
    public FocusPage(String id, String focusid, String pageid, String url, String website, Date downloaddate, String title, String summary, Integer clickcount, Integer replycount, Integer forwardcount, Integer type, Date publishdate, Integer newslevel, Integer polarity, Integer groupcount, String groupseedid, String isMaininfo) {
        super();
        this.id = id;
        this.focusid = focusid;
        this.pageid = pageid;
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
        this.groupcount = groupcount;
        this.groupseedid = groupseedid;
        this.isMaininfo = isMaininfo;
    }

    /**
     * 
     */
    public FocusPage() {
        super();
    }
}