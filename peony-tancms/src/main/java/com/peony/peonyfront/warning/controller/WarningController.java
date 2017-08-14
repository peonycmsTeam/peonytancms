package com.peony.peonyfront.warning.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.peony.core.base.pojo.PageParameter;
import com.peony.core.base.pojo.Pagination;
import com.peony.core.common.utils.DateUtils;
import com.peony.core.common.utils.UUIDGenerator;
import com.peony.peonyfront.attention.model.AttentionInfo;
import com.peony.peonyfront.attention.service.AttentionInfoService;
import com.peony.peonyfront.briefreport.model.BriefReportTemp;
import com.peony.peonyfront.briefreport.model.BriefreportInfo;
import com.peony.peonyfront.briefreport.service.BriefreportInfoService;
import com.peony.peonyfront.briefreport.service.BriefreportTempService;
import com.peony.peonyfront.event.model.Eventnews;
import com.peony.peonyfront.event.service.EventnewsService;
import com.peony.peonyfront.event.service.ExportSupportService;
import com.peony.peonyfront.key.service.IdService;
import com.peony.peonyfront.login.model.PhoneLogin;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.login.service.PhoneLoginService;
import com.peony.peonyfront.operationlog.model.OperationLog;
import com.peony.peonyfront.operationlog.service.OperationLogService;
import com.peony.peonyfront.subject.model.SubjectPage;
import com.peony.peonyfront.subject.model.WebDictionary;
import com.peony.peonyfront.subject.service.SubjectPageService;
import com.peony.peonyfront.subject.service.WebDictionaryService;
import com.peony.peonyfront.topic.model.TopicPage;
import com.peony.peonyfront.util.DocumentHandler;
import com.peony.peonyfront.util.HTMLSpirit;
import com.peony.peonyfront.util.Snapshot;
import com.peony.peonyfront.util.export.AssignedCell;
import com.peony.peonyfront.util.export.ExportExcel;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;
import com.peony.peonyfront.warning.model.Warning;
import com.peony.peonyfront.warning.service.WarningService;

@Controller
@RequestMapping("/warning")
public class WarningController {
    @Autowired
    private WarningService         warningservice;
    @Autowired
    private SubjectPageService     subjectPageService;
    @Autowired
    private IdService              idService;
    @Autowired
    private WebDictionaryService   webDictionaryService;
    @Autowired
    private BriefreportTempService briefreportTempService;
    @Autowired
    private EventnewsService       eventnewsService;
    @Autowired
    private BriefreportInfoService briefreportInfoService;
    @Autowired
    private AttentionInfoService   attentionInfoService;

    @Autowired
    private OperationLogService    operationLogService;

    @Autowired
    private PhoneLoginService      PhoneLoginService;
    @Autowired
    protected ExportSupportService exportSupportService;

