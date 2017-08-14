package com.peony.peonyfront.report.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.peony.core.base.controller.BaseController;
import com.peony.peonyfront.key.service.IdService;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.operationlog.model.OperationLog;
import com.peony.peonyfront.operationlog.service.OperationLogService;
import com.peony.peonyfront.report.model.ReportConfig;
import com.peony.peonyfront.report.model.ReportTemp;
import com.peony.peonyfront.report.service.ReportConfigService;
import com.peony.peonyfront.report.service.ReportTempService;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;

/**
 * @author jhj 日报配置
 */
@Controller
@RequestMapping("/reportconfig")
public class ReportConfigController extends BaseController {
    @Autowired
    private ReportConfigService reportConfigService;

    @Autowired
    private ReportTempService   reportTempService;

    @Autowired
    private IdService           idService;

    @Autowired
    private OperationLogService operationLogService;

    /**
     * 日报配置
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listReportConfig")
    public ModelAndView listReportConfig(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("reportTemp") ReportTemp reportTemp) {
        User user = (User) request.getSession().getAttribute("userfront");
        ModelAndView mv = new ModelAndView();
        // reportTemp.setUserId(String.valueOf(user.getUserId()));
        List<ReportTemp> listReportTemp = this.reportTempService.selectReports(reportTemp);

        List<ReportConfig> listReprotConfig = this.reportConfigService.selectReportConfigByUserId(user.getUserId());
        if (listReprotConfig.size() > 0) {
            mv.addObject("reportConfig", listReprotConfig.get(0));
        } else {
            mv.addObject("reportConfig", new ReportConfig());
        }

        List timeList = new ArrayList();
        for (int i = 0; i < 25; i++) {
            timeList.add(i);
        }

        OperationLog operationLog = new OperationLog(new Date(), user.getUserId(), user.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "日报定制", OperateType.FIND.toString(), OperateMode.前台系统配置.toString());
        this.operationLogService.insertSelective(operationLog);

        mv.addObject("listReportTemp", listReportTemp);
        mv.addObject("timeList", timeList);
        mv.setViewName("report/report_config");
        return mv;
    }

    @RequestMapping(value = "/updateReportConfig", method = RequestMethod.POST)
    @ResponseBody
    public String updateReportConfig(@ModelAttribute("reportConfig") ReportConfig reportConfig, HttpServletRequest request) {

        if (reportConfig.getReportConfigId() != null) {
            ReportConfig rc = this.reportConfigService.selectByPrimaryKey(reportConfig.getReportConfigId());
            if (!rc.getReportCreate().equals("2")) {// 已开通
                if (reportConfig.getReportCreate() == null) {
                    rc.setReportCreate("0");
                } else {
                    rc.setReportCreate("1");
                }
            }
            if (!rc.getReportSend().equals("2")) {// 已开通
                if (reportConfig.getReportSend() == null) {
                    rc.setReportSend("0");
                } else {
                    rc.setReportSend("1");
                }
            }
            rc.setReportTempId(reportConfig.getReportTempId());
            rc.setCreateTime(reportConfig.getCreateTime());
            this.reportConfigService.updateByPrimaryKeySelective(rc);
        }
        return "";
    }

}
