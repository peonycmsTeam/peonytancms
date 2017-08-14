package com.peony.peonyfront.baseclassref.service;

import java.util.List;

import com.peony.peonyfront.baseclassref.model.BaseclassRef;

public interface BaseclassRefService {
    /**
     * 根据pid查找该订阅下的信息列表
     * 
     * @param basPid
     * @return
     */
    List<BaseclassRef> findBsseclassInfoList(int basPid);
}
