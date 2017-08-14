package com.peony.peonyfront.mail.model;

import java.util.Date;

public class MailTemp {
    private Integer mailTempId;

    private Integer userId;

    private Date    createTime;

    private String  name;

    private String  address;

    private String  url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getMailTempId() {
        return mailTempId;
    }

    public void setMailTempId(Integer mailTempId) {
        this.mailTempId = mailTempId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}