package com.peony.peonyfront.attention.dao;

import com.peony.peonyfront.attention.model.AttentionInfocnt;

public interface AttentionInfocntMapper {
    int deleteByPrimaryKey(Integer attentionInfoId);

    int insert(AttentionInfocnt record);

    int insertSelective(AttentionInfocnt record);

    AttentionInfocnt selectByPrimaryKey(Integer attentionInfoId);

    int updateByPrimaryKeySelective(AttentionInfocnt record);

    int updateByPrimaryKey(AttentionInfocnt record);
}