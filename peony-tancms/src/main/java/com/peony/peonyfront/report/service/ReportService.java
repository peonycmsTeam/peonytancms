package com.peony.peonyfront.report.service;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.report.model.Report;

public interface ReportService {
    /**
     * 查询日报分页
     * 
     * @return
     */
    Pagination<Report> selectReportByPage(Report report);

    Report selectByPrimaryKey(Integer reportId);
}
