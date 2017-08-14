package com.peony.peonyfront.baseclass.service;

import java.util.List;

import com.peony.peonyfront.baseclass.model.Baseclass;

public interface BaseclassService {
    /**
     * 查询所有分类
     *
     * @param Code
     * @return
     */
    List<Baseclass> selectBaseClassParent(String[] Code);

    /**
     * 根据父节点查询子节点
     *
     * @param Code
     * @return
     */
    List<Baseclass> selectBaseClassChildren(Integer Code);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Baseclass selectBaseClassByBaseClassId(Integer id);
}
