package com.peony.peonyfront.region.dao;

import java.util.List;

import com.peony.peonyfront.region.model.RegionKeywords;

public interface RegionKeywordsMapper {

    List<RegionKeywords> selectByRegionId(RegionKeywords regionKeywords);

    List<RegionKeywords> selectByPK(RegionKeywords regionKeywords);
}