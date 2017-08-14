package com.peony.peonyfront.system.service;

import java.util.List;

import com.peony.peonyfront.system.model.BaseClassKeyws;

/**
 * 区域关键字接口
 * 
 * @author jhj
 *
 */
public interface BaseClassKeywsService {
    /**
     * 按地域id 获取列表 分页
     * 
     * @return
     */
    List<BaseClassKeyws> selectBaseClassKeywsByBaseClassId(BaseClassKeyws baseClassKeyws);

    List<BaseClassKeyws> selectBaseClassKeywsByBaseclassKeywsId(BaseClassKeyws baseClassKeyws);
}
