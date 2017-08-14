package com.peony.peonyfront.briefreport.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.peonyfront.briefreport.dao.BriefReportTempMapper;
import com.peony.peonyfront.briefreport.model.BriefReportTemp;

/**
 * 简报模板
 *
 * @author jhj
 */
@Service
public class BriefreportTempServiceImpl implements BriefreportTempService {
    @Resource
    private BriefReportTempMapper briefReportTempMapper;

    @Override
    public int deleteByPrimaryKey(Integer briefreportTempId) {
        return this.briefReportTempMapper.deleteByPrimaryKey(briefreportTempId);
    }

    @Override
    public int insert(BriefReportTemp record) {
        return this.briefReportTempMapper.insert(record);
    }

    @Override
    public int insertSelective(BriefReportTemp record) {
        return this.briefReportTempMapper.insertSelective(record);
    }

    @Override
    public BriefReportTemp selectByPrimaryKey(Integer briefreportTempId) {
        return this.briefReportTempMapper.selectByPrimaryKey(briefreportTempId);
    }

    @Override
    public int updateByPrimaryKeySelective(BriefReportTemp record) {
        return this.briefReportTempMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BriefReportTemp record) {
        return this.briefReportTempMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<BriefReportTemp> selectBriefReportTemps(BriefReportTemp record) {
        return this.briefReportTempMapper.selectBriefReportTemps(record);
    }

    @Override
    public BriefReportTemp findTemByUserId(Integer userId) {
        BriefReportTemp briefReportTemp = new BriefReportTemp();
        briefReportTemp.setUserId(String.valueOf(userId));
        return this.briefReportTempMapper.findTemByUserId(briefReportTemp);
    }
}
