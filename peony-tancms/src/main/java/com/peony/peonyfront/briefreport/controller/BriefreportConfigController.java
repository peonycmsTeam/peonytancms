package com.peony.peonyfront.briefreport.controller;

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
import com.peony.peonyfront.briefreport.model.BriefReportConfig;
import com.peony.peonyfront.briefreport.model.BriefReportTemp;
import com.peony.peonyfront.briefreport.service.BriefReportConfigService;
import com.peony.peonyfront.briefreport.service.BriefreportTempService;
import com.peony.peonyfront.key.service.IdService;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.login.service.UserService;
import com.peony.peonyfront.mail.model.MailConfig;
import com.peony.peonyfront.operationlog.model.OperationLog;
import com.peony.peonyfront.operationlog.service.OperationLogService;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;

/**
 * 简报模板配置
 * 
 * @author jhj
 */
@Controller
@RequestMapping("/briefreportconfig")
public class BriefreportConfigController extends BaseController {

    @Autowired
    private UserService              userService;              // 用户服务接口

    @Autowired
    private BriefReportConfigService briefReportConfigService; // 简报配置服务接口

    @Autowired
    private BriefreportTempService   briefreportTempService;   // 邮件模板服务接口

    @Autowired
    private IdService                idService;                // Id配置服务接口

    @Autowired
    private OperationLogService      operationLogService;

    /**
     * 日报配置
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listBriefReportConfig")
    public ModelAndView listBriefReportConfig(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("briefReportTemp") BriefReportTemp briefReportTemp) {
        User user = (User) request.getSession().getAttribute("userfront");
        ModelAndView mv = new ModelAndView();
        // mailTemp.setUserId(String.valueOf(user.getUserId()));
        if (user.getUserType().equals("2")) {
            briefReportTemp.setUserType("2");
        } else {
            briefReportTemp.setUserType("1");
        }

        List<BriefReportTemp> listBriefReportTemp = this.briefreportTempService.selectBriefReportTemps(briefReportTemp);

        List<BriefReportConfig> listBriefReportConfig = this.briefReportConfigService.selectBriefReportConfigByUserId(user.getUserId());
        if (listBriefReportConfig.size() > 0) {
            mv.addObject("briefReportConfig", listBriefReportConfig.get(0));
        } else {
            mv.addObject("briefReportConfig", new BriefReportConfig());
        }

        OperationLog operationLog = new OperationLog(new Date(), user.getUserId(), user.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "简报模板管理", OperateType.FIND.toString(), OperateMode.前台系统配置.toString());
        this.operationLogService.insertSelective(operationLog);

        mv.addObject("listBriefReportTemp", listBriefReportTemp);
        mv.setViewName("briefreport/briefreport_config");
        return mv;
    }

    @RequestMapping(value = "/updateBriefReportConfig", method = RequestMethod.POST)
    @ResponseBody
    public String updateReportConfig(@ModelAttribute("briefReportConfig") BriefReportConfig briefReportConfig, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("userfront");
        if (briefReportConfig.getBriefreportConfigId() != null) {
            briefReportConfig.setCreateTime(new Date());
            this.briefReportConfigService.updateByPrimaryKeySelective(briefReportConfig);
        } else {
            this.briefReportConfigService.deleteByUserId(user.getUserId());
            briefReportConfig.setUserId(user.getUserId());
            briefReportConfig.setCreateTime(new Date());
            briefReportConfig.setBriefreportConfigId(idService.NextKey("briefreport_config_id"));
            this.briefReportConfigService.insertSelective(briefReportConfig);
        }
        return "";
    }

}
