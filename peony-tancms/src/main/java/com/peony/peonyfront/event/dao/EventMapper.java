package com.peony.peonyfront.event.dao;

import java.util.List;

import com.peony.peonyfront.event.model.Event;

public interface EventMapper {
    int deleteByPrimaryKey(Integer eventid);

    int insert(Event record);

    int insertSelective(Event record);

    Event selectByPrimaryKey(Integer eventid);

    int updateByPrimaryKeySelective(Event record);

    int updateByPrimaryKey(Event record);

    List<Event> selectEventByEventIdArray(Event event);

    List<Event> selectEventByIsPublic(Event event);

    List<Event> findEvent();
}