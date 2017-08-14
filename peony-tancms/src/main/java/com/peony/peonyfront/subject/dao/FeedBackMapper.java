package com.peony.peonyfront.subject.dao;

import com.peony.peonyfront.subject.model.FeedBack;

public interface FeedBackMapper {

    /**
     * 新增
     * 
     * @param feedBack
     * @return
     */
    int insertSelective(FeedBack feedBack);
}