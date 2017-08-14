package com.peony.peonyfront.topic.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.topic.model.Topic;

public interface TopicService {
    /**
     * 添加舆情专题
     * 
     * @param record
     * @return
     */
    int insertSelective(Topic record);

    /**
     * 根据id查询实体类
     */
    Topic selectByPrimaryKey(Integer id);

    /**
     * 修改专题
     */
    int updateByPrimaryKey(Topic record);

    /**
     * 删除专题
     * 
     * @param userkeywsId
     * @return
     */
    int deleteByPrimaryKey(Topic record);

    /**
     * 修改专题
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Topic record);

    /**
     * 分页查询
     * 
     * @param record
     * @return
     */
    Pagination<Topic> selectByPage(Topic record);

    /**
     * 查询用户专题个数
     * 
     * @param userId
     * @return
     */
    int selectTopicCount(@Param(value = "userId") Integer userId);

}