    /**
     * 显示预警列表
     * 
     * @param warning
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listWarning")
    public ModelAndView listWarning(@ModelAttribute("warning") Warning warning, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        PageParameter pageParameter = new PageParameter();
        List<WebDictionary> dictonary = this.webDictionaryService.findAllWebDictionary();
        mv.addObject("dictonary", dictonary);
        // 每页显示条数
        if (warning.getPageSize() != 0) {
            mv.addObject("pageSize", warning.getPageSize());
            pageParameter.setPageSize(warning.getPageSize());
        }
        if (null == request.getParameter("pageNo")) {
            pageParameter.setCurrentPage(1);
            mv.addObject("page", 1);
        } else {
            pageParameter.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
            mv.addObject("page", request.getParameter("pageNo"));
        }
        // 选择类型
        if (warning.getWaringType() != null) {
            mv.addObject("waringType", warning.getWaringType());
            if (warning.getWaringType() == 0) {
                warning.setWaringType(null);
            }
        }
        // 选择倾向性
        if (warning.getPolarity() != null) {
            mv.addObject("polarity", warning.getPolarity());
            if (warning.getPolarity() == 2) {
                warning.setPolarity(null);
            }
        }
        // 排序类型(发布时间和预警时间)
        if (warning.getTime() != null) {
            mv.addObject("time", warning.getTime());
            if ("1".equals(warning.getTime())) {
                warning.setTime(null);
            }
        }
        // 查询已读未读
        if (warning.getIsRead() != null) {
            mv.addObject("isRead", warning.getIsRead());
            if ("2".equals(warning.getIsRead())) {
                warning.setIsRead(null);
            }
        }
        // 自动手动预警查询
        if (warning.getType() != null) {
            mv.addObject("type", warning.getType());
            if (warning.getType() == 2) {
                warning.setType(null);
            }
        }
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatterBegin = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        // 选择显示时间
        if (warning.getSelectDate() != null) {
            mv.addObject("selectDate", warning.getSelectDate());
            // 显示全部
            if ("1".equals(warning.getSelectDate())) {
                warning.setSelectDate(null);
            }
            // 近三天（前两天加今天）
            if ("3".equals(warning.getSelectDate())) {
                cal.add(Calendar.DAY_OF_MONTH, -2);
            }
            // 近七天（前六天加今天）
            if ("4".equals(warning.getSelectDate())) {
                cal.add(Calendar.DAY_OF_MONTH, -6);
            }
            // 近30天（前29天加今天）
            if ("5".equals(warning.getSelectDate())) {
                cal.add(Calendar.MONTH, -1);
            }
        } else {
            mv.addObject("selectDate", "3");
            warning.setSelectDate("3");
            cal.add(Calendar.DAY_OF_MONTH, -2);
        }
        warning.setBeginDate(formatterBegin.format(cal.getTime()));
        // --用户id为1
        User user = (User) request.getSession().getAttribute("userfront");
        warning.setUserId(user.getUserId());
        warning.setPageParameter(pageParameter);
        Pagination<Warning> warnings = this.warningservice.selectWaringByPage(warning);

        OperationLog operationLog = new OperationLog(new Date(), user.getUserId(), user.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "舆情预警", OperateType.FIND.toString(), OperateMode.舆情预警.toString());
        this.operationLogService.insertSelective(operationLog);
        mv.addObject("warnings", warnings);
        mv.setViewName("warning/list_warning");
        return mv;
    }

    /**
     * 首页显示预警内容
     * 
     * @return
     */
    @RequestMapping(value = "/listhomePageWarnings")
    public ModelAndView listhomePageWarnings(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        // 预警信息列表
        PageParameter pageParameter = new PageParameter();
        pageParameter.setCurrentPage(1);
        pageParameter.setPageSize(7);
        Warning warning = new Warning();
        // --用户id
        User user = (User) request.getSession().getAttribute("userfront");
        warning.setUserId(user.getUserId());
        warning.setPageParameter(pageParameter);
        Pagination<Warning> warnings = this.warningservice.selectWaringByPage(warning);
        // 新闻，正负面不显示
        mv.addObject("warnings", warnings);
        return mv;
    }

