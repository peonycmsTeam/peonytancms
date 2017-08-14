package com.peony.peonyfront.briefreport.model;

import java.util.Date;

import com.peony.core.base.pojo.BasePojo;

public class Briefreport extends BasePojo {

    private static final long serialVersionUID = 1L;

    private Integer           briefreportId;        // 简报id

    private String            name;                 // 简报名称

    private Date              time;                 // 简报时间

    private String            userId;               // 用户id

    private String            periods;              // 期数

    private String            company;              // 单位名称

    public Integer getBriefreportId() {
        return briefreportId;
    }

    public void setBriefreportId(Integer briefreportId) {
        this.briefreportId = briefreportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPeriods() {
        return periods;
    }

    public void setPeriods(String periods) {
        this.periods = periods;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

}