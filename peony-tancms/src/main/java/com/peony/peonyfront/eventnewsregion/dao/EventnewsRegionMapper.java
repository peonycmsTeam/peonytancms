package com.peony.peonyfront.eventnewsregion.dao;

import com.peony.peonyfront.eventnewsregion.model.EventnewsRegion;

public interface EventnewsRegionMapper {
    int deleteByPrimaryKey(String id);

    int insert(EventnewsRegion record);

    int insertSelective(EventnewsRegion record);

    EventnewsRegion selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EventnewsRegion record);

    int updateByPrimaryKey(EventnewsRegion record);
}