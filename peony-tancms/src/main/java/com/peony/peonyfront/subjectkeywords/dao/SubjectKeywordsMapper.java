package com.peony.peonyfront.subjectkeywords.dao;

import java.util.List;

import com.peony.peonyfront.subjectkeywords.model.SubjectKeywords;

public interface SubjectKeywordsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SubjectKeywords record);

    int insertSelective(SubjectKeywords record);

    SubjectKeywords selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SubjectKeywords record);

    int updateByPrimaryKey(SubjectKeywords record);

    /**
     * 根据分类表id查询关键词列表
     * 
     * @param pid
     * @return
     */
    List<SubjectKeywords> selectSubjectListBySubjectId(Integer subjectId);

    List<SubjectKeywords> selectSubjectListByUserId(Integer userId);

    /**
     * 根据分类节点id删除该节点下的关键词
     * 
     * @param id
     * @return
     */
    int deleteBySubjectId(Integer subjectId);
}