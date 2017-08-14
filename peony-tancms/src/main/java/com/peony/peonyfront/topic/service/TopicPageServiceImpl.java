package com.peony.peonyfront.topic.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.topic.dao.TopicPageMapper;
import com.peony.peonyfront.topic.model.TopicPage;
import com.peony.peonyfront.util.log.Action;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;

@Service
public class TopicPageServiceImpl implements TopicPageService {
    @Resource
    private TopicPageMapper topicPageMapper;

    @Override
    public TopicPage selectByPrimaryKey(String id) {
        return this.topicPageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(TopicPage record) {
        return this.topicPageMapper.updateByPrimaryKey(record);
    }

    @Override
    @Action(description = "删除事件专题信息", operateMode = OperateMode.事件专题, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.DELETE)
    public int deleteByPrimaryKey(String id) {
        return this.topicPageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TopicPage record) {
        return this.topicPageMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Action(description = "加入事件专题", operateMode = OperateMode.事件专题, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.SAVE)
    public int insertSelective(TopicPage record) {
        return this.topicPageMapper.insert(record);
    }

    @Override
    public Pagination<TopicPage> selectByPage(TopicPage record) {
        List<TopicPage> topics = this.topicPageMapper.selectByPage(record);

        return new Pagination<TopicPage>(topics, record.getPageParameter());
    }

    @Override
    public List selectByCount(Map map) {
        List countList = this.topicPageMapper.selectByCount(map);
        return countList;
    }

    @Override
    public List selectByMedia(Map map) {
        List countList = this.topicPageMapper.selectByMedia(map);
        return countList;
    }

    @Override
    public List selectByMediaCount(Map map) {
        List countList = this.topicPageMapper.selectByMediaCount(map);
        return countList;
    }

    @Override
    public List selectByTypeCount(Map map) {
        List countList = this.topicPageMapper.selectByTypeCount(map);
        return countList;
    }

    @Override
    @Action(description = "删除事件专题信息", operateMode = OperateMode.事件专题, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.DELETE)
    public int delTopicPageByIds(String[] ids, int userid) {
        return this.topicPageMapper.delTopicPageByIds(ids, userid);
    }

    @Override
    public List<TopicPage> selectTopicPageByIds(String[] ids, Integer userid) {
        return this.topicPageMapper.selectTopicPageByIds(ids, userid);
    }

    @Override
    public List<TopicPage> selectTopicPageBySelectTime(TopicPage record) {
        return this.topicPageMapper.selectTopicPageBySelectTime(record);
    }

}
