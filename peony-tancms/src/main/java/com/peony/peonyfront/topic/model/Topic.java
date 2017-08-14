package com.peony.peonyfront.topic.model;

import java.util.Date;
import java.util.Map;

import com.peony.core.base.pojo.BasePojo;

public class Topic extends BasePojo {
    private Integer id;

    private Integer userid;

    private String  name;

    private String  abstruct;

    private Integer state;

    private Date    startTime;

    private Date    endTime;

    private Date    createTime;

    private Date    updateTime;

    private Date    lastupdatedtime;

    private Map     dayMap;         // 今日数据
    private Map     countMap;       // 数据总量

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map getDayMap() {
        return dayMap;
    }

    public void setDayMap(Map dayMap) {
        this.dayMap = dayMap;
    }

    public Map getCountMap() {
        return countMap;
    }

    public void setCountMap(Map countMap) {
        this.countMap = countMap;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbstruct() {
        return abstruct;
    }

    public void setAbstruct(String abstruct) {
        this.abstruct = abstruct;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * @param id
     * @param userid
     * @param name
     * @param abstruct
     * @param state
     * @param startTime
     * @param endTime
     * @param createTime
     * @param updateTime
     * @param lastupdatedtime
     * @param dayMap
     * @param countMap
     */
    public Topic(Integer id, Integer userid, String name, String abstruct, Integer state, Date startTime, Date endTime, Date createTime, Date updateTime, Date lastupdatedtime, Map dayMap, Map countMap) {
        super();
        this.id = id;
        this.userid = userid;
        this.name = name;
        this.abstruct = abstruct;
        this.state = state;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.lastupdatedtime = lastupdatedtime;
        this.dayMap = dayMap;
        this.countMap = countMap;
    }

    /**
     * 
     */
    public Topic() {
        super();
    }
}