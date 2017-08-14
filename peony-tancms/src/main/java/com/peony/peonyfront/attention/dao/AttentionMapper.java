package com.peony.peonyfront.attention.dao;

import java.util.List;

import com.peony.peonyfront.attention.model.Attention;

public interface AttentionMapper {
    int deleteByPrimaryKey(Integer attentionId);

    int insert(Attention record);

    int insertSelective(Attention record);

    Attention selectByPrimaryKey(Integer attentionId);

    int updateByPrimaryKeySelective(Attention record);

    int updateByPrimaryKey(Attention record);

    List<Attention> selectByPage(Attention attention);

    List<Attention> selectAll(Attention attention);

}