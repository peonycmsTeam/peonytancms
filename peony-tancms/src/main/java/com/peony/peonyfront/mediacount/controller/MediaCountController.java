package com.peony.peonyfront.mediacount.controller;

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
import com.peony.peonyfront.mediacount.model.MediaCount;
import com.peony.peonyfront.mediacount.service.MediaCountService;
import com.peony.peonyfront.userregion.model.UserRegion;
import com.peony.peonyfront.userregion.service.UserRegionService;

@Controller
@RequestMapping("mediacount")
public class MediaCountController {
    @Autowired
    private UserRegionService userregionservice;
    @Autowired
    private MediaCountService mediacountservice;

    /**
     * 全国媒体类型及各媒体的影响力。
     * 
     * @param request
     * @return
     */
    @RequestMapping("listMediaCount")
    public ModelAndView listMediaCount(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        MediaCount media = (MediaCount) request.getSession().getAttribute("");
        Calendar cal = Calendar.getInstance();
        int m = cal.get(Calendar.MONTH) + 1;
        String month1 = m + "";
        // 将参数传入Map集合
        Map map = new HashMap();
        map.put("date", m);
        MediaCount re = new MediaCount();
        re.setMonth(month1);
        String type = request.getParameter("type");
        String type1 = type + "";
        List<MediaCount> Mediaone = new ArrayList<MediaCount>();
        if (type1.equals("3")) {
            Mediaone = this.mediacountservice.selectMediaoneByDate(re);
        } else if (type1.equals("1")) {
            Mediaone = this.mediacountservice.selectMediaoneByDateZhou(re);
        } else if (type1.equals("2")) {
            Mediaone = this.mediacountservice.selectMediaoneByDateYue(re);
        } else {
            Mediaone = this.mediacountservice.selectMediaoneByDate(re);
        }
        int s = Mediaone.size();
        int[] countmumber = new int[s];
        // 开始遍历从数据库获取的数据
        Map map1 = new HashMap();
        for (int i = 0; i < Mediaone.size(); i++) {
            MediaCount mediacount = Mediaone.get(i);
            countmumber[i] = mediacount.getCountmumber();
            map1.put("countmumber" + i, countmumber[i]);
        }
        ;
        Map map2 = new HashMap();
        map.put("date", m);
        MediaCount rs = new MediaCount();
        rs.setMonth(month1);
        List<MediaCount> listCount = new ArrayList<MediaCount>();
        if (type1.equals("3")) {
            listCount = this.mediacountservice.selectMediaByDate(rs);
        } else if (type1.equals("1")) {
            listCount = this.mediacountservice.selectMediaByDateZhou(rs);
        } else if (type1.equals("2")) {
            listCount = this.mediacountservice.selectMediaByDateYue(rs);
        } else {
            listCount = this.mediacountservice.selectMediaByDate(rs);
        }
        int j = listCount.size();
        String[] webSitec = new String[j];
        int[] countmumberc = new int[j];
        Map map5 = new HashMap();
        for (int i = 0; i < listCount.size(); i++) {
            MediaCount mediacount = listCount.get(i);
            webSitec[i] = mediacount.getWebsite();
            countmumberc[i] = mediacount.getCountmumber();
            map5.put("webSitec" + i, webSitec[i]);
            map5.put("countmumberc" + i, countmumberc[i]);
        }
        List<MediaCount> listBbs = new ArrayList<MediaCount>();
        if (type1.equals("3")) {
            listBbs = this.mediacountservice.selectMediaBbsByDate(rs);
        } else if (type1.equals("1")) {
            listBbs = this.mediacountservice.selectMediaBbsByDateZhou(rs);
        } else if (type1.equals("2")) {
            listBbs = this.mediacountservice.selectMediaBbsByDateYue(rs);
        } else {
            listBbs = this.mediacountservice.selectMediaBbsByDate(rs);
        }
        int n = listBbs.size();
        String[] webSiteb = new String[n];
        int[] countmumberb = new int[n];
        Map map4 = new HashMap();
        for (int i = 0; i < listBbs.size(); i++) {
            MediaCount mediacount = listBbs.get(i);
            webSiteb[i] = mediacount.getWebsite();
            countmumberb[i] = mediacount.getCountmumber();
            map4.put("webSiteb" + i, webSiteb[i]);
            map4.put("countmumberb" + i, countmumberb[i]);
        }
        List<MediaCount> listBlog = new ArrayList<MediaCount>();
        if (type1.equals("3")) {
            listBlog = this.mediacountservice.selectMediaBlogByDate(rs);
        } else if (type1.equals("1")) {
            listBlog = this.mediacountservice.selectMediaBlogByDateZhou(rs);
        } else if (type1.equals("2")) {
            listBlog = this.mediacountservice.selectMediaBlogByDateYue(rs);
        } else {
            listBlog = this.mediacountservice.selectMediaBlogByDate(rs);
        }
        int t = listBlog.size();
        String[] webSites = new String[t];
        int[] countmumbers = new int[t];
        Map map3 = new HashMap();
        for (int i = 0; i < listBlog.size(); i++) {
            MediaCount mediacount = listBlog.get(i);
            webSites[i] = mediacount.getWebsite();
            countmumbers[i] = mediacount.getCountmumber();
            map3.put("webSites" + i, webSites[i]);
            map3.put("countmumbers" + i, countmumbers[i]);
        }
        mv.addAllObjects(map1);
        mv.addAllObjects(map3);
        mv.addAllObjects(map4);
        mv.addAllObjects(map5);
        mv.setViewName("mediacount/list_mediacount");
        return mv;
    }

