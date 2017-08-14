package com.peony.peonyfront.report.service;

import java.util.List;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.report.model.ReportTemp;

public interface ReportTempService {
    int deleteByPrimaryKey(Integer reportTempId);

    int insert(ReportTemp record);

    int insertSelective(ReportTemp record);

    ReportTemp selectByPrimaryKey(Integer reportTempId);

    int updateByPrimaryKeySelective(ReportTemp record);

    int updateByPrimaryKey(ReportTemp record);

    /**
     * 日报模板分页
     * 
     * @param reportTemp
     * @return
     */
    Pagination<ReportTemp> selectReportTempByPage(ReportTemp reportTemp);

    /**
     * 日报模板不分页
     * 
     * @param reportTemp
     * @return
     */
    List<ReportTemp> selectReports(ReportTemp reportTemp);

}
