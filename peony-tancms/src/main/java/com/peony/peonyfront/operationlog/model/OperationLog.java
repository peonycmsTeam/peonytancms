package com.peony.peonyfront.operationlog.model;

import java.util.Date;

import com.peony.core.base.pojo.BasePojo;

public class OperationLog extends BasePojo {
    private String  operationLogId;

    private Date    time;

    private Integer userId;

    private String  name;

    private String  type;          // 专门用户判断登录

    private String  loginType;

    private String  content1;

    private String  operateType;   // 操作类型 增删改查

    private String  operateModule;

    private Integer counts;        // 字段不在数据库中 只用于统计

    private String  beginTime;

    private String  endTime;

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public OperationLog() {

    }

    /** 构造函数 **/
    public OperationLog(Date time, Integer userId, String name, String type, String loginType, String content1, String operateType, String operateModule) {
        super();
        this.time = time;
        this.userId = userId;
        this.name = name;
        this.type = type;
        this.loginType = loginType;
        this.content1 = content1;
        this.operateType = operateType;
        this.operateModule = operateModule;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getOperateModule() {
        return operateModule;
    }

    public void setOperateModule(String operateModule) {
        this.operateModule = operateModule;
    }

    public String getOperationLogId() {
        return operationLogId;
    }

    public void setOperationLogId(String operationLogId) {
        this.operationLogId = operationLogId;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

}