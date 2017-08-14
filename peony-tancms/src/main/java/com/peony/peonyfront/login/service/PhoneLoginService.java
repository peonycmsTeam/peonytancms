package com.peony.peonyfront.login.service;

import java.util.List;

import com.peony.peonyfront.login.model.PhoneLogin;

public interface PhoneLoginService {

    List<PhoneLogin> selectByUserId(PhoneLogin phoneLogin);

    int insertSelective(PhoneLogin phoneLogin);

    int updateByPrimaryKeySelective(PhoneLogin phoneLogin);

    int deletePrimark(String macid);
}
