package com.peony.peonyfront.userregion.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.userregion.dao.UserRegionMapper;
import com.peony.peonyfront.userregion.model.UserRegion;

@Service
public class UserRegionServiceImpl implements UserRegionService {
    @Resource
    private UserRegionMapper UserRegionMapper;

    @Override
    public UserRegion selectByUserId(Integer userId) {
        return this.UserRegionMapper.selectByUserId(userId);
    }

}
