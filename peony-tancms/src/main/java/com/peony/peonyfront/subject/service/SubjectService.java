package com.peony.peonyfront.subject.service;

import java.util.List;

import com.peony.peonyfront.subject.model.Subject;

public interface SubjectService {
    /**
     * 添加舆情分类
     * 
     * @param record
     * @return
     */
    int insertSelective(Subject record);

    /**
     * 查询出用户设置的节点
     */
    List<Subject> selectUserKeywsByUserId(Integer userId);

    /**
     * 根据id查询实体类
     */
    Subject selectByPrimaryKey(Integer id);

    /**
     * 子节点设为根节点
     */
    int updateByPrimaryKey(Subject record);

    /**
     * 根据节点id查询该节点下的子节点
     */
    List<Subject> selectChildnodesById(Integer id);

    /**
     * 删除子节点
     * 
     * @param userkeywsId
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 查询该用户下的所根节点
     */
    List<Subject> selectNodesByUserId(Integer userId);

    /**
     * 修改或添加关键词
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Subject record);

    /**
     * 通过用户id和pid查询订制舆情
     * 
     * @param subject
     * @return
     */
    List<Subject> selectSubjectByUserIdAndPid(Subject subject);

    boolean selectCountByPid(Subject subject);

    List<Subject> selectSubjectByUserIdExclusive(Subject subject);
}
