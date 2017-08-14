package com.peony.peonyfront.subject.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.peony.core.base.pojo.BasePojo;

public class Subject extends BasePojo {

    private static final long serialVersionUID = 1L;

    private Integer           id;

    private Integer           userid;

    private Integer           state;

    private String            name;

    private Date              createtime;

    private Date              lastupdatedtime;

    private Date              droptime;

    private Date              updateTime;

    private Integer           pid;

    private String            ischildnodes;

    private Integer           count;

    // 用来存储当前节点下的下级子节点
    private List<Subject>     list             = new ArrayList<Subject>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    public Date getDroptime() {
        return droptime;
    }

    public void setDroptime(Date droptime) {
        this.droptime = droptime;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getIschildnodes() {
        return ischildnodes;
    }

    public void setIschildnodes(String ischildnodes) {
        this.ischildnodes = ischildnodes;
    }

    public List<Subject> getList() {
        return list;
    }

    public void setList(List<Subject> list) {
        this.list = list;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}