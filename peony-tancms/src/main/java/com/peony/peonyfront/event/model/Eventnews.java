package com.peony.peonyfront.event.model;

import java.util.Date;

import com.peony.core.base.pojo.BasePojo;

public class Eventnews extends BasePojo {

    private static final long serialVersionUID = 1L;

    private String            id;

    private Integer           eventid;

    private String            summary;

    private String            title;

    private Date              producedate;

    private String            website;

    private String            websiteplate;

    private String            url;

    private String            dateYm;

    private Integer           polarity;

    private Integer           templateid;

    private Integer           type;

    private Integer           sitepriority;

    private Integer           newslevel;

    private String            pageid;

    private Date              publishdate;

    private Integer           groupcount;

    private String            groupseedid;

    private String            isMaininfo;

    private String            regionID;

    private String            eventArray;

    private String            timeMethod;

    private String            beginTime;

    private String            endTime;

    private Integer           provinceid;

    private Integer           count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(Integer provinceid) {
        this.provinceid = provinceid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getEventid() {
        return eventid;
    }

    public void setEventid(Integer eventid) {
        this.eventid = eventid;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getProducedate() {
        return producedate;
    }

    public void setProducedate(Date producedate) {
        this.producedate = producedate;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDateYm() {
        return dateYm;
    }

    public void setDateYm(String dateYm) {
        this.dateYm = dateYm;
    }

    public Integer getPolarity() {
        return polarity;
    }

    public void setPolarity(Integer polarity) {
        this.polarity = polarity;
    }

    public Integer getTemplateid() {
        return templateid;
    }

    public void setTemplateid(Integer templateid) {
        this.templateid = templateid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSitepriority() {
        return sitepriority;
    }

    public void setSitepriority(Integer sitepriority) {
        this.sitepriority = sitepriority;
    }

    public Integer getNewslevel() {
        return newslevel;
    }

    public void setNewslevel(Integer newslevel) {
        this.newslevel = newslevel;
    }

    public String getPageid() {
        return pageid;
    }

    public void setPageid(String pageid) {
        this.pageid = pageid;
    }

    public String getWebsiteplate() {
        return websiteplate;
    }

    public void setWebsiteplate(String websiteplate) {
        this.websiteplate = websiteplate;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
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

    public String getRegionID() {
        return regionID;
    }

    public void setRegionID(String regionID) {
        this.regionID = regionID;
    }

    public String getEventArray() {
        return eventArray;
    }

    public void setEventArray(String eventArray) {
        this.eventArray = eventArray;
    }

    public String getTimeMethod() {
        return timeMethod;
    }

    public void setTimeMethod(String timeMethod) {
        this.timeMethod = timeMethod;
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
}