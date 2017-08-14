package com.peony.peonyfront.eventindex.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.eventindex.dao.EventIndexMapper;
import com.peony.peonyfront.eventindex.model.EventIndex;

@Service
public class EventIndexServiceImpl implements EventIndexService {

    @Resource
    private EventIndexMapper eventIndexMapper;

    @Override
    public int insert(EventIndex eventIndex) {
        return this.eventIndexMapper.insertSelective(eventIndex);
    }

    @Override
    public List<EventIndex> selectEventIndexByDate(String time) {
        return this.eventIndexMapper.selectEventIndexByDate(time);
    }

    @Override
    public List<EventIndex> selectEventIndexChinaByDate(EventIndex eventindex) {
        // TODO Auto-generated method stub
        return this.eventIndexMapper.selectEventIndexChinaByDate(eventindex);
    }

    @Override
    public List<EventIndex> selectEventIndexShengByDate(EventIndex eventindex) {
        // TODO Auto-generated method stub
        return this.eventIndexMapper.selectEventIndexShengByDate(eventindex);
    }

    @Override
    public List<EventIndex> selectEventIndexChinaZhouByDate(EventIndex eventindex) {
        // TODO Auto-generated method stub
        return this.eventIndexMapper.selectEventIndexChinaZhouByDate(eventindex);
    }

    @Override
    public List<EventIndex> selectEventIndexChinaYueByDate(EventIndex eventindex) {
        // TODO Auto-generated method stub
        return this.eventIndexMapper.selectEventIndexChinaYueByDate(eventindex);
    }

    @Override
    public List<EventIndex> selectEventIndexShengZhouByDate(EventIndex eventindex) {
        // TODO Auto-generated method stub
        return this.eventIndexMapper.selectEventIndexShengZhouByDate(eventindex);
    }

    @Override
    public List<EventIndex> selectEventIndexShengYueByDate(EventIndex eventindex) {
        // TODO Auto-generated method stub
        return this.eventIndexMapper.selectEventIndexShengYueByDate(eventindex);
    }
}
