package com.peony.peonyfront.userservice.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.userservice.dao.UserServiceMapper;
import com.peony.peonyfront.userservice.model.UserService;

@Service
public class UserServiceServiceImpl implements UserServiceService {
    @Resource
    private UserServiceMapper userServiceMapper;

    @Override
    public UserService selectByPrimaryKey(Integer userserviceId) {
        return this.userServiceMapper.selectByPrimaryKey(userserviceId);
    }

    @Override
    public UserService selectByUserId(Integer userId) {
        return this.userServiceMapper.selectByUserId(userId);
    }

}
