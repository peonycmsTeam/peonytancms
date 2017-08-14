package com.peony.peonyfront.homepage.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.peony.core.base.pojo.PageParameter;
import com.peony.core.base.pojo.Pagination;
import com.peony.core.common.utils.CalendarUtil;
import com.peony.core.common.utils.DateUtils;
import com.peony.core.common.utils.JsonUtil;
import com.peony.core.common.utils.tedis.TedisUtil;
import com.peony.peonyfront.briefreport.model.Briefreport;
import com.peony.peonyfront.briefreport.service.BriefreportService;
import com.peony.peonyfront.event.model.Event;
import com.peony.peonyfront.event.model.Eventnews;
import com.peony.peonyfront.event.model.Subscription;
import com.peony.peonyfront.event.service.EventService;
import com.peony.peonyfront.event.service.EventnewsService;
import com.peony.peonyfront.event.service.SubscriptionService;
import com.peony.peonyfront.focus.model.Focus;
import com.peony.peonyfront.focus.service.FocusService;
import com.peony.peonyfront.homepage.controller.model.Statistics;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.officeplatform.service.MailService;
import com.peony.peonyfront.operationlog.model.OperationLog;
import com.peony.peonyfront.operationlog.service.OperationLogService;
import com.peony.peonyfront.region.model.Region;
import com.peony.peonyfront.region.service.RegionService;
import com.peony.peonyfront.report.model.Report;
import com.peony.peonyfront.report.service.ReportService;
import com.peony.peonyfront.subject.model.Subject;
import com.peony.peonyfront.subject.model.SubjectPage;
import com.peony.peonyfront.subject.service.SubjectPageService;
import com.peony.peonyfront.subject.service.SubjectService;
import com.peony.peonyfront.thinktank.model.Pubinfo;
import com.peony.peonyfront.thinktank.service.PubinfoService;
import com.peony.peonyfront.util.JSONUtil;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;
import com.peony.peonyfront.warning.model.Warning;
import com.peony.peonyfront.warning.service.WarningService;

@Controller
@RequestMapping("/homepage")
public class HomePageController {
    @Autowired
    private WarningService      warningservice;
    @Autowired
    private ReportService       reportService;
    @Autowired
    private FocusService        focusService;
    @Autowired
    private RegionService       regionService;
    @Autowired
    private PubinfoService      pubinfoService;
    @Autowired
    private SubjectPageService  subjectPageService;                                        // 订制舆情页面信息查询服务
    @Autowired
    private SubjectService      subjectService;                                            // 订制舆情页面信息查询服务
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private EventnewsService    eventnewsService;
    @Autowired
    private MailService         mailService;

    @Autowired
    private EventService        eventService;
    @Autowired
    private BriefreportService  briefreportService;

    @Autowired
    private OperationLogService operationLogService;

    @Autowired
    private TedisUtil           tedisUtil;

    private static final Log    log          = LogFactory.getLog(HomePageController.class);

    // 0 3 4 5 境内 1，2表示境外
    private final static String NEW_LEVEL_JN = "0,3,4,5";

    // @Autowired
    // private InfoService infoService;
    /**
     * 首页显示内容
     *
     * @return
     */
    @RequestMapping(value = "/listHomePage")
    public ModelAndView homePageInfo(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        // 新闻，正负面不显示
        User user = (User) request.getSession().getAttribute("userfront");
        Pubinfo pubinfo = new Pubinfo();
        PageParameter page = new PageParameter();
        page.setCurrentPage(1);
        page.setPageSize(3);
        pubinfo.setPageParameter(page);
        pubinfo.setSubjectRecommend("1");// 专题推荐
        Pagination<Pubinfo> pagination = this.pubinfoService.selectPubinfos(pubinfo);

        OperationLog operationLog = new OperationLog(new Date(), user.getUserId(), user.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "首页", OperateType.FIND.toString(), OperateMode.首页.toString());
        this.operationLogService.insertSelective(operationLog);

        mv.addObject("pagination", pagination);
        mv.setViewName("homepage/list_homepage");
        return mv;
    }

