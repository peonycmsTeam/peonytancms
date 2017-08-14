package com.peony.peonyfront.officeplatform.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.peony.core.base.pojo.PageParameter;
import com.peony.core.base.pojo.Pagination;
import com.peony.core.common.utils.log.OperateType;
import com.peony.peonyfront.key.service.IdService;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.officeplatform.model.Mail;
import com.peony.peonyfront.officeplatform.service.MailService;
import com.peony.peonyfront.operationlog.model.OperationLog;
import com.peony.peonyfront.operationlog.service.OperationLogService;
import com.peony.peonyfront.util.DocumentHandler;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.Type;

@Controller
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private MailService         mailService;
    @Autowired
    private IdService           idService;

    @Autowired
    private OperationLogService operationLogService;

    /**
     * 跳转到发件箱页面
     * 
     * @return
     */
    @RequestMapping(value = "/listMail")
    public ModelAndView listMail(@ModelAttribute("warning") Mail mail, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        System.out.println("0".equals(request.getParameter("pageNo")));
        PageParameter pageParameter = new PageParameter();
        if (null == request.getParameter("pageNo")) {
            pageParameter.setCurrentPage(1);
            mv.addObject("page", 1);
        } else {
            pageParameter.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
            mv.addObject("page", request.getParameter("pageNo"));
        }
        // --用户id
        User user = (User) request.getSession().getAttribute("userfront");
        mail.setSendUserId(user.getUserId());
        mail.setPageParameter(pageParameter);
        Pagination<Mail> pagination = this.mailService.selectMailByPage(mail);

        User u = (User) request.getSession().getAttribute("userfront");
        OperationLog operationLog = new OperationLog(new Date(), u.getUserId(), u.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "发件箱", OperateType.FIND.toString(), OperateMode.办公平台.toString());
        this.operationLogService.insertSelective(operationLog);

        mv.addObject("pagination", pagination);
        mv.setViewName("officeplatform/list_mail");
        return mv;
    }

    /**
     * 跳转到发送邮件页面
     * 
     * @return
     */
    @RequestMapping(value = "/toSendMail")
    public ModelAndView toSendMail(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("officeplatform/send_mail");
        User u = (User) request.getSession().getAttribute("userfront");
        OperationLog operationLog = new OperationLog(new Date(), u.getUserId(), u.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "写邮件", OperateType.FIND.toString(), OperateMode.办公平台.toString());
        this.operationLogService.insertSelective(operationLog);
        return mv;
    }

    /**
     * 根据id删除邮件
     * 
     * @param mailId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/delMail")
    @ResponseBody
    public Map<String, Object> delMail(@ModelAttribute("mailId") String mailId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> mapModel = Maps.newHashMap();
        String[] id = mailId.split(",");
        int totalCount = Integer.parseInt(request.getParameter("totalCount"));
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));
        if (this.mailService.delMailByMailIds(id) > 0) {
            mapModel.put("state", true);
            int totalPage = 0;
            if ((totalCount - id.length) % 10 == 0) {
                totalPage = (totalCount - id.length) / 10;
            } else {
                totalPage = (totalCount - id.length) / 10 + 1;
            }
            if (totalPage == 0 || pageNo == 0) {
                mapModel.put("pageNo", 1);
            } else if (pageNo <= totalPage) {
                mapModel.put("pageNo", pageNo);
            } else {
                mapModel.put("pageNo", totalPage);
            }
        } else {
            mapModel.put("state", false);
        }
        return mapModel;
    }

    /**
     * 发送邮件
     * 
     * @param mail
     * @param request
     * @param response
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/sendMail")
    @ResponseBody
    public String sendMail(@ModelAttribute("warning") Mail mail, HttpServletRequest request, HttpServletResponse response) throws MessagingException, UnsupportedEncodingException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("content", mail.getContent());
        map.put("title", mail.getTitle());
        map.put("email", mail.getReceiveUser());
        DocumentHandler doc = new DocumentHandler();
        int i = doc.SendMailtest(map, request, response);
        mail.setState(String.valueOf(i));
        saveMail(mail);
        // SimpleEmail em = new SimpleEmail();
        // em.setHostName("smtp.vip.163.com");
        // em.setAuthentication("yqvip@vip.163.com", "peony123!");
        // User user = (User) request.getSession().getAttribute("userfront");
        // mail.setSendUserId(user.getUserId());
        // String s;
        // try {
        // em.setFrom(javax.mail.internet.MimeUtility.encodeText("云网端舆情服务系统")+"
        // <"+"yqvip@vip.163.com"+">");
        // em.addTo(mail.getReceiveUser());
        // em.setSubject(mail.getTitle());
        // em.setCharset("GB2312");
        // em.setMsg(mail.getContent());
        // em.send();
        // // 保存邮件
        // mail.setState("1");
        // saveMail(mail);
        // s = "1";
        // } catch (EmailException e) {
        // e.printStackTrace();
        // mail.setState("0");
        // saveMail(mail);
        // s = "0";
        // }
        return String.valueOf(i);

    }

    /**
     * 保存已发送邮件
     * 
     * @param sate
     * @return
     */
    private int saveMail(Mail mail) {
        mail.setIsDelete(0);
        mail.setMailId(idService.NextKey("mailId"));
        mail.setSendTime(new Date());
        return this.mailService.insertSelective(mail);
    }

    /**
     * 
     * @param mail
     * @param request
     * @param response
     */
    @RequestMapping(value = "/testSendMail")
    @ResponseBody
    public void mailtest(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("userfront");
        java.util.Properties props = new java.util.Properties();
        props.setProperty("mail.smtp.auth", "true");// 指定是否需要SMTP验证
        props.setProperty("mail.smtp.host", "smtp.163.com");// 指定SMTP服务器
        props.put("mail.transport.protocol", "smtp");
        Session mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(true);// 是否在控制台显示debug信息
        InternetAddress fromAddress;
        try {
            fromAddress = new InternetAddress("zhyz0727@163.com");
            InternetAddress toAddress = new InternetAddress(user.getEmail());
            MimeMessage testMessage = new MimeMessage(mailSession);
            testMessage.setFrom(fromAddress);
            testMessage.addRecipient(javax.mail.Message.RecipientType.TO, toAddress);
            testMessage.setSentDate(new java.util.Date());
            testMessage.setSubject(MimeUtility.encodeText("html邮件测试", "gb2312", "B"));
            testMessage.setContent(creatHtml(request, response), "text/html;charset=gb2312");
            Transport transport = mailSession.getTransport("smtp");
            transport.connect("smtp.163.com", "zhyz0727@163.com", "yunzhao0727");
            transport.sendMessage(testMessage, testMessage.getAllRecipients());
            transport.close();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成html
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public String creatHtml(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DocumentHandler doc = new DocumentHandler();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        String imgUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/images/wyfl_nl_033.jpg";
        dataMap.put("img", imgUrl);
        String fileName = "123.html";
        SimpleDateFormat datatime = new SimpleDateFormat("yyyyMMdd");
        String ctxPath = request.getSession().getServletContext().getRealPath("/") + "files/" + datatime.format(new Date()) + "/";
        doc.createHtml(ctxPath, fileName, null, dataMap, request, response);
        File f = new File(ctxPath + fileName);
        String lines = "";
        if (f.exists()) {
            InputStreamReader read = new InputStreamReader(new FileInputStream(f), "utf-8");
            BufferedReader reader = new BufferedReader(read);
            String line;
            while ((line = reader.readLine()) != null) {
                lines += line;
            }
            read.close();
        }
        return lines;
    }
}
