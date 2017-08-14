package com.peony.peonyfront.thinktank.model;

import com.peony.core.base.pojo.BasePojo;

public class Channel extends BasePojo {
    private static final long serialVersionUID = 1L;

    private Integer           channelId;

    private Integer           channelPid;

    private String            name;

    private String            orderno;

    private Integer           infoNum;              // 该节点下的文章总数

    private Integer           seminarNum;           // 专题推荐总数

    private Integer           caseNum;              // 案例推荐总数

    private String            groupName;            // 所属分类

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getChannelPid() {
        return channelPid;
    }

    public void setChannelPid(Integer channelPid) {
        this.channelPid = channelPid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public Integer getInfoNum() {
        return infoNum;
    }

    public void setInfoNum(Integer infoNum) {
        this.infoNum = infoNum;
    }

    public Integer getSeminarNum() {
        return seminarNum;
    }

    public void setSeminarNum(Integer seminarNum) {
        this.seminarNum = seminarNum;
    }

    public Integer getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(Integer caseNum) {
        this.caseNum = caseNum;
    }

}