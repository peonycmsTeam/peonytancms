package com.peony.peonyfront.analysis.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.search.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.peony.peonyfront.analysis.controller.modle.Link;
import com.peony.peonyfront.analysis.controller.modle.Meitifenbu;
import com.peony.peonyfront.analysis.controller.modle.Node;
import com.peony.peonyfront.event.model.Event;
import com.peony.peonyfront.event.model.Subscription;
import com.peony.peonyfront.event.service.EventService;
import com.peony.peonyfront.event.service.SubscriptionService;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.region.model.Region;
import com.peony.peonyfront.region.service.RegionService;
import com.peony.peonyfront.subject.model.Subject;
import com.peony.peonyfront.subject.model.SubjectPage;
import com.peony.peonyfront.subject.model.WebDictionary;
import com.peony.peonyfront.subject.service.SubjectService;
import com.peony.peonyfront.subject.service.WebDictionaryService;
import com.peony.peonyfront.subjectkeywords.model.SubjectKeywords;
import com.peony.peonyfront.subjectkeywords.service.SubjectKeywordsService;
import com.peony.peonyfront.userregion.model.UserRegion;
import com.peony.peonyfront.userregion.service.UserRegionService;
import com.peony.peonyfront.util.SearchUtil;

/**
 * 综合分析控制类
 *
 * @author yanglin
 *
 */
@Controller
@RequestMapping("/analysis")
public class AnalysisController {

    @Autowired
    private WebDictionaryService   webDictionaryService;

    @Autowired
    private UserRegionService      userRegionService;

    @Autowired
    private SubscriptionService    subscriptionService;

    @Autowired
    private EventService           eventService;

    @Autowired
    private RegionService          regionService;

    @Autowired
    private SubjectService         subjectService;

    @Autowired
    private SubjectKeywordsService subjectKeywordsService;

    @RequestMapping(value = "/toAnalysisList")
    public ModelAndView toAnalysisList(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("analysis/list_analysis");
        // 地域
        User user = (User) request.getSession().getAttribute("userfront");
        int userId = user.getUserId();

        UserRegion userRegion = this.userRegionService.selectByUserId(userId);
        List<Region> RList = new ArrayList<Region>();
        if (userRegion != null) {
            RList = this.regionService.selectByParentIdAndPK(userRegion.getRegionId());
        }

        String areaValue = "";
        String areaNameValue = "";
        for (int i = 0; i < RList.size(); i++) {
            if ("".equals(areaValue)) {
                areaValue = RList.get(i).getRegionid().toString();
                areaNameValue = RList.get(i).getRegionname();
            } else {
                areaValue = areaValue + "," + RList.get(i).getRegionid().toString();
                areaNameValue = areaNameValue + "," + RList.get(i).getRegionname();
            }
        }
        mv.addObject("regionList", RList);

        // 媒体类型
        List<WebDictionary> webDictionaryList = this.webDictionaryService.findAllWebDictionary();
        mv.addObject("webDictionaryList", webDictionaryList);
        String meitiValue = "";
        for (int i = 0; i < webDictionaryList.size(); i++) {
            if ("".equals(meitiValue)) {
                meitiValue = webDictionaryList.get(i).getValue().toString();
            } else {
                meitiValue = meitiValue + "," + webDictionaryList.get(i).getValue().toString();
            }
        }

        // 政务舆情分类
        Subscription subscription = this.subscriptionService.findSubscriptionByUserId(user.getUserId().toString());
        Event event = new Event();
        event.setEventArray(subscription.getEventId());
        List<Event> eventList = this.eventService.findEventByEventIdArray(event);
        if (null == eventList) {
            eventList = new ArrayList<Event>();
        }
        String eventValue = "";
        for (int i = 0; i < eventList.size(); i++) {
            if ("".equals(eventValue)) {
                eventValue = eventList.get(i).getEventid().toString();
            } else {
                eventValue = eventValue + "," + eventList.get(i).getEventid().toString();
            }
        }
        mv.addObject("eventList", eventList);

        // 定制舆情分类
        Subject subject = new Subject();
        subject.setUserid(userId);
        List<Subject> subjectList = this.subjectService.selectSubjectByUserIdExclusive(subject);
        if (null == subjectList) {
            subjectList = new ArrayList<Subject>();
        }
        String subjectValue = "";
        for (int i = 0; i < subjectList.size(); i++) {
            if ("".equals(subjectValue)) {
                subjectValue = subjectList.get(i).getId().toString();
            } else {
                subjectValue = subjectValue + "," + subjectList.get(i).getId().toString();
            }
        }
        mv.addObject("subjectList", subjectList);

        // 页面时间
        SubjectPage subjectPage = new SubjectPage();
        if (null == request.getParameter("stime") || "".equals(request.getParameter("stime")) || null == request.getParameter("etime") || "".equals(request.getParameter("etime"))) {
            this.dealTime("3", subjectPage);
        } else {
            subjectPage.setBeginTime(request.getParameter("stime"));
            subjectPage.setEndTime(request.getParameter("etime"));
        }
        mv.addObject("beginTime", subjectPage.getBeginTime());
        mv.addObject("endTime", subjectPage.getEndTime());

        // area
        if (null == request.getParameter("quyu")) {
            mv.addObject("quyu", "-1");
            mv.addObject("quyuValue", areaValue);
            mv.addObject("quyuNameValue", areaNameValue);
        } else {
            if (request.getParameter("quyu").contains("-1")) {
                mv.addObject("quyu", "-1");
                mv.addObject("quyuValue", areaValue);
                mv.addObject("quyuNameValue", areaNameValue);
            } else {
                mv.addObject("quyu", "," + request.getParameter("quyu") + ",");
                mv.addObject("quyuValue", request.getParameter("quyu"));
                mv.addObject("quyuNameValue", request.getParameter("quyuNameValue"));
            }
        }

        // 媒体类型
        if (null == request.getParameter("meiti")) {
            mv.addObject("meiti", "-1");
            mv.addObject("meitiValue", meitiValue);
        } else {
            if (request.getParameter("meiti").contains("-1")) {
                mv.addObject("meiti", "-1");
                mv.addObject("meitiValue", meitiValue);
            } else {
                mv.addObject("meiti", "," + request.getParameter("meiti") + ",");
                mv.addObject("meitiValue", request.getParameter("meiti"));
            }
        }

        // 模块
        if (null == request.getParameter("mokuai")) {
            mv.addObject("mokuai", "-1");
            mv.addObject("mokuaiValue", "1,2");
        } else {
            if (request.getParameter("mokuai").contains("-1")) {
                mv.addObject("mokuai", "-1");
                mv.addObject("mokuaiValue", "1,2");
            } else {
                mv.addObject("mokuai", "," + request.getParameter("mokuai") + ",");
                mv.addObject("mokuaiValue", request.getParameter("mokuai"));
            }
        }

        // 政务分类
        if (null == request.getParameter("zhengwufenlei")) {
            mv.addObject("zhengwufenlei", "-1");
            mv.addObject("zhengwufenleiValue", eventValue);
        } else {
            if (request.getParameter("zhengwufenlei").contains("-1")) {
                mv.addObject("zhengwufenlei", "-1");
                mv.addObject("zhengwufenleiValue", eventValue);
            } else {
                mv.addObject("zhengwufenlei", "," + request.getParameter("zhengwufenlei") + ",");
                mv.addObject("zhengwufenleiValue", request.getParameter("zhengwufenlei"));
            }
        }

        // 定制分类
        if (null == request.getParameter("dingzhifenlei")) {
            mv.addObject("dingzhifenlei", "-1");
            mv.addObject("dingzhifenleiValue", subjectValue);
        } else {
            if (request.getParameter("dingzhifenlei").contains("-1")) {
                mv.addObject("dingzhifenlei", "-1");
                mv.addObject("dingzhifenleiValue", subjectValue);
            } else {
                mv.addObject("dingzhifenlei", "," + request.getParameter("dingzhifenlei") + ",");
                mv.addObject("dingzhifenleiValue", request.getParameter("dingzhifenlei"));
            }
        }
        mv.addObject("userType", user.getUserType());
        return mv;
    }

