package com.peony.peonyfront.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.system.dao.BaseClassMapper;
import com.peony.peonyfront.system.model.BaseClass;

/**
 * 区域服务
 * 
 * @author jhj
 * @date
 */

@Service
public class BaseClassServiceImpl implements BaseClassService {
    @Resource
    private BaseClassMapper baseClassMapper;

    @Override
    public List<BaseClass> selectPublicBaseClass() {
        return this.baseClassMapper.selectPublicBaseClass();
    }

    @Override
    public List<BaseClass> selectBaseClassByUserId(int userId) {
        return this.baseClassMapper.selectBaseClassByUserId(userId);
    }

}
