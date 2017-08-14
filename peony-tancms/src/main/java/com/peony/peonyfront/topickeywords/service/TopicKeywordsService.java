package com.peony.peonyfront.topickeywords.service;

import java.util.List;

import com.peony.peonyfront.topickeywords.model.TopicKeywords;

public interface TopicKeywordsService {
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

    /**
     * 新增操作
     * 
     * @param record
     * @return
     */
    int insertSelective(TopicKeywords record);

}
