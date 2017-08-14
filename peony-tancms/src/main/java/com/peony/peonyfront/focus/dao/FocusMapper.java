package com.peony.peonyfront.focus.dao;

import java.util.List;

import com.peony.peonyfront.focus.model.Focus;
import com.peony.peonyfront.topic.model.Topic;

public interface FocusMapper {
    int deleteByPrimaryKey(String id);

    int insert(Focus record);

    int insertSelective(Focus record);

    Focus selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Focus record);

    int updateByPrimaryKey(Focus record);

    List<Focus> selectByPage(Focus record);
}