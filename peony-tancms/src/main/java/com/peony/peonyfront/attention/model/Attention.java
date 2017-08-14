package com.peony.peonyfront.attention.model;

import java.util.Date;

import com.peony.core.base.pojo.BasePojo;

/**
 * 用户收藏关系表
 *
 * @author vv
 *
 */
public class Attention extends BasePojo {

    private static final long serialVersionUID = 1L;

    private Integer           attentionId;

    private String            name;

    private Date              time;

    private String            userId;

    public Integer getAttentionId() {
        return this.attentionId;
    }

    public void setAttentionId(Integer attentionId) {
        this.attentionId = attentionId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return this.time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}