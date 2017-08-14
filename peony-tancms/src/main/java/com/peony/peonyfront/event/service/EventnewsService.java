package com.peony.peonyfront.event.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.event.model.Eventnews;

public interface EventnewsService {

    public Pagination<Eventnews> findByPage(Eventnews eventnews);

    /**
     * 首页定制舆情列表 按标题去重
     * 
     * @param eventnews
     * @return
     */
    public Pagination<Eventnews> selectGroupByTitle(Eventnews eventnews);

    public Eventnews load(Eventnews eventnews);

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

    List<Eventnews> CountByPolarity(Eventnews eventnews);

    List<Eventnews> CountByWebSite(Eventnews eventnews);

    List<Eventnews> CountBySrcType(Eventnews eventnews);

    List<Eventnews> CountByPolarityAndType(Eventnews eventnews);

    List<Eventnews> selectEventnewsByGroupseedid(Eventnews eventnews);

    /**
     * 信息重复量（热度指数）
     * 
     * @param map
     * @return
     */
    List CountByGroupCount(Map map);

    /**
     * 去重前数据（敏感指数）
     */
    List CountByProvinceId(Map map);

    /**
     * 省份的自媒体数据量（关注指数）
     */
    List CountByProvinceIdMedia(Map map);

    /**
     * 行业信息重复量（热度指数）
     * 
     * @param map
     * @return
     */
    List CountByGroupCountAndIndustry(Map map);

    /**
     * 行业的去重前数据（敏感指数）
     * 
     * @param map
     * @return
     */
    List CountByIndustry(Map map);

    /**
     * 行业的自媒体数据量（关注指数）
     */
    List CountByIndustryMedia(Map map);

    /**
     * 查询groupcount前十的eventnews
     * 
     * @param eventnews
     * @return
     */
    List<Eventnews> selectEventnewsByGroupCount(Map map);

    /**
     * 统计各类型媒体的具体数量
     */
    List CountByMediaType(Map map);

    /**
     * 市级信息重复量
     * 
     * @param map
     * @return
     */
    List CountByGroupCountByRegion(Map map);

    /**
     * 市级去重前数据
     */
    List CountByRegion(Map map);

    /**
     * 市级的自媒体数据量
     */
    List CountByRegionIdMedia(Map map);

    /**
     * 市级行业信息重复量
     * 
     * @param map
     * @return
     */
    List CountByGroupCountAndRegionIdAndIndustry(Map map);

    /**
     * 市级行业的去重前数据
     * 
     * @param map
     * @return
     */
    List CountByRegionIdAndIndustry(Map map);

    /**
     * 市级行业的自媒体数据量
     */
    List CountByRegionIdAndIndustryMedia(Map map);

    /**
     * 查询市区域groupcount前十的eventnews
     * 
     * @param eventnews
     * @return
     */
    List<Eventnews> selectEventnewsByRegionAndGroupCount(Map map);

    /**
     * 统计市级媒体各类型具体数量
     */
    List CountByEventAndRegionIdAndMedia(Map map);

    /**
     * 统计市级自媒体具体数量
     */
    List CountByEventAndRegionId(Map map);

    /**
     * 统计各类型下媒体的具体数量
     */
    List CountByEventAndProvinceIdAndMedia(Map map);

    /**
     * 统计自媒体具体数量
     */
    List CountByEventAndProvinceId(Map map);

}
