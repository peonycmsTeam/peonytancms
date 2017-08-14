package com.peony.peonyfront.region.service;

import java.util.List;
import java.util.Map;

import com.peony.peonyfront.region.model.Region;

public interface RegionService {
    /**
     * 根据regionId查找下级地域
     * 
     * @param regionid
     * @return
     */
    List<Region> selectByParentId(Integer regionid);

    /**
     * 根据主键查询
     * 
     * @param regionid
     * @return
     */
    Region selectByPrimaryKey(Integer regionid);

    /**
     * 根据用户id查询所属区域
     * 
     * @param userId
     * @return
     */
    List<Region> selectByUserId(Integer userId);

    List<Region> selectLikeParentId(Region region);

    List<Region> selectByParentIdAndPK(Integer regionid);

    List<Region> selectRegion();

    List<Region> selectRegionByProvinceId(Map map);

}
