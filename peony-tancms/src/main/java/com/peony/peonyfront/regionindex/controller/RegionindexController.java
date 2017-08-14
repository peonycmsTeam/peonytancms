package com.peony.peonyfront.regionindex.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.RegEx;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.peony.core.common.utils.CalendarUtil;
import com.peony.core.common.utils.DateUtils;
import com.peony.core.common.utils.JsonUtil;
import com.peony.core.common.utils.tedis.TedisUtil;
import com.peony.peonyfront.homepage.controller.model.Statistics;
import com.peony.peonyfront.industryindex.model.IndustryIndex;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.region.model.Region;
import com.peony.peonyfront.region.service.RegionService;
import com.peony.peonyfront.regionindex.model.Regionindex;
import com.peony.peonyfront.regionindex.service.RegionindexService;
import com.peony.peonyfront.userregion.model.UserRegion;
import com.peony.peonyfront.userregion.service.UserRegionService;

/**
 * 省内城市指数排行
 * 
 * @author vv
 * 
 */
@Controller
@RequestMapping("/regionindex")
public class RegionindexController {
    @Autowired
    private RegionindexService regionindexservice;
    @Autowired
    private TedisUtil          tedisUtil;
    @Autowired
    private UserRegionService  userregionservice;
    @Autowired
    private RegionService      regionservice;

    @RequestMapping("listRegionIndex")
    public ModelAndView listCount(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        User user = (User) request.getSession().getAttribute("userfront");
        int userid = user.getUserId();
        UserRegion userregion = this.userregionservice.selectByUserId(userid);
        int regionid = userregion.getRegionId();
        Calendar cal = Calendar.getInstance();
        int m = cal.get(Calendar.MONTH) + 1;
        String month = m + "";
        // 将参数传入Map集合
        Map map = new HashMap();
        map.put("date", month);
        map.put("regionid", regionid);
        Regionindex ri = new Regionindex();
        ri.setMonth(month);
        ri.setRegionid(regionid);
        // 从后台获取需要的数据
        String type = request.getParameter("type");
        String type1 = type + "";
        List<Regionindex> reList = new ArrayList<Regionindex>();
        if (type1.equals("3")) {
            reList = this.regionindexservice.countSelectByCount(ri);
        } else if (type1.equals("1")) {
            reList = this.regionindexservice.countSelectByCountZhou(ri);
        } else if (type1.equals("2")) {
            reList = this.regionindexservice.countSelectByCountYue(ri);
        } else {
            reList = this.regionindexservice.countSelectByCount(ri);
        }
        int t = reList.size();
        // 创建数组 将遍历完的数据 依次放入
        String[] regionname = new String[t];
        float[] heatindex = new float[t];
        float[] sensitiveindex = new float[t];
        float[] focusindex = new float[t];
        float[] compositeindex = new float[t];
        // 开始遍历数据
        for (int i = 0; i < reList.size(); i++) {
            Regionindex regionindex = reList.get(i);
            regionname[i] = regionindex.getRegionname();
            heatindex[i] = regionindex.getHeatindex();
            sensitiveindex[i] = regionindex.getSensitiveindex();
            focusindex[i] = regionindex.getFocusindex();
            compositeindex[i] = regionindex.getCompositeindex();
        }
        // 将数据添加到MV中 在页面中显示出来
        mv.addObject("regionname", Arrays.toString(regionname).replace("[", "['").replace("]", "']").replace(",", "','").replace(" ", ""));
        mv.addObject("heatindex", Arrays.toString(heatindex));
        mv.addObject("sensitiveindex", Arrays.toString(sensitiveindex));
        mv.addObject("focusindex", Arrays.toString(focusindex));
        mv.addObject("compositeindex", Arrays.toString(compositeindex));
        mv.setViewName("regionindex/list_regionindex");
        return mv;
    }

