package com.peony.peonyfront.industryindex.dao;

import java.util.List;

import com.peony.peonyfront.industryindex.model.IndustryIndex;

public interface IndustryIndexMapper {
    int deleteByPrimaryKey(String id);

    int insert(IndustryIndex record);

    int insertSelective(IndustryIndex record);

    IndustryIndex selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(IndustryIndex record);

    int updateByPrimaryKey(IndustryIndex record);

    /**
     * 反映国内各地市舆情热点行业 根据行业综合指数 只显示排行前10
     * 
     * @param industryindex
     * @return
     */

    /**
     * 查询日期为昨天的IndustryIndex
     * 
     * @param time
     * @return
     */
    List<IndustryIndex> selectIndustryIndexByDate(String time);

    /**
     * 反映国内各地市近七天舆情热点行业 根据行业综合指数 只显示排行前10
     * 
     * @param industryindex
     * @return
     */

    List<IndustryIndex> selectIndustryIndexZhou(IndustryIndex industryindex);

    /**
     * 反映国内各地市上月舆情热点行业 根据行业综合指数 只显示排行前10
     * 
     * @param industryindex
     * @return
     */

    List<IndustryIndex> selectIndustryIndexYue(IndustryIndex industryindex);

    /**
     * 反映国内各地市舆情热点行业 根据行业综合指数 只显示排行前10
     * 
     * @param industryindex
     * @return
     */

    List<IndustryIndex> selectIndustryIndex(IndustryIndex industryindex);

    /**
     * 反映全国各行业舆情的舆情热度，敏感度与观众关注情况
     * 
     * @param industryindex
     * @return
     */
    List<IndustryIndex> selectdistribute(IndustryIndex industryindex);

    /**
     * 反映全国各行业舆情的舆情热度，敏感度与观众关注情况
     * 
     * @param industryindex
     * @return
     */
    List<IndustryIndex> selectdistributeZhou(IndustryIndex industryindex);

    /**
     * 反映全国各行业舆情的舆情热度，敏感度与观众关注情况
     * 
     * @param industryindex
     * @return
     */
    List<IndustryIndex> selectdistributeYue(IndustryIndex industryindex);

    /**
     * 反映省内各地市舆情热点行业 根据行业综合指数 只显示排行前10
     * 
     * @param industryindex
     * @return
     */
    List<IndustryIndex> selectIndustryCount(IndustryIndex industryindex);

    /**
     * 反映省内各地市舆情热点行业 根据行业综合指数 只显示排行前10
     * 
     * @param industryindex
     * @return
     */
    List<IndustryIndex> selectIndustryCountZhou(IndustryIndex industryindex);

    /**
     * 反映省内各地市舆情热点行业 根据行业综合指数 只显示排行前10
     * 
     * @param industryindex
     * @return
     */
    List<IndustryIndex> selectIndustryCountYue(IndustryIndex industryindex);

    /**
     * 反映省内各行业的舆情的舆情热度，敏感度与观众的关注情况
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
