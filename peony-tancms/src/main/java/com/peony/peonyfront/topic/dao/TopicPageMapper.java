package com.peony.peonyfront.topic.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.peony.peonyfront.topic.model.TopicPage;

public interface TopicPageMapper {
    int deleteByPrimaryKey(String id);

    int insert(TopicPage record);

    int insertSelective(TopicPage record);

    TopicPage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TopicPage record);

    int updateByPrimaryKey(TopicPage record);

    List<TopicPage> selectByPage(TopicPage record);

    List selectByCount(Map map);

    List selectByMediaCount(Map map);

    List selectByMedia(Map map);

    List selectByTypeCount(Map map);

    /**
     * 批量删除
     * 
     * @param ids
     * @return
     */
    int delTopicPageByIds(@Param(value = "ids") String[] ids, @Param(value = "userid") Integer userid);

    /**
     * 根据id查询出所选项
     * 
     * @param ids
     * @return
     */
    List<TopicPage> selectTopicPageByIds(@Param(value = "ids") String[] ids, @Param(value = "userid") Integer userid);

    /**
     * 导出时间选择项
     * 
     * @param record
     * @return
     */
    List<TopicPage> selectTopicPageBySelectTime(TopicPage record);

    /**
     * 删除事件专题下的数据
     * 
     * @param topicId
     * @return
     */
    int delTopicPageByTopicId(@Param(value = "topicid") Integer topicid, @Param(value = "userid") Integer userid);
}