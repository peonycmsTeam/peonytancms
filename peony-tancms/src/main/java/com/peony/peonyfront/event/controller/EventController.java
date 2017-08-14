package com.peony.peonyfront.event.controller;

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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.peony.core.base.controller.BaseController;
import com.peony.core.base.pojo.PageParameter;
import com.peony.core.base.pojo.Pagination;
import com.peony.core.common.utils.DateUtils;
import com.peony.peonyfront.event.model.Event;
import com.peony.peonyfront.event.model.Eventnews;
import com.peony.peonyfront.event.model.Subscription;
import com.peony.peonyfront.event.service.EventService;
import com.peony.peonyfront.event.service.EventnewsService;
import com.peony.peonyfront.event.service.ExportSupportService;
import com.peony.peonyfront.event.service.SubscriptionService;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.mail.model.MailTemp;
import com.peony.peonyfront.mail.service.MailTempService;
import com.peony.peonyfront.officeplatform.model.Mail;
import com.peony.peonyfront.operationlog.model.OperationLog;
import com.peony.peonyfront.operationlog.service.OperationLogService;
import com.peony.peonyfront.region.model.Region;
import com.peony.peonyfront.region.service.RegionService;
import com.peony.peonyfront.subject.model.WebDictionary;
import com.peony.peonyfront.subject.service.WebDictionaryService;
import com.peony.peonyfront.system.model.ZTreeNode;
import com.peony.peonyfront.util.DocumentHandler;
import com.peony.peonyfront.util.HTMLSpirit;
import com.peony.peonyfront.util.Snapshot;
import com.peony.peonyfront.util.export.AssignedCell;
import com.peony.peonyfront.util.export.ExportExcel;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;

/**
 * 政务舆情
 *
 * @author vv
 *
 */
@Controller
@RequestMapping("/event")
public class EventController extends BaseController {

    private final static String    NEW_LEVEL_JN = "0,3,4,5";

    private final static String    NEW_LEVEL_JW = "1,2";

    @Autowired
    private RegionService          regionService;

    @Autowired
    private SubscriptionService    subscriptionService;

    @Autowired
    private EventService           eventService;

    @Autowired
    private EventnewsService       eventnewsService;

    @Autowired
    private WebDictionaryService   webDictionaryService;
    @Autowired
    private MailTempService        mailTempService;

    @Autowired
    private OperationLogService    operationLogService;
    @Autowired
    protected ExportSupportService exportSupportService;

    private static final Log       log          = LogFactory.getLog(EventController.class);

    @RequestMapping(value = "/toListEvent")
    public ModelAndView toListEvent(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("event/list_event");
        User user = (User) request.getSession().getAttribute("userfront");
        List<Region> list = this.regionService.selectByUserId(user.getUserId());
        if (CollectionUtils.isEmpty(list)) {
            mv.setViewName("event/list_event_region_missing");
            log.warn("User's regional key words are empty");
            return mv;
        }
        List<WebDictionary> webDictionaryList = this.webDictionaryService.findAllWebDictionary();

        OperationLog operationLog = new OperationLog(new Date(), user.getUserId(), user.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "政务舆情", OperateType.FIND.toString(), OperateMode.政务舆情.toString());
        this.operationLogService.insertSelective(operationLog);

        mv.addObject("firstRegionID", list.get(0).getRegionid());
        mv.addObject("webDictionaryList", webDictionaryList);
        // 事件分类列表
        Subscription subscription = this.subscriptionService.findSubscriptionByUserId(String.valueOf(user.getUserId()));

        List<Event> eventList = new ArrayList<Event>();
        if (null == subscription) {
            subscription = new Subscription();
        } else {
            Event event = new Event();
            if (!"".equals(subscription.getEventId())) {
                event.setEventArray(subscription.getEventId());
                eventList = this.eventService.findEventByEventIdArray(event);
            } else {
                mv.setViewName("event/list_event_tishi");
                return mv;
            }
        }

        if (null != request.getParameter("timeMethod") && !"".equals(request.getParameter("timeMethod"))) {
            mv.addObject("timeMethod", "1");
        } else {
            mv.addObject("timeMethod", "0");
        }

        mv.addObject("eventList", eventList);
        return mv;
    }