    /**
     * 办公平台近七日预警
     * 
     * @param warning
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listSevenWarning")
    public ModelAndView listSevenWarning(@ModelAttribute("warning") Warning warning, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        PageParameter pageParameter = new PageParameter();
        List<WebDictionary> dictonary = this.webDictionaryService.findAllWebDictionary();
        mv.addObject("dictonary", dictonary);
        // 按标题查询
        if (warning.getTitle() != null) {
            mv.addObject("title", warning.getTitle());
            if ("".equals(warning.getTitle())) {
                warning.setTitle(null);
            }
        }
        // 每页显示条数
        if (warning.getPageSize() != 0) {
            mv.addObject("pageSize", warning.getPageSize());
            pageParameter.setPageSize(warning.getPageSize());
        }
        if (null == request.getParameter("pageNo")) {
            pageParameter.setCurrentPage(1);
            mv.addObject("page", 1);
        } else {
            pageParameter.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
            mv.addObject("page", request.getParameter("pageNo"));
        }
        // 选择类型
        if (warning.getWaringType() != null) {
            mv.addObject("waringType", warning.getWaringType());
            if (warning.getWaringType() == 0) {
                warning.setWaringType(null);
            }
        }
        // 查询已读未读
        if (warning.getIsRead() != null) {
            mv.addObject("isRead", warning.getIsRead());
            if ("2".equals(warning.getIsRead())) {
                warning.setIsRead(null);
            }
        }
        // 选择倾向性
        if (warning.getPolarity() != null) {
            mv.addObject("polarity", warning.getPolarity());
            if (warning.getPolarity() == 2) {
                warning.setPolarity(null);
            }
        }
        // 自动手动预警查询
        if (warning.getType() != null) {
            mv.addObject("type", warning.getType());
            if (warning.getType() == 2) {
                warning.setType(null);
            }
        }
        // 显示近七天预警
        warning.setSelectDate("7");
        SimpleDateFormat formatterBegin = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -6);
        warning.setBeginDate(formatterBegin.format(cal.getTime()));

        // 排序类型(发布时间和预警时间)
        if (warning.getTime() != null) {
            mv.addObject("time", warning.getTime());
            if ("1".equals(warning.getTime())) {
                warning.setTime(null);
            }
        }
        // --用户id为1
        User user = (User) request.getSession().getAttribute("userfront");
        warning.setUserId(user.getUserId());
        warning.setPageParameter(pageParameter);
        Pagination<Warning> warnings = this.warningservice.selectWaringByPage(warning);

        OperationLog operationLog = new OperationLog(new Date(), user.getUserId(), user.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "近七日预警", OperateType.FIND.toString(), OperateMode.办公平台.toString());
        this.operationLogService.insertSelective(operationLog);

        mv.addObject("pagination", warnings);
        mv.setViewName("officeplatform/list_warning");
        return mv;
    }

    /**
     * 根据id删除预警
     * 
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delWarning")
    @ResponseBody
    public Map<String, Object> delWarning(@ModelAttribute("ids") String ids, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> mapModel = Maps.newHashMap();
        String[] id = ids.split(",");
        int state = this.warningservice.delWaringByWaringIds(id);
        int totalCount = Integer.parseInt(request.getParameter("totalCount"));
        int pagesize = Integer.parseInt(request.getParameter("pageSize"));
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));

        // 删除成功
        if (state == 0) {
            mapModel.put("state", true);
            int totalPage = 0;
            if ((totalCount - id.length) % pagesize == 0) {
                totalPage = (totalCount - id.length) / pagesize;
            } else {
                totalPage = (totalCount - id.length) / pagesize + 1;
            }
            if (totalPage == 0 || pageNo == 0) {
                mapModel.put("pageNo", 1);
            } else {
                if (pageNo <= totalPage) {
                    mapModel.put("pageNo", pageNo);
                } else {
                    mapModel.put("pageNo", totalPage);
                }
            }
        } else {
            // 删除失败
            mapModel.put("state", false);
        }
        return mapModel;
    }

    /**
     * 点击标题，跳转到详细页
     * 
     * @param warningId
     * @return
     */
    @RequestMapping(value = "/getWarningInfo/{warningId}")
    @ResponseBody
    public ModelAndView getWarningInfo(@PathVariable("warningId") String warningId) {
        ModelAndView mv = new ModelAndView();
        Warning warning = this.warningservice.selectByPrimaryKey(warningId);
        if ("0".equals(warning.getIsRead())) {
            // 置为已读
            warning.setIsRead("1");
            this.warningservice.updateByPrimaryKeySelective(warning);
        }
        // //境外舆情显示html
        // if(warning.getNewslev()==1||warning.getNewslev()==2){
        // warning.setSummary(Snapshot.getHTMLContent(warning.getPageId()));
        // }
        mv.addObject("warning", warning);
        mv.setViewName("warning/info_warning");
        return mv;
    }

    /**
     * 点击标题，跳转到详细页(办公平台)
     * 
     * @param warningId
     * @return
     */
    @RequestMapping(value = "/getWarningInfoToOffice/{warningId}")
    @ResponseBody
    public ModelAndView getWarningInfoToOffice(@PathVariable("warningId") String warningId) {
        ModelAndView mv = new ModelAndView();
        Warning warning = this.warningservice.selectByPrimaryKey(warningId);
        if ("0".equals(warning.getIsRead())) {
            // 置为已读
            warning.setIsRead("1");
            this.warningservice.updateByPrimaryKeySelective(warning);
        }
        mv.addObject("warning", warning);
        mv.setViewName("officeplatform/info_warning");
        return mv;
    }

