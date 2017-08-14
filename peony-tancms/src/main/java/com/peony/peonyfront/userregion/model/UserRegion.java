package com.peony.peonyfront.userregion.model;

import com.peony.core.base.pojo.BasePojo;

public class UserRegion extends BasePojo {
    private Integer regionId;

    private Integer userId;

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}