    /**
     * 首页显示日报
     *
     * @return
     */
    @RequestMapping(value = "/listReport")
    public ModelAndView listReport(@ModelAttribute("report") Report report, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        // 日报信息列表
        PageParameter pageParameter = new PageParameter();
        pageParameter.setCurrentPage(1);
        pageParameter.setPageSize(7);
        report.setPageParameter(pageParameter);
        User user = (User) request.getSession().getAttribute("userfront");
        report.setUserId(String.valueOf(user.getUserId()));
        Pagination<Report> pagination = this.reportService.selectReportByPage(report);
        mv.addObject("pagination", pagination);
        mv.setViewName("homepage/list_report");
        return mv;
    }

    /**
     * 首页显示政务舆情
     *
     * @return
     */
    @RequestMapping(value = "/listEventnews")
    public ModelAndView listEventnews(@ModelAttribute("eventnews") Eventnews eventnews, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        User user = (User) request.getSession().getAttribute("userfront");
        String allRegionID = "";

        List<Region> list = this.regionService.selectByUserId(user.getUserId());
        if (!CollectionUtils.isEmpty(list)) {
            allRegionID = list.get(0).getRegionid().toString();
            // 事件分类列表
            eventnews.setRegionID(allRegionID);
            eventnews.setEventid(-1);
            Subscription subscription = this.subscriptionService.findSubscriptionByUserId(user.getUserId().toString());
            eventnews.setEventArray(subscription.getEventId());

            // 设置provinceid
            if (eventnews.getRegionID().length() > 2) {
                eventnews.setProvinceid(Integer.parseInt(eventnews.getRegionID().substring(0, 2)));
            } else {
                eventnews.setProvinceid(Integer.parseInt(eventnews.getRegionID()));
            }

            this.dealTime("2", eventnews);// 近3天数据
            Pagination<Eventnews> pagination = this.eventnewsService.selectGroupByTitle(eventnews);
            if (null == pagination || CollectionUtils.isEmpty(pagination.getList())) {
                this.dealTime("3", eventnews);// 近7天数据
                pagination = this.eventnewsService.selectGroupByTitle(eventnews);
            }
            mv.addObject("pagination", pagination);
            mv.setViewName("homepage/list_eventnews");
            return mv;
        } else {
            log.warn("You don't set the region keywords");
            mv.setViewName("homepage/list_no_eventnews");
            return mv;
        }
    }

    /**
     * 首页显示行业舆情
     *
     * @return
     */
    @RequestMapping(value = "/listIndustryNews")
    public ModelAndView listIndustryNews(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mv = new ModelAndView();
        String IndustryId = "";

        User user = (User) request.getSession().getAttribute("userfront");
        Subscription subscription = this.subscriptionService.findSubscriptionByUserId(user.getUserId().toString());
        Event event = new Event();
        event.setEventArray(subscription.getEventId());
        List<Event> list = this.eventService.findEventByEventIdArray(event);
        IndustryId = list.get(0).getEventid().toString();

        SubjectPage subjectPage = new SubjectPage();
        PageParameter pageParameter = new PageParameter();
        pageParameter.setCurrentPage(1);
        pageParameter.setPageSize(10);
        subjectPage.setPageParameter(pageParameter);

        event.setEventArray(IndustryId);
        List<Event> listEvents = this.eventService.findEventByEventIdArray(event);

        subjectPage.setUserid(listEvents.get(0).getStatus());
        subjectPage.setNewslevelConditions(NEW_LEVEL_JN);
        subjectPage.setSubjectidArray(IndustryId);
        Pagination<SubjectPage> pagination = this.subjectPageService.selectGroupByTitle(subjectPage);
        // 页面信息来源
        subjectPage.setNewslev(0);
        mv.addObject("pagination", pagination);
        mv.addObject("userId", listEvents.get(0).getStatus());
        mv.setViewName("homepage/list_industry");
        return mv;
    }

