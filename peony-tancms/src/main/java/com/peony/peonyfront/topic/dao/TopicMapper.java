package com.peony.peonyfront.topic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peony.peonyfront.topic.model.Topic;

public interface TopicMapper {
    int deleteByPrimaryKey(Integer topicId);

    int insert(Topic record);

    int insertSelective(Topic record);

    Topic selectByPrimaryKey(Integer topicId);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKey(Topic record);

    List<Topic> selectByPage(Topic record);

    /**
     * 查询用户专题个数
     * 
     * @param userId
     * @return
     */
    int selectTopicCount(@Param(value = "userId") Integer userId);
}