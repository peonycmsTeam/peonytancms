package com.peony.peonyfront.briefreport.model;

import com.peony.core.base.pojo.BasePojo;

public class BriefreportInfoCnt extends BasePojo {

    private static final long serialVersionUID = 1L;

    private Integer           briefreportInfoId;

    private String            content;

    private Integer           orderno;

    public Integer getBriefreportInfoId() {
        return this.briefreportInfoId;
    }

    public void setBriefreportInfoId(Integer briefreportInfoId) {
        this.briefreportInfoId = briefreportInfoId;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getOrderno() {
        return this.orderno;
    }

    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }
}