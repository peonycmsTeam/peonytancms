package com.peony.peonyfront.report.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.report.dao.ReportMapper;
import com.peony.peonyfront.report.model.Report;

@Service
public class ReportServiceImpl implements ReportService {
    @Resource
    private ReportMapper reportMapper;

    @Override
    public Pagination<Report> selectReportByPage(Report report) {
        List<Report> reports = this.reportMapper.selectReportByPage(report);
        return new Pagination<Report>(reports, report.getPageParameter());
    }

    @Override
    public Report selectByPrimaryKey(Integer reportId) {
        return this.reportMapper.selectByPrimaryKey(reportId);
    }

}
