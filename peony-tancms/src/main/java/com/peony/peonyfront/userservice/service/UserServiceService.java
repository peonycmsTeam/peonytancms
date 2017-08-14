package com.peony.peonyfront.userservice.service;

import com.peony.peonyfront.userservice.model.UserService;

public interface UserServiceService {
    /**
     * 根据主键id查询
     * 
     * @param userserviceId
     * @return
     */
    public UserService selectByPrimaryKey(Integer userserviceId);

    public UserService selectByUserId(Integer userId);
}