    /**
     * 首页显示公共专题
     *
     * @return
     */
    @RequestMapping(value = "/listFocus")
    public ModelAndView listFocus(@ModelAttribute("focus") Focus focus, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        User user = (User) request.getSession().getAttribute("userfront");
        List<Region> Regionlist = this.regionService.selectByUserId(user.getUserId());
        if (!CollectionUtils.isEmpty(Regionlist)) {
            PageParameter pageParameter = new PageParameter();
            pageParameter.setCurrentPage(1);
            pageParameter.setPageSize(7);
            focus.setPageParameter(pageParameter);
            focus.setRegionid(Regionlist.get(0).getRegionid());
            Pagination<Focus> pagination = this.focusService.selectByPage(focus);
            mv.addObject("pagination", pagination);
            mv.setViewName("homepage/list_focus");
            return mv;
        } else {
            log.warn("You don't set the region keywords");
            mv.setViewName("homepage/list_no_focus");
            return mv;
        }
    }

    /**
     * 首页显示案例列表
     *
     * @param pubinfo
     * @return
     */
    @RequestMapping(value = "/listPubinfo")
    @ResponseBody
    public ModelAndView listPubinfo(@ModelAttribute("pubinfo") Pubinfo pubinfo, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        PageParameter pageParameter = new PageParameter();
        pageParameter.setCurrentPage(1);
        pageParameter.setPageSize(7);

        pubinfo.setPageParameter(pageParameter);
        // pubinfo.setCaseRecommend("1");
        User user = (User) request.getSession().getAttribute("userfront");
        if (user.getUserType().equals("1")) {
            pubinfo.setChannelId("8");// 企业
        } else {
            pubinfo.setChannelId("7");// 政府
        }
        Pagination<Pubinfo> pagination = this.pubinfoService.selectPubinfos(pubinfo);
        mv.addObject("pagination", pagination);
        mv.setViewName("homepage/list_pubinfo");
        return mv;
    }

