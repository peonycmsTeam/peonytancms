package com.peony.peonyfront.system.dao;

import java.util.List;

import com.peony.peonyfront.system.model.BaseClass;

public interface BaseClassMapper {

    /**
     * 查询公共舆情
     * 
     * @return
     */
    List<BaseClass> selectPublicBaseClass();

    List<BaseClass> selectBaseClassByUserId(Integer userId);
}