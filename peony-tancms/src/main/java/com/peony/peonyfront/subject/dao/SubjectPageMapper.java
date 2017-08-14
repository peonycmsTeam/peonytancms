package com.peony.peonyfront.subject.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.peony.peonyfront.subject.model.SubjectPage;

/**
 * 订制舆情信息dao
 * 
 * @author vv
 * 
 */
public interface SubjectPageMapper {

    /**
     * 分页查询
     * 
     * @param subjectPage
     * @return
     */
    List<SubjectPage> selectByPage(SubjectPage subjectPage);

    /**
     * 首页去重 定制舆情列表
     * 
     * @param subjectPage
     * @return
     */
    List<SubjectPage> selectGroupByTitle(SubjectPage subjectPage);

    /**
     * 批量删除
     * 
     * @param ids
     * @return
     */
    int batchDelete(SubjectPage subjectPage);

    /**
     * 修改
     * 
     * @param subjectPage
     * @return
     */
    int update(SubjectPage subjectPage);

    /**
     * 单个查询
     * 
     * @param subjectPage
     * @return
     */
    SubjectPage load(SubjectPage subjectPage);

    /**
     * 根据id导出所选项
     * 
     * @param ids
     * @return
     */
    List<SubjectPage> selectSubjectPageByIds(@Param(value = "ids") String[] ids);

    /**
     * 根据id导出所选项(相似信息单独显示)
     * 
     * @param ids
     * @return
     */
    List<SubjectPage> selectSubjectPageByIdsNotColspan(@Param(value = "ids") String[] ids);

    /**
     * 根据所选时间导出
     * 
     * @param subjectPage
     * @return
     */
    List<SubjectPage> selectSubjectPageByTime(SubjectPage subjectPage);

    /**
     * 根据所选时间导出
     * 
     * @param subjectPage
     * @return
     */
    List<SubjectPage> selectSubjectPageByTimeNotColspan(SubjectPage subjectPage);

    /**
     * 根据用户id统计 舆情信息条数
     * 
     * @param userid
     * @return
     */
    List selectTotalCount(SubjectPage subjectPage);

    /**
     * 按时间段 倾向性统计
     * 
     * @param map
     * @return
     */
    List selectByCount(Map map);

    List<SubjectPage> countSubjectTypeByUserId(SubjectPage subjectPage);

    List<SubjectPage> countSubjectWebSiteByUserId(SubjectPage subjectPage);

    List<SubjectPage> countSubjectPolarityByUserId(SubjectPage subjectPage);

    List<SubjectPage> countSubjectPolarityAndTypeByUserId(SubjectPage subjectPage);

    List<SubjectPage> selectSubjectPageByGroupseedid(SubjectPage subjectPage);

    /**
     * 按时间导出查询总数(信息合并)
     * 
     * @param subjectPage
     * @return
     */
    int selectSubjectPageByTimeCount(SubjectPage subjectPage);

    /**
     * 按时间导出查询总数(信息单独显示)
     * 
     * @param subjectPage
     * @return
     */
    int selectSubjectPageByTimeNotColspanCount(SubjectPage subjectPage);
}