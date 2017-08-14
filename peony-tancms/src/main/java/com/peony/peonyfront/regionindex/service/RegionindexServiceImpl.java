package com.peony.peonyfront.regionindex.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.regionindex.dao.RegionindexMapper;
import com.peony.peonyfront.regionindex.model.Regionindex;

;

@Service
public class RegionindexServiceImpl implements RegionindexService {
    @Resource
    private RegionindexMapper regionindexMapper;

    /**
     * 根据各市综合指数 对全省城市进行排行
     *
     * @param compositeIndex
     * @return
     */
    @Override
    public List<Regionindex> countSelectByCount(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectRegionIndex(regionindex);
    }

    /**
     * 根据综合指数 查看全省一年内的舆情走势
     *
     * @param regionindex
     * @return
     */
    @Override
    public List<Regionindex> tendencySelectByCount(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selecttendency(regionindex);
    }

    @Override
    public int insert(Regionindex regionIndex) {
        return this.regionindexMapper.insertSelective(regionIndex);
    }

    @Override
    public List<Regionindex> selectRegionIndexByDate(String time) {
        return this.regionindexMapper.selectRegionIndexByDate(time);
    }

    /**
     * 根据综合指数查看省内地区 本月与上月的舆情增长率排行
     *
     * @param regionindex
     * @return
     */
    @Override
    public List<Regionindex> GrowthRateSelectByCount(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectGrowthRate(regionindex);
    }

    @Override
    public List<Regionindex> GrowthRateSelectByCounts(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectGrowthRates(regionindex);
    }

    @Override
    public List<Regionindex> RegionChinaSelectByCont(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectRegionChina(regionindex);
    }

    @Override
    public List<Regionindex> GrowthRateChinaSelectByCount(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectGrowthRateChina(regionindex);
    }

    @Override
    public List<Regionindex> GrowthRateChinaSelectByCounts(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectGrowthRatesChina(regionindex);
    }

    @Override
    public List<Regionindex> selectChinaDistribute(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectChinaDistribute(regionindex);
    }

    @Override
    public List<Regionindex> selecttendencychina(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selecttendencychina(regionindex);
    }

    @Override
    public List<Regionindex> selecttendencychina1(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selecttendencychina1(regionindex);
    }

    @Override
    public List<Regionindex> selectShengDistribute(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectShengDistribute(regionindex);
    }

    @Override
    public List<Regionindex> selectShengDistribute1(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectShengDistribute1(regionindex);
    }

    @Override
    public List<Regionindex> countSelectByCountZhou(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectRegionIndexZhou(regionindex);
    }

    @Override
    public List<Regionindex> countSelectByCountYue(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectRegionIndexYue(regionindex);
    }

    @Override
    public List<Regionindex> tendencySelectByCountZhou(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selecttendencyZhou(regionindex);
    }

    @Override
    public List<Regionindex> tendencySelectByCountYue(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selecttendencyYue(regionindex);
    }

    @Override
    public List<Regionindex> RegionChinaSelectByContZhou(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectRegionChinaZhou(regionindex);
    }

    @Override
    public List<Regionindex> RegionChinaSelectByContYue(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectRegionChinaYue(regionindex);
    }

    @Override
    public List<Regionindex> selectChinaDistributeZhou(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectChinaDistributeZhou(regionindex);
    }

    @Override
    public List<Regionindex> selectChinaDistributeYue(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectChinaDistributeYue(regionindex);
    }

    @Override
    public List<Regionindex> selecttendencychinaZhou(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selecttendencychinaZhou(regionindex);
    }

    @Override
    public List<Regionindex> selecttendencychinaYue(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selecttendencychinaYue(regionindex);
    }

    @Override
    public List<Regionindex> selecttendencychinaZhou1(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selecttendencychinaZhou1(regionindex);
    }

    @Override
    public List<Regionindex> selecttendencychinaYue1(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selecttendencychinaYue1(regionindex);
    }

    @Override
    public List<Regionindex> selectShengDistributeZhou(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectShengDistributeZhou(regionindex);
    }

    @Override
    public List<Regionindex> selectShengDistributeZhou1(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectShengDistributeZhou1(regionindex);
    }

    @Override
    public List<Regionindex> selectShengDistributeYue(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectShengDistributeYue(regionindex);
    }

    @Override
    public List<Regionindex> selectShengDistributeYue1(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectShengDistributeYue1(regionindex);
    }

    @Override
    public List<Regionindex> GrowthRateSelectByCountZhou(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectGrowthRateZhou(regionindex);
    }

    @Override
    public List<Regionindex> GrowthRateSelectByCountsZhou(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectGrowthRatesZhou(regionindex);
    }

    @Override
    public List<Regionindex> GrowthRateSelectByCountYue(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectGrowthRateYue(regionindex);
    }

    @Override
    public List<Regionindex> GrowthRateSelectByCountsYue(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectGrowthRatesYue(regionindex);
    }

    @Override
    public List<Regionindex> selectGrowthRateChinaZhou(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectGrowthRateChinaZhou(regionindex);
    }

    @Override
    public List<Regionindex> selectGrowthRatesChinaZhou(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectGrowthRatesChinaZhou(regionindex);
    }

    @Override
    public List<Regionindex> selectGrowthRateChinaYue(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectGrowthRateChinaYue(regionindex);
    }

    @Override
    public List<Regionindex> selectGrowthRatesChinaYue(Regionindex regionindex) {
        // TODO Auto-generated method stub
        return this.regionindexMapper.selectGrowthRatesChinaYue(regionindex);
    }

}
