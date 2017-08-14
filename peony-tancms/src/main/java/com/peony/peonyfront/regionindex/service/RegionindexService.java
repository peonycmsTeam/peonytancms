package com.peony.peonyfront.regionindex.service;

import java.util.List;
import java.util.Map;

import com.peony.peonyfront.event.model.Subscription;
import com.peony.peonyfront.regionindex.model.Regionindex;

public interface RegionindexService {

    /**
     * 根据各市综合指数 对本月全省城市进行排行
     * 
     * @param compositeIndex
     * @return
     */

    List<Regionindex> countSelectByCount(Regionindex regionindex);

    /**
     * 根据各市综合指数 对本周全省城市进行排行
     * 
     * @param compositeIndex
     * @return
     */

    List<Regionindex> countSelectByCountZhou(Regionindex regionindex);

    /**
     * 根据各市综合指数 对上月全省城市进行排行
     * 
     * @param compositeIndex
     * @return
     */

    List<Regionindex> countSelectByCountYue(Regionindex regionindex);

    /**
     * 根据综合指数 查看全省本月的舆情走势
     * 
     * @param regionindex
     * @return
     */
    List<Regionindex> tendencySelectByCount(Regionindex regionindex);

    /**
     * 根据综合指数 查看全省近七天的舆情走势
     * 
     * @param regionindex
     * @return
     */
    List<Regionindex> tendencySelectByCountZhou(Regionindex regionindex);

    /**
     * 根据综合指数 查看全省上月的舆情走势
     * 
     * @param regionindex
     * @return
     */
    List<Regionindex> tendencySelectByCountYue(Regionindex regionindex);

    /**
     * 根据综合指数查看省内地区 本月与上月的舆情增长率排行
     * 
     * @param regionindex
     * @return
     */

    List<Regionindex> GrowthRateSelectByCount(Regionindex regionindex);

    List<Regionindex> GrowthRateSelectByCounts(Regionindex regionindex);

    List<Regionindex> GrowthRateSelectByCountZhou(Regionindex regionindex);

    List<Regionindex> GrowthRateSelectByCountsZhou(Regionindex regionindex);

    List<Regionindex> GrowthRateSelectByCountYue(Regionindex regionindex);

    List<Regionindex> GrowthRateSelectByCountsYue(Regionindex regionindex);

    /**
     * 根据各省综合指数 对全国城市本月进行排行
     * 
     * @param compositeIndex
     * @return
     */
    List<Regionindex> RegionChinaSelectByCont(Regionindex regionindex);

    /**
     * 根据各省综合指数 对全国城市近七天进行排行
     * 
     * @param compositeIndex
     * @return
     */
    List<Regionindex> RegionChinaSelectByContZhou(Regionindex regionindex);

    /**
     * 根据各省综合指数 对全国城市上月进行排行
     * 
     * @param compositeIndex
     * @return
     */
    List<Regionindex> RegionChinaSelectByContYue(Regionindex regionindex);

    /**
     * 插入regionIndex
     * 
     * @param regionIndex
     * @return
     */
    public int insert(Regionindex regionIndex);

    /**
     * 查询日期为昨天的regionindex
     * 
     * @param record
     * @return
     */
    List<Regionindex> selectRegionIndexByDate(String time);

    /**
     * 根据综合指数，对省内城市进行增长排行
     * 
     * @param compositeIndex
     * @return
     */
    List<Regionindex> GrowthRateChinaSelectByCount(Regionindex regionindex);

    List<Regionindex> GrowthRateChinaSelectByCounts(Regionindex regionindex);

    /**
     * 全国舆情分布图 根据综合指数进行本月查询
     */
    List<Regionindex> selectChinaDistribute(Regionindex regionindex);

    /**
     * 全国舆情分布图 根据综合指数进行近七天查询
     */
    List<Regionindex> selectChinaDistributeZhou(Regionindex regionindex);

    /**
     * 全国舆情分布图 根据综合指数进行上月查询
     */
    List<Regionindex> selectChinaDistributeYue(Regionindex regionindex);

    /**
     * 根据综合指数，对全国本月进行统计
     * 
     * @param month
     * @return
     */
    List<Regionindex> selecttendencychina(Regionindex regionindex);

    /**
     * 根据综合指数，对全国近七天进行统计
     * 
     * @param month
     * @return
     */
    List<Regionindex> selecttendencychinaZhou(Regionindex regionindex);

    /**
     * 根据综合指数，对全国上月分进行统计
     * 
     * @param month
     * @return
     */
    List<Regionindex> selecttendencychinaYue(Regionindex regionindex);

    List<Regionindex> selecttendencychina1(Regionindex regionindex);

    List<Regionindex> selecttendencychinaZhou1(Regionindex regionindex);

    List<Regionindex> selecttendencychinaYue1(Regionindex regionindex);

    List<Regionindex> selectShengDistribute(Regionindex regionindex);

    List<Regionindex> selectShengDistribute1(Regionindex regionindex);

    List<Regionindex> selectShengDistributeZhou(Regionindex regionindex);

    List<Regionindex> selectShengDistributeZhou1(Regionindex regionindex);

    List<Regionindex> selectShengDistributeYue(Regionindex regionindex);

    List<Regionindex> selectShengDistributeYue1(Regionindex regionindex);

    /**
     * 根据综合指数，对全国城市进行增长排行
     * 
     * @param compositeIndex
     * @return
     */
    List<Regionindex> selectGrowthRateChinaZhou(Regionindex regionindex);

    List<Regionindex> selectGrowthRatesChinaZhou(Regionindex regionindex);

    /**
     * 根据综合指数，对全国城市进行增长排行
     * 
     * @param compositeIndex
     * @return
     */
    List<Regionindex> selectGrowthRateChinaYue(Regionindex regionindex);

    List<Regionindex> selectGrowthRatesChinaYue(Regionindex regionindex);

}