    /**
     * 目录树
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/findChildren", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody List<ZTreeNode> findChildren(Model model, HttpServletRequest request) {
        // id = null 查父子节点
        User user = (User) request.getSession().getAttribute("userfront");
        List<Region> list = new ArrayList<Region>();
        if (null == request.getParameter("id")) {
            list = this.regionService.selectByUserId(user.getUserId());
        } else {
            list = this.regionService.selectByParentId(Integer.parseInt(request.getParameter("id")));
        }

        List<ZTreeNode> nodeList = new ArrayList<ZTreeNode>();
        for (Region Region : list) {
            ZTreeNode node = new ZTreeNode();
            node.setId(Region.getRegionid().toString());
            node.setName(Region.getRegionname());
            node.setOpen(false);
            if (null == request.getParameter("id")) {
                node.setIsParent("true");
            } else {
                node.setIsParent("false");
            }
            nodeList.add(node);
        }
        return nodeList;
    }

    @RequestMapping(value = "/toListEventPage")
    public ModelAndView toListEventPage(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("event/list_event_right");
        String allRegionID = "";
        String userRegionID = "";
        // session中获取当前用户id 暂定为1 左侧目录树
        User user = (User) request.getSession().getAttribute("userfront");

        // 信息列表
        Pagination<Eventnews> pagination = new Pagination<Eventnews>();
        Eventnews eventnews = new Eventnews();

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
        eventnews.setPageParameter(pageParameter);

        // 设置类型 if!=null && !=-1时，是通过页面点击进行查询的
        if (null != request.getParameter("eventid") && !"-1".equals(request.getParameter("eventid"))) {
            eventnews.setEventid(Integer.parseInt(request.getParameter("eventid")));
            eventnews.setEventArray(request.getParameter("eventid"));
        } else {
            eventnews.setEventid(-1);
            Subscription subscription = this.subscriptionService.findSubscriptionByUserId(user.getUserId().toString());
            eventnews.setEventArray(subscription.getEventId());
            // eventnews.setEventArray(subscription.getEventId());
        }
        // 地域id
        String clickRegionId = "";
        if (null != request.getParameter("regionID")) {
            eventnews.setRegionID(request.getParameter("regionID"));
            clickRegionId = request.getParameter("regionID");
        } else {
            List<Region> list = this.regionService.selectByUserId(user.getUserId());
            // 当前用户注册的userRegionID
            if (null != list && list.size() > 0) {
                allRegionID = list.get(0).getRegionid().toString();
                userRegionID = list.get(0).getRegionid().toString();
                // for (int j = 0; j < list.size(); j++) {
                // List<Region> secondList =
                // regionService.selectByParentId(list.get(j).getRegionid());
                // if (null != secondList && secondList.size() > 0) {
                // list.get(j).setList(secondList);
                // }
                // for (int i = 0; i < secondList.size(); i++) {
                // allRegionID =
                // allRegionID+","+secondList.get(i).getRegionid().toString();
                // }
                // }
            } else {
                list = new ArrayList<Region>();
            }
            eventnews.setRegionID(allRegionID);
            clickRegionId = allRegionID;
        }
        // 设置provinceid
        if (eventnews.getRegionID().length() > 2) {
            eventnews.setProvinceid(Integer.parseInt(eventnews.getRegionID().substring(0, 2)));
        } else {
            eventnews.setProvinceid(Integer.parseInt(eventnews.getRegionID()));
        }
        // 倾向性
        if (null != request.getParameter("polarityorientation") && !"-2".equals(request.getParameter("polarityorientation"))) {
            eventnews.setPolarity(Integer.valueOf(request.getParameter("polarityorientation")));
        }
        // 网站来源
        if (null != request.getParameter("type")) {
            eventnews.setType(Integer.parseInt(request.getParameter("type")));
        }
        // 查询的时间范围
        if (null != request.getParameter("timeMethod")) {
            this.dealTime(request.getParameter("timeMethod"), eventnews);
            eventnews.setTimeMethod(request.getParameter("timeMethod"));
        } else {
            this.dealTime("2", eventnews);
            eventnews.setTimeMethod("2");
        }
        pagination = this.eventnewsService.findByPage(eventnews);

        mv.addObject("pagination", pagination);
        mv.addObject("clickRegionId", clickRegionId);
        mv.addObject("currentEventnews", eventnews);
        mv.addObject("userRegionID", userRegionID);
        return mv;
    }

    /**
     * 根据页面选择的时间段，来生成查询条件的开始时间、结束时间
     *
     * @param time
     * @param subjectPage
     */
    private void dealTime(String time, Eventnews eventnews) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        // 当前日期
        eventnews.setEndTime(df.format(new Date()) + " 23:59:59");

