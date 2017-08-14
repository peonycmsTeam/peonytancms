package com.peony.peonyfront.event.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.event.dao.EventnewsMapper;
import com.peony.peonyfront.event.model.Eventnews;

@Service
public class EventnewsServiceImpl implements EventnewsService {

    @Resource
    private EventnewsMapper eventnewsMapper;

    @Override
    public Pagination<Eventnews> findByPage(Eventnews eventnews) {
        List<Eventnews> eventnewsList = this.eventnewsMapper.selectByPage(eventnews);
        return new Pagination<Eventnews>(eventnewsList, eventnews.getPageParameter());
    }

    @Override
    public Eventnews load(Eventnews eventnews) {
        return this.eventnewsMapper.load(eventnews);
    }

    @Override
    public List<Eventnews> selectByEventNewsIds(String[] eventnewsIds) {
        return this.eventnewsMapper.selectByEventNewsIds(eventnewsIds);
    }

    @Override
    public List<Eventnews> selectByTimes(Eventnews eventnews) {
        return this.eventnewsMapper.selectByTimes(eventnews);
    }

    @Override
    public List<Eventnews> CountByPolarity(Eventnews eventnews) {
        return this.eventnewsMapper.selectCountByPolarity(eventnews);
    }

    @Override
    public List<Eventnews> CountByPolarityAndType(Eventnews eventnews) {
        return this.eventnewsMapper.selectCountByPolarityAndType(eventnews);
    }

    @Override
    public List<Eventnews> CountBySrcType(Eventnews eventnews) {
        return this.eventnewsMapper.selectCountBySrcType(eventnews);
    }

    @Override
    public List<Eventnews> CountByWebSite(Eventnews eventnews) {
        return this.eventnewsMapper.selectCountByWebSite(eventnews);
    }

    @Override
    public List<Eventnews> selectEventnewsByGroupseedid(Eventnews eventnews) {
        return this.eventnewsMapper.selectEventnewsByGroupseedid(eventnews);
    }

    @Override
    public Pagination<Eventnews> selectGroupByTitle(Eventnews eventnews) {
        List<Eventnews> eventnewsList = this.eventnewsMapper.selectGroupByTitle(eventnews);
        return new Pagination<Eventnews>(eventnewsList, eventnews.getPageParameter());
    }

    @Override
    public List CountByGroupCount(Map map) {
        return this.eventnewsMapper.selectCountByGroupCount(map);
    }

    @Override
    public List CountByProvinceId(Map map) {
        return this.eventnewsMapper.selectCountByProvinceId(map);
    }

    @Override
    public List CountByProvinceIdMedia(Map map) {
        return this.eventnewsMapper.selectCountByProvinceIdMedia(map);
    }

    @Override
    public List CountByGroupCountAndIndustry(Map map) {
        return this.eventnewsMapper.selectCountByGroupCountAndIndustry(map);
    }

    @Override
    public List CountByIndustry(Map map) {
        return this.eventnewsMapper.selectCountByIndustry(map);
    }

    @Override
    public List CountByIndustryMedia(Map map) {
        return this.eventnewsMapper.selectCountByIndustryMedia(map);
    }

    @Override
    public List<Eventnews> selectEventnewsByGroupCount(Map map) {
        return this.eventnewsMapper.selectEventnewsByGroupCount(map);
    }

    @Override
    public List CountByMediaType(Map map) {
        return this.eventnewsMapper.CountByMediaType(map);
    }

    @Override
    public List CountByGroupCountByRegion(Map map) {
        return this.eventnewsMapper.CountByGroupCountByRegion(map);
    }

    @Override
    public List CountByRegion(Map map) {
        return this.eventnewsMapper.CountByRegion(map);
    }

    @Override
    public List CountByRegionIdMedia(Map map) {
        return this.eventnewsMapper.CountByRegionIdMedia(map);
    }

    @Override
    public List CountByGroupCountAndRegionIdAndIndustry(Map map) {
        return this.eventnewsMapper.CountByGroupCountAndRegionIdAndIndustry(map);
    }

    @Override
    public List CountByRegionIdAndIndustry(Map map) {
        return this.eventnewsMapper.CountByRegionIdAndIndustry(map);
    }

    @Override
    public List CountByRegionIdAndIndustryMedia(Map map) {
        return this.eventnewsMapper.CountByRegionIdAndIndustryMedia(map);
    }

    @Override
    public List<Eventnews> selectEventnewsByRegionAndGroupCount(Map map) {
        return this.eventnewsMapper.selectEventnewsByRegionAndGroupCount(map);
    }

    @Override
    public List CountByEventAndRegionIdAndMedia(Map map) {
        return this.eventnewsMapper.selectCountByEventAndRegionIdAndMedia(map);
    }

    @Override
    public List CountByEventAndRegionId(Map map) {
        return this.eventnewsMapper.selectCountByEventAndRegionId(map);
    }

    @Override
    public List CountByEventAndProvinceIdAndMedia(Map map) {
        return this.eventnewsMapper.selectCountByEventAndProvinceIdAndMedia(map);
    }

    @Override
    public List CountByEventAndProvinceId(Map map) {
        return this.eventnewsMapper.selectCountByEventAndProvinceId(map);
    }

}
