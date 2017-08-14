package com.peony.peonyfront.eventindex.dao;

import java.util.List;

import com.peony.peonyfront.eventindex.model.EventIndex;

public interface EventIndexMapper {
    int deleteByPrimaryKey(String id);

    int insert(EventIndex record);

    int insertSelective(EventIndex record);

    EventIndex selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EventIndex record);

    int updateByPrimaryKey(EventIndex record);

    List<EventIndex> selectEventIndexByDate(String time);

    /**
     * 根据综合指数 对本月 全国 事件指数top10进行排行
     * 
     * @param eventindex
     * @return
     */
    List<EventIndex> selectEventIndexChinaByDate(EventIndex eventindex);

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
     * 根据综合指数 对本月全省事件指数top10 进行排行
     * 
     * @param eventindex
     * @return
     */
    List<EventIndex> selectEventIndexShengByDate(EventIndex eventindex);

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