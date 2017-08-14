package com.peony.peonyfront.subject.model;

import java.util.Date;

import com.peony.core.base.pojo.BasePojo;

/**
 * 订制舆情信息模版类
 * 
 * @author vv
 *
 */
public class SubjectPage extends BasePojo {

    private static final long serialVersionUID = 1L;

    private String            id;

    private Integer           subjectid;

    private String            pageid;

    private Date              updatetime;

    private String            idArray;

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

    // 不一样 线上是String
    private String            groupseedid;

    private String            isMaininfo;

    private Integer           isRead;

    private String            beginTime;

    private String            endTime;

    private String            time;                 // 用于回显时间点

    private Integer           userid;

    private String            startTime;            // 导出开始时间

    private String            overTime;             // 导出结束时间

    private Integer           newslev;              // 区分定制和境外舆情

    private String            newslevelConditions;

    private Integer           count;

    private String            subjectidArray;

    private Integer           state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getSubjectidArray() {
        return subjectidArray;
    }

    public void setSubjectidArray(String subjectidArray) {
        this.subjectidArray = subjectidArray;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getIdArray() {
        return idArray;
    }

    public void setIdArray(String idArray) {
        this.idArray = idArray;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getOverTime() {
        return overTime;
    }

    public void setOverTime(String overTime) {
        this.overTime = overTime;
    }

    public Integer getNewslev() {
        return newslev;
    }

    public void setNewslev(Integer newslev) {
        this.newslev = newslev;
    }

    public String getNewslevelConditions() {
        return newslevelConditions;
    }

    public void setNewslevelConditions(String newslevelConditions) {
        this.newslevelConditions = newslevelConditions;
    }

}