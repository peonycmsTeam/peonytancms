package com.peony.peonyfront.briefreport.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peony.peonyfront.briefreport.model.BriefReportConfig;

public interface BriefReportConfigMapper {
    int deleteByPrimaryKey(Integer briefreportConfigId);

    int deleteByUserId(@Param(value = "userId") Integer userId);

    int insert(BriefReportConfig record);

    int insertSelective(BriefReportConfig record);

    BriefReportConfig selectByPrimaryKey(Integer briefreportConfigId);

    int updateByPrimaryKeySelective(BriefReportConfig record);

    int updateByPrimaryKey(BriefReportConfig record);

    List<BriefReportConfig> selectBriefReportConfigByUserId(@Param(value = "userId") Integer userId);
}