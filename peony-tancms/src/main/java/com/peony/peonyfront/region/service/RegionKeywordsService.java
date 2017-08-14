package com.peony.peonyfront.region.service;

import java.util.List;

import com.peony.peonyfront.region.model.RegionKeywords;

public interface RegionKeywordsService {
    public List<RegionKeywords> selectByRegionId(RegionKeywords regionKeywords);

    public List<RegionKeywords> selectByPK(RegionKeywords regionKeywords);
}
