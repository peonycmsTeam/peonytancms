package com.peony.peonyfront.industryindex.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.peony.peonyfront.industryindex.model.IndustryIndex;
import com.peony.peonyfront.industryindex.service.IndustryIndexService;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.region.model.Region;
import com.peony.peonyfront.regionindex.model.Regionindex;
import com.peony.peonyfront.userregion.model.UserRegion;
import com.peony.peonyfront.userregion.service.UserRegionService;

@Controller
@RequestMapping("industryindex")
public class IndustryIndexController {
    @Autowired
    private UserRegionService    userregionservice;
    @Autowired
    private IndustryIndexService industryindexservice;

    /**
     * 反应国各地市的舆情热点行业。以行业综合指数为排序依据。只显示前10的行业。
     * 
     * @param request
     * @return
     */
    @RequestMapping("listIndustryIndex")
    public ModelAndView listIndustryIndex(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Calendar cal = Calendar.getInstance();
        int m = cal.get(Calendar.MONTH) + 1;
        String month1 = m + "";
        // 将参数传入Map集合
        Map map = new HashMap();
        map.put("date", m);
        IndustryIndex re = new IndustryIndex();
        re.setMonth(month1);
        String type = request.getParameter("type");
        String type1 = type + "";
        List<IndustryIndex> reList = new ArrayList<IndustryIndex>();
        if (type1.equals("3")) {
            reList = this.industryindexservice.countSelectByCount(re);
        } else if (type1.equals("1")) {
            reList = this.industryindexservice.countSelectByCountZhou(re);
        } else if (type1.equals("2")) {
            reList = this.industryindexservice.countSelectByCountYue(re);
        } else {
            reList = this.industryindexservice.countSelectByCount(re);
        }
        int s = reList.size();
        String[] eventname = new String[s];
        float[] compositeindex = new float[s];
        // 开始遍历从数据库获取的数据
        for (int i = 0; i < reList.size(); i++) {
            IndustryIndex industryindex = reList.get(i);
            eventname[i] = industryindex.getEventname();
            compositeindex[i] = industryindex.getCompositeindex();
            float b = (float) (Math.round(compositeindex[i] * 100)) / 100;
            compositeindex[i] = b;
        }
        ;
        mv.addObject("eventname", Arrays.toString(eventname).replace("[", "['").replace("]", "']").replace(",", "','").replace(" ", ""));
        mv.addObject("compositeindex", Arrays.toString(compositeindex));
        mv.setViewName("industryindex/list_industryindex");
        return mv;
    }

    /**
     * 
     * 反应全国各行业舆情舆情的热度、敏感度、和民众关注度情况
     * 
     * @param request
     * @return
     */
    @RequestMapping("listDistribute")
    public ModelAndView listDistribute(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Calendar cal = Calendar.getInstance();
        int m = cal.get(Calendar.MONTH) + 1;
        String month = m + "";
        // 将参数传入Map集合
        Map map = new HashMap();
        map.put("date", month);

        IndustryIndex re = new IndustryIndex();
        re.setMonth(month);
        String type = request.getParameter("type");
        String type1 = type + "";
        List<IndustryIndex> reList = new ArrayList<IndustryIndex>();
        if (type1.equals("3")) {
            reList = this.industryindexservice.distributeSelectByCount(re);
        } else if (type1.equals("1")) {
            reList = this.industryindexservice.selectdistributeZhou(re);
        } else if (type1.equals("2")) {
            reList = this.industryindexservice.selectdistributeYue(re);
        } else {
            reList = this.industryindexservice.distributeSelectByCount(re);
        }
        int s = reList.size();
        String[] eventname = new String[s];
        float[] heatIndex = new float[s];
        float[] sensitiveIndex = new float[s];
        float[] focusIndex = new float[s];
        // 开始遍历从数据库获取的数据

        Map map2 = new HashMap();
        for (int i = 0; i < reList.size(); i++) {
            IndustryIndex industryindex = reList.get(i);
            eventname[i] = industryindex.getEventname();
            heatIndex[i] = industryindex.getHeatindex();
            sensitiveIndex[i] = industryindex.getSensitiveindex();
            focusIndex[i] = industryindex.getFocusindex();
            map2.put("eventname" + i, eventname[i]);
            map2.put("heatIndex" + i, heatIndex[i] * 3);
            map2.put("sensitiveIndex" + i, sensitiveIndex[i] * 3);
            map2.put("focusIndex" + i, focusIndex[i] * 3);

        }
        ;
        mv.addAllObjects(map2);
        mv.setViewName("industryindex/list_distribute");
        return mv;
    }

