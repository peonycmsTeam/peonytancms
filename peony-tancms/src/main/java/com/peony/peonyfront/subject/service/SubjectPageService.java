package com.peony.peonyfront.subject.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.subject.model.SubjectPage;

/**
 * 订制舆情信息服务
 * 
 * @author vv
 * 
 */
public interface SubjectPageService {

    /**
     * 分页查询
     * 
     * @param subjectPage
     * @return
     */
    public Pagination<SubjectPage> findByPage(SubjectPage subjectPage);

    /**
     * 首页去重 定制舆情列表
     * 
     * @param subjectPage
     * @return
     */
    public Pagination<SubjectPage> selectGroupByTitle(SubjectPage subjectPage);

    /**
     * 根据主键批量删除
     * 
     * @param ids
     * @return
     */
    public int batchDelete(SubjectPage subjectPage);

    /**
     * 修改倾向性,同时向反馈表中插入数据
     * 
     * @param subjectPage
     * @return
     */
    public int update(SubjectPage subjectPage);

    /**
     * 修改已读未读状态
     * 
     * @param subjectPage
     * @return
     */
    public int updateSubjectPageIsRead(SubjectPage subjectPage);

    /**
     * 单个查询
     * 
     * @param subjectPage
     * @return
     */
    public SubjectPage load(SubjectPage subjectPage);

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
     * 根据所选时间导出(excel)
     * 
     * @param subjectPage
     * @return
     */
    List<SubjectPage> downExcelSelectSubjectPageByTime(SubjectPage subjectPage);

    /**
     * 根据所选时间导出(单独显示)
     * 
     * @param subjectPage
     * @return
     */
    List<SubjectPage> downExcelSelectSubjectPageByTimeNotColspan(SubjectPage subjectPage);

    /**
     * 根据所选时间导出总数(excel)
     * 
     * @param subjectPage
     * @return
     */
    int downExcelSelectSubjectPageByTimeCount(SubjectPage subjectPage);

    /**
     * 根据所选时间导出总数(单独显示)
     * 
     * @param subjectPage
     * @return
     */
    int downExcelSelectSubjectPageByTimeNotColspanCount(SubjectPage subjectPage);

    /**
     * 根据用户id统计 舆情信息条数
     * 
     * @param userid
     * @return
     */
    List totalSelectCount(SubjectPage subjectPage);

    /**
     * 按时间段 倾向性统计
     * 
     * @param map
     * @return
     */
    List countSelectByCount(Map map);

    List<SubjectPage> countSubjectTypeByUserId(SubjectPage subjectPage);

    List<SubjectPage> countSubjectWebSiteByUserId(SubjectPage subjectPage);

    List<SubjectPage> countSubjectPolarityByUserId(SubjectPage subjectPage);

    List<SubjectPage> countSubjectPolarityAndTypeByUserId(SubjectPage subjectPage);

    List<SubjectPage> selectSubjectPageByGroupseedid(SubjectPage subjectPage);

}