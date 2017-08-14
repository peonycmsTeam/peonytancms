package com.peony.peonyfront.briefreport.model;

import java.util.Date;

import com.peony.core.base.pojo.BasePojo;

/**
 * 简报模板
 * 
 * @author jhj
 */
public class BriefReportTemp extends BasePojo {
    /**
     * 
     */
    private static final long serialVersionUID = -3125638798860992796L;

    private Integer           briefreportTempId;

    private String            userId;

    private Date              createTime;

    private String            name;

    private String            address;

    private String            url;

    private String            userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getBriefreportTempId() {
        return briefreportTempId;
    }

    public void setBriefreportTempId(Integer briefreportTempId) {
        this.briefreportTempId = briefreportTempId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}