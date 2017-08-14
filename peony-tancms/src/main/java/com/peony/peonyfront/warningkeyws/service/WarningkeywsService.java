package com.peony.peonyfront.warningkeyws.service;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.warningkeyws.model.Warningkeyws;

public interface WarningkeywsService {
    /**
     * 添加关键词设置
     * 
     * @param warningkeyws
     * @return
     */
    int insertWarningkeyws(Warningkeyws warningkeyws);

    public Pagination<Warningkeyws> findByPage(Warningkeyws warningkeyws);

    int deleteByPrimaryKey(Integer warningkeywsId);

    Warningkeyws selectByPrimaryKey(Integer warningkeywsId);

    int updateByPrimaryKey(Warningkeyws warningkeyws);
}
