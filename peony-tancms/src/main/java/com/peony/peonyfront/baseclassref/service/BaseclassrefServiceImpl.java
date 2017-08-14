package com.peony.peonyfront.baseclassref.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.baseclassref.dao.BaseclassRefMapper;
import com.peony.peonyfront.baseclassref.model.BaseclassRef;

/**
 * 订阅信息管理
 * 
 * @author zhyz
 *
 */
@Service
public class BaseclassrefServiceImpl implements BaseclassRefService {
    @Resource
    private BaseclassRefMapper baseclassRefMapper;

    @Override
    public List<BaseclassRef> findBsseclassInfoList(int basPid) {

        return this.baseclassRefMapper.findBsseclassInfoList(basPid);
    }

}
