package com.peony.peonyfront.subjectkeywords.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peony.peonyfront.subjectkeywords.model.SubjectKeywords;

public interface SubjectKeywordsService {
    /**
     * 根据分类表id查询关键词列表
     * 
     * @param pid
     * @return
     */
    List<SubjectKeywords> selectSubjectListBySubjectId(Integer subjectId);

    /**
     * 根据用户id查询关键词列表
     * 
     * @param userId
     * @return
     */
    List<SubjectKeywords> selectSubjectListByUserId(@Param(value = "userId") Integer userId);

    /**
     * 根据分类节点id删除该节点下的关键词
     * 
     * @param id
     * @return
     */
    int deleteBySubjectId(Integer subjectId);

    /**
     * 新增操作
     * 
     * @param record
     * @return
     */
    int insertSelective(SubjectKeywords record);
}
