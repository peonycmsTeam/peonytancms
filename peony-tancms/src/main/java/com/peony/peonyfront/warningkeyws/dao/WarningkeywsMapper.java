package com.peony.peonyfront.warningkeyws.dao;

import java.util.List;

import com.peony.peonyfront.warningkeyws.model.Warningkeyws;

public interface WarningkeywsMapper {
    int deleteByPrimaryKey(Integer warningkeywsId);

    int insert(Warningkeyws record);

    int insertSelective(Warningkeyws record);

    Warningkeyws selectByPrimaryKey(Integer warningkeywsId);

    int updateByPrimaryKeySelective(Warningkeyws record);

    int updateByPrimaryKey(Warningkeyws record);

    List<Warningkeyws> selectByPage(Warningkeyws warningkeyws);
}