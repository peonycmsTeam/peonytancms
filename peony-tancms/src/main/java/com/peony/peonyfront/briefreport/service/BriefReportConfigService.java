package com.peony.peonyfront.briefreport.service;

import java.util.List;

import com.peony.peonyfront.briefreport.model.BriefReportConfig;

public interface BriefReportConfigService {
    int deleteByPrimaryKey(Integer briefreportConfigId);

    int deleteByUserId(Integer userId);

    int insert(BriefReportConfig record);

    int insertSelective(BriefReportConfig record);

    BriefReportConfig selectByPrimaryKey(Integer briefreportConfigId);

    int updateByPrimaryKeySelective(BriefReportConfig record);

    int updateByPrimaryKey(BriefReportConfig record);

    List<BriefReportConfig> selectBriefReportConfigByUserId(Integer userId);
}
