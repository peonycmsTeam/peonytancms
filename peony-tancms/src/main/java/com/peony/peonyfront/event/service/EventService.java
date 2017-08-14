package com.peony.peonyfront.event.service;

import java.util.List;

import com.peony.peonyfront.event.model.Event;

public interface EventService {

    List<Event> findEventByEventIdArray(Event event);

    List<Event> findEventByIsPublic(Event event);

    Event selectByPrimaryKey(Integer eventid);

    List<Event> findEvent();
}
