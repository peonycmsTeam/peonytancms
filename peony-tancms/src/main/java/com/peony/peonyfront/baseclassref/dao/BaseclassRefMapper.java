package com.peony.peonyfront.baseclassref.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peony.peonyfront.baseclassref.model.BaseclassRef;

public interface BaseclassRefMapper {
    int deleteByPrimaryKey(Integer baseclassRefId);

    int insert(BaseclassRef record);

    int insertSelective(BaseclassRef record);

    BaseclassRef selectByPrimaryKey(Integer baseclassRefId);

    int updateByPrimaryKeySelective(BaseclassRef record);

    int updateByPrimaryKey(BaseclassRef record);

    /**
     * 根据订阅id查找该订阅下的信息列表
     *
     * @param basPid
     * @return
     */
    List<BaseclassRef> findBsseclassInfoList(@Param(value = "basPid") int basPid);
}