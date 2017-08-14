package com.peony.peonyfront.region.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.region.dao.RegionMapper;
import com.peony.peonyfront.region.model.Region;

@Service
public class RegionServiceImpl implements RegionService {

    @Resource
    private RegionMapper regionMapper;

    @Override
    public List<Region> selectByParentId(Integer regionid) {
        return this.regionMapper.selectByParentId(regionid);
    }

    @Override
    public Region selectByPrimaryKey(Integer regionid) {
        return this.regionMapper.selectByPrimaryKey(regionid);
    }

    @Override
    public List<Region> selectByUserId(Integer userId) {
        return this.regionMapper.selectByUserId(userId);
    }

    @Override
    public List<Region> selectLikeParentId(Region region) {
        return this.regionMapper.selectLikeParentId(region);
    }

    @Override
    public List<Region> selectByParentIdAndPK(Integer regionid) {
        return this.regionMapper.selectByParentIdAndPK(regionid);
    }

    @Override
    public List<Region> selectRegion() {
        return this.regionMapper.selectRegion();
    }

    @Override
    public List<Region> selectRegionByProvinceId(Map map) {
        return this.regionMapper.selectRegionByProvinceId(map);
    }

}
