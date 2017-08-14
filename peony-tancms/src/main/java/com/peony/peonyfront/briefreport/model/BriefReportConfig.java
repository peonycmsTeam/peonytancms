package com.peony.peonyfront.briefreport.model;

import java.util.Date;

import com.peony.core.base.pojo.BasePojo;

public class BriefReportConfig extends BasePojo {
    /**
     * 
     */
    private static final long serialVersionUID = -5648224661101559961L;

    private Integer           briefreportConfigId;

    private Integer           userId;

    private Integer           briefreportTempId;

    private Date              createTime;

    public Integer getBriefreportConfigId() {
        return briefreportConfigId;
    }

    public void setBriefreportConfigId(Integer briefreportConfigId) {
        this.briefreportConfigId = briefreportConfigId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBriefreportTempId() {
        return briefreportTempId;
    }

    public void setBriefreportTempId(Integer briefreportTempId) {
        this.briefreportTempId = briefreportTempId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}