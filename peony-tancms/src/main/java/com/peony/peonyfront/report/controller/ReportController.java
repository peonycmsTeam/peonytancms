package com.peony.peonyfront.report.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.peony.core.base.pojo.PageParameter;
import com.peony.core.base.pojo.Pagination;
import com.peony.core.common.utils.log.OperateType;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.operationlog.model.OperationLog;
import com.peony.peonyfront.operationlog.service.OperationLogService;
import com.peony.peonyfront.report.model.Report;
import com.peony.peonyfront.report.service.ReportService;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.Type;

/**
 * 日报
 * 
 * @author zhyz
 * 
 */
@Controller
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService       reportService;

    @Autowired
    private OperationLogService operationLogService;

    /**
     * 跳转到办公平台内七日日报页面
     * 
     * @return
     */
    @RequestMapping(value = "/listSevenReport")
    public ModelAndView listSevenReport(@ModelAttribute("report") Report report, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        PageParameter pageParameter = new PageParameter();
        pageParameter.setCurrentPage(1);
        pageParameter.setPageSize(7);
        report.setPageParameter(pageParameter);
        // --用户id
        User user = (User) request.getSession().getAttribute("userfront");
        report.setUserId(String.valueOf(user.getUserId()));
        Pagination<Report> pagination = this.reportService.selectReportByPage(report);

        OperationLog operationLog = new OperationLog(new Date(), user.getUserId(), user.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "近七日日报", OperateType.FIND.toString(), OperateMode.办公平台.toString());
        this.operationLogService.insertSelective(operationLog);

        mv.addObject("pagination", pagination);
        mv.setViewName("officeplatform/list_sevenreport");
        return mv;
    }

    /**
     * 跳转到日报页面
     * 
     * @return
     */
    @RequestMapping(value = "/listReport")
    public ModelAndView listReport(@ModelAttribute("report") Report report, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        PageParameter pageParameter = new PageParameter();
        if (null == request.getParameter("pageNo")) {
            pageParameter.setCurrentPage(1);
        } else {
            pageParameter.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
        }
        report.setPageParameter(pageParameter);
        // --用户id
        User user = (User) request.getSession().getAttribute("userfront");
        report.setUserId(String.valueOf(user.getUserId()));
        Pagination<Report> pagination = this.reportService.selectReportByPage(report);
        mv.addObject("pagination", pagination);
        mv.setViewName("report/list_report");
        return mv;
    }

    @RequestMapping(value = "/loadView")
    public ModelAndView loadView(@ModelAttribute("report") Report report, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        int reportId = report.getReportId();
        report = this.reportService.selectByPrimaryKey(reportId);
        String address = report.getAddress();
        address = address.substring(0, address.lastIndexOf("/") + 1);
        report.setAddress(address);
        mv.addObject("report", report);
        mv.setViewName("report/view_report");
        return mv;
    }
}
