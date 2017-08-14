package com.peony.peonyfront.login.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.login.dao.PhoneLoginMapper;
import com.peony.peonyfront.login.model.PhoneLogin;

@Service
public class PhoneLoginServiceImpl implements PhoneLoginService {

    @Resource
    private PhoneLoginMapper phoneLoginMapper;

    @Override
    public List<PhoneLogin> selectByUserId(PhoneLogin phoneLogin) {
        return this.phoneLoginMapper.selectByUserId(phoneLogin);
    }

    @Override
    public int insertSelective(PhoneLogin phoneLogin) {
        return this.phoneLoginMapper.insertSelective(phoneLogin);
    }

    @Override
    public int updateByPrimaryKeySelective(PhoneLogin phoneLogin) {
        return this.phoneLoginMapper.updateByPrimaryKeySelective(phoneLogin);
    }

    @Override
    public int deletePrimark(String macid) {
        this.phoneLoginMapper.deleteByPrimaryMacid(macid);
        return 0;
    }

}
