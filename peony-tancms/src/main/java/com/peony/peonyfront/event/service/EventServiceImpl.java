package com.peony.peonyfront.event.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.event.dao.EventMapper;
import com.peony.peonyfront.event.model.Event;

@Service
public class EventServiceImpl implements EventService {

    @Resource
    private EventMapper eventMapper;

    @Override
    public List<Event> findEventByEventIdArray(Event event) {
        return this.eventMapper.selectEventByEventIdArray(event);
    }

    @Override
    public List<Event> findEventByIsPublic(Event event) {
        return this.eventMapper.selectEventByIsPublic(event);
    }

    @Override
    public Event selectByPrimaryKey(Integer eventid) {
        return this.eventMapper.selectByPrimaryKey(eventid);
    }

    @Override
    public List<Event> findEvent() {
        return this.eventMapper.findEvent();
    }

}