    /**
     * 加入预警
     * 
     * @param pageId
     * @return
     */
    @RequestMapping(value = "/saveWarningByPageId/{pageId}")
    @ResponseBody
    public int saveWarningByPageId(@PathVariable("pageId") String pageId, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("userfront");
        SubjectPage subjectPage = new SubjectPage();
        subjectPage.setId(pageId);
        subjectPage.setUserid(user.getUserId());
        subjectPage = this.subjectPageService.load(subjectPage);
        if (subjectPage != null) {
            String waringId = UUIDGenerator.random();
            Warning warning = new Warning();
            warning.setWarningId(waringId);
            warning.setPageId(subjectPage.getPageid());
            warning.setTitle(subjectPage.getTitle());
            warning.setWarnTime(new Date());
            warning.setUrl(subjectPage.getUrl());
            warning.setSummary(subjectPage.getSummary());
            warning.setUserId(user.getUserId());
            warning.setType(0);
            warning.setIsRead("0");
            warning.setClickcount(subjectPage.getClickcount());
            warning.setDownloaddate(subjectPage.getDownloaddate());
            warning.setForwardcount(subjectPage.getForwardcount());
            warning.setPolarity(subjectPage.getPolarity());
            warning.setPublishdate(subjectPage.getPublishdate());
            warning.setReplycount(subjectPage.getReplycount());
            warning.setWaringType(subjectPage.getType());
            warning.setWebsite(subjectPage.getWebsite());
            warning.setNewsLevel(subjectPage.getNewslevel());
            int is = this.warningservice.saveSubjectPage(warning);
            // 保存成功，发送到手机客户端
            if (is == 1) {
                DocumentHandler doc = new DocumentHandler();
                PhoneLogin phoneLogin = new PhoneLogin();
                phoneLogin.setUserId(user.getUserId());
                phoneLogin.setType("1");
                phoneLogin.setPushSwitch("1");
                List<PhoneLogin> list = PhoneLoginService.selectByUserId(phoneLogin);
                doc.warningSendPhone(user.getUserId(), waringId, subjectPage.getTitle(), list);
            }
            return is;
        } else {
            return 0;
        }
    }

    /**
     * 手机端 发送预警 userId/title/uuid/type//token_time
     * 
     * @throws IOException
     * @throws ClientProtocolException
     */
    private void toSendYJ(String userId, String title, String uuid, String type, String token_time) throws ClientProtocolException, IOException {
        String url = "http://down.peonydata.com/Push/PushService";
        // url="http://58.30.49.21:8080/Push/PushService";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userId", userId));
        params.add(new BasicNameValuePair("title", title));
        params.add(new BasicNameValuePair("uuid", uuid));
        params.add(new BasicNameValuePair("type", type));
        params.add(new BasicNameValuePair("token_time", token_time));
        topost(url, params);
    }

