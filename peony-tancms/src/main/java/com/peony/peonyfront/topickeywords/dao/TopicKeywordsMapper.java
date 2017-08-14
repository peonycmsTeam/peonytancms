package com.peony.peonyfront.topickeywords.dao;

import java.util.List;

import com.peony.peonyfront.topickeywords.model.TopicKeywords;

public interface TopicKeywordsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TopicKeywords record);

    /**
     * 新增操作
     * 
     * @param record
     * @return
     */
    int insertSelective(TopicKeywords record);

    TopicKeywords selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TopicKeywords record);

    int updateByPrimaryKey(TopicKeywords record);

    /**
     * 根据专题表id查询关键词列表
     * 
     * @param pid
     * @return
     */

    List<TopicKeywords> selectTopicListByPid(Integer topicId);

    /**
     * 根据专题id删除关键词
     * 
     * @param id
     * @return
     */
    int deleteByTopicId(Integer topicId);
}