    /**
     * 全省舆情走势
     * 
     * @param request
     * @return
     */
    @RequestMapping("listTendency")
    public ModelAndView listTendency(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        User user = (User) request.getSession().getAttribute("userfront");
        int userid = user.getUserId();
        UserRegion userregion = this.userregionservice.selectByUserId(userid);
        int regionid = userregion.getRegionId();
        Calendar cal = Calendar.getInstance();
        int m = cal.get(Calendar.MONTH) + 1;
        String month1 = m + "";
        // 将参数传入Map集合
        Map map = new HashMap();
        map.put("date", m);
        map.put("regionid", regionid);
        Regionindex re = new Regionindex();
        re.setMonth(month1);
        re.setRegionid(regionid);
        String type = request.getParameter("type");
        String type1 = type + "";
        List<Regionindex> reList = new ArrayList<Regionindex>();
        if (type1.equals("3")) {
            reList = this.regionindexservice.tendencySelectByCount(re);
        } else if (type1.equals("1")) {
            reList = this.regionindexservice.tendencySelectByCountZhou(re);
        } else if (type1.equals("2")) {
            reList = this.regionindexservice.tendencySelectByCountYue(re);
        } else {
            reList = this.regionindexservice.tendencySelectByCount(re);
        }
        int s = reList.size();
        String[] month = new String[s];
        float[] compositeindex = new float[s];
        // 开始遍历从数据库获取的数据
        for (int i = 0; i < reList.size(); i++) {
            Regionindex regionindex = reList.get(i);
            month[i] = regionindex.getDate();
            compositeindex[i] = (float) (Math.round(regionindex.getCompositeindex() * 100)) / 100;
        }
        mv.addObject("month", Arrays.toString(month).replace("[", "['").replace("]", "']").replace(",", "','").replace(" ", ""));
        mv.addObject("compositeindex", Arrays.toString(compositeindex));
        mv.setViewName("regionindex/list_tendency");
        return mv;
    }

