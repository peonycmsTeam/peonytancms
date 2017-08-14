package com.peony.peonyfront.report.service;

import java.util.List;

import com.peony.peonyfront.report.model.ReportConfig;

public interface ReportConfigService {
    int deleteByPrimaryKey(Integer reportConfigId);

    int insert(ReportConfig record);

    int insertSelective(ReportConfig record);

    ReportConfig selectByPrimaryKey(Integer reportConfigId);

    int updateByPrimaryKeySelective(ReportConfig record);

    int updateByPrimaryKey(ReportConfig record);

    List<ReportConfig> selectReportConfigByUserId(Integer userId);
}
