package com.peony.peonyfront.report.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.peony.core.base.controller.BaseController;
import com.peony.core.base.pojo.PageParameter;
import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.report.model.ReportTemp;
import com.peony.peonyfront.report.service.ReportTempService;

/**
 * 日报模板
 * 
 * @author jhj
 */
@Controller
@RequestMapping("/reportTemp")
public class ReportTempController extends BaseController {

    @Autowired
    private ReportTempService reportTempService;

    /**
     * 日报模板列表
     * 
     * @param reportTemp
     * @param request
     * @return
     */
    @RequestMapping(value = "/listReportTemp")
    public ModelAndView listReportTemp(@ModelAttribute("reportTemp") ReportTemp reportTemp, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        User user = (User) request.getSession().getAttribute("userfront");
        PageParameter pageParameter = new PageParameter();
        if (null == request.getParameter("pageNo")) {
            pageParameter.setCurrentPage(1);
        } else {
            pageParameter.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
        }
        reportTemp.setPageParameter(pageParameter);
        // --用户id
        reportTemp.setUserId(String.valueOf(user.getUserId()));
        Pagination<ReportTemp> pagination = this.reportTempService.selectReportTempByPage(reportTemp);
        mv.addObject("pagination", pagination);
        mv.setViewName("reporttemp/list_reporttemp");
        return mv;
    }

}
