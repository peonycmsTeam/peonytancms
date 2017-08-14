package com.peony.peonyfront.topickeywords.model;

import com.peony.core.base.pojo.BasePojo;

public class TopicKeywords extends BasePojo {
    private Integer id;

    private Integer topicid;

    private String  name;

    private String  keywords;

    private String  rejectflag;

    private Integer regionid;

    private Integer mainbodyid;

    private Integer eventid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTopicid() {
        return topicid;
    }

    public void setTopicid(Integer topicid) {
        this.topicid = topicid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getRejectflag() {
        return rejectflag;
    }

    public void setRejectflag(String rejectflag) {
        this.rejectflag = rejectflag;
    }

    public Integer getRegionid() {
        return regionid;
    }

    public void setRegionid(Integer regionid) {
        this.regionid = regionid;
    }

    public Integer getMainbodyid() {
        return mainbodyid;
    }

    public void setMainbodyid(Integer mainbodyid) {
        this.mainbodyid = mainbodyid;
    }

    public Integer getEventid() {
        return eventid;
    }

    public void setEventid(Integer eventid) {
        this.eventid = eventid;
    }
}