package com.peony.peonyfront.system.model;

import java.util.Date;

import com.peony.core.base.pojo.BasePojo;

public class BaseClassKeyws extends BasePojo {

    private static final long serialVersionUID = 1L;

    private Integer           baseclassKeywsId;

    private Integer           baseclassId;

    private String            name;

    private Date              createTime;

    private String            baseclassIdArray;

    private String            content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBaseclassIdArray() {
        return baseclassIdArray;
    }

    public void setBaseclassIdArray(String baseclassIdArray) {
        this.baseclassIdArray = baseclassIdArray;
    }

    public Integer getBaseclassKeywsId() {
        return baseclassKeywsId;
    }

    public void setBaseclassKeywsId(Integer baseclassKeywsId) {
        this.baseclassKeywsId = baseclassKeywsId;
    }

    public Integer getBaseclassId() {
        return baseclassId;
    }

    public void setBaseclassId(Integer baseclassId) {
        this.baseclassId = baseclassId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}