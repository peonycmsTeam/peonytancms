package com.peony.peonyfront.event.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.peony.peonyfront.event.model.Eventnews;

public interface EventnewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Eventnews record);

    int insertSelective(Eventnews record);

    Eventnews selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Eventnews record);

    int updateByPrimaryKey(Eventnews record);

    List<Eventnews> selectByPage(Eventnews eventnews);

    /**
     * 首页定制舆情列表 按标题去重
     * 
     * @param eventnews
     * @return
     */
    List<Eventnews> selectGroupByTitle(Eventnews eventnews);

    Eventnews load(Eventnews eventnews);

    /**
     * 根据id导出所选项
     * 
     * @param eventnewsIds
     * @return
     */
    List<Eventnews> selectByEventNewsIds(@Param(value = "eventnewsIds") String[] eventnewsIds);

    /**
     * 根据所选时间导出
     * 
     * @param eventnews
     * @return
     */
    List<Eventnews> selectByTimes(Eventnews eventnews);

    List<Eventnews> selectCountByPolarity(Eventnews eventnews);

    List<Eventnews> selectCountByWebSite(Eventnews eventnews);

    List<Eventnews> selectCountBySrcType(Eventnews eventnews);

    List<Eventnews> selectCountByPolarityAndType(Eventnews eventnews);

    List<Eventnews> selectEventnewsByGroupseedid(Eventnews eventnews);

    List selectCountByGroupCount(Map map);

    List selectCountByProvinceId(Map map);

    List selectCountByProvinceIdMedia(Map map);

    List selectCountByGroupCountAndIndustry(Map map);

    List selectCountByIndustry(Map map);

    List selectCountByIndustryMedia(Map map);

    List<Eventnews> selectEventnewsByGroupCount(Map map);

    List CountByMediaType(Map map);

    List CountByGroupCountByRegion(Map map);

    List CountByRegion(Map map);

    List CountByRegionIdMedia(Map map);

    List CountByGroupCountAndRegionIdAndIndustry(Map map);

    List CountByRegionIdAndIndustry(Map map);

    List CountByRegionIdAndIndustryMedia(Map map);

    List<Eventnews> selectEventnewsByRegionAndGroupCount(Map map);

    List selectCountByEventAndRegionIdAndMedia(Map map);

    List selectCountByEventAndRegionId(Map map);

    List selectCountByEventAndProvinceIdAndMedia(Map map);

    List selectCountByEventAndProvinceId(Map map);

}