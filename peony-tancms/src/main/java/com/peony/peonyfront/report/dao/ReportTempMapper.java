package com.peony.peonyfront.report.dao;

import java.util.List;

import com.peony.peonyfront.report.model.ReportTemp;

public interface ReportTempMapper {
    int deleteByPrimaryKey(Integer reportTempId);

    int insert(ReportTemp record);

    int insertSelective(ReportTemp record);

    ReportTemp selectByPrimaryKey(Integer reportTempId);

    int updateByPrimaryKeySelective(ReportTemp record);

    int updateByPrimaryKey(ReportTemp record);

    List<ReportTemp> selectReportTempByPage(ReportTemp record);

    List<ReportTemp> selectReportTemps(ReportTemp record);
}