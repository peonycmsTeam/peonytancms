package com.peony.peonyfront.industry.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.peony.core.base.controller.BaseController;
import com.peony.core.base.pojo.PageParameter;
import com.peony.core.base.pojo.Pagination;
import com.peony.core.common.utils.UUIDGenerator;
import com.peony.peonyfront.attention.model.AttentionInfo;
import com.peony.peonyfront.attention.service.AttentionInfoService;
import com.peony.peonyfront.briefreport.model.BriefreportInfo;
import com.peony.peonyfront.briefreport.service.BriefreportInfoService;
import com.peony.peonyfront.event.model.Event;
import com.peony.peonyfront.event.model.Subscription;
import com.peony.peonyfront.event.service.EventService;
import com.peony.peonyfront.event.service.SubscriptionService;
import com.peony.peonyfront.key.service.IdService;
import com.peony.peonyfront.login.model.PhoneLogin;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.login.service.PhoneLoginService;
import com.peony.peonyfront.operationlog.model.OperationLog;
import com.peony.peonyfront.operationlog.service.OperationLogService;
import com.peony.peonyfront.subject.model.Subject;
import com.peony.peonyfront.subject.model.SubjectPage;
import com.peony.peonyfront.subject.model.WebDictionary;
import com.peony.peonyfront.subject.service.SubjectPageService;
import com.peony.peonyfront.subject.service.SubjectService;
import com.peony.peonyfront.subject.service.WebDictionaryService;
import com.peony.peonyfront.system.model.ZTreeNode;
import com.peony.peonyfront.util.DocumentHandler;
import com.peony.peonyfront.util.HTMLSpirit;
import com.peony.peonyfront.util.Snapshot;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;
import com.peony.peonyfront.warning.model.Warning;
import com.peony.peonyfront.warning.service.WarningService;

/**
 * 行业舆情
 *
 * @author yanglin
 *
 */
@Controller
@RequestMapping("/industry")
public class IndustryController extends BaseController {

    private final Log              logger       = LogFactory.getLog(IndustryController.class);

    // 0 3 4 5 境内 1，2表示境外
    private final static String    NEW_LEVEL_JN = "0,3,4,5";

    @Autowired
    private SubscriptionService    subscriptionService;                                       // 用户订制关系

    @Autowired
    private EventService           eventService;                                              // 订制舆情分类

    @Autowired
    private SubjectPageService     subjectPageService;

    @Autowired
    private WebDictionaryService   webDictionaryService;

    @Autowired
    private WarningService         warningService;

    @Autowired
    private IdService              idService;

    @Autowired
    private AttentionInfoService   attentionInfoService;

    @Autowired
    private BriefreportInfoService briefreportInfoService;

    @Autowired
    private SubjectService         subjectService;

    @Autowired
    private OperationLogService    operationLogService;

    @Autowired
    private PhoneLoginService      PhoneLoginService;

    /**
     * 系统配置-政务舆情配置
     *
     * @return
     */
    @RequestMapping(value = "/toListIndustry")
    public ModelAndView toListIndustry(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("industry/list_industry");
        // firstIndustryId
        User user = (User) request.getSession().getAttribute("userfront");
        Subscription subscription = this.subscriptionService.findSubscriptionByUserId(user.getUserId().toString());
        if (subscription == null || "".equals(subscription.getEventId())) {
            // 提示页面 需要先配置行业舆情
            mv.setViewName("industry/list_industry_tishi");
            return mv;
        } else {
            mv.addObject("firstIndustryId", subscription.getEventId().split(",")[0]);
        }

        if (null != request.getParameter("timeMethod") && !"".equals(request.getParameter("timeMethod"))) {
            mv.addObject("timeMethod", "1");
        } else {
            mv.addObject("timeMethod", "0");
        }

        OperationLog operationLog = new OperationLog(new Date(), user.getUserId(), user.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "行业舆情", OperateType.FIND.toString(), OperateMode.行业舆情.toString());
        this.operationLogService.insertSelective(operationLog);

        // 页面信息来源
        List<WebDictionary> webDictionaryList = this.webDictionaryService.findAllWebDictionary();
        mv.addObject("webDictionaryList", webDictionaryList);

        return mv;
    }

