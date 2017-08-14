package com.peony.peonyfront.report.model;

import java.util.Date;

import com.peony.core.base.pojo.BasePojo;

/**
 * 日报模板
 * 
 * @author jhj
 */
public class ReportTemp extends BasePojo {
    private Integer reportTempId;

    private Date    createTime;

    private String  name;

    private String  userId;

    private String  isShare;

    private String  address;

    public Integer getReportTempId() {
        return reportTempId;
    }

    public void setReportTempId(Integer reportTempId) {
        this.reportTempId = reportTempId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIsShare() {
        return isShare;
    }

    public void setIsShare(String isShare) {
        this.isShare = isShare;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}