    /**
     * 省内城市舆情增长率排行
     * 
     */
    @RequestMapping("listGrowthRate")
    public ModelAndView listGrowthRate(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        User user = (User) request.getSession().getAttribute("userfront");
        int userid = user.getUserId();
        UserRegion userregion = this.userregionservice.selectByUserId(userid);
        int regionid = userregion.getRegionId();
        Date endDate = new Date(); // 当前时间
        SimpleDateFormat stimeformat = new SimpleDateFormat("yyyy-MM-dd");
        String etime = stimeformat.format(endDate);
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH, -6);// 统计7天数据
        String stime = stimeformat.format(calender.getTime());
        Calendar calender2 = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH, -6);// 统计7天数据
        String stime1 = stimeformat.format(calender.getTime());
        // 找到两个时间间隔
        Map map3 = new HashMap();
        map3.put("beginTime", stime);
        map3.put("endTime", stime1);
        map3.put("regionid", regionid);
        Regionindex rd = new Regionindex();
        rd.setBeginTime(stime);
        rd.setEndTime(stime1);
        rd.setRegionid(regionid);
        Calendar cal = Calendar.getInstance();
        int m = cal.get(Calendar.MONTH) + 1;
        String month1 = m + "";
        String month2 = m - 1 + "";
        String month3 = m - 2 + "";
        // 将参数传入Map集合
        Map map = new HashMap();
        map.put("date", m);
        map.put("regionid", regionid);
        Regionindex re = new Regionindex();
        re.setMonth(month1);
        re.setRegionid(regionid);
        Regionindex rs = new Regionindex();
        rs.setMonth(month2);
        rs.setRegionid(regionid);
        Regionindex ra = new Regionindex();
        ra.setMonth(month3);
        ra.setRegionid(regionid);
        String type = request.getParameter("type");
        String type1 = type + "";
        List<Regionindex> reList = new ArrayList<Regionindex>();
        if (type1.equals("3")) {
            reList = this.regionindexservice.GrowthRateSelectByCount(re);
        } else if (type1.equals("1")) {
            reList = this.regionindexservice.GrowthRateSelectByCountZhou(re);
        } else if (type1.equals("2")) {
            reList = this.regionindexservice.GrowthRateSelectByCountYue(rs);
        } else {
            reList = this.regionindexservice.GrowthRateSelectByCount(re);
        }
        List<Regionindex> rrList = new ArrayList<Regionindex>();
        if (type1.equals("3")) {
            rrList = this.regionindexservice.GrowthRateSelectByCounts(rs);
        } else if (type1.equals("1")) {
            rrList = this.regionindexservice.GrowthRateSelectByCountsZhou(rd);
        } else if (type1.equals("2")) {
            rrList = this.regionindexservice.GrowthRateSelectByCountsYue(ra);
        } else {
            rrList = this.regionindexservice.GrowthRateSelectByCounts(rs);
        }
        // int s = reList.size();
        // int v= rrList.size();
        int s = reList.size() > rrList.size() ? rrList.size() : rrList.size();
        float[] compositeindex = new float[s];
        String[] regionname = new String[s];
        float[] compositeindexs = new float[s];
        float[] growthrate = new float[s];
        // 开始遍历从数据库获取的数据
        for (int i = 0; i < s; i++) {
            Regionindex regionindex = reList.get(i);
            regionname[i] = regionindex.getRegionname();
            compositeindex[i] = regionindex.getCompositeindex();
        }
        for (int j = 0; j < s; j++) {
            Regionindex regionindex = rrList.get(j);
            compositeindexs[j] = regionindex.getCompositeindex();
        }
        for (int t = 0; t < s; t++) {
            growthrate[t] = (compositeindex[t] - compositeindexs[t]) / compositeindexs[t] * 100;
        }
        ;
        mv.addObject("regionname", Arrays.toString(regionname).replace("[", "['").replace("]", "']").replace(",", "','").replace(" ", ""));
        mv.addObject("compositeindex", Arrays.toString(compositeindex));
        mv.addObject("compositeindexs", Arrays.toString(compositeindexs));
        mv.addObject("growthrate", Arrays.toString(growthrate));
        mv.setViewName("regionindex/list_growthrate");
        return mv;
    }

    /**
     * 全国指数排行
     * 
     * @param request
     * @return
     */
    @RequestMapping("listRegionChina")
    public ModelAndView listChina(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Calendar cal = Calendar.getInstance();
        int m = cal.get(Calendar.MONTH) + 1;
        String month = m + "";
        // 将参数传入Map集合
        Map map = new HashMap();
        map.put("date", month);
        Regionindex ri = new Regionindex();
        ri.setMonth(month);
        // 从后台获取需要的数据
        String type = request.getParameter("type");
        String type1 = type + "";
        List<Regionindex> reList = new ArrayList<Regionindex>();
        if (type1.equals("3")) {
            reList = this.regionindexservice.RegionChinaSelectByCont(ri);
        } else if (type1.equals("1")) {
            reList = this.regionindexservice.RegionChinaSelectByContZhou(ri);
        } else if (type1.equals("2")) {
            reList = this.regionindexservice.RegionChinaSelectByContYue(ri);
        } else {
            reList = this.regionindexservice.RegionChinaSelectByCont(ri);
        }
        int t = reList.size();
        // 创建数组 将遍历完的数据 依次放入
        String[] regionname = new String[t];
        float[] heatindex = new float[t];
        float[] sensitiveindex = new float[t];
        float[] focusindex = new float[t];
        float[] compositeindex = new float[t];
        // 开始遍历数据
        for (int i = 0; i < reList.size(); i++) {
            Regionindex regionindex = reList.get(i);
            regionname[i] = regionindex.getRegionname();
            heatindex[i] = regionindex.getHeatindex();
            sensitiveindex[i] = regionindex.getSensitiveindex();
            focusindex[i] = regionindex.getFocusindex();
            compositeindex[i] = regionindex.getCompositeindex();
        }
        // 将数据添加到MV中 在页面中显示出来
        mv.addObject("regionname", Arrays.toString(regionname).replace("[", "['").replace("]", "']").replace(",", "','").replace(" ", ""));
        mv.addObject("heatindex", Arrays.toString(heatindex));
        mv.addObject("sensitiveindex", Arrays.toString(sensitiveindex));
        mv.addObject("focusindex", Arrays.toString(focusindex));
        mv.addObject("compositeindex", Arrays.toString(compositeindex));
        mv.setViewName("regionindex/list_regionchina");
        return mv;
    }

    /**
     * 全国城市舆情增长率排行
     * 
     */
    @RequestMapping("listGrowthRateChina")
    public ModelAndView listGrowthRateChina(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Date endDate = new Date(); // 当前时间
        SimpleDateFormat stimeformat = new SimpleDateFormat("yyyy-MM-dd");
        String etime = stimeformat.format(endDate);
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH, -6);// 统计7天数据
        String stime = stimeformat.format(calender.getTime());
        Calendar calender2 = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH, -6);// 统计7天数据
        String stime1 = stimeformat.format(calender.getTime());
        // 找到要查询的开始时间与结束时间
        Map map3 = new HashMap();
        map3.put("beginTime", stime);
        map3.put("endTime", stime1);
        Regionindex rd = new Regionindex();
        rd.setBeginTime(stime);
        rd.setEndTime(stime1);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH) + 1;
        String month1 = m + "";
        String month2 = m - 1 + "";
        String month3 = m - 2 + " ";
        // 将参数传入Map集合
        Map map = new HashMap();
        map.put("date", m);
        Regionindex rs = new Regionindex();
        rs.setMonth(month2);
        Regionindex ra = new Regionindex();
        ra.setMonth(month3);
        String type = request.getParameter("type");
        String type1 = type + "";
        List<Regionindex> rrList = new ArrayList<Regionindex>();
        if (type1.equals("3")) {
            rrList = this.regionindexservice.GrowthRateChinaSelectByCounts(rs);
        } else if (type1.equals("1")) {
            rrList = this.regionindexservice.selectGrowthRatesChinaZhou(rd);
        } else if (type1.equals("2")) {
            rrList = this.regionindexservice.selectGrowthRatesChinaYue(ra);
        } else {
            rrList = this.regionindexservice.GrowthRateChinaSelectByCounts(rs);
        }
        Regionindex re = new Regionindex();
        re.setMonth(month1);
        List<Regionindex> reList = new ArrayList<Regionindex>();
        if (type1.equals("3")) {
            reList = this.regionindexservice.GrowthRateChinaSelectByCount(re);
        } else if (type1.equals("1")) {
            reList = this.regionindexservice.selectGrowthRateChinaZhou(re);
        } else if (type1.equals("2")) {
            reList = this.regionindexservice.selectGrowthRateChinaYue(rs);
        } else {
            reList = this.regionindexservice.GrowthRateChinaSelectByCount(re);
        }
        int s = reList.size() > rrList.size() ? rrList.size() : rrList.size();
        float[] compositeindex = new float[s];
        String[] regionname = new String[s];
        float[] compositeindexs = new float[s];
        float[] growthrate = new float[s];
        // 开始遍历从数据库获取的数据
        for (int i = 0; i < s; i++) {
            Regionindex regionindex = reList.get(i);
            regionname[i] = regionindex.getRegionname();
            compositeindex[i] = regionindex.getCompositeindex();
        }
        for (int j = 0; j < s; j++) {
            Regionindex regionindex = rrList.get(j);
            compositeindexs[j] = regionindex.getCompositeindex();
        }
        for (int t = 0; t < s; t++) {
            growthrate[t] = (compositeindex[t] - compositeindexs[t]) / compositeindexs[t] * 100;
        }
        ;
        mv.addObject("regionname", Arrays.toString(regionname).replace("[", "['").replace("]", "']").replace(",", "','").replace(" ", ""));
        mv.addObject("compositeindex", Arrays.toString(compositeindex));
        mv.addObject("compositeindexs", Arrays.toString(compositeindexs));
        mv.addObject("growthrate", Arrays.toString(growthrate));
        mv.setViewName("regionindex/list_growthratechina");
        return mv;
    }

    /**
     * 全国舆情分布图 根据综合指数查询
     */
    @RequestMapping("listChinaDistribute")
    public ModelAndView listChinaDistribute(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Calendar cal = Calendar.getInstance();
        int m = cal.get(Calendar.MONTH) + 1;
        String month1 = m + "";
        // 将参数传入Map集合
        Map map = new HashMap();
        map.put("date", m);
        Regionindex re = new Regionindex();
        re.setMonth(month1);
        String type = request.getParameter("type");
        String type1 = type + "";
        List<Regionindex> reList = new ArrayList<Regionindex>();
        if (type1.equals("3")) {
            reList = this.regionindexservice.selectChinaDistribute(re);
        } else if (type1.equals("1")) {
            reList = this.regionindexservice.selectChinaDistributeZhou(re);
        } else if (type1.equals("2")) {
            reList = this.regionindexservice.selectChinaDistributeYue(re);
        } else {
            reList = this.regionindexservice.selectChinaDistribute(re);
        }
        int s = reList.size();
        String[] regionname = new String[s];
        float[] compositeindex = new float[s];
        Map map2 = new HashMap();
        // 开始遍历从数据库获取的数据
        for (int i = 0; i < reList.size(); i++) {
            Regionindex regionindex = reList.get(i);
            regionname[i] = regionindex.getRegionname();
            compositeindex[i] = regionindex.getCompositeindex();
            map2.put("regionname" + i, regionname[i]);
            map2.put("compositeindex" + i, compositeindex[i]);
        }
        List<Regionindex> rsList = new ArrayList<Regionindex>();
        if (type1.equals("3")) {
            rsList = this.regionindexservice.selecttendencychina1(re);
        } else if (type1.equals("1")) {
            rsList = this.regionindexservice.selecttendencychinaZhou1(re);
        } else if (type1.equals("2")) {
            rsList = this.regionindexservice.selecttendencychinaYue1(re);
        } else {
            rsList = this.regionindexservice.selecttendencychina1(re);
        }
        int r = rsList.size();
        String[] regionnames = new String[r];
        float[] compositeindexs = new float[r];
        Map map3 = new HashMap();
        for (int t = 0; t < rsList.size(); t++) {
            Regionindex regionindex = rsList.get(t);
            regionnames[t] = regionindex.getRegionname();
            compositeindexs[t] = regionindex.getCompositeindex();
            map3.put("regionnames" + t, regionnames[t]);
            map3.put("compositeindexs" + t, compositeindexs[t]);
        }
        mv.addAllObjects(map3);
        mv.addAllObjects(map2);
        mv.setViewName("regionindex/list_chinadistribute");
        return mv;
    }

    /**
     * 全国舆情走势
     * 
     * @param request
     * @return
     */
    @RequestMapping("listTendencyChina")
    public ModelAndView listTendencyChina(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Calendar cal = Calendar.getInstance();
        int m = cal.get(Calendar.MONTH) + 1;
        String month = m + "";
        // 将参数传入Map集合
        Map map = new HashMap();
        map.put("date", m);
        Regionindex re = new Regionindex();
        re.setMonth(month);
        String type = request.getParameter("type");
        String type1 = type + "";
        List<Regionindex> reList = new ArrayList<Regionindex>();
        if (type1.equals("3")) {
            reList = this.regionindexservice.selecttendencychina(re);
        } else if (type1.equals("1")) {
            reList = this.regionindexservice.selecttendencychinaZhou(re);
        } else if (type1.equals("2")) {
            reList = this.regionindexservice.selecttendencychinaYue(re);
        } else {
            reList = this.regionindexservice.selecttendencychina(re);
        }
        int s = reList.size();
        String[] date1 = new String[s];
        float[] compositeindex = new float[s];
        // 开始遍历从数据库获取的数据
        for (int i = 0; i < reList.size(); i++) {
            Regionindex regionindex = reList.get(i);
            date1[i] = regionindex.getDate();
            compositeindex[i] = (float) (Math.round(regionindex.getCompositeindex() * 100)) / 100;
        }
        mv.addObject("date1", Arrays.toString(date1).replace("[", "['").replace("]", "']").replace(",", "','").replace(" ", ""));
        mv.addObject("compositeindex", Arrays.toString(compositeindex));
        mv.setViewName("regionindex/list_tendencychina");
        return mv;
    }

    /**
     * 全省舆情分布图 根据综合指数查询
     */
    @RequestMapping("listShengDistribute")
    public ModelAndView listShengDistribute(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        User user = (User) request.getSession().getAttribute("userfront");
        int userid = user.getUserId();
        UserRegion userregion = this.userregionservice.selectByUserId(userid);
        int regionid = userregion.getRegionId();
        List<Region> region = this.regionservice.selectByUserId(userid);
        int ss = region.size();
        String[] regionnamed = new String[ss];
        for (int w = 0; w < region.size(); w++) {
            Region regionn = region.get(w);
            regionnamed[w] = regionn.getRegionname();
        }
        Calendar cal = Calendar.getInstance();
        int m = cal.get(Calendar.MONTH) + 1;
        String month1 = m + "";
        // 将参数传入Map集合
        Map map = new HashMap();
        map.put("date", m);
        map.put("regionid", regionid);
        Regionindex re = new Regionindex();
        re.setMonth(month1);
        re.setRegionid(regionid);
        String type = request.getParameter("type");
        String type1 = type + "";
        List<Regionindex> reList = new ArrayList<Regionindex>();
        if (type1.equals("3")) {
            reList = this.regionindexservice.selectShengDistribute(re);
        } else if (type1.equals("1")) {
            reList = this.regionindexservice.selectShengDistributeZhou(re);
        } else if (type1.equals("2")) {
            reList = this.regionindexservice.selectShengDistributeYue(re);
        } else {
            reList = this.regionindexservice.selectShengDistribute(re);
        }
        int s = reList.size();
        String[] regionname = new String[s];
        float[] compositeindex = new float[s];

        Map map2 = new HashMap();
        Map map4 = new HashMap();
        // 开始遍历从数据库获取的数据
        for (int i = 0; i < reList.size(); i++) {
            Regionindex regionindex = reList.get(i);
            regionname[i] = regionindex.getRegionname();
            compositeindex[i] = regionindex.getCompositeindex();
            map2.put("regionname" + i, regionname[i]);
            map4.put(regionname[i], compositeindex[i]);
        }
        List<Regionindex> rsList = new ArrayList<Regionindex>();
        if (type1.equals("3")) {
            rsList = this.regionindexservice.selectShengDistribute1(re);
        } else if (type1.equals("1")) {
            rsList = this.regionindexservice.selectShengDistributeZhou1(re);
        } else if (type1.equals("2")) {
            rsList = this.regionindexservice.selectShengDistributeYue1(re);
        } else {
            rsList = this.regionindexservice.selectShengDistribute1(re);
        }
        int r = rsList.size();
        String[] regionnames = new String[r];
        float[] compositeindexs = new float[r];
        Map map3 = new HashMap();
        for (int t = 0; t < rsList.size(); t++) {
            Regionindex regionindex = rsList.get(t);
            regionnames[t] = regionindex.getRegionname();
            compositeindexs[t] = regionindex.getCompositeindex();
            map3.put("regionnames" + t, regionnames[t]);
            map3.put("compositeindexs" + t, compositeindexs[t]);
        }
        mv.addAllObjects(map3);
        mv.addObject("regionnamed", Arrays.toString(regionnamed).replace("[", " ").replace("省]", "").replace(",", " ").replace(" ", ""));
        mv.addObject("map2", map2);
        mv.addObject("map4", map4);
        mv.setViewName("regionindex/list_shengdistribute");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/listDrawing")
    public ModelAndView listWorkCount(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String type = request.getParameter("type");
        mv.addObject("type", type);
        mv.setViewName("regionindex/list_drawing");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/listDrawings")
    public ModelAndView listWorkCounts(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String type = request.getParameter("type");
        mv.addObject("type", type);
        mv.setViewName("regionindex/list_drawings");
        return mv;
    }
}
