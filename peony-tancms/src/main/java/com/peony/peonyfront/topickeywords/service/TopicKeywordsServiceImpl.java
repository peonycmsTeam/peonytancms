package com.peony.peonyfront.topickeywords.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.topickeywords.dao.TopicKeywordsMapper;
import com.peony.peonyfront.topickeywords.model.TopicKeywords;

@Service
public class TopicKeywordsServiceImpl implements TopicKeywordsService {
    @Resource
    private TopicKeywordsMapper topicKeywordsMapper;

    /**
     * 根据专题表id查询关键词列表
     *
     * @param pid
     * @return
     */
    @Override
    public List<TopicKeywords> selectTopicListByPid(Integer topicId) {
        return this.topicKeywordsMapper.selectTopicListByPid(topicId);
    }

    /**
     * 根据专题id删除关键词
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByTopicId(Integer topicId) {
        return this.topicKeywordsMapper.deleteByTopicId(topicId);
    }

    @Override
    public int insertSelective(TopicKeywords record) {
        return this.topicKeywordsMapper.insertSelective(record);
    }

}
