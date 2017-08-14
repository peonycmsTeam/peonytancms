package com.peony.peonyfront.mail.model;

import java.util.Date;

public class MailConfig {
    private Integer mailConfigId;

    private Integer userId;

    private Integer mailTempId;

    private Date    createTime;

    public Integer getMailConfigId() {
        return mailConfigId;
    }

    public void setMailConfigId(Integer mailConfigId) {
        this.mailConfigId = mailConfigId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMailTempId() {
        return mailTempId;
    }

    public void setMailTempId(Integer mailTempId) {
        this.mailTempId = mailTempId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}