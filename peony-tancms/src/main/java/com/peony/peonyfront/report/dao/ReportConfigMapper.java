package com.peony.peonyfront.report.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peony.peonyfront.report.model.ReportConfig;

public interface ReportConfigMapper {
    int deleteByPrimaryKey(Integer reportConfigId);

    int insert(ReportConfig record);

    int insertSelective(ReportConfig record);

    ReportConfig selectByPrimaryKey(Integer reportConfigId);

    int updateByPrimaryKeySelective(ReportConfig record);

    int updateByPrimaryKey(ReportConfig record);

    List<ReportConfig> selectReportConfigByUserId(@Param(value = "userId") Integer userId);
}