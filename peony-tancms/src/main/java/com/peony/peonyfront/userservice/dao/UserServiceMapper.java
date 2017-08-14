package com.peony.peonyfront.userservice.dao;

import com.peony.peonyfront.userservice.model.UserService;

public interface UserServiceMapper {
    int deleteByPrimaryKey(Integer userserviceId);

    int insert(UserService record);

    int insertSelective(UserService record);

    UserService selectByPrimaryKey(Integer userserviceId);

    int updateByPrimaryKeySelective(UserService record);

    int updateByPrimaryKey(UserService record);

    UserService selectByUserId(Integer userId);
}