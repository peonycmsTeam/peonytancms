package com.peony.peonyfront.briefreport.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.briefreport.dao.BriefreportInfoCntMapper;
import com.peony.peonyfront.briefreport.model.BriefreportInfoCnt;

@Service
public class BriefreportInfoCntServiceImpl implements BriefreportInfoCntService {
    @Resource
    private BriefreportInfoCntMapper briefreportInfoCntMapper;

    @Override
    public BriefreportInfoCnt selectByPrimaryKey(Integer briefreportInfoId) {
        return this.briefreportInfoCntMapper.selectByPrimaryKey(briefreportInfoId);
    }

    @Override
    public int delBriefreportInfoCntByBriefreportInfoIds(String[] briefreportInfoIds) {
        return this.briefreportInfoCntMapper.delBriefreportInfoCntByBriefreportInfoIds(briefreportInfoIds);
    }

}
