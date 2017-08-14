package com.peony.peonyfront.report.dao;

import java.util.List;

import com.peony.peonyfront.report.model.Report;

public interface ReportMapper {
    int deleteByPrimaryKey(Integer reportId);

    int insert(Report report);

    int insertSelective(Report report);

    Report selectByPrimaryKey(Integer reportId);

    int updateByPrimaryKeySelective(Report report);

    int updateByPrimaryKey(Report report);

    /**
     * 查询日报分页
     * 
     * @return
     */
    List<Report> selectReportByPage(Report report);
}