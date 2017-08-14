package com.peony.peonyfront.topic.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.topic.model.TopicPage;

public interface TopicPageService {
    /**
     * 添加舆情专题
     * 
     * @param record
     * @return
     */
    int insertSelective(TopicPage record);

    /**
     * 根据id查询实体类
     */
    TopicPage selectByPrimaryKey(String id);

    /**
     * 修改专题
     */
    int updateByPrimaryKey(TopicPage record);

    /**
     * 删除专题
     * 
     * @param userkeywsId
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 修改专题
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(TopicPage record);

    /**
     * 分页查询
     * 
     * @param record
     * @return
     */
    Pagination<TopicPage> selectByPage(TopicPage record);

    /**
     * 查询汇总
     * 
     * @param id
     * @param sTime
     * @param eTime
     * @return
     */
    List selectByCount(Map map);

    /**
     * 查询分类媒体汇总
     * 
     * @param id
     * @param sTime
     * @param eTime
     * @return
     */
    List selectByMedia(Map map);

    /**
     * 查询媒体汇总
     * 
     * @param id
     * @param sTime
     * @param eTime
     * @return
     */
    List selectByMediaCount(Map map);

    /**
     * 查询汇总
     * 
     * @param id
     * @param sTime
     * @return
     */
    List selectByTypeCount(Map map);

    /**
     * 批量删除
     * 
     * @param ids
     * @return
     */
    int delTopicPageByIds(@Param(value = "ids") String[] ids, int userid);

    /**
     * 根据id查询出所选项
     * 
     * @param ids
     * @return
     */
    List<TopicPage> selectTopicPageByIds(@Param(value = "ids") String[] ids, Integer userid);

    /**
     * 导出时间选择项
     * 
     * @param record
     * @return
     */
    List<TopicPage> selectTopicPageBySelectTime(TopicPage record);
}
