package com.peony.peonyfront.briefreport.service;

import java.util.List;

import com.peony.peonyfront.briefreport.model.BriefReportTemp;

public interface BriefreportTempService {
    int deleteByPrimaryKey(Integer briefreportTempId);

    int insert(BriefReportTemp record);

    int insertSelective(BriefReportTemp record);

    BriefReportTemp selectByPrimaryKey(Integer briefreportTempId);

    int updateByPrimaryKeySelective(BriefReportTemp record);

    int updateByPrimaryKey(BriefReportTemp record);

    List<BriefReportTemp> selectBriefReportTemps(BriefReportTemp record);

    /**
     * 根据用户id找到用户所使用模板
     * 
     * @param record
     * @return
     */
    BriefReportTemp findTemByUserId(Integer userId);
}
