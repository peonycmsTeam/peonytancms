package com.peony.peonyfront.system.service;

import java.util.List;

import com.peony.peonyfront.system.model.BaseClass;

/**
 * 基础服务接口
 * 
 * @author jhj
 * @date
 */
public interface BaseClassService {

    /**
     * 查询公共舆情分类
     * 
     * @return
     */
    List<BaseClass> selectPublicBaseClass();

    List<BaseClass> selectBaseClassByUserId(int userId);
}
