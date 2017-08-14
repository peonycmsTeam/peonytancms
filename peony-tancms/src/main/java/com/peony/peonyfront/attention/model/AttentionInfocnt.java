package com.peony.peonyfront.attention.model;

import com.peony.core.base.pojo.BasePojo;

/**
 * 舆情内容表
 *
 * @author vv
 *
 */
public class AttentionInfocnt extends BasePojo {

    private static final long serialVersionUID = 1L;

    private Integer           attentionInfoId;      // 主键id

    private String            content;              // 内容

    private Integer           orderno;              // 次序

    public Integer getAttentionInfoId() {
        return this.attentionInfoId;
    }

    public void setAttentionInfoId(Integer attentionInfoId) {
        this.attentionInfoId = attentionInfoId;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getOrderno() {
        return this.orderno;
    }

    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }
}