        Calendar calendar = Calendar.getInstance();

        // 近1天
        if ("1".equals(time)) {
            eventnews.setBeginTime(df.format(new Date()) + " 00:00:00");
        } else if ("2".equals(time)) {
            // 近3天
            calendar.add(Calendar.DATE, -2);
            Date date = calendar.getTime();
            eventnews.setBeginTime(df.format(date) + " 00:00:00");
        } else if ("3".equals(time)) {
            // 近7天
            calendar.add(Calendar.DATE, -6);
            Date date = calendar.getTime();
            eventnews.setBeginTime(df.format(date) + " 00:00:00");
        } else if ("4".equals(time)) {
            // 近1个月
            calendar.add(Calendar.MONTH, -1);
            Date date = calendar.getTime();
            eventnews.setBeginTime(df.format(date) + " 00:00:00");
        } else if ("5".equals(time)) {
            eventnews.setBeginTime(null);
            eventnews.setEndTime(null);
        }
    }

    /**
     * 政务信息详细页
     *
     * @return
     */
    @RequestMapping(value = "/loadEventnews")
    public ModelAndView loadEventnews(HttpServletRequest request, HttpServletResponse response) {
        Eventnews eventnews = new Eventnews();
        ModelAndView mv = new ModelAndView();
        List<Eventnews> list = new ArrayList<Eventnews>();
        if (null != request.getParameter("currentEventnewsId") && !"".equals(request.getParameter("currentEventnewsId"))) {
            eventnews.setId(request.getParameter("currentEventnewsId"));
            eventnews.setProvinceid(Integer.parseInt(request.getParameter("regionID").substring(0, 2)));
            eventnews = this.eventnewsService.load(eventnews);
            if (NEW_LEVEL_JW.contains(eventnews.getNewslevel().toString())) {
                if (eventnews.getType() == 6) {
                    mv.addObject("html", eventnews.getSummary());
                } else {
                    mv.addObject("html", Snapshot.getHTMLContent(eventnews.getPageid()));
                }
                mv.setViewName("event/show_page_ywinfo");
            } else {
                mv.setViewName("event/show_page_info");
            }
            //list = this.eventnewsService.selectEventnewsByGroupseedid(eventnews);
        }
        mv.addObject("eventnews", eventnews);
        //mv.addObject("list", list);
        return mv;
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
        SimpleDateFormat datatime = new SimpleDateFormat("yyyyMMdd");
        // map内放模板需要的数据
        Map<String, Object> dataMap = new HashMap<String, Object>();
        String[] eventNewsids = ids.split(",");
        List<Eventnews> listeventNews = this.eventnewsService.selectByEventNewsIds(eventNewsids);
        // 显示正文、去除html标签
        for (int i = 0; i < listeventNews.size(); i++) {
            Eventnews a = listeventNews.get(i);
            if (a.getType() != 3 && a.getType() != 6) {
                a.setSummary(Snapshot.getTestContent(a.getPageid()));
            } else {
                a.setSummary(HTMLSpirit.delHTMLTag(a.getSummary()));
            }
            listeventNews.set(i, a);
        }
        dataMap.put("list", listeventNews);
        dataMap.put("title", "政务舆情");
        User user = (User) request.getSession().getAttribute("userfront");
        dataMap.put("name", user.getName());
        Date dt = new Date();
        dataMap.put("date", dt);
        // 生成文件名
        String fileName = user.getName() + "_" + dt.getTime() + "_政务舆情.doc";
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
     * 导出选择项(excel)
     *
     * @param ids
     * @param request
     * @param response
     */
    @RequestMapping(value = "/downloadXmlByIds/{ids}")
    @ResponseBody
    public Object downloadXmlByIds(@PathVariable("ids") String ids, HttpServletRequest req, HttpServletResponse res) {
        // map内放模板需要的数据
        String[] eventNewsids = ids.split(",");
        List<Eventnews> listeventNews = this.eventnewsService.selectByEventNewsIds(eventNewsids);
        // 显示正文、去除html标签
        for (int i = 0; i < listeventNews.size(); i++) {
            Eventnews a = listeventNews.get(i);
            a.setSummary(HTMLSpirit.delHTMLTag(a.getSummary()));
            listeventNews.set(i, a);
        }
        User user = (User) req.getSession().getAttribute("userfront");
        List<AssignedCell[]> data = this.getAssignedCellData(listeventNews);
        // String downName=className+"-"+"123"+"-成绩录入模板"+".xls";
        // 生成文件名
        String title = "政务舆情";
        Date dt = new Date();
        String downName = user.getName() + "_" + dt.getTime() + "_" + title + ".xls";
        ExportExcel ee = new ExportExcel();
        return ee.exportExcelByTemplate(title, downName, res, req.getSession(), data, this.exportSupportService);
    }

    /**
     * 弹出选择时间页面
     *
     * @return
     */
    @RequestMapping(value = "/toExportByTime/{val}")
    @ResponseBody
    public ModelAndView toExportByTime(@PathVariable("val") String val) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("val", val);
        mv.setViewName("event/select_eventTime");
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
    public void downloadBySelectTime(Eventnews eventnews, HttpServletRequest request, HttpServletResponse response) {
        SimpleDateFormat datatime = new SimpleDateFormat("yyyyMMdd");
        // map内放模板需要的数据
        Map<String, Object> dataMap = new HashMap<String, Object>();
        User user = (User) request.getSession().getAttribute("userfront");
        dataMap.put("name", user.getName());
        eventnews.setBeginTime(eventnews.getBeginTime() + " 00:00:00");
        eventnews.setEndTime(eventnews.getEndTime() + " 23:59:59");
        List<Eventnews> listeventNews = this.getEventList(eventnews, request, response);
        // 显示正文、去除html标签
        for (int i = 0; i < listeventNews.size(); i++) {
            Eventnews a = listeventNews.get(i);
            if (a.getType() != 3 && a.getType() != 6) {
                a.setSummary(Snapshot.getTestContent(a.getPageid()));
            } else {
                a.setSummary(HTMLSpirit.delHTMLTag(a.getSummary()));
            }
            listeventNews.set(i, a);
        }
        dataMap.put("list", listeventNews);
        Date dt = new Date();
        dataMap.put("date", dt);
        dataMap.put("title", "政务舆情");
        // 生成文件名
        String fileName = user.getName() + "_" + dt.getTime() + "_政务舆情.doc";
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
     * 按选择时间下载(Excle)
     *
     * @param warning
     * @param request
     * @param response
     */
    @RequestMapping(value = "/downloadBySelectTimeExcel")
    @ResponseBody
    public Object downloadBySelectTimeExcle(Eventnews eventnews, HttpServletRequest request, HttpServletResponse response) {
        // map内放模板需要的数据
        User user = (User) request.getSession().getAttribute("userfront");
        eventnews.setBeginTime(eventnews.getBeginTime() + " 00:00:00");
        eventnews.setEndTime(eventnews.getEndTime() + " 23:59:59");
        List<Eventnews> listeventNews = this.getEventList(eventnews, request, response);
        // 显示正文、去除html标签
        for (int i = 0; i < listeventNews.size(); i++) {
            Eventnews a = listeventNews.get(i);
            a.setSummary(HTMLSpirit.delHTMLTag(a.getSummary()));
            listeventNews.set(i, a);
        }
        List<AssignedCell[]> data = this.getAssignedCellData(listeventNews);
        // String downName=className+"-"+"123"+"-成绩录入模板"+".xls";
        // 生成文件名
        String title = "政务舆情";
        Date dt = new Date();
        String downName = user.getName() + "_" + dt.getTime() + "_" + title + ".xls";
        ExportExcel ee = new ExportExcel();
        return ee.exportExcelByTemplate(title, downName, response, request.getSession(), data, this.exportSupportService);
    }

    /**
     * 获取excel里要输入的数据
     *
     * @param listeventNews
     * @return
     */
    private List<AssignedCell[]> getAssignedCellData(List<Eventnews> listeventNews) {
        List<AssignedCell[]> data = new ArrayList<AssignedCell[]>();
        // 添加数据
        for (Eventnews e : listeventNews) {
            String polarity = "";
            if (e.getPolarity() == -1) {
                polarity = "负面";
            } else if (e.getPolarity() == 0) {
                polarity = "争议";
            } else {
                polarity = "正面";
            }
            // System.out.println(e.getPolarity()+":"+polarity);
            AssignedCell[] row1 = { new AssignedCell(0, 0, e.getTitle(), 0),
                    // new AssignedCell(0, 1, e.getSummary(), 0),
                    new AssignedCell(0, 1, e.getUrl(), 0), new AssignedCell(0, 2, e.getWebsite(), 0), new AssignedCell(0, 3, DateUtils.dateToStr(e.getPublishdate(), "yyyy-MM-dd HH:mm:ss"), 0), new AssignedCell(0, 4, polarity, 0), };
            data.add(row1);
        }
        return data;
    }

    /**
     * 得到eventList
     */
    private List<Eventnews> getEventList(Eventnews eventnews, HttpServletRequest request, HttpServletResponse response) {
        // session中获取当前用户id 暂定为1 左侧目录树
        User user = (User) request.getSession().getAttribute("userfront");
        if (!"".equals(request.getParameter("eventid")) && !"-1".equals(request.getParameter("eventid"))) {
            eventnews.setEventid(Integer.parseInt(request.getParameter("eventid")));
        } else {
            eventnews.setEventid(-1);
        }
        // 地域id
        if (!"".equals(request.getParameter("regionID"))) {
            eventnews.setRegionID(request.getParameter("regionID"));
        } else {
            String allRegionID = "";
            List<Region> list = this.regionService.selectByUserId(user.getUserId());
            // 当前用户注册的userRegionID
            String userRegionID = "";
            if (null != list && list.size() > 0) {
                allRegionID = list.get(0).getRegionid().toString();
                userRegionID = list.get(0).getRegionid().toString();
                for (int j = 0; j < list.size(); j++) {
                    List<Region> secondList = this.regionService.selectByParentId(list.get(j).getRegionid());
                    if (null != secondList && secondList.size() > 0) {
                        list.get(j).setList(secondList);
                    }
                    for (int i = 0; i < secondList.size(); i++) {
                        allRegionID = allRegionID + "," + secondList.get(i).getRegionid().toString();
                    }
                }
            }
            eventnews.setRegionID(allRegionID);
        }
        // 设置provinceid
        if (eventnews.getRegionID().length() > 2) {
            eventnews.setProvinceid(Integer.parseInt(eventnews.getRegionID().substring(0, 2)));
        } else {
            eventnews.setProvinceid(Integer.parseInt(eventnews.getRegionID()));
        }
        // 倾向性
        if (!"".equals(request.getParameter("polarityorientation")) && !"-2".equals(request.getParameter("polarityorientation"))) {
            eventnews.setPolarity(Integer.valueOf(request.getParameter("polarityorientation")));
        }
        // 网站来源
        if (!"".equals(request.getParameter("type"))) {
            eventnews.setType(Integer.parseInt(request.getParameter("type")));
        }
        List<Eventnews> listEventnews = new ArrayList<Eventnews>();
        listEventnews = this.eventnewsService.selectByTimes(eventnews);
        return listEventnews;
    }

    /**
     * 发送邮件
     *
     * @param ids
     * @param request
     * @param response
     */
    @RequestMapping(value = "/sendMail")
    @ResponseBody
    public int sendMail(Mail mail, HttpServletRequest request, HttpServletResponse response) {
        String ids = mail.getIds();
        int i = 0;
        try {
            SimpleDateFormat datatime = new SimpleDateFormat("yyyyMMdd");
            // map内放模板需要的数据
            Map<String, Object> dataMap = new HashMap<String, Object>();
            String[] eventNewsids = ids.split(",");
            List<Eventnews> listeventNews = this.eventnewsService.selectByEventNewsIds(eventNewsids);
            dataMap.put("list", listeventNews);
            User user = (User) request.getSession().getAttribute("userfront");
            Date dt = new Date();
            // 生成文件名
            String fileName = user.getName() + "_" + dt.getTime() + "_mail.html";
            // --模板(从数据库查找所需要模板)
            String template = "mailTemplate.ftl";
            MailTemp mailTemp = this.mailTempService.findTemplateByUserId(user.getUserId());
            if (null != mailTemp) {
                template = mailTemp.getAddress();
            }
            DocumentHandler doc = new DocumentHandler();
            String ctxPath = request.getSession().getServletContext().getRealPath("/") + "files/" + datatime.format(new Date()) + "/";
            String lines = doc.createHtml(ctxPath, fileName, template, dataMap, request, response);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("content", lines);
            // map.put("title", "政务舆情");
            if ("".equals(mail.getTitle())) {
                map.put("title", user.getName() + "_政务舆情");
            } else {
                map.put("title", mail.getTitle());
            }

            map.put("email", mail.getReceiveUser());
            i = doc.SendMailtest(map, request, response);
        } catch (Exception e) {
            i = 0;
        }
        return i;
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
        } else if (type == 7) {
            return "微信";
        } else {
            return "其他";
        }
    }

    /**
     * 分析报表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/showfenxi")
    public ModelAndView showfenxi(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("event/doshow_fenxi");
        mv.addObject("eventid", request.getParameter("eventid"));
        mv.addObject("regionID", request.getParameter("regionID"));

        //
        Eventnews eventnews = new Eventnews();
        this.dealTime("2", eventnews);
        eventnews.setTimeMethod("2");
        if (null != request.getParameter("eventid") && !"-1".equals(request.getParameter("eventid"))) {
            eventnews.setEventArray(request.getParameter("eventid"));
        } else {
            User user = (User) request.getSession().getAttribute("userfront");
            Subscription subscription = this.subscriptionService.findSubscriptionByUserId(user.getUserId().toString());
            eventnews.setEventArray(subscription.getEventId());
        }

        eventnews.setRegionID(request.getParameter("regionID"));
        eventnews.setProvinceid(Integer.parseInt(request.getParameter("regionID").substring(0, 2)));
        List<Eventnews> list = this.eventnewsService.CountBySrcType(eventnews);

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("value", list.get(i).getCount());
            jsonObject.put("name", this.dealTypeName(list.get(i).getType()));
            jsonArray.add(jsonObject);
        }
        mv.addObject("result1", jsonArray.toString());

        list = this.eventnewsService.CountByWebSite(eventnews);
        JSONArray jsonArray2 = new JSONArray();
        JSONArray jsonArray3 = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            jsonArray2.add(list.get(i).getWebsite());
            jsonArray3.add(list.get(i).getCount());
        }
        mv.addObject("result2", jsonArray2.toString());
        mv.addObject("result3", jsonArray3.toString());

        list = this.eventnewsService.CountByPolarity(eventnews);
        JSONArray jsonArray4 = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("value", list.get(i).getCount());
            jsonObject.put("name", this.dealPolarity(list.get(i).getPolarity()));
            jsonArray4.add(jsonObject);
        }
        mv.addObject("result4", jsonArray4.toString());

        //
        JSONArray jsonArray5 = new JSONArray();
        JSONArray jsonArray6 = new JSONArray();
        JSONArray jsonArray7 = new JSONArray();
        List<WebDictionary> webDictionaryList = this.webDictionaryService.findAllWebDictionary();
        for (int i = 0; i < webDictionaryList.size(); i++) {
            eventnews.setType(Integer.parseInt(webDictionaryList.get(i).getValue()));
            list = this.eventnewsService.CountByPolarityAndType(eventnews);
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

    /**
     * 跳出到填写邮件页面
     *
     * @return
     */
    @RequestMapping(value = "/toWriteEmail")
    @ResponseBody
    public ModelAndView toWriteEmail(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        User user = (User) request.getSession().getAttribute("userfront");
        mv.addObject("title", user.getName() + "_政务舆情");
        mv.setViewName("event/write_email");
        return mv;
    }

    /**
     * 点击添加到专题跳转
     *
     * @return
     */
    @RequestMapping(value = "/toAddTopic")
    @ResponseBody
    public ModelAndView toAddTopic(HttpServletRequest request, HttpServletResponse response) {
        Eventnews eventnews = new Eventnews();
        if (null != request.getParameter("currentEventnewsId") && !"".equals(request.getParameter("currentEventnewsId"))) {
            eventnews.setId(request.getParameter("currentEventnewsId"));
            eventnews.setProvinceid(Integer.parseInt(request.getParameter("regionID").substring(0, 2)));
            eventnews = this.eventnewsService.load(eventnews);
        }
        eventnews.setSummary(HTMLSpirit.delHTMLTag(eventnews.getSummary()));
        ModelAndView mv = new ModelAndView();
        mv.addObject("topic", eventnews);
        mv.setViewName("subject/add_topic_info");
        return mv;
    }

    // /**
    // * 政务舆情 按来源统计
    // * @param regionID
    // * @param request
    // * @param response
    // * @return
    // */
    // @RequestMapping(value = "/selectCountBySrcType/{regionID}",method =
    // RequestMethod.POST, produces="text/plain;charset=utf-8")
    // public @ResponseBody String
    // selectCountBySrcType(@PathVariable("regionID") String
    // regionID,HttpServletRequest request,HttpServletResponse response){
    // Eventnews eventnews = new Eventnews();
    // eventnews.setRegionID(regionID);
    // eventnews.setProvinceid(Integer.parseInt(regionID.substring(0, 2)));
    // List<Eventnews> list = eventnewsService.selectCountBySrcType(eventnews);
    //
    // JSONArray jsonArray = new JSONArray();
    // for (int i = 0; i < list.size(); i++) {
    // JSONObject jsonObject = new JSONObject();
    // jsonObject.put("value", list.get(i).getCount());
    // jsonObject.put("name", dealTypeName(list.get(i).getSrctype()));
    // jsonArray.add(jsonObject);
    // }
    // return jsonArray.toString();
    // }
    //
    // /**
    // * 政务舆情 按WebSite统计
    // * @param regionID
    // * @param request
    // * @param response
    // * @return
    // */
    // @RequestMapping(value = "/selectCountByWebSite/{regionID}",method =
    // RequestMethod.POST, produces="text/plain;charset=utf-8")
    // public @ResponseBody String
    // selectCountByWebSite(@PathVariable("regionID") String
    // regionID,HttpServletRequest request,HttpServletResponse response){
    // Eventnews eventnews = new Eventnews();
    // eventnews.setRegionID(regionID);
    // eventnews.setProvinceid(Integer.parseInt(regionID.substring(0, 2)));
    // List<Eventnews> list = eventnewsService.selectCountByWebSite(eventnews);
    // JSONArray jsonArray2 = new JSONArray();
    // JSONArray jsonArray3 = new JSONArray();
    // for (int i = 0; i < list.size(); i++) {
    // jsonArray2.add(list.get(i).getWebsite());
    // jsonArray3.add(list.get(i).getCount());
    // }
    // return jsonArray2.toString()+"&&"+jsonArray3.toString();
    // }
    //
    // /**
    // * 政务舆情 按Polarity(统计
    // * @param regionID
    // * @param request
    // * @param response
    // * @return
    // */
    // @RequestMapping(value = "/selectCountByPolarity/{regionID}",method =
    // RequestMethod.POST, produces="text/plain;charset=utf-8")
    // public @ResponseBody String
    // selectCountByPolarity(@PathVariable("regionID") String
    // regionID,HttpServletRequest request,HttpServletResponse response){
    // Eventnews eventnews = new Eventnews();
    // eventnews.setRegionID(regionID);
    // eventnews.setProvinceid(Integer.parseInt(regionID.substring(0, 2)));
    // List<Eventnews> list = eventnewsService.selectCountByPolarity(eventnews);
    // JSONArray jsonArray4 = new JSONArray();
    // for (int i = 0; i < list.size(); i++) {
    // JSONObject jsonObject = new JSONObject();
    // jsonObject.put("value", list.get(i).getCount());
    // jsonObject.put("name", dealPolarity(list.get(i).getPolarity()));
    // jsonArray4.add(jsonObject);
    // }
    // return jsonArray4.toString();
    // }
    //
    // /**
    // * 政务舆情 按Polarity(统计
    // * @param regionID
    // * @param request
    // * @param response
    // * @return
    // */
    // @RequestMapping(value = "/selectCountByPolarityAndType/{regionID}",method
    // = RequestMethod.POST, produces="text/plain;charset=utf-8")
    // public @ResponseBody String
    // selectCountByPolarityAndType(@PathVariable("regionID") String
    // regionID,HttpServletRequest request,HttpServletResponse response){
    // Eventnews eventnews = new Eventnews();
    // eventnews.setRegionID(regionID);
    // eventnews.setProvinceid(Integer.parseInt(regionID.substring(0, 2)));
    // //页面信息来源
    // JSONArray jsonArray5 = new JSONArray();
    // JSONArray jsonArray6 = new JSONArray();
    // JSONArray jsonArray7 = new JSONArray();
    // List<Eventnews> list = new ArrayList<Eventnews>();
    // List<WebDictionary> webDictionaryList =
    // webDictionaryService.findAllWebDictionary();
    // for (int i = 0; i < webDictionaryList.size(); i++) {
    // eventnews.setSrctype(Integer.parseInt(webDictionaryList.get(i).getValue()));
    // list = eventnewsService.selectCountByPolarityAndType(eventnews);
    // for (int j = 0; j < list.size(); j++) {
    // //count,polarity,type
    // if(list.get(j).getPolarity()==1){
    // jsonArray5.add(list.get(j).getCount());
    // }else if(list.get(j).getPolarity()==-1){
    // jsonArray6.add(list.get(j).getCount());
    // }else {
    // jsonArray7.add(list.get(j).getCount());
    // }
    // }
    // }
    // return
    // jsonArray5.toString()+"&&"+jsonArray6.toString()+"&&"+jsonArray7.toString();
    // }
    /***********************************************
     *
     */

    /**
     *
     * @param ids
     * @param request
     * @param response
     */
    @RequestMapping(value = "/sendMailTest")
    @ResponseBody
    public int sendMailTest(Mail mail, HttpServletRequest request, HttpServletResponse response) {
        // String ids=mail.getIds();
        mail.setReceiveUser("81672046@qq.com");
        int i = 0;
        try {
            SimpleDateFormat datatime = new SimpleDateFormat("yyyyMMdd");
            // map内放模板需要的数据
            Map<String, Object> dataMap = new HashMap<String, Object>();
            // String[] eventNewsids = ids.split(",");
            // List<Eventnews> listeventNews = this.eventnewsService
            // .selectByEventNewsIds(eventNewsids);
            // dataMap.put("list", listeventNews);
            User user = (User) request.getSession().getAttribute("userfront");
            // 生成文件名
            String fileName = user.getName() + "_" + datatime.format(new Date()) + "_mail.html";
            // --模板(从数据库查找所需要模板)
            String template = "report.ftl";
            // MailTemp
            // mailTemp=this.mailTempService.findTemplateByUserId(user.getUserId());
            // if(null!=mailTemp){
            // template=mailTemp.getAddress();
            // }
            DocumentHandler doc = new DocumentHandler();
            String ctxPath = request.getSession().getServletContext().getRealPath("/") + "files/" + datatime.format(new Date()) + "/";
            String lines = doc.createHtml(ctxPath, fileName, template, dataMap, request, response);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("content", lines);
            // map.put("title", "政务舆情");
            if ("".equals(mail.getTitle())) {
                map.put("title", user.getName() + "_政务舆情");
            } else {
                map.put("title", mail.getTitle());
            }
            map.put("email", mail.getReceiveUser());
            i = doc.SendMailtest(map, request, response);
        } catch (Exception e) {
            i = 0;
        }
        return i;
    }

    /**
     * 查询全国政务舆情 按省份
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/toListAllEvent")
    public ModelAndView toListAllEvent(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("event/list_allevent");
        User user = (User) request.getSession().getAttribute("userfront");
        List<Region> list = this.regionService.selectByParentId(-1);
        List<WebDictionary> webDictionaryList = this.webDictionaryService.findAllWebDictionary();

        OperationLog operationLog = new OperationLog(new Date(), user.getUserId(), user.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "政务舆情", OperateType.FIND.toString(), OperateMode.政务舆情.toString());
        this.operationLogService.insertSelective(operationLog);
        mv.addObject("list", list);
        mv.addObject("firstRegionID", list.get(0).getRegionid());
        mv.addObject("webDictionaryList", webDictionaryList);
        // 事件分类列表
        Subscription subscription = this.subscriptionService.findSubscriptionByUserId(String.valueOf(user.getUserId()));

        List<Event> eventList = new ArrayList<Event>();
        if (null == subscription) {
            subscription = new Subscription();
        } else {
            Event event = new Event();
            if (!"".equals(subscription.getEventId())) {
                event.setEventArray(subscription.getEventId());
                eventList = this.eventService.findEventByEventIdArray(event);
            } else {
                mv.setViewName("event/list_event_tishi");
                return mv;
            }
        }

        if (null != request.getParameter("timeMethod") && !"".equals(request.getParameter("timeMethod"))) {
            mv.addObject("timeMethod", "1");
        } else {
            mv.addObject("timeMethod", "0");
        }

        mv.addObject("eventList", eventList);
        return mv;
    }
}