    public static String topost(String url, List<NameValuePair> ce) throws ClientProtocolException, IOException {
        HttpPost httpPost = new HttpPost(url);
        HttpResponse httpResponse = null;
        // 设置httpPost请求参数
        httpPost.setEntity(new UrlEncodedFormEntity(ce, HTTP.UTF_8));
        httpResponse = new DefaultHttpClient().execute(httpPost);
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            // 第三步，使用getEntity方法活得返回结果
            String str = EntityUtils.toString(httpResponse.getEntity());
            return str;
        }
        return "";
    }

    /**
     * 加入预警（eventnewId）
     * 
     * @param eventnews
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/saveWarningByByEvennews")
    @ResponseBody
    public int saveWarningByByEvennews(@ModelAttribute("Eventnews") Eventnews eventnews, HttpServletRequest request, HttpServletResponse response) {
        eventnews.setProvinceid(Integer.parseInt(eventnews.getRegionID().substring(0, 2)));
        eventnews = this.eventnewsService.load(eventnews);
        User user = (User) request.getSession().getAttribute("userfront");
        if (eventnews != null) {
            String waringId = UUIDGenerator.random();
            Warning warning = new Warning();
            warning.setWarningId(waringId);
            warning.setPageId(eventnews.getPageid());
            warning.setTitle(eventnews.getTitle());
            warning.setWarnTime(new Date());
            warning.setUrl(eventnews.getUrl());
            warning.setSummary(eventnews.getSummary());
            warning.setUserId(user.getUserId());
            warning.setType(0);
            warning.setIsRead("0");
            warning.setPolarity(eventnews.getPolarity());
            warning.setPublishdate(eventnews.getPublishdate());
            warning.setWaringType(eventnews.getType());
            warning.setWebsite(eventnews.getWebsite());
            warning.setNewsLevel(eventnews.getNewslevel());
            int is = this.warningservice.saveSubjectPage(warning);
            // 保存成功，发送到手机客户端
            if (is == 1) {
                DocumentHandler doc = new DocumentHandler();
                PhoneLogin phoneLogin = new PhoneLogin();
                phoneLogin.setUserId(user.getUserId());
                phoneLogin.setType("1");
                phoneLogin.setPushSwitch("1");
                List<PhoneLogin> list = PhoneLoginService.selectByUserId(phoneLogin);
                doc.warningSendPhone(user.getUserId(), waringId, eventnews.getTitle(), list);
            }
            return is;
        } else {
            return 0;
        }

    }

    /**
     * 加入预警（briefreportInfoId）
     * 
     * @param briefreportInfo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/saveWarningByBriefreportInfoId/{briefreportInfoId}")
    @ResponseBody
    public int saveWarningByBriefreportInfoId(@PathVariable("briefreportInfoId") int briefreportInfoId, HttpServletRequest request, HttpServletResponse response) {
        BriefreportInfo briefreportInfo = this.briefreportInfoService.selectByPrimaryKey(briefreportInfoId);
        if (briefreportInfo != null) {
            briefreportInfo = this.briefreportInfoService.selectByPrimaryKey(briefreportInfo.getBriefreportInfoId());
            User user = (User) request.getSession().getAttribute("userfront");
            Warning warning = new Warning();
            String warningId = UUIDGenerator.random();
            warning.setWarningId(warningId);
            warning.setPageId(briefreportInfo.getPageId());
            warning.setTitle(briefreportInfo.getTitle());
            warning.setWarnTime(new Date());
            warning.setUrl(briefreportInfo.getUrl());
            warning.setSummary(briefreportInfo.getSummary());
            warning.setUserId(user.getUserId());
            warning.setType(0);
            warning.setIsRead("0");
            warning.setPolarity(briefreportInfo.getPolarity());
            warning.setPublishdate(briefreportInfo.getPublishdate());
            warning.setWaringType(briefreportInfo.getType());
            warning.setWebsite(briefreportInfo.getWebsite());
            warning.setNewsLevel(briefreportInfo.getNewsLevel());
            int is = this.warningservice.saveSubjectPage(warning);
            // 保存成功，发送到手机客户端
            if (is == 1) {
                DocumentHandler doc = new DocumentHandler();
                PhoneLogin phoneLogin = new PhoneLogin();
                phoneLogin.setUserId(user.getUserId());
                phoneLogin.setType("1");
                phoneLogin.setPushSwitch("1");
                List<PhoneLogin> list = PhoneLoginService.selectByUserId(phoneLogin);
                doc.warningSendPhone(user.getUserId(), warningId, briefreportInfo.getTitle(), list);
            }
            return is;
        } else {
            return 0;
        }
    }

    /**
     * 加入预警（attentionInfoId）
     * 
     * @param attentionInfo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/saveWarningByAttentionId/{attentionInfoId}")
    @ResponseBody
    public int saveWarningByAttentionId(@PathVariable("attentionInfoId") int attentionInfoId, HttpServletRequest request, HttpServletResponse response) {
        AttentionInfo attentionInfo = this.attentionInfoService.selectByPrimaryKey(attentionInfoId);
        if (attentionInfo != null) {
            attentionInfo = this.attentionInfoService.selectByPrimaryKey(attentionInfo.getAttentionInfoId());
            User user = (User) request.getSession().getAttribute("userfront");
            Warning warning = new Warning();
            String warningId = UUIDGenerator.random();
            warning.setWarningId(warningId);
            warning.setPageId(attentionInfo.getPageId());
            warning.setTitle(attentionInfo.getTitle());
            warning.setWarnTime(new Date());
            warning.setUrl(attentionInfo.getUrl());
            warning.setSummary(attentionInfo.getSummary());
            warning.setUserId(user.getUserId());
            warning.setType(0);
            warning.setIsRead("0");
            warning.setPolarity(attentionInfo.getPolarity());
            warning.setPublishdate(attentionInfo.getPublishdate());
            warning.setWaringType(attentionInfo.getType());
            warning.setWebsite(attentionInfo.getWebsite());
            warning.setNewsLevel(attentionInfo.getNewsLevel());
            int is = this.warningservice.saveSubjectPage(warning);
            // 保存成功，发送到手机客户端
            if (is == 1) {
                DocumentHandler doc = new DocumentHandler();
                PhoneLogin phoneLogin = new PhoneLogin();
                phoneLogin.setUserId(user.getUserId());
                phoneLogin.setType("1");
                phoneLogin.setPushSwitch("1");
                List<PhoneLogin> list = PhoneLoginService.selectByUserId(phoneLogin);
                doc.warningSendPhone(user.getUserId(), warningId, attentionInfo.getTitle(), list);
            }
            return is;
        } else {
            return 0;
        }
    }

    /**
     * 导出选择项(word)
     * 
     * @param ids
     * @param request
     * @param response
     */
    @RequestMapping(value = "/downloadByIds/{ids}")
    @ResponseBody
    public void downloadByIds(@PathVariable("ids") String ids, HttpServletRequest request, HttpServletResponse response) {
        // map内放模板需要的数据
        Map<String, Object> dataMap = new HashMap<String, Object>();
        String[] warningids = ids.split(",");
        List<Warning> listWarning = this.warningservice.selectByWarningIds(warningids);
        // 显示正文、去除html标签
        for (int i = 0; i < listWarning.size(); i++) {
            Warning a = listWarning.get(i);
            if (a.getWaringType() != 3 && a.getWaringType() != 6) {
                a.setSummary(Snapshot.getTestContent(a.getPageId()));
            } else {
                a.setSummary(HTMLSpirit.delHTMLTag(a.getSummary()));
            }
            listWarning.set(i, a);
        }
        dataMap.put("list", listWarning);
        User user = (User) request.getSession().getAttribute("userfront");
        dataMap.put("name", user.getName());
        Date dt = new Date();
        dataMap.put("date", dt);
        dataMap.put("title", "舆情预警");
        // 生成文件名
        String fileName = user.getName() + "_" + dt.getTime() + "_舆情预警.doc";
        DocumentHandler doc = new DocumentHandler();
        doc.createDoc(fileName, "Template.ftl", dataMap, request, response);
    }

    /**
     * 导出选择项(excel)
     * 
     * @param ids
     * @param request
     * @param response
     */
    @RequestMapping(value = "/downloadExcelByIds/{ids}")
    @ResponseBody
    public Object downloadExcelByIds(@PathVariable("ids") String ids, HttpServletRequest request, HttpServletResponse response) {
        String[] warningids = ids.split(",");
        List<Warning> listWarning = this.warningservice.selectByWarningIds(warningids);
        // 显示正文、去除html标签
        for (int i = 0; i < listWarning.size(); i++) {
            Warning a = listWarning.get(i);
            a.setSummary(HTMLSpirit.delHTMLTag(a.getSummary()));
            listWarning.set(i, a);
        }
        List<AssignedCell[]> data = getAssignedCellData(listWarning);
        User user = (User) request.getSession().getAttribute("userfront");
        Date dt = new Date();
        // 生成文件名
        String title = "舆情预警";
        String downName = user.getName() + "_" + dt.getTime() + "_" + title + ".xls";
        ExportExcel ee = new ExportExcel();
        return ee.exportExcelByTemplate(title, downName, response, request.getSession(), data, exportSupportService);

    }

    /**
     * 跳转到选择时间()
     * 
     * @return
     */
    @RequestMapping(value = "/toSelectTime/{val}")
    @ResponseBody
    public ModelAndView toSelectTime(@PathVariable("val") String val) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("val", val);
        mv.setViewName("officeplatform/select_warningTime");
        return mv;
    }

    /**
     * 按选择时间下载(word)
     * 
     * @param warning
     * @param request
     * @param response
     */
    @RequestMapping(value = "/downloadBySelectTime")
    @ResponseBody
    public void downloadBySelectTime(Warning warning, HttpServletRequest request, HttpServletResponse response) {
        SimpleDateFormat datatime = new SimpleDateFormat("yyyyMMdd");
        warning.setBtime(warning.getBtime() + " 00:00:00");
        warning.setEtime(warning.getEtime() + " 23:59:59");
        User user = (User) request.getSession().getAttribute("userfront");
        warning.setUserId(user.getUserId());
        Map<String, Object> dataMap = new HashMap<String, Object>();
        List<Warning> listWarning = this.warningservice.selectWaringByTime(warning);
        // 显示正文、去除html标签
        for (int i = 0; i < listWarning.size(); i++) {
            Warning a = listWarning.get(i);
            if (a.getWaringType() != 3 && a.getWaringType() != 6) {
                a.setSummary(Snapshot.getTestContent(a.getPageId()));
            } else {
                a.setSummary(HTMLSpirit.delHTMLTag(a.getSummary()));
            }
            listWarning.set(i, a);
        }
        dataMap.put("list", listWarning);
        dataMap.put("name", user.getName());
        Date dt = new Date();
        dataMap.put("date", dt);
        dataMap.put("title", "舆情预警");
        // 生成文件名
        String fileName = user.getName() + "_" + dt.getTime() + "_舆情预警.doc";
        // --模板(从数据库查找所需要模板)
        // BriefReportTemp
        // briefreportTemp=this.briefreportTempService.findTemByUserId(user.getUserId());
        // String template="";
        // if(null!=briefreportTemp){
        // template=briefreportTemp.getAddress();
        // }else{
        // template="Template.ftl";
        // }
        DocumentHandler doc = new DocumentHandler();
        doc.createDoc(fileName, "Template.ftl", dataMap, request, response);
    }

    /**
     * 按选择时间下载(excel)
     * 
     * @param warning
     * @param request
     * @param response
     */
    @RequestMapping(value = "/downloadExcelBySelectTime")
    @ResponseBody
    public Object downloadExcelBySelectTime(Warning warning, HttpServletRequest request, HttpServletResponse response) {
        warning.setBtime(warning.getBtime() + " 00:00:00");
        warning.setEtime(warning.getEtime() + " 23:59:59");
        User user = (User) request.getSession().getAttribute("userfront");
        warning.setUserId(user.getUserId());
        List<Warning> listWarning = this.warningservice.selectWaringByTime(warning);
        // 显示正文、去除html标签
        for (int i = 0; i < listWarning.size(); i++) {
            Warning a = listWarning.get(i);
            a.setSummary(HTMLSpirit.delHTMLTag(a.getSummary()));
            listWarning.set(i, a);
        }
        Date dt = new Date();
        List<AssignedCell[]> data = getAssignedCellData(listWarning);
        // 生成文件名
        String title = "舆情预警";
        String downName = user.getName() + "_" + dt.getTime() + "_" + title + ".xls";
        ExportExcel ee = new ExportExcel();
        return ee.exportExcelByTemplate(title, downName, response, request.getSession(), data, exportSupportService);
    }

    /**
     * 获取excel里要输入的数据
     * 
     * @param listeventNews
     * @return
     */
    private List<AssignedCell[]> getAssignedCellData(List<Warning> listeventNews) {
        List<AssignedCell[]> data = new ArrayList<AssignedCell[]>();
        // 添加数据
        for (Warning e : listeventNews) {
            String polarity = "";
            if (e.getPolarity() == -1) {
                polarity = "负面";
            } else if (e.getPolarity() == 0) {
                polarity = "争议";
            } else {
                polarity = "正面";
            }
            // System.out.println(e.getPolarity()+":"+polarity);
            AssignedCell[] row1 = { new AssignedCell(0, 0, e.getTitle(), 0), new AssignedCell(0, 1, e.getSummary(), 0), new AssignedCell(0, 2, e.getUrl(), 0), new AssignedCell(0, 3, e.getWebsite(), 0), new AssignedCell(0, 4, DateUtils.dateToStr(e.getPublishdate(), "yyyy-MM-dd HH:mm:ss"), 0), new AssignedCell(0, 5, polarity, 0), };
            data.add(row1);
        }
        return data;
    }

    /**
     * 跳转到添加专题页面
     * 
     * @param warningId
     * @return
     */
    @RequestMapping(value = "/toAddTopic/{warningId}")
    @ResponseBody
    public ModelAndView toAddTopic(@PathVariable("warningId") String warningId) {
        ModelAndView mv = new ModelAndView();
        Warning warning = this.warningservice.selectByPrimaryKey(warningId);
        warning.setSummary(HTMLSpirit.delHTMLTag(warning.getSummary()));
        mv.addObject("topic", warning);
        mv.setViewName("subject/add_topic_info");
        return mv;
    }
}
