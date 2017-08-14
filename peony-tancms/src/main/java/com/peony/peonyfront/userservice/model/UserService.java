package com.peony.peonyfront.userservice.model;

import java.util.Date;

import com.peony.core.base.pojo.BasePojo;

public class UserService extends BasePojo {
    private Integer userserviceId;

    private Integer userId;

    private Integer keywsNumber;

    private Integer warnKeywsNumber;

    private Integer emailNumber;

    private Integer phoneNumber;

    private String  reportTime;

    private Integer mobileNumber;

    private String  warnReTime;

    private Date    deadline;

    private String  status;

    private String  sendMethod;

    public Integer getUserserviceId() {
        return userserviceId;
    }

    public void setUserserviceId(Integer userserviceId) {
        this.userserviceId = userserviceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getKeywsNumber() {
        return keywsNumber;
    }

    public void setKeywsNumber(Integer keywsNumber) {
        this.keywsNumber = keywsNumber;
    }

    public Integer getWarnKeywsNumber() {
        return warnKeywsNumber;
    }

    public void setWarnKeywsNumber(Integer warnKeywsNumber) {
        this.warnKeywsNumber = warnKeywsNumber;
    }

    public Integer getEmailNumber() {
        return emailNumber;
    }

    public void setEmailNumber(Integer emailNumber) {
        this.emailNumber = emailNumber;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public Integer getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Integer mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getWarnReTime() {
        return warnReTime;
    }

    public void setWarnReTime(String warnReTime) {
        this.warnReTime = warnReTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSendMethod() {
        return sendMethod;
    }

    public void setSendMethod(String sendMethod) {
        this.sendMethod = sendMethod;
    }
}