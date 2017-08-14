package com.peony.peonyfront.userregion.service;

import com.peony.peonyfront.userregion.model.UserRegion;

public interface UserRegionService {
    /**
     * 根据userId查询出用户所对应的地域
     * 
     * @param regionId
     * @return
     */
    UserRegion selectByUserId(Integer userId);
}
