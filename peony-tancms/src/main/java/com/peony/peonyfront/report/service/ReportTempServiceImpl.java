package com.peony.peonyfront.report.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.report.dao.ReportTempMapper;
import com.peony.peonyfront.report.model.ReportTemp;

/**
 * 日报模板服务
 * 
 * @author jhj
 */
@Service
public class ReportTempServiceImpl implements ReportTempService {

    @Resource
    private ReportTempMapper reportTempMapper;

    @Override
    public Pagination<ReportTemp> selectReportTempByPage(ReportTemp reportTemp) {
        List<ReportTemp> reportTemps = this.reportTempMapper.selectReportTempByPage(reportTemp);
        return new Pagination<ReportTemp>(reportTemps, reportTemp.getPageParameter());
    }

    @Override
    public List<ReportTemp> selectReports(ReportTemp reportTemp) {
        return this.reportTempMapper.selectReportTemps(reportTemp);
    }

    @Override
    public int deleteByPrimaryKey(Integer reportTempId) {
        return this.reportTempMapper.deleteByPrimaryKey(reportTempId);
    }

    @Override
    public int insert(ReportTemp record) {
        return this.reportTempMapper.insert(record);
    }

    @Override
    public int insertSelective(ReportTemp record) {
        return this.reportTempMapper.insertSelective(record);
    }

    @Override
    public ReportTemp selectByPrimaryKey(Integer reportTempId) {
        return this.reportTempMapper.selectByPrimaryKey(reportTempId);
    }

    @Override
    public int updateByPrimaryKeySelective(ReportTemp record) {
        return this.reportTempMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ReportTemp record) {
        return this.reportTempMapper.updateByPrimaryKey(record);
    }

}