    /**
     * 订制舆情页面
     *
     * @return
     */
    @RequestMapping(value = "/listSubjectSentiment")
    public ModelAndView listSubjectSentiment(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("userfront");
        SubjectPage subjectPage = new SubjectPage();
        subjectPage.setUserid(user.getUserId());
        Subject subject = new Subject();
        subject.setUserid(user.getUserId());
        List<Subject> list = this.subjectService.selectSubjectByUserIdExclusive(subject);
        String subjectidArray = "";
        for (int i = 0; i < list.size(); i++) {
            if ("".equals(subjectidArray)) {
                subjectidArray = list.get(i).getId().toString();
            } else {
                subjectidArray = subjectidArray + "," + list.get(i).getId().toString();
            }

        }
        subjectPage.setSubjectidArray(subjectidArray);
        Pagination<SubjectPage> pagination = new Pagination<SubjectPage>();
        this.dealTime("2", subjectPage);// 近3天数据
        pagination = this.subjectPageService.selectGroupByTitle(subjectPage);
        if (pagination.getList().size() == 0) {
            this.dealTime("3", subjectPage);// 近7天数据
            pagination = this.subjectPageService.selectGroupByTitle(subjectPage);
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("pagination", pagination);
        mv.setViewName("homepage/list_subject_sentiment");
        return mv;
    }

    /**
     * 首页 企业客户舆情简报
     *
     * @param briefreport
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listBriefreport")
    public ModelAndView listBriefreport(@ModelAttribute("briefreport") Briefreport briefreport, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        // --uerId从session里面获取
        User user = (User) request.getSession().getAttribute("userfront");
        briefreport.setUserId(String.valueOf(user.getUserId()));
        PageParameter pageParameter = new PageParameter();
        pageParameter.setCurrentPage(1);
        pageParameter.setPageSize(7);

        briefreport.setPageParameter(pageParameter);
        Pagination<Briefreport> pagination = this.briefreportService.selectByPage(briefreport);
        mv.addObject("pagination", pagination);
        mv.addObject("briefreport", briefreport);
        mv.setViewName("homepage/list_briefreport");
        return mv;
    }

    /**
     * 统计页面
     *
     * @return
     */
    @RequestMapping(value = "/listCount")
    public ModelAndView listCount(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        User user = (User) request.getSession().getAttribute("userfront");

        Date now = new Date(); // 当前时间
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd-HH");
        String currentHour = sf.format(now);

        String key = user.getUserId() + "-" + currentHour + "-listCount";

        if (this.tedisUtil.isExists(key) && null != this.tedisUtil.tedisGetString(key) && !"".equals(this.tedisUtil.tedisGetString(key))) {

            log.info("listCount found in cache. Key: " + key);

            String tjData = this.tedisUtil.tedisGetString(key);
            try {
                Statistics statistics = JsonUtil.fromJson(tjData, new TypeReference<Statistics>() {
                });
                mv.addObject("count1", statistics.getCount1());
                mv.addObject("count2", statistics.getCount2());
                mv.addObject("count3", statistics.getCount3());
                mv.addObject("mytimes", statistics.getMytimes());
                mv.addObject("allcount1", statistics.getAllcount1());
                mv.addObject("allcount2", statistics.getAllcount2());
                mv.addObject("allcount3", statistics.getAllcount3());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            log.info("listCount not found in cache. Key: " + key);

            Date endDate = new Date(); // 当前时间
            SimpleDateFormat stimeformat = new SimpleDateFormat("yyyy-MM-dd");
            String etime = stimeformat.format(endDate);

            Calendar calender = Calendar.getInstance();
            calender.add(Calendar.DAY_OF_MONTH, -6);// 统计7天数据
            String stime = stimeformat.format(calender.getTime());

            Map map = new HashMap();
            map.put("beginTime", stime);
            map.put("endTime", etime);
            map.put("userid", user.getUserId());
            // 找到两个时间间隔
            long s = CalendarUtil.getDays(etime, stime) + 1;

            String[] times = new String[(int) s];
            Calendar cal = Calendar.getInstance();
            cal.setTime(DateUtils.strToDate(stime, "yyyy-MM-dd"));
            for (int i = 0; i < s; i++) {
                times[i] = DateUtils.dateToStr(cal.getTime(), "yyyy-MM-dd");
                cal.add(Calendar.DATE, 1);
            }
            int[] count1 = new int[(int) s];// 正面
            int[] count2 = new int[(int) s];// 负面
            int[] count3 = new int[(int) s];// 争议

            int allcount1 = 0;// 正面总数
            int allcount2 = 0;// 负面总数
            int allcount3 = 0;// 争议总数
            // 从开始日期开始遍历
            List<Map> reList = this.subjectPageService.countSelectByCount(map);
            // 声明最大值和位置
            for (int j = 0; j < times.length; j++) {

                String time = times[j];
                for (int i = 0; i < reList.size(); i++) {
                    Map remap = reList.get(i);
                    int type = (Integer) remap.get("polarity");
                    int count = new Long((Long) remap.get("count")).intValue();
                    String publishDate = (String) remap.get("publishDate");
                    if (time.equals(publishDate)) {
                        switch (type) {
                        case 1:
                            count1[j] = count;
                            allcount1 += count;
                            break;
                        case -1:
                            count2[j] = count;
                            allcount2 += count;
                            break;
                        case 0:
                            count3[j] = count;
                            allcount3 += count;
                            break;
                        default:
                            break;
                        }

                    }
                }

            }

            mv.addObject("count1", Arrays.toString(count1));
            mv.addObject("count2", Arrays.toString(count2));
            mv.addObject("count3", Arrays.toString(count3));
            mv.addObject("mytimes", Arrays.toString(times).replace("[", "['").replace("]", "']").replace(",", "','").replace(" ", ""));

            mv.addObject("allcount1", allcount1);
            mv.addObject("allcount2", allcount2);
            mv.addObject("allcount3", allcount3);
            Statistics statistics = new Statistics();
            statistics.setCount1(Arrays.toString(count1));
            statistics.setCount2(Arrays.toString(count2));
            statistics.setCount3(Arrays.toString(count3));
            statistics.setMytimes(Arrays.toString(times).replace("[", "['").replace("]", "']").replace(",", "','").replace(" ", ""));
            statistics.setAllcount1(Integer.toString(allcount1));
            statistics.setAllcount2(Integer.toString(allcount2));
            statistics.setAllcount3(Integer.toString(allcount3));
            try {
                this.tedisUtil.tedisSetString(key, JsonUtil.toJson(statistics));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mv.setViewName("homepage/list_count");
        return mv;
    }

    /**
     * 统计页面
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listWorkCount")
    public String listWorkCount(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("userfront");
        int warnCount = this.warningservice.selectWarningTodayCount(user.getUserId());
        int mailCount = this.mailService.selectMailTodayCount(user.getUserId());
        Map countMap = new HashMap<String, Integer>();
        countMap.put("warnCount", warnCount);
        countMap.put("mailCount", mailCount);
        String json = JSONUtil.map2JSON(countMap);
        return json;
    }

    /**
     * 定制舆情总数
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectTotalCount")
    public Map selectTotalCount(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("userfront");
        SubjectPage subjectPage = new SubjectPage();
        subjectPage.setUserid(user.getUserId());
        Subject subject = new Subject();
        subject.setUserid(user.getUserId());
        List<Subject> list = this.subjectService.selectSubjectByUserIdExclusive(subject);
        String subjectidArray = "";
        for (int i = 0; i < list.size(); i++) {
            if ("".equals(subjectidArray)) {
                subjectidArray = list.get(i).getId().toString();
            } else {
                subjectidArray = subjectidArray + "," + list.get(i).getId().toString();
            }

        }
        subjectPage.setSubjectidArray(subjectidArray);
        this.dealTime("3", subjectPage);// 近7天数据
        List<Map> reList = this.subjectPageService.totalSelectCount(subjectPage);

        Map countMap = new HashMap<String, Integer>();
        long totalcount = 0;
        long newscount = 0;
        long bbscount = 0;
        long blogcount = 0;
        long weibocount = 0;
        long twittercount = 0;
        long journalcount = 0;
        long weixincount = 0;
        String mediaType = "";
        for (int i = 0; i < reList.size(); i++) {
            Map remap = reList.get(i);
            int type = (Integer) remap.get("type");
            if (type == 1) {
                newscount = new Long((Long) remap.get("count")).intValue();
            }
            if (type == 2) {
                bbscount = new Long((Long) remap.get("count")).intValue();
            }
            if (type == 3) {
                weibocount = new Long((Long) remap.get("count")).intValue();
            }
            if (type == 4) {
                blogcount = new Long((Long) remap.get("count")).intValue();
            }
            if (type == 5) {
                journalcount = new Long((Long) remap.get("count")).intValue();
            }
            if (type == 6) {
                twittercount = new Long((Long) remap.get("count")).intValue();
            }
            if (type == 7) {
                weixincount = new Long((Long) remap.get("count")).intValue();
            }
        }

        totalcount = newscount + bbscount + blogcount + weibocount + twittercount + journalcount + weixincount;
        countMap.put("totalcount", totalcount);
        countMap.put("newscount", newscount);
        countMap.put("bbscount", bbscount);
        countMap.put("blogcount", blogcount);
        countMap.put("weibocount", weibocount);
        countMap.put("twittercount", twittercount);
        countMap.put("journalcount", journalcount);
        countMap.put("weixincount", weixincount);

        return countMap;
    }

    @RequestMapping(value = "/listWarning")
    public ModelAndView listWarning(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        // 预警信息列表
        PageParameter pageParameter = new PageParameter();
        pageParameter.setCurrentPage(1);
        pageParameter.setPageSize(7);
        Warning warning = new Warning();
        // --用户id
        User user = (User) request.getSession().getAttribute("userfront");
        int userId = user.getUserId();
        warning.setUserId(userId);
        warning.setPageParameter(pageParameter);
        Pagination<Warning> warnings = this.warningservice.selectWaringByPage(warning);
        mv.addObject("warnings", warnings);
        mv.setViewName("homepage/list_warning");
        return mv;
    }

    /**
     * 根据页面选择的时间段，来生成查询条件的开始时间、结束时间
     *
     * @param time
     * @param Eventnews
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
     * 根据页面选择的时间段，来生成查询条件的开始时间、结束时间 重载
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
}
