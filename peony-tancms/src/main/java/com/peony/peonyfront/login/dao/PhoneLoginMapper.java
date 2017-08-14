package com.peony.peonyfront.login.dao;

import java.util.List;

import com.peony.peonyfront.login.model.PhoneLogin;

public interface PhoneLoginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PhoneLogin record);

    int insertSelective(PhoneLogin record);

    PhoneLogin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PhoneLogin record);

    int updateByPrimaryKey(PhoneLogin record);

    List<PhoneLogin> selectByUserId(PhoneLogin phoneLogin);

    int deleteByPrimaryMacid(String macid);

}