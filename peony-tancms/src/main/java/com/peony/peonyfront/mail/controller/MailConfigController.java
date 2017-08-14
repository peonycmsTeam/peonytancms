package com.peony.peonyfront.mail.controller;

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
import com.peony.peonyfront.key.service.IdService;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.login.service.UserService;
import com.peony.peonyfront.mail.model.MailConfig;
import com.peony.peonyfront.mail.model.MailTemp;
import com.peony.peonyfront.mail.service.MailConfigService;
import com.peony.peonyfront.mail.service.MailTempService;
import com.peony.peonyfront.operationlog.model.OperationLog;
import com.peony.peonyfront.operationlog.service.OperationLogService;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;

/**
 * 邮件模板配置
 * 
 * @author jhj
 */
@Controller
@RequestMapping("/mail")
public class MailConfigController extends BaseController {

    @Autowired
    private UserService         userService;        // 用户服务接口

    @Autowired
    private MailConfigService   mailConfigService;  // 邮件配置服务接口

    @Autowired
    private MailTempService     mailTempService;    // 邮件模板服务接口

    @Autowired
    private IdService           idService;          // Id配置服务接口

    @Autowired
    private OperationLogService operationLogService;

    /**
     * 日报配置
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listMailConfig")
    public ModelAndView listMailConfig(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("mailTemp") MailTemp mailTemp) {
        User user = (User) request.getSession().getAttribute("userfront");
        ModelAndView mv = new ModelAndView();
        // mailTemp.setUserId(String.valueOf(user.getUserId()));
        List<MailTemp> listMailTemp = this.mailTempService.selectMailTemps(mailTemp);

        List<MailConfig> listMailConfig = this.mailConfigService.selectMailConfigByUserId(user.getUserId());
        if (listMailConfig.size() > 0) {
            mv.addObject("mailConfig", listMailConfig.get(0));
        } else {
            mv.addObject("mailConfig", new MailConfig());
        }

        OperationLog operationLog = new OperationLog(new Date(), user.getUserId(), user.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "邮件模板配置", OperateType.FIND.toString(), OperateMode.前台系统配置.toString());
        this.operationLogService.insertSelective(operationLog);

        mv.addObject("listMailTemp", listMailTemp);
        mv.setViewName("mail/mail_config");
        return mv;
    }

    @RequestMapping(value = "/updateMailConfig", method = RequestMethod.POST)
    @ResponseBody
    public String updateMailConfig(@ModelAttribute("MailConfig") MailConfig mailConfig, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("userfront");
        if (mailConfig.getMailConfigId() != null) {
            mailConfig.setCreateTime(new Date());
            this.mailConfigService.updateByPrimaryKeySelective(mailConfig);
        } else {
            this.mailConfigService.deleteByUserId(user.getUserId());
            mailConfig.setUserId(user.getUserId());
            mailConfig.setCreateTime(new Date());
            mailConfig.setMailConfigId(idService.NextKey("mail_config_id"));
            this.mailConfigService.insertSelective(mailConfig);
        }
        return "";
    }

}
