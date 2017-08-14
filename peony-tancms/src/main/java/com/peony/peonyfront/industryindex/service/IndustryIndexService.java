package com.peony.peonyfront.industryindex.service;

import java.util.List;

import com.peony.peonyfront.industryindex.model.IndustryIndex;

public interface IndustryIndexService {
    /**
     * 反映全国内各地市舆情热点行业 根据热点行业综合指数 只取排行前十的行业
     * 
     * @param industryindex
     * @return
     */

    List<IndustryIndex> countSelectByCount(IndustryIndex industryindex);

    /**
     * 反映全国内各地市近七天舆情热点行业 根据热点行业综合指数 只取排行前十的行业
     * 
     * @param industryindex
     * @return
     */

    List<IndustryIndex> countSelectByCountZhou(IndustryIndex industryindex);

    /**
     * 反映全国内各地市上月舆情热点行业 根据热点行业综合指数 只取排行前十的行业
     * 
     * @param industryindex
     * @return
     */

    List<IndustryIndex> countSelectByCountYue(IndustryIndex industryindex);

    /**
     * 反映全国各地市行业舆情 的舆情热度 敏感度 与关注情况
     * 
     * @param industryindex
     * @return
     */
    List<IndustryIndex> distributeSelectByCount(IndustryIndex industryindex);

    /**
     * 反映全国各行业近七天舆情的舆情热度，敏感度与观众关注情况
     * 
     * @param industryindex
     * @return
     */
    List<IndustryIndex> selectdistributeZhou(IndustryIndex industryindex);

    /**
     * 反映全国各行业上月舆情的舆情热度，敏感度与观众关注情况
     * 
     * @param industryindex
     * @return
     */
    List<IndustryIndex> selectdistributeYue(IndustryIndex industryindex);

    /**
     * 反映省内各地市舆情热点行业 根据热点行业综合指数 只取排行前十的行业
     * 
     * @param industryindex
     * @return
     */
    List<IndustryIndex> IndustryIndexSelectByCount(IndustryIndex industryindex);

    /**
     * 反映省内各地市近七天舆情热点行业 根据行业综合指数 只显示排行前10
     * 
     * @param industryindex
     * @return
     */
    List<IndustryIndex> selectIndustryCountZhou(IndustryIndex industryindex);

    /**
     * 反映省内各地市上月舆情热点行业 根据行业综合指数 只显示排行前10
     * 
     * @param industryindex
     * @return
     */
    List<IndustryIndex> selectIndustryCountYue(IndustryIndex industryindex);

    /**
     * 插入industryIndex
     * 
     * @param industryIndex
     * @return
     */
    public int insert(IndustryIndex industryIndex);

    /**
     * 查询日期为昨天的IndustryIndex
     * 
     * @param record
     * @return
     */
    List<IndustryIndex> selectIndustryIndexByDate(String time);

    /**
     * 反映省内各地市行业舆情 的舆情热度 敏感度与关注情况
     */
    List<IndustryIndex> selectTdistribteSheng(IndustryIndex industryindex);

    /**
     * 反映省内各行业近七天的舆情的舆情热度，敏感度与观众的关注情况
     */
    List<IndustryIndex> selectTdistribteShengZhou(IndustryIndex industryindex);

    /**
     * 反映省内各行业上月的舆情的舆情热度，敏感度与观众的关注情况
     */
    List<IndustryIndex> selectTdistribteShengYue(IndustryIndex industryindex);
}
