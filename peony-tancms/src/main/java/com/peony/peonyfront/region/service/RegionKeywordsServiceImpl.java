package com.peony.peonyfront.region.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.region.dao.RegionKeywordsMapper;
import com.peony.peonyfront.region.model.RegionKeywords;

@Service
public class RegionKeywordsServiceImpl implements RegionKeywordsService {

    @Resource
    private RegionKeywordsMapper regionKeywordsMapper;

    @Override
    public List<RegionKeywords> selectByRegionId(RegionKeywords regionKeywords) {
        return this.regionKeywordsMapper.selectByRegionId(regionKeywords);
    }

    @Override
    public List<RegionKeywords> selectByPK(RegionKeywords regionKeywords) {
        return this.regionKeywordsMapper.selectByPK(regionKeywords);
    }

}
