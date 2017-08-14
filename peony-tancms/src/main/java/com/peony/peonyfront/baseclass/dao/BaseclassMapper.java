package com.peony.peonyfront.baseclass.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peony.peonyfront.baseclass.model.Baseclass;

public interface BaseclassMapper {
    int deleteByPrimaryKey(Integer baseclassId);

    int insert(Baseclass record);

    int insertSelective(Baseclass record);

    Baseclass selectByPrimaryKey(Integer baseclassId);

    int updateByPrimaryKeySelective(Baseclass record);

    int updateByPrimaryKey(Baseclass record);

    /**
     * 查询所有父节点
     *
     * @param parentCode
     * @return
     */
    List<Baseclass> selectParent(@Param(value = "parentCode") String[] parentCode);

    /**
     * 根据父节点查询子节点
     *
     * @param parentId
     * @return
     */
    List<Baseclass> selectChildren(Baseclass record);
}