    private void dealTime(String time, SubjectPage subjectPage) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        // 当前日期
        subjectPage.setEndTime(df.format(new Date()));

        Calendar calendar = Calendar.getInstance();

        // 近1天
        if ("1".equals(time)) {
            subjectPage.setBeginTime(df.format(new Date()));
        } else if ("2".equals(time)) {
            // 近3天
            calendar.add(Calendar.DATE, -2);
            Date date = calendar.getTime();
            subjectPage.setBeginTime(df.format(date));
        } else if ("3".equals(time)) {
            // 近7天
            calendar.add(Calendar.DATE, -6);
            Date date = calendar.getTime();
            subjectPage.setBeginTime(df.format(date));
        } else if ("4".equals(time)) {
            // 近1个月
            calendar.add(Calendar.MONTH, -1);
            Date date = calendar.getTime();
            subjectPage.setBeginTime(df.format(date));
        } else if ("5".equals(time)) {
            subjectPage.setBeginTime(null);
            subjectPage.setEndTime(null);
        }
    }

    /**
     * 趋势分析
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws CorruptIndexException
     */
    @RequestMapping(value = "/qushifenxi")
    public ModelAndView qushifenxi(HttpServletRequest request, HttpServletResponse response) throws CorruptIndexException, IOException {
        String quyuNameValue = request.getParameter("quyuNameValue");
        String meitiValue = request.getParameter("meitiValue");
        String mokuaiValue = request.getParameter("mokuaiValue");
        String zhengwufenleiValue = request.getParameter("zhengwufenleiValue");
        String dingzhifenleiValue = request.getParameter("dingzhifenleiValue");
        String stime = request.getParameter("stime");
        String etime = request.getParameter("etime");
        String dateString = this.daysBetween(stime, etime);
        String quyuValue = request.getParameter("quyuValue");
        String[] day = dateString.split(",", -1);
        Map<String, Integer> map = new HashMap<String, Integer>();
        User user = (User) request.getSession().getAttribute("userfront");
        // 行业用户
        String quyuNameValueArray[] = quyuNameValue.split(",", -1);
        String quyuValueArray[] = quyuValue.split(",", -1);
        String zhengwufenleiValueArray[] = zhengwufenleiValue.split(",", -1);
        String dingzhifenleiValueArray[] = dingzhifenleiValue.split(",", -1);
        if (!user.getUserType().equals("2")) {
            quyuNameValueArray = null;
            quyuValueArray = null;
            mokuaiValue = "1";
            zhengwufenleiValueArray = null;
        }

        // 1 定制 2政务
        if (mokuaiValue.indexOf("1") < 0) {
            dingzhifenleiValueArray = null;
        }
        if (mokuaiValue.indexOf("2") < 0) {
            zhengwufenleiValueArray = null;
        }

        String zhengwu = "";
        String dingzhi = "";
        int[] total = new int[day.length];
        SearchUtil su = new SearchUtil();
        for (int i = 0; i < mokuaiValue.split(",", -1).length; i++) {
            if (mokuaiValue.split(",", -1)[i].equals("2")) {
                map = su.searchCount(user.getUserId().toString(), mokuaiValue.split(",", -1)[i], quyuValueArray, day, meitiValue.split(",", -1), zhengwufenleiValueArray, dingzhifenleiValueArray);
            } else {
                map = su.searchCount(user.getUserId().toString(), mokuaiValue.split(",", -1)[i], null, day, meitiValue.split(",", -1), zhengwufenleiValueArray, dingzhifenleiValueArray);
            }

            // 政务
            if (mokuaiValue.split(",", -1)[i].equals("2")) {
                for (int j = 0; j < day.length; j++) {
                    if ("".equals(zhengwu)) {
                        zhengwu = map.get(day[j]).toString();
                        total[j] = total[j] + map.get(day[j]);
                    } else {
                        zhengwu = zhengwu + "," + map.get(day[j]).toString();
                        total[j] = total[j] + map.get(day[j]);
                    }
                }
            } else {
                // 定制
                for (int j = 0; j < day.length; j++) {
                    if ("".equals(dingzhi)) {
                        dingzhi = map.get(day[j]).toString();
                        total[j] = total[j] + map.get(day[j]);
                    } else {
                        dingzhi = dingzhi + "," + map.get(day[j]).toString();
                        total[j] = total[j] + map.get(day[j]);
                    }
                }
            }

        }

        String totalString = "";
        for (int i = 0; i < total.length; i++) {
            if ("".equals(totalString)) {
                totalString = Integer.toString(total[i]);
            } else {
                totalString = totalString + "," + Integer.toString(total[i]);
            }
        }

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < day.length; i++) {
            jsonArray.add(day[i]);
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("analysis/qushifenxi");
        mv.addObject("zhengwuyuqing", "[" + zhengwu + "]");
        mv.addObject("dingzhiyuqing", "[" + dingzhi + "]");
        mv.addObject("zhengtiqushi", "[" + totalString + "]");
        mv.addObject("dateString", jsonArray.toJSONString().replaceAll("\"", "'"));

        return mv;
    }

    private String daysBetween(String smdate, String bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        try {
            d = sdf.parse(smdate);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(smdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time1 = cal.getTimeInMillis();
        try {
            cal.setTime(sdf.parse(bdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        int day = Integer.parseInt(String.valueOf(between_days));
        String date = "";
        for (int i = 0; i <= day; i++) {
            Calendar now = Calendar.getInstance();
            now.setTime(d);
            now.set(Calendar.DATE, now.get(Calendar.DATE) + i);
            if ("".equals(date)) {
                date = sdf.format(now.getTime());
            } else {
                date = date + "," + sdf.format(now.getTime());
            }
        }
        return date;
    }

    /**
     * 媒体分布
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws CorruptIndexException
     */
    @RequestMapping(value = "/meitifenbu")
    public ModelAndView meitifenbu(HttpServletRequest request, HttpServletResponse response) throws CorruptIndexException, IOException {

        String quyuNameValue = request.getParameter("quyuNameValue");
        String meitiValue = request.getParameter("meitiValue");
        String mokuaiValue = request.getParameter("mokuaiValue");
        String zhengwufenleiValue = request.getParameter("zhengwufenleiValue");
        String dingzhifenleiValue = request.getParameter("dingzhifenleiValue");
        String stime = request.getParameter("stime");
        String etime = request.getParameter("etime");
        String dateString = this.daysBetween(stime, etime);
        String quyuValue = request.getParameter("quyuValue");
        String[] day = dateString.split(",", -1);
        Map<String, Integer> map = new HashMap<String, Integer>();
        User user = (User) request.getSession().getAttribute("userfront");

        // 行业用户
        String quyuNameValueArray[] = quyuNameValue.split(",", -1);
        String zhengwufenleiValueArray[] = zhengwufenleiValue.split(",", -1);
        String dingzhifenleiValueArray[] = dingzhifenleiValue.split(",", -1);
        String quyuValueArray[] = quyuValue.split(",", -1);

        if (!user.getUserType().equals("2")) {
            quyuNameValueArray = null;
            mokuaiValue = "1";
            zhengwufenleiValueArray = null;
            quyuValueArray = null;
        }

        // 1 定制 2政务
        if (mokuaiValue.indexOf("1") < 0) {
            dingzhifenleiValueArray = null;
        }
        if (mokuaiValue.indexOf("2") < 0) {
            zhengwufenleiValueArray = null;
        }

        int[] total = new int[meitiValue.split(",", -1).length];
        SearchUtil su = new SearchUtil();
        for (int i = 0; i < mokuaiValue.split(",", -1).length; i++) {

            if (mokuaiValue.split(",", -1)[i].equals("2")) {
                map = su.mediaCount(user.getUserId().toString(), mokuaiValue.split(",", -1)[i], quyuValueArray, stime, etime, meitiValue.split(",", -1), zhengwufenleiValueArray, dingzhifenleiValueArray);
            } else {
                map = su.mediaCount(user.getUserId().toString(), mokuaiValue.split(",", -1)[i], null, stime, etime, meitiValue.split(",", -1), zhengwufenleiValueArray, dingzhifenleiValueArray);
            }
            for (int j = 0; j < meitiValue.split(",", -1).length; j++) {
                total[j] = total[j] + map.get(meitiValue.split(",", -1)[j]);
            }
        }

        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArrayName = new JSONArray();
        for (int i = 0; i < total.length; i++) {
            Meitifenbu meitifenbu = new Meitifenbu();
            if (meitiValue.split(",", -1)[i].equals("1")) {
                meitifenbu.setValue(total[i]);
                meitifenbu.setName("新闻");
                jsonArrayName.add("新闻");
            } else if (meitiValue.split(",", -1)[i].equals("2")) {
                meitifenbu.setValue(total[i]);
                meitifenbu.setName("论坛");
                jsonArrayName.add("论坛");
            } else if (meitiValue.split(",", -1)[i].equals("3")) {
                meitifenbu.setValue(total[i]);
                meitifenbu.setName("微博");
                jsonArrayName.add("微博");
            } else if (meitiValue.split(",", -1)[i].equals("4")) {
                meitifenbu.setValue(total[i]);
                meitifenbu.setName("博客");
                jsonArrayName.add("博客");
            } else if (meitiValue.split(",", -1)[i].equals("5")) {
                meitifenbu.setValue(total[i]);
                meitifenbu.setName("报刊");
                jsonArrayName.add("报刊");
            } else if (meitiValue.split(",", -1)[i].equals("6")) {
                meitifenbu.setValue(total[i]);
                meitifenbu.setName("twitter");
                jsonArrayName.add("twitter");
            } else if (meitiValue.split(",", -1)[i].equals("7")) {
                meitifenbu.setValue(total[i]);
                meitifenbu.setName("微信");
                jsonArrayName.add("微信");
            }

            jsonArray.add(meitifenbu);
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("analysis/meitifenbu");
        mv.addObject("meitifenbu", jsonArray.toJSONString().replaceAll("\"", "'").replaceAll("'value'", "value").replaceAll("'name'", "name"));
        mv.addObject("meitifenbuName", jsonArrayName.toJSONString().replaceAll("\"", "'"));
        return mv;
    }

    /**
     * 网站排行
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/wangzhanpaihang")
    public ModelAndView wangzhanpaihang(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("analysis/wangzhanpaihang");

        String quyuNameValue = request.getParameter("quyuNameValue");
        String meitiValue = request.getParameter("meitiValue");
        String mokuaiValue = request.getParameter("mokuaiValue");
        String zhengwufenleiValue = request.getParameter("zhengwufenleiValue");
        String dingzhifenleiValue = request.getParameter("dingzhifenleiValue");
        String stime = request.getParameter("stime");
        String etime = request.getParameter("etime");
        String quyuValue = request.getParameter("quyuValue");

        User user = (User) request.getSession().getAttribute("userfront");

        // 行业用户
        String quyuNameValueArray[] = quyuNameValue.split(",", -1);
        String zhengwufenleiValueArray[] = zhengwufenleiValue.split(",", -1);
        String dingzhifenleiValueArray[] = dingzhifenleiValue.split(",", -1);
        String quyuValueArray[] = quyuValue.split(",", -1);

        if (!user.getUserType().equals("2")) {
            quyuNameValueArray = null;
            mokuaiValue = "1";
            zhengwufenleiValueArray = null;
            quyuValueArray = null;
        }

        // 1 定制 2政务
        if (mokuaiValue.indexOf("1") < 0) {
            dingzhifenleiValueArray = null;
        }
        if (mokuaiValue.indexOf("2") < 0) {
            zhengwufenleiValueArray = null;
        }
        SearchUtil su = new SearchUtil();
        String result = "";
        // userId,module,region[],time[],type[],event[],subject[]
        Query bq = su.getQuery(user.getUserId().toString(), mokuaiValue.split(",", -1), quyuValueArray, stime, etime, meitiValue.split(",", -1), zhengwufenleiValueArray, dingzhifenleiValueArray);
        try {
            result = su.webSiteCount(bq, user.getUserId().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.println(result.split("&", -1)[0]);
        // System.out.println(result.split("&", -1)[1]);
        mv.addObject("name", "[" + result.split("&", -1)[0] + "]");
        mv.addObject("value", "[" + result.split("&", -1)[1] + "]");
        return mv;
    }

    /**
     * 情感分析
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws CorruptIndexException
     */
    @RequestMapping(value = "/qingganfenxi")
    public ModelAndView qingganfenxi(HttpServletRequest request, HttpServletResponse response) throws CorruptIndexException, IOException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("analysis/qingganfenxi");

        String quyuNameValue = request.getParameter("quyuNameValue");
        String meitiValue = request.getParameter("meitiValue");
        String mokuaiValue = request.getParameter("mokuaiValue");
        String zhengwufenleiValue = request.getParameter("zhengwufenleiValue");
        String dingzhifenleiValue = request.getParameter("dingzhifenleiValue");
        String stime = request.getParameter("stime");
        String etime = request.getParameter("etime");
        String quyuValue = request.getParameter("quyuValue");
        String dateString = this.daysBetween(stime, etime);
        String[] day = dateString.split(",", -1);
        User user = (User) request.getSession().getAttribute("userfront");

        // 行业用户
        String quyuNameValueArray[] = quyuNameValue.split(",", -1);
        String zhengwufenleiValueArray[] = zhengwufenleiValue.split(",", -1);
        String dingzhifenleiValueArray[] = dingzhifenleiValue.split(",", -1);
        String quyuValueArray[] = quyuValue.split(",", -1);

        if (!user.getUserType().equals("2")) {
            quyuNameValueArray = null;
            mokuaiValue = "1";
            zhengwufenleiValueArray = null;
            quyuValueArray = null;
        }

        // 1 定制 2政务
        if (mokuaiValue.indexOf("1") < 0) {
            dingzhifenleiValueArray = null;
        }
        if (mokuaiValue.indexOf("2") < 0) {
            zhengwufenleiValueArray = null;
        }

        SearchUtil su = new SearchUtil();
        Map<String, Integer> map = new HashMap<String, Integer>();
        // userId,module,region[],startTime,endTime,type[],event[],subject[]
        int zhengmian = 0;
        int fumian = 0;
        int zhengyi = 0;
        for (int i = 0; i < mokuaiValue.split(",", -1).length; i++) {
            if (mokuaiValue.split(",", -1)[i].equals("2")) {
                map = su.emotionCount(user.getUserId().toString(), mokuaiValue.split(",", -1)[i], quyuValueArray, stime, etime, meitiValue.split(",", -1), zhengwufenleiValueArray, dingzhifenleiValueArray);
            } else {
                map = su.emotionCount(user.getUserId().toString(), mokuaiValue.split(",", -1)[i], null, stime, etime, meitiValue.split(",", -1), zhengwufenleiValueArray, dingzhifenleiValueArray);
            }

            zhengmian = zhengmian + Integer.parseInt(map.get("1").toString());
            fumian = fumian + Integer.parseInt(map.get("-1").toString());
            zhengyi = zhengyi + Integer.parseInt(map.get("0").toString());
        }
        JSONArray jsonArray = new JSONArray();
        Meitifenbu meitifenbu1 = new Meitifenbu();
        meitifenbu1.setName("负面");
        meitifenbu1.setValue(fumian);
        jsonArray.add(meitifenbu1);

        Meitifenbu meitifenbu2 = new Meitifenbu();
        meitifenbu2.setName("争议");
        meitifenbu2.setValue(zhengyi);
        jsonArray.add(meitifenbu2);

        Meitifenbu meitifenbu3 = new Meitifenbu();
        meitifenbu3.setName("正面");
        meitifenbu3.setValue(zhengmian);
        jsonArray.add(meitifenbu3);

        mv.addObject("zhengfumian", jsonArray.toString());

        return mv;
    }

    /**
     * 情感分析
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws CorruptIndexException
     */
    @RequestMapping(value = "/qingganfenxi2")
    public ModelAndView qingganfenxi2(HttpServletRequest request, HttpServletResponse response) throws CorruptIndexException, IOException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("analysis/qingganfenxi2");

        String quyuNameValue = request.getParameter("quyuNameValue");
        String meitiValue = request.getParameter("meitiValue");
        String mokuaiValue = request.getParameter("mokuaiValue");
        String zhengwufenleiValue = request.getParameter("zhengwufenleiValue");
        String dingzhifenleiValue = request.getParameter("dingzhifenleiValue");
        String stime = request.getParameter("stime");
        String etime = request.getParameter("etime");
        String quyuValue = request.getParameter("quyuValue");
        String dateString = this.daysBetween(stime, etime);
        String[] day = dateString.split(",", -1);
        User user = (User) request.getSession().getAttribute("userfront");

        // 行业用户
        String quyuNameValueArray[] = quyuNameValue.split(",", -1);
        String zhengwufenleiValueArray[] = zhengwufenleiValue.split(",", -1);
        String dingzhifenleiValueArray[] = dingzhifenleiValue.split(",", -1);
        String quyuValueArray[] = quyuValue.split(",", -1);

        if (!user.getUserType().equals("2")) {
            quyuNameValueArray = null;
            mokuaiValue = "1";
            zhengwufenleiValueArray = null;
            quyuValueArray = null;
        }

        // 1 定制 2政务
        if (mokuaiValue.indexOf("1") < 0) {
            dingzhifenleiValueArray = null;
        }
        if (mokuaiValue.indexOf("2") < 0) {
            zhengwufenleiValueArray = null;
        }

        Map<String, Integer> map = new HashMap<String, Integer>();
        // userId,module,region[],startTime,endTime,type,event[],subject[]
        int zString[] = new int[7];
        int fString[] = new int[7];
        int zhengyiString[] = new int[7];
        String name = "";
        for (int j = 0; j < meitiValue.split(",", -1).length; j++) {
            if ("1".equals(meitiValue.split(",", -1)[j])) {
                if ("".equals(name)) {
                    name = "'新闻'";
                } else {
                    name = name + ",'新闻'";
                }
            } else if ("2".equals(meitiValue.split(",", -1)[j])) {
                if ("".equals(name)) {
                    name = "'论坛'";
                } else {
                    name = name + ",'论坛'";
                }
            } else if ("3".equals(meitiValue.split(",", -1)[j])) {
                if ("".equals(name)) {
                    name = "'微博'";
                } else {
                    name = name + ",'微博'";
                }
            } else if ("4".equals(meitiValue.split(",", -1)[j])) {
                if ("".equals(name)) {
                    name = "'博客'";
                } else {
                    name = name + ",'博客'";

                }
            } else if ("5".equals(meitiValue.split(",", -1)[j])) {
                if ("".equals(name)) {
                    name = "'报刊'";
                } else {
                    name = name + ",'报刊'";
                }
            } else if ("6".equals(meitiValue.split(",", -1)[j])) {
                if ("".equals(name)) {
                    name = "'twitter'";
                } else {
                    name = name + ",'twitter'";
                }
            } else if ("7".equals(meitiValue.split(",", -1)[j])) {
                if ("".equals(name)) {
                    name = "'微信'";
                } else {
                    name = name + ",'微信'";
                }
            }
        }

        SearchUtil su = new SearchUtil();

        for (int i = 0; i < mokuaiValue.split(",", -1).length; i++) {
            if (mokuaiValue.split(",", -1)[i].equals("2")) {
                for (int j = 0; j < meitiValue.split(",", -1).length; j++) {
                    map = su.emotionMediaCount(user.getUserId().toString(), mokuaiValue.split(",", -1)[i], quyuValueArray, stime, etime, meitiValue.split(",", -1)[j], zhengwufenleiValueArray, dingzhifenleiValue.split(",", -1));

                    zString[j] += Integer.valueOf(map.get("1"));
                    fString[j] += Integer.valueOf(map.get("-1"));
                    zhengyiString[j] += Integer.valueOf(map.get("0"));
                }
            } else {
                for (int j = 0; j < meitiValue.split(",", -1).length; j++) {
                    map = su.emotionMediaCount(user.getUserId().toString(), mokuaiValue.split(",", -1)[i], null, stime, etime, meitiValue.split(",", -1)[j], zhengwufenleiValueArray, dingzhifenleiValue.split(",", -1));
                    zString[j] += Integer.valueOf(map.get("1"));
                    fString[j] += Integer.valueOf(map.get("-1"));
                    zhengyiString[j] += Integer.valueOf(map.get("0"));
                }
            }

        }

        mv.addObject("Name", "[" + name + "]");
        mv.addObject("zString", "[" + converToString(zString).toString() + "]");
        mv.addObject("fString", "[" + converToString(fString).toString() + "]");
        mv.addObject("zhengyiString", "[" + converToString(zhengyiString).toString() + "]");
        return mv;
    }

    /**
     * 获取热点分类词
     *
     * @param request
     * @param response
     * @return
     * @throws CorruptIndexException
     * @throws IOException
     */
    public List gethotClassification(HttpServletRequest request, HttpServletResponse response) throws CorruptIndexException, IOException {
        ModelAndView mv = new ModelAndView();
        User user = (User) request.getSession().getAttribute("userfront");

        List<SubjectKeywords> list = this.subjectKeywordsService.selectSubjectListByUserId(user.getUserId());

        // 行业词库读取
        Set eventSet = readTxtFile("/home/yj_event.txt");

        Set<String> qxxSet = new HashSet();// 用户倾向性set
        Set<String> mainSet = new HashSet();// 用户主体set

        Set<String> allSet = new HashSet();
        if (list.size() > 0) {
            for (SubjectKeywords s : list) {
                String name = s.getName();
                if (name.equals("main")) {
                    String sk = s.getKeywords();
                    String skArray[] = sk.split(" +");
                    for (String main : skArray) {
                        mainSet.add(main);
                    }
                }
                if (name.equals("qxx")) {
                    String qx = s.getKeywords();
                    String qxxArray[] = qx.split(" +");
                    for (String qxx : qxxArray) {
                        qxxSet.add(qxx);
                    }

                }

            }

            allSet.addAll(qxxSet);
            allSet.addAll(mainSet);
            allSet.addAll(eventSet);
        }
        SearchUtil su = new SearchUtil();
        Map map = su.hotspot(user.getUserId().toString());// key:word
                                                          // value:tf*idf

        // 产生热点词的集合 allSet
        // 遍历allSet
        Map termMap = new HashMap();
        for (String word : allSet) {
            if (map.containsKey(word)) {
                if (qxxSet.contains(word)) {
                    termMap.put(word, (Double) map.get(word) * 0.8);
                } else if (eventSet.contains(word)) {
                    termMap.put(word, (Double) map.get(word) * 0.6);
                } else if (mainSet.contains(word)) {
                    termMap.put(word, (Double) map.get(word) * 0.5);
                }
            }
        }
        // 对termMap 排序
        List<Map.Entry<String, Double>> m = sortMap(termMap);
        // printMap(m);
        return m;

    }

    public static String converToString(int[] ig) {
        String str = "";
        if (ig != null && ig.length > 0) {
            for (int i = 0; i < ig.length; i++) {
                str += ig[i] + ",";
            }
        }
        str = str.substring(0, str.length() - 1);
        return str;
    }

    public static List sortMap(Map oldMap) {
        List<Map.Entry<String, Double>> list_Data = new ArrayList<Map.Entry<String, Double>>(oldMap.entrySet());
        Collections.sort(list_Data, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                if ((o2.getValue() - o1.getValue()) > 0) {
                    return 1;
                } else if ((o2.getValue() - o1.getValue()) == 0) {
                    return 0;
                } else {
                    return -1;
                }
            }

        });
        return list_Data;
    }

    private static void printMap(List list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));

        }
    }

    /**
     * 功能：Java读取txt文件的内容 4：一行一行的输出。readline()。 备注：需要考虑的是异常情况
     *
     * @param filePath
     */
    public static Set readTxtFile(String filePath) {
        try {
            Set set = new HashSet();
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    set.add(lineTxt.replaceAll(",", "").replaceAll(" +", ""));
                }
                read.close();
                return set;
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 热点分析跳转
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/toHotAnalysis")
    public ModelAndView toHotAnalysis(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("analysis/hotanalysis");
        return mv;
    }

    /**
     * 热点分类
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/hotClassification")
    public ModelAndView hotClassification(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        JSONArray nodes = new JSONArray();
        // List<Node> nodes = new ArrayList<Node>();
        Node first = new Node();
        first.setName("热点词");
        first.setValue("50");
        first.setId("0");
        first.setDepth("0");
        // first.setCategory(2);
        nodes.add(first);
        JSONArray links = new JSONArray();
        try {
            List<Map.Entry<String, Double>> m = this.gethotClassification(request, response);
            int count = m.size();
            int insert;
            if (count > 10) {
                insert = 10;
            } else {
                insert = count;
            }
            // 前十个
            for (int i = 0; i < insert; i++) {
                Node n = new Node();
                n.setName(m.get(i).getKey());
                // n.setName(String.valueOf(i+1));
                n.setValue("10");
                n.setId(String.valueOf(i + 1));
                // n.setDepth("1");
                // n.setCategory(1);
                nodes.add(n);
                // Node d = createRandomNode(nodes, "1", "24");
                links.add(new Link("0", n.getId(), "5"));
            }
            int outside;
            // 后20个
            if (count > 10) {
                if (count >= 29) {
                    outside = 30;
                } else {
                    outside = count;
                }
                for (int j = 10; j < outside; j++) {
                    // for (int k = 1; k <= 5; k++) {
                    // Node n = createRandomNode(nodes, "2", "2");
                    // links.add(new Link(String.valueOf(j), n.getId(), "1"));
                    // }
                    Node n = new Node();
                    n.setName(m.get(j).getKey());
                    // n.setName(String.valueOf(j+1));
                    n.setValue("10");
                    n.setId(String.valueOf(j + 1));
                    n.setDepth("8");
                    // n.setCategory(0);
                    nodes.add(n);
                    // Node d = createRandomNode(nodes, "1", "24");
                    links.add(new Link("0", n.getId(), "1"));
                }
            }
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mv.addObject("nodes", nodes.toJSONString());
        mv.addObject("links", links.toJSONString());
        mv.setViewName("analysis/hotClassification");
        return mv;
    }

    private Node createRandomNode(JSONArray nodes, String depth, String val) {
        Node n = new Node();
        n.setName("node_" + depth);
        n.setValue(val);
        n.setId(String.valueOf(nodes.size()));
        n.setDepth(depth);
        nodes.add(n);
        return n;
    }

    // 热词分析
    @RequestMapping(value = "/hotWordAnalysis")
    public ModelAndView hotWordAnalysis(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        JSONArray nodes = new JSONArray();
        Node first = new Node();
        first.setName("定制舆情");
        first.setValue("50");
        first.setId("0");
        first.setDepth("0");
        nodes.add(first);
        JSONArray links = new JSONArray();
        for (int i = 1; i <= 5; i++) {
            Node d = this.createRandomNode(nodes, "1", "24");
            links.add(new Link("0", d.getId(), "1"));
        }
        mv.addObject("nodes", nodes.toJSONString());
        mv.addObject("links", links.toJSONString());
        mv.setViewName("analysis/hotWordAnalysis");
        return mv;
    }

    /**
     * 地域热词
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/areaHotWordAnalysis")
    public ModelAndView areaHotWordAnalysis(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        User user = (User) request.getSession().getAttribute("userfront");
        UserRegion ur = new UserRegion();
        if ("2".equals(user.getUserType())) {// 政府用户
            // 获取用户所在地域
            ur = this.userRegionService.selectByUserId(user.getUserId());
            Region region = this.regionService.selectByPrimaryKey(ur.getRegionId());
            // 添加页面要显示的地域名

            if (String.valueOf(ur.getRegionId()).length() == 2) {
                mv.addObject("area", region.getRegionabbr());
                mv.setViewName("analysis/areaHotWordAnalysis");// 省级用户
            } else if (String.valueOf(ur.getRegionId()).length() == 4) {// 市级用户
                mv.setViewName("analysis/areaHotWordAnalysisByCountry");
                mv.addObject("area", region.getRegionname());
            } else if (String.valueOf(ur.getRegionId()).length() == 6) {
                ur.setRegionId(Integer.parseInt(String.valueOf(ur.getRegionId()).substring(0, 4)));
                mv.setViewName("analysis/areaHotWordAnalysisByCountry");
                region = this.regionService.selectByPrimaryKey(ur.getRegionId());
                mv.addObject("area", region.getRegionname());
            }
        } else {// 企业用户
            mv.addObject("area", "china");
            mv.setViewName("analysis/areaHotWordAnalysis");
        }
        // 获取下级地域，并查询数量
        List<Region> reginIds;
        if ("2".equals(user.getUserType())) {
            reginIds = this.regionService.selectByParentId(ur.getRegionId());
            this.getcityValue(mv, user.getUserId(), reginIds);
        } else {
            reginIds = this.regionService.selectByParentId(-1);
            this.getProvinceValue(mv, user.getUserId(), reginIds);
        }
        // mv.setViewName("analysis/areaHotWordAnalysisByCountry");
        return mv;
    }

    /**
     * 获取市级或者县级数据
     *
     * @param mv
     * @param map
     * @param su
     */
    private void getcityValue(ModelAndView mv, Integer userId, List<Region> reginIds) {
        Region ids[] = new Region[reginIds.size()];
        for (int i = 0; i < reginIds.size(); i++) {
            ids[i] = reginIds.get(i);
        }

        SearchUtil su = new SearchUtil();
        try {
            // 添加页面要展示的数据
            Map<String, Integer> map = su.regionCount(String.valueOf(userId), ids);
            mv.addObject("map", map);
            Collection<String> c = map.keySet();
            List<String> list = new ArrayList<String>();
            for (Iterator it = c.iterator(); it.hasNext();) {
                String s = (String) it.next();
                list.add(s);
            }
            mv.addObject("arealist", list);
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取省级数据
     *
     * @param mv
     * @param userId
     * @param reginIds
     */
    private void getProvinceValue(ModelAndView mv, Integer userId, List<Region> reginIds) {
        Region ids[] = new Region[reginIds.size()];
        for (int i = 0; i < reginIds.size(); i++) {
            ids[i] = reginIds.get(i);
        }

        SearchUtil su = new SearchUtil();
        try {
            // 添加页面要展示的数据
            Map<String, Integer> map = su.regionCount(String.valueOf(userId), ids);
            Map<String, Integer> newmap = new HashMap<String, Integer>();

            Collection<String> c = map.keySet();
            List<String> list = new ArrayList<String>();
            for (Iterator it = c.iterator(); it.hasNext();) {
                String s = (String) it.next();
                list.add(s.replace("省", "").replace("市", "").replace("回族自治区", "").replace("自治区", ""));
                newmap.put(s.replace("省", "").replace("市", "").replace("回族自治区", "").replace("自治区", ""), map.get(s));
            }
            mv.addObject("arealist", list);
            mv.addObject("map", newmap);
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 地域热词
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/words")
    public ModelAndView words(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("analysis/words");
        return mv;
    }

    /**
     * 地域热词
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/wordsOne")
    public ModelAndView wordsOne(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("analysis/wordsOne");
        return mv;
    }
}
