package com.peony.peonyfront.subject.dao;

import java.util.List;

import com.peony.peonyfront.subject.model.Subject;

public interface SubjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Subject record);

    int insertSelective(Subject record);

    Subject selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKey(Subject record);

    /**
     * 查询出用户设置的节点
     */
    List<Subject> selectSubjectByUserId(Integer userId);

    /**
     * 根据节点id查询该节点下的子节点
     */
    List<Subject> selectChildnodesById(Integer id);

    /**
     * 查询该用户下的所根节点
     */
    List<Subject> selectNodesByUserId(Integer userId);

    /**
     * 通过用户id和pid查询订制舆情
     * 
     * @param subject
     * @return
     */
    List<Subject> selectSubjectByUserIdAndPid(Subject subject);

    Subject selectCountByPid(Subject subject);

    List<Subject> selectSubjectByUserIdExclusive(Subject subject);
}