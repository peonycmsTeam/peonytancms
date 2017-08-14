package com.peony.peonyfront.region.dao;

import java.util.List;
import java.util.Map;

import com.peony.peonyfront.region.model.Region;

public interface RegionMapper {
    int deleteByPrimaryKey(Integer regionid);

    int insert(Region record);

    int insertSelective(Region record);

    Region selectByPrimaryKey(Integer regionid);

    int updateByPrimaryKeySelective(Region record);

    int updateByPrimaryKey(Region record);

    /**
     * 根据regionId查找下级地域
     * 
     * @param regionid
     * @return
     */
    List<Region> selectByParentId(Integer regionid);

    List<Region> selectLikeParentId(Region record);

    /**
     * 通过用户id查询所属区域
     * 
     * @param userId
     * @return
     */
    List<Region> selectByUserId(Integer userId);

    List<Region> selectByParentIdAndPK(Integer regionid);

    List<Region> selectRegion();

    List<Region> selectRegionByProvinceId(Map map);

}