    /**
     * 反应省各地市的舆情热点行业。以行业综合指数为排序依据。只显示前10的行业。
     * 
     * @param request
     * @return
     */
    @RequestMapping("listIndustryCount")
    public ModelAndView listIndustryCount(HttpServletRequest request) {
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
        IndustryIndex re = new IndustryIndex();
        re.setMonth(month1);
        re.setRegionid(regionid);
        String type = request.getParameter("type");
        String type1 = type + "";
        List<IndustryIndex> reList = new ArrayList<IndustryIndex>();
        if (type1.equals("3")) {
            reList = this.industryindexservice.IndustryIndexSelectByCount(re);
        } else if (type1.equals("1")) {
            reList = this.industryindexservice.selectIndustryCountZhou(re);
        } else if (type1.equals("2")) {
            reList = this.industryindexservice.selectIndustryCountYue(re);
        } else {
            reList = this.industryindexservice.IndustryIndexSelectByCount(re);
        }
        int s = reList.size();
        String[] eventname = new String[s];
        float[] compositeindex = new float[s];
        float b;
        // 开始遍历从数据库获取的数据
        for (int i = 0; i < reList.size(); i++) {
            IndustryIndex industryindex = reList.get(i);
            eventname[i] = industryindex.getEventname();
            compositeindex[i] = industryindex.getCompositeindex();
            b = (float) (Math.round(compositeindex[i] * 100)) / 100;
            compositeindex[i] = b;
        }
        ;
        mv.addObject("eventname", Arrays.toString(eventname).replace("[", "['").replace("]", "']").replace(",", "','").replace(" ", ""));
        mv.addObject("compositeindex", Arrays.toString(compositeindex));
        mv.setViewName("industryindex/list_industrycount");
        return mv;
    }

    /**
     * 
     * 反应全省各行业舆情舆情的热度、敏感度、和民众关注度情况
     * 
     * @param request
     * @return
     */
    @RequestMapping("listDistributeSheng")
    public ModelAndView listDistributeSheng(HttpServletRequest request) {
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
        IndustryIndex re = new IndustryIndex();
        re.setMonth(month1);
        re.setRegionid(regionid);
        String type = request.getParameter("type");
        String type1 = type + "";
        List<IndustryIndex> reList = new ArrayList<IndustryIndex>();
        if (type1.equals("3")) {
            reList = this.industryindexservice.selectTdistribteSheng(re);
        } else if (type1.equals("1")) {
            reList = this.industryindexservice.selectTdistribteShengZhou(re);
        } else if (type1.equals("2")) {
            reList = this.industryindexservice.selectTdistribteShengYue(re);
        } else {
            reList = this.industryindexservice.selectTdistribteSheng(re);
        }
        int s = reList.size();
        String[] eventname = new String[s];
        float[] heatIndex = new float[s];
        float[] sensitiveIndex = new float[s];
        float[] focusIndex = new float[s];
        // 开始遍历从数据库获取的数据
        Map map2 = new HashMap();
        for (int i = 0; i < reList.size(); i++) {
            IndustryIndex industryindex = reList.get(i);
            eventname[i] = industryindex.getEventname();
            heatIndex[i] = industryindex.getHeatindex();
            sensitiveIndex[i] = industryindex.getSensitiveindex();
            focusIndex[i] = industryindex.getFocusindex();

            map2.put("eventname" + i, eventname[i]);
            map2.put("heatIndex" + i, heatIndex[i] * 18);
            map2.put("sensitiveIndex" + i, sensitiveIndex[i] * 18);
            map2.put("focusIndex" + i, focusIndex[i] * 18);

        }
        ;
        mv.addAllObjects(map2);
        mv.setViewName("industryindex/list_distributesheng");
        return mv;
    }

}