    @RequestMapping("listMediaCountSheng")
    public ModelAndView listMediaCountSheng(HttpServletRequest request) {
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
        MediaCount re = new MediaCount();
        re.setMonth(month1);
        re.setRegionid(regionid);
        String type = request.getParameter("type");
        String type1 = type + "";
        List<MediaCount> Weixin = new ArrayList<MediaCount>();
        if (type1.equals("3")) {
            Weixin = this.mediacountservice.selectWeixinByDates(re);
        } else if (type1.equals("1")) {
            Weixin = this.mediacountservice.selectWeixinByDatesZhou(re);
        } else if (type1.equals("2")) {
            Weixin = this.mediacountservice.selectWeixinByDatesYue(re);
        } else {
            Weixin = this.mediacountservice.selectWeixinByDates(re);
        }
        int s = Weixin.size();
        int[] countmumber = new int[s];
        Map map1 = new HashMap();
        for (int i = 0; i < Weixin.size(); i++) {
            MediaCount mediacount = Weixin.get(i);
            countmumber[i] = mediacount.getCountmumber();
            map1.put("countmumber" + i, countmumber[i]);
        }
        // 开始遍历从数据库获取的数据
        mv.addAllObjects(map1);
        Map map2 = new HashMap();
        map.put("date", m);
        map.put("regionid", regionid);
        MediaCount rs = new MediaCount();
        rs.setMonth(month1);
        rs.setRegionid(regionid);
        List<MediaCount> listCount = new ArrayList<MediaCount>();
        if (type1.equals("3")) {
            listCount = this.mediacountservice.selectMediaByDate(rs);
        } else if (type1.equals("1")) {
            listCount = this.mediacountservice.selectMediaByDateZhou(rs);
        } else if (type1.equals("2")) {
            listCount = this.mediacountservice.selectMediaByDateYue(rs);
        } else {
            listCount = this.mediacountservice.selectMediaByDate(rs);
        }
        int j = listCount.size();
        String[] webSitec = new String[j];
        int[] countmumberc = new int[j];
        Map map3 = new HashMap();
        for (int i = 0; i < listCount.size(); i++) {
            MediaCount mediacount = listCount.get(i);
            webSitec[i] = mediacount.getWebsite();
            countmumberc[i] = mediacount.getCountmumber();
            map3.put("webSitec" + i, webSitec[i]);
            map3.put("countmumberc" + i, countmumberc[i]);
        }
        List<MediaCount> listBbs = new ArrayList<MediaCount>();
        if (type1.equals("3")) {
            listBbs = this.mediacountservice.selectMediaBbsByDate(rs);
        } else if (type1.equals("1")) {
            listBbs = this.mediacountservice.selectMediaBbsByDateZhou(rs);
        } else if (type1.equals("2")) {
            listBbs = this.mediacountservice.selectMediaBbsByDateYue(rs);
        } else {
            listBbs = this.mediacountservice.selectMediaBbsByDate(rs);
        }
        int n = listBbs.size();
        String[] webSiteb = new String[n];
        int[] countmumberb = new int[n];
        Map map4 = new HashMap();
        for (int i = 0; i < listBbs.size(); i++) {
            MediaCount mediacount = listBbs.get(i);
            webSiteb[i] = mediacount.getWebsite();
            countmumberb[i] = mediacount.getCountmumber();
            map4.put("webSiteb" + i, webSiteb[i]);
            map4.put("countmumberb" + i, countmumberb[i]);
        }
        List<MediaCount> listBlog = new ArrayList<MediaCount>();
        if (type1.equals("3")) {
            listBlog = this.mediacountservice.selectMediaBlogByDateSheng(rs);
        } else if (type1.equals("1")) {
            listBlog = this.mediacountservice.selectMediaBlogByDateShengZhou(rs);
        } else if (type1.equals("2")) {
            listBlog = this.mediacountservice.selectMediaBlogByDateShengYue(rs);
        } else {
            listBlog = this.mediacountservice.selectMediaBlogByDateSheng(rs);
        }
        int t = listBlog.size();
        String[] webSites = new String[t];
        int[] countmumbers = new int[t];
        Map map5 = new HashMap();
        for (int i = 0; i < listBlog.size(); i++) {
            MediaCount mediacount = listBlog.get(i);
            webSites[i] = mediacount.getWebsite();
            countmumbers[i] = mediacount.getCountmumber();
            map5.put("webSites" + i, webSites[i]);
            map5.put("countmumbers" + i, countmumbers[i]);
        }
        mv.addAllObjects(map3);
        mv.addAllObjects(map4);
        mv.addAllObjects(map5);
        mv.setViewName("mediacount/list_mediacountsheng");
        return mv;
    }
}
