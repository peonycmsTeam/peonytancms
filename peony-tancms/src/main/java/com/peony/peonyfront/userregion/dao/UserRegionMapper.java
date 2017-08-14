package com.peony.peonyfront.userregion.dao;

import java.util.List;

import com.peony.peonyfront.userregion.model.UserRegion;

public interface UserRegionMapper {
    int deleteByPrimaryKey(Integer regionId);

    int insert(UserRegion record);

    int insertSelective(UserRegion record);

    UserRegion selectByPrimaryKey(Integer regionId);

    int updateByPrimaryKeySelective(UserRegion record);

    int updateByPrimaryKey(UserRegion record);

    /**
     * 根据userId查询出用户所对应的地域
     * 
     * @param regionId
     * @return
     */
    UserRegion selectByUserId(Integer userId);
}