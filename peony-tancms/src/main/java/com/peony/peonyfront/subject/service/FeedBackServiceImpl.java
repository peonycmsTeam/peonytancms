package com.peony.peonyfront.subject.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.subject.dao.FeedBackMapper;
import com.peony.peonyfront.subject.model.FeedBack;

@Service
public class FeedBackServiceImpl implements FeedBackService {

    @Resource
    private FeedBackMapper feedBackMapper;

    @Override
    public int insert(FeedBack feedBack) {
        return this.feedBackMapper.insertSelective(feedBack);
    }

}
