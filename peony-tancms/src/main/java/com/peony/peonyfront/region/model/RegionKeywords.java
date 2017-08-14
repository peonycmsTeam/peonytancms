package com.peony.peonyfront.region.model;

import java.util.Date;

import com.peony.core.base.pojo.BasePojo;

public class RegionKeywords extends BasePojo {

    private static final long serialVersionUID = 1L;

    private String            regionid;

    private String            regionwords;

    private Date              modifyTime;

    private String            userName;

    private String            content;

    public String getRegionid() {
        return regionid;
    }

    public void setRegionid(String regionid) {
        this.regionid = regionid;
    }

    public String getRegionwords() {
        return regionwords;
    }

    public void setRegionwords(String regionwords) {
        this.regionwords = regionwords;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}