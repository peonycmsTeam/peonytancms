package com.peony.peonyfront.system.model;

import com.peony.core.base.pojo.BasePojo;

public class BaseClass extends BasePojo {

    private static final long serialVersionUID = 1L;

    private Integer           baseclassId;

    private Integer           baseclassPid;

    private String            name;

    private String            order;

    private String            explain;

    private Integer           userId;

    public Integer getBaseclassId() {
        return baseclassId;
    }

    public void setBaseclassId(Integer baseclassId) {
        this.baseclassId = baseclassId;
    }

    public Integer getBaseclassPid() {
        return baseclassPid;
    }

    public void setBaseclassPid(Integer baseclassPid) {
        this.baseclassPid = baseclassPid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}