    @RequestMapping(value = "/findChildren", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody List<ZTreeNode> findChildren(Model model, HttpServletRequest request) {
        List<ZTreeNode> nodeList = new ArrayList<ZTreeNode>();
        if (null == request.getParameter("id")) {
            ZTreeNode node = new ZTreeNode();
            node.setId("1");
            node.setName("行业舆情");
            node.setOpen(false);
            node.setIsParent("true");
            nodeList.add(node);
        } else {
            User user = (User) request.getSession().getAttribute("userfront");
            Subscription subscription = this.subscriptionService.findSubscriptionByUserId(user.getUserId().toString());
            Event event = new Event();
            event.setEventArray(subscription.getEventId());
            List<Event> list = this.eventService.findEventByEventIdArray(event);
            for (Event tempEvent : list) {
                ZTreeNode node = new ZTreeNode();
                node.setId(tempEvent.getEventid().toString());
                node.setName(tempEvent.getEventname());
                node.setOpen(false);
                node.setIsParent("false");
                nodeList.add(node);
            }
        }
        return nodeList;

    }

    @RequestMapping(value = "/toListIndustryPage")
    public ModelAndView toListIndustryPage(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("industry/list_industry_right");
        String IndustryId = "";
        if ("1".equals(request.getParameter("IndustryId"))) {
            User user = (User) request.getSession().getAttribute("userfront");
            Subscription subscription = this.subscriptionService.findSubscriptionByUserId(user.getUserId().toString());
            Event event = new Event();
            event.setEventArray(subscription.getEventId());
            List<Event> list = this.eventService.findEventByEventIdArray(event);
            IndustryId = list.get(0).getEventid().toString();
        } else {
            IndustryId = request.getParameter("IndustryId");
        }

        SubjectPage subjectPage = new SubjectPage();

        PageParameter pageParameter = new PageParameter();
        if (null == request.getParameter("pageNo")) {
            pageParameter.setCurrentPage(1);
        } else {
            pageParameter.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
        }
        if (null != request.getParameter("pagesize")) {
            pageParameter.setPageSize(Integer.parseInt(request.getParameter("pagesize")));
        } else {
            pageParameter.setPageSize(10);
        }
        subjectPage.setPageParameter(pageParameter);

        // 标题
        if (null != request.getParameter("title") && !"".equals(request.getParameter("title"))) {
            subjectPage.setTitle(request.getParameter("title"));
        }
        // 网站来源
        if (null != request.getParameter("type")) {
            subjectPage.setType(Integer.parseInt(request.getParameter("type")));
        }
        // 正负面
        if (null != request.getParameter("polarity")) {
            subjectPage.setPolarity(Integer.parseInt(request.getParameter("polarity")));
        }
        // 已读未读
        if (null != request.getParameter("isRead")) {
            subjectPage.setIsRead(Integer.parseInt(request.getParameter("isRead")));
        }
        // 处理时间
        if (null == request.getParameter("time")) {
            this.dealTime("2", subjectPage);
            subjectPage.setTime("2");
        } else {
            this.dealTime(request.getParameter("time"), subjectPage);
            subjectPage.setTime(request.getParameter("time"));
        }

        Event event = new Event();
        event.setEventArray(IndustryId);
        List<Event> listEvents = this.eventService.findEventByEventIdArray(event);

        subjectPage.setUserid(listEvents.get(0).getStatus());
        subjectPage.setNewslevelConditions(NEW_LEVEL_JN);
        subjectPage.setSubjectidArray(IndustryId);
        subjectPage.setSubjectid(Integer.parseInt(IndustryId));
        Pagination<SubjectPage> pagination = this.subjectPageService.findByPage(subjectPage);
        this.logger.info("行业舆情获取到的信息条数  ============= " + pagination.getList().size());
        subjectPage.setNewslev(0);
        mv.addObject("CurrentSubjectPage", subjectPage);
        mv.addObject("pagination", pagination);
        mv.addObject("userId", listEvents.get(0).getStatus());
        mv.addObject("eventId", IndustryId);
        return mv;
    }

    /**
     * 根据页面选择的时间段，来生成查询条件的开始时间、结束时间
     *
     * @param time
     * @param subjectPage
     */
    private void dealTime(String time, SubjectPage subjectPage) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        // 当前日期
        subjectPage.setEndTime(df.format(new Date()) + " 23:59:59");

        Calendar calendar = Calendar.getInstance();

        // 近1天
        if ("1".equals(time)) {
            subjectPage.setBeginTime(df.format(new Date()) + " 00:00:00");
        } else if ("2".equals(time)) {
            // 近3天
            calendar.add(Calendar.DATE, -2);
            Date date = calendar.getTime();
            subjectPage.setBeginTime(df.format(date) + " 00:00:00");
        } else if ("3".equals(time)) {
            // 近7天
            calendar.add(Calendar.DATE, -6);
            Date date = calendar.getTime();
            subjectPage.setBeginTime(df.format(date) + " 00:00:00");
        } else if ("4".equals(time)) {
            // 近1个月
            calendar.add(Calendar.MONTH, -1);
            Date date = calendar.getTime();
            subjectPage.setBeginTime(df.format(date) + " 00:00:00");
        } else if ("5".equals(time)) {
            subjectPage.setBeginTime(null);
            subjectPage.setEndTime(null);
        }
    }

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteSubjectPage", method = RequestMethod.POST)
    public @ResponseBody int deleteSubjectPage(@RequestParam(value = "ids[]", required = true) String[] ids, HttpServletRequest request) {
        SubjectPage subjectPage = new SubjectPage();
        // 从缓存中加载用户id
        int userId = Integer.parseInt(request.getParameter("userId"));
        subjectPage.setUserid(userId);
        String result = "";
        for (int i = 0; i < ids.length; i++) {
            if (i == 0) {
                result = "'" + ids[i] + "'";
            } else {
                result = result + "," + "'" + ids[i] + "'";
            }
        }
        subjectPage.setIdArray(result);
        int totalCount = Integer.parseInt(request.getParameter("totalCount"));
        int pagesize = Integer.parseInt(request.getParameter("pagesize"));
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));
        if (this.subjectPageService.batchDelete(subjectPage) > 0) {
            int totalPage = 0;
            if ((totalCount - ids.length) % pagesize == 0) {
                totalPage = (totalCount - ids.length) / pagesize;
            } else {
                totalPage = (totalCount - ids.length) / pagesize + 1;
            }
            if (totalPage == 0) {
                return 1;
            } else {
                if (pageNo <= totalPage) {
                    return pageNo;
                } else {
                    return totalPage;
                }
            }
        } else {
            return 0;
        }
    }

    /**
     * 修改倾向性
     *
     * @param subjectPage
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateSubjectPagePolarity", method = RequestMethod.POST)
    public @ResponseBody int updateSubjectPagePolarity(@ModelAttribute("subjectPage") SubjectPage subjectPage, HttpServletRequest request) {
        // 从缓存中加载用户id
        subjectPage.setUserid(Integer.parseInt(request.getParameter("userId")));
        return this.subjectPageService.update(subjectPage);
    }

    /**
     * 加入预警
     *
     * @param pageId
     * @return
     */
    @RequestMapping(value = "/saveWarningByPageId/{pageId}/{userId}")
    @ResponseBody
    public int saveWarningByPageId(@PathVariable("pageId") String pageId, @PathVariable("userId") String userId, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("userfront");
        SubjectPage subjectPage = new SubjectPage();
        subjectPage.setId(pageId);
        subjectPage.setUserid(Integer.parseInt(userId));
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
            int is = this.warningService.saveSubjectPage(warning);
            // 保存成功，发送到手机客户端
            if (is == 1) {
                DocumentHandler doc = new DocumentHandler();
                PhoneLogin phoneLogin = new PhoneLogin();
                phoneLogin.setUserId(user.getUserId());
                phoneLogin.setType("1");
                phoneLogin.setPushSwitch("1");
                List<PhoneLogin> list = this.PhoneLoginService.selectByUserId(phoneLogin);
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
     * 订制信息详细页
     *
     * @return
     */
    @RequestMapping(value = "/toAddTopic")
    public ModelAndView toAddTopic(HttpServletRequest request, HttpServletResponse response) {
        SubjectPage subjectPage = new SubjectPage();
        if (null != request.getParameter("currentId") && !"".equals(request.getParameter("currentId"))) {
            subjectPage.setId(request.getParameter("currentId"));
            subjectPage.setUserid(Integer.parseInt(request.getParameter("userId")));
            subjectPage = this.subjectPageService.load(subjectPage);
        }

        ModelAndView mv = new ModelAndView();
        subjectPage.setSummary(HTMLSpirit.delHTMLTag(subjectPage.getSummary()));
        mv.setViewName("subject/add_topic_info");
        mv.addObject("topic", subjectPage);
        return mv;
    }

    /**
     * 添加到收藏夹(pageId)
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/addToAttention")
    @ResponseBody
    public int addToAttention(AttentionInfo attentionInfo, SubjectPage subjectPage, HttpServletRequest request, HttpServletResponse response) {
        subjectPage.setUserid(Integer.parseInt(request.getParameter("userId")));
        subjectPage = this.subjectPageService.load(subjectPage);
        if (subjectPage != null) {
            attentionInfo.setAttentionInfoId(this.idService.NextKey("attentionInfoId"));
            attentionInfo.setTitle(subjectPage.getTitle());
            attentionInfo.setType(subjectPage.getType());
            attentionInfo.setWebsite(subjectPage.getWebsite());
            attentionInfo.setUrl(subjectPage.getUrl());
            attentionInfo.setPtime(new Date());
            attentionInfo.setPublishdate(subjectPage.getPublishdate());
            attentionInfo.setVisitcount(subjectPage.getClickcount());
            attentionInfo.setReply(subjectPage.getReplycount());
            attentionInfo.setSummary(subjectPage.getSummary());
            attentionInfo.setForwardcount(subjectPage.getForwardcount());
            attentionInfo.setPolarity(subjectPage.getPolarity());
            attentionInfo.setIsRead(0);
            attentionInfo.setPageId(subjectPage.getPageid());
            attentionInfo.setNewsLevel(subjectPage.getNewslevel());
            return this.attentionInfoService.insertSelective(attentionInfo);
        } else {
            return 0;
        }
    }

    /**
     * 添加到简报(pageId)
     *
     * @param briefreportInfo
     * @return
     */
    @RequestMapping(value = "/addToBriefreport")
    @ResponseBody
    public int addToBriefreport(@ModelAttribute("attention") BriefreportInfo briefreportInfo, SubjectPage subjectPage, HttpServletRequest request, HttpServletResponse response) {
        subjectPage.setUserid(Integer.parseInt(request.getParameter("userId")));
        subjectPage = this.subjectPageService.load(subjectPage);
        if (subjectPage != null) {
            briefreportInfo.setBriefreportInfoId(this.idService.NextKey("briefreportInfoId"));
            briefreportInfo.setTitle(subjectPage.getTitle());
            briefreportInfo.setType(subjectPage.getType());
            briefreportInfo.setWebsite(subjectPage.getWebsite());
            briefreportInfo.setUrl(subjectPage.getUrl());
            briefreportInfo.setPtime(new Date());
            briefreportInfo.setPublishdate(subjectPage.getPublishdate());
            briefreportInfo.setVisitcount(subjectPage.getClickcount());
            briefreportInfo.setReply(subjectPage.getReplycount());
            briefreportInfo.setSummary(subjectPage.getSummary());
            briefreportInfo.setPolarity(subjectPage.getPolarity());
            briefreportInfo.setForwardcount(subjectPage.getForwardcount());
            briefreportInfo.setPageId(subjectPage.getPageid());
            briefreportInfo.setNewsLevel(subjectPage.getNewslevel());
            return this.briefreportInfoService.insertSelective(briefreportInfo);
        } else {
            return 0;
        }
    }

    @RequestMapping(value = "/toshowfenxi")
    public ModelAndView toshowfenxi(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("industry/show_fenxi");
        mv.addObject("subjectid", request.getParameter("subjectid"));
        mv.addObject("userId", request.getParameter("userId"));
        return mv;
    }

    @RequestMapping(value = "/showfenxi")
    public ModelAndView showfenxi(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("subject/doshow_fenxi");
        SubjectPage subjectPage = new SubjectPage();
        subjectPage.setUserid(Integer.parseInt(request.getParameter("userId")));
        subjectPage.setNewslevelConditions(NEW_LEVEL_JN);
        if ("1".equals(request.getParameter("subjectid"))) {
            Subject subject = new Subject();
            subject.setUserid(Integer.parseInt(request.getParameter("userId")));
            subject.setPid(1);
            List<Subject> list = this.subjectService.selectSubjectByUserIdAndPid(subject);
            if (null != list && list.size() > 0) {
                String subjectidArray = "";
                for (int i = 0; i < list.size(); i++) {
                    if ("".equals(subjectidArray)) {
                        subjectidArray = list.get(i).getId().toString();
                    } else {
                        subjectidArray = subjectidArray + "," + list.get(i).getId().toString();
                    }
                }
                subjectPage.setSubjectidArray(subjectidArray);
            } else {
                subjectPage.setSubjectidArray("1");
            }
        } else {
            subjectPage.setSubjectidArray(request.getParameter("subjectid"));
        }
        List<SubjectPage> list = this.subjectPageService.countSubjectTypeByUserId(subjectPage);

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("value", list.get(i).getCount());
            jsonObject.put("name", this.dealTypeName(list.get(i).getType()));
            jsonArray.add(jsonObject);
        }
        mv.addObject("result1", jsonArray.toString());

        list = this.subjectPageService.countSubjectWebSiteByUserId(subjectPage);
        JSONArray jsonArray2 = new JSONArray();
        JSONArray jsonArray3 = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            jsonArray2.add(list.get(i).getWebsite());
            jsonArray3.add(list.get(i).getCount());
        }
        mv.addObject("result2", jsonArray2.toString());
        mv.addObject("result3", jsonArray3.toString());

        list = this.subjectPageService.countSubjectPolarityByUserId(subjectPage);
        JSONArray jsonArray4 = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("value", list.get(i).getCount());
            jsonObject.put("name", this.dealPolarity(list.get(i).getPolarity()));
            jsonArray4.add(jsonObject);
        }
        mv.addObject("result4", jsonArray4.toString());

        // 页面信息来源
        JSONArray jsonArray5 = new JSONArray();
        JSONArray jsonArray6 = new JSONArray();
        JSONArray jsonArray7 = new JSONArray();
        List<WebDictionary> webDictionaryList = this.webDictionaryService.findAllWebDictionary();
        for (int i = 0; i < webDictionaryList.size(); i++) {
            subjectPage.setType(Integer.parseInt(webDictionaryList.get(i).getValue()));
            list = this.subjectPageService.countSubjectPolarityAndTypeByUserId(subjectPage);
            for (int j = 0; j < list.size(); j++) {
                // count,polarity,type
                if (list.get(j).getPolarity() == 1) {
                    jsonArray5.add(list.get(j).getCount());
                } else if (list.get(j).getPolarity() == -1) {
                    jsonArray6.add(list.get(j).getCount());
                } else {
                    jsonArray7.add(list.get(j).getCount());
                }
            }
        }
        mv.addObject("result5", jsonArray5.toString());
        mv.addObject("result6", jsonArray6.toString());
        mv.addObject("result7", jsonArray7.toString());

        return mv;
    }

    private String dealPolarity(int polarity) {
        if (polarity == 1) {
            return "正面";
        } else if (polarity == -1) {
            return "负面";
        } else if (polarity == 0) {
            return "争议";
        } else {
            return "其他";
        }
    }

    private String dealTypeName(int type) {
        if (type == 1) {
            return "新闻";
        } else if (type == 2) {
            return "论坛";
        } else if (type == 3) {
            return "微博";
        } else if (type == 4) {
            return "博客";
        } else if (type == 5) {
            return "报刊";
        } else if (type == 6) {
            return "twiter";
        } else {
            return "其他";
        }
    }

    /**
     * 订制信息详细页
     *
     * @return
     */
    @RequestMapping(value = "/loadSubjectPage")
    public ModelAndView loadSubjectPage(HttpServletRequest request, HttpServletResponse response) {
        SubjectPage subjectPage = new SubjectPage();
        List<SubjectPage> list = new ArrayList<SubjectPage>();
        if (null != request.getParameter("currentId") && !"".equals(request.getParameter("currentId"))) {
            subjectPage.setId(request.getParameter("currentId"));
            subjectPage.setUserid(Integer.parseInt(request.getParameter("userId")));
            subjectPage = this.subjectPageService.load(subjectPage);
            subjectPage.setGroupseedid(request.getParameter("groupseedid"));
            list = this.subjectPageService.selectSubjectPageByGroupseedid(subjectPage);
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("industry/show_page_info");
        mv.addObject("subjectPage", subjectPage);
        mv.addObject("userId", request.getParameter("userId"));
        mv.addObject("list", list);
        return mv;
    }

    /**
     * 弹出选择时间页面
     *
     * @return
     */
    @RequestMapping(value = "/toExportByTime")
    @ResponseBody
    public ModelAndView toExportByTime() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("industry/select_industryTime");
        return mv;
    }

    /**
     * 按选择时间下载
     *
     * @param warning
     * @param request
     * @param response
     */
    @RequestMapping(value = "/downloadBySelectTime")
    @ResponseBody
    public void downloadBySelectTime(SubjectPage subjectPage, HttpServletRequest request, HttpServletResponse response) {
        SimpleDateFormat datatime = new SimpleDateFormat("yyyyMMdd");
        // map内放模板需要的数据
        Map<String, Object> dataMap = new HashMap<String, Object>();
        User user = (User) request.getSession().getAttribute("userfront");
        dataMap.put("name", user.getName());
        subjectPage.setBeginTime(subjectPage.getStartTime() + " 00:00:00");
        subjectPage.setEndTime(subjectPage.getOverTime() + " 23:59:59");
        subjectPage.setNewslevel(subjectPage.getNewslev());
        List<SubjectPage> listSubjectPage = this.getPageList(subjectPage, request, response);
        // 显示正文、去除html标签
        for (int i = 0; i < listSubjectPage.size(); i++) {
            SubjectPage a = listSubjectPage.get(i);
            if (a.getType() != 3 && a.getType() != 6) {
                a.setSummary(Snapshot.getTestContent(a.getPageid()));
            } else {
                a.setSummary(HTMLSpirit.delHTMLTag(a.getSummary()));
            }
            listSubjectPage.set(i, a);
        }
        dataMap.put("list", listSubjectPage);
        Date dt = new Date();
        dataMap.put("date", dt);
        dataMap.put("title", "定制舆情");
        // 生成文件名
        String fileName = user.getName() + "_" + dt.getTime() + "_定制舆情.doc";
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

    private List<SubjectPage> getPageList(SubjectPage subjectPage, HttpServletRequest request, HttpServletResponse response) {

        String IndustryId = "";
        if ("1".equals(request.getParameter("IndustryId"))) {
            User user = (User) request.getSession().getAttribute("userfront");
            Subscription subscription = this.subscriptionService.findSubscriptionByUserId(user.getUserId().toString());
            Event event = new Event();
            event.setEventArray(subscription.getEventId());
            List<Event> list = this.eventService.findEventByEventIdArray(event);
            IndustryId = list.get(0).getEventid().toString();
        } else {
            IndustryId = request.getParameter("IndustryId");
        }
        Event event = new Event();
        event.setEventArray(IndustryId);
        List<Event> listEvents = this.eventService.findEventByEventIdArray(event);
        subjectPage.setUserid(listEvents.get(0).getStatus());
        subjectPage.setNewslevelConditions(NEW_LEVEL_JN);
        subjectPage.setSubjectidArray(IndustryId);
        subjectPage.setSubjectid(Integer.parseInt(IndustryId));
        List<SubjectPage> listSubjectPage = this.subjectPageService.selectSubjectPageByTime(subjectPage);
        return listSubjectPage;
    }

}
