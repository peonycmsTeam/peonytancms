package com.peony.peonyfront.regionindex.dao;

import java.util.List;
import java.util.Map;

import com.peony.peonyfront.regionindex.model.Regionindex;

public interface RegionindexMapper {
    int deleteByPrimaryKey(String id);

    int insert(Regionindex record);

    int insertSelective(Regionindex record);

    Regionindex selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Regionindex record);

    int updateByPrimaryKey(Regionindex record);

    /**
     * 根据各市综合指数 对本月省内城市进行排行
     * 
     * @param compositeIndex
     * @return
     */

    List<Regionindex> selectRegionIndex(Regionindex regionindex);

    /**
     * 根据各市综合指数 对本周省内城市进行排行
     * 
     * @param compositeIndex
     * @return
     */

    List<Regionindex> selectRegionIndexZhou(Regionindex regionindex);

    /**
     * 根据各市综合指数 对上月省内城市进行排行
     * 
     * @param compositeIndex
     * @return
     */

    List<Regionindex> selectRegionIndexYue(Regionindex regionindex);

    /**
     * 根据综合指数，对全省本月进行统计走势图
     * 
     * @param month
     * @return
     */
    List<Regionindex> selecttendency(Regionindex regionindex);

    /**
     * 根据综合指数，对全省近七天进行统计走势图
     * 
     * @param month
     * @return
     */
    List<Regionindex> selecttendencyZhou(Regionindex regionindex);

    /**
     * 根据综合指数，对全省上月进行统计走势图
     * 
     * @param month
     * @return
     */
    List<Regionindex> selecttendencyYue(Regionindex regionindex);

    /**
     * 根据综合指数，对省内城市进行增长排行
     * 
     * @param compositeIndex
     * @return
     */
    List<Regionindex> selectGrowthRate(Regionindex regionindex);

    List<Regionindex> selectGrowthRates(Regionindex regionindex);

    /**
     * 根据综合指数，对上周省内城市进行增长排行
     * 
     * @param regionindex
     * @return
     */
    List<Regionindex> selectGrowthRateZhou(Regionindex regionindex);

    List<Regionindex> selectGrowthRatesZhou(Regionindex regionindex);

    /**
     * 根据综合指数，对省内上月增长 进行排行
     * 
     * @param regionindex
     * @return
     */
    List<Regionindex> selectGrowthRateYue(Regionindex regionindex);

    List<Regionindex> selectGrowthRatesYue(Regionindex regionindex);

    /**
     * 全国各省 根据综合指数 本月进行排行
     */
    List<Regionindex> selectRegionChina(Regionindex regionindex);

    /**
     * 全国各省 根据综合指数近七天 进行排行
     */
    List<Regionindex> selectRegionChinaZhou(Regionindex regionindex);

    /**
     * 全国各省 根据综合指数对上月 进行排行
     */
    List<Regionindex> selectRegionChinaYue(Regionindex regionindex);

    /**
     * 查询日期为昨天的regionindex
     * 
     * @param record
     * @return
     */
    List<Regionindex> selectRegionIndexByDate(String time);

    /**
     * 根据综合指数，对全国城市进行增长排行
     * 
     * @param compositeIndex
     * @return
     */
    List<Regionindex> selectGrowthRateChina(Regionindex regionindex);

    List<Regionindex> selectGrowthRatesChina(Regionindex regionindex);

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