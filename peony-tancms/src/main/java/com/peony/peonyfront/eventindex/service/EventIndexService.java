package com.peony.peonyfront.eventindex.service;

import java.util.List;

import com.peony.peonyfront.eventindex.model.EventIndex;

public interface EventIndexService {

    /**
     * 插入eventIndex
     * 
     * @param eventIndex
     * @return
     */
    public int insert(EventIndex eventIndex);

    /**
     * 查询日期为昨天的eventIndex
     * 
     * @param record
     * @return
     */
    List<EventIndex> selectEventIndexByDate(String time);

    List<EventIndex> selectEventIndexChinaByDate(EventIndex eventindex);

    List<EventIndex> selectEventIndexShengByDate(EventIndex eventindex);

    /**
     * 根据综合指数 对近七天 全国 事件指数top10进行排行
     * 
     * @param eventindex
     * @return
     */
    List<EventIndex> selectEventIndexChinaZhouByDate(EventIndex eventindex);

    /**
     * 根据综合指数 对上月全国 事件指数top10进行排行
     * 
     * @param eventindex
     * @return
     */
    List<EventIndex> selectEventIndexChinaYueByDate(EventIndex eventindex);

    /**
     * 根据综合指数 对近七天全省事件指数top10 进行排行
     * 
     * @param eventindex
     * @return
     */
    List<EventIndex> selectEventIndexShengZhouByDate(EventIndex eventindex);

    /**
     * 根据综合指数 对上月全省事件指数top10 进行排行
     * 
     * @param eventindex
     * @return
     */
    List<EventIndex> selectEventIndexShengYueByDate(EventIndex eventindex);
}
