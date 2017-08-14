package com.peony.peonyfront.topic.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.topic.dao.TopicMapper;
import com.peony.peonyfront.topic.dao.TopicPageMapper;
import com.peony.peonyfront.topic.model.Topic;
import com.peony.peonyfront.topic.util.WhJdcbConnection;
import com.peony.peonyfront.util.log.Action;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;

@Service
public class TopicServiceImpl implements TopicService {
    @Resource
    private TopicMapper     topicMapper;
    @Resource
    private TopicPageMapper topicPageMapper;

    @Override
    public Topic selectByPrimaryKey(Integer id) {
        return this.topicMapper.selectByPrimaryKey(id);
    }

    @Override
    @Action(description = "修改事件专题", operateMode = OperateMode.事件专题, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.UPDATE)
    public int updateByPrimaryKey(Topic record) {
        return this.topicMapper.updateByPrimaryKey(record);
    }

    @Override
    @Action(description = "删除事件专题", operateMode = OperateMode.事件专题, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.DELETE)
    public int deleteByPrimaryKey(Topic record) {
        int i = this.topicMapper.updateByPrimaryKeySelective(record);
        if (i == 1) {
            WhJdcbConnection whJdcbConnection = new WhJdcbConnection();
            whJdcbConnection.DeleteTopic(record);
            this.topicPageMapper.delTopicPageByTopicId(record.getId(), record.getUserid());
        }
        return i;
    }

    @Override
    @Action(description = "修改事件专题", operateMode = OperateMode.事件专题, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.UPDATE)
    public int updateByPrimaryKeySelective(Topic record) {
        int i = this.topicMapper.updateByPrimaryKeySelective(record);
        return i;
    }

    @Override
    @Action(description = "新增事件专题", operateMode = OperateMode.事件专题, loginType = LoginType.PC, type = Type.OPERATE, operateType = OperateType.SAVE)
    public int insertSelective(Topic record) {
        return this.topicMapper.insert(record);
    }

    @Override
    public Pagination<Topic> selectByPage(Topic record) {
        List<Topic> topics = this.topicMapper.selectByPage(record);

        return new Pagination<Topic>(topics, record.getPageParameter());
    }

    @Override
    public int selectTopicCount(Integer userId) {
        return this.topicMapper.selectTopicCount(userId);
    }
}
