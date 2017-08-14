package com.peony.peonyfront.eventindex.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.peony.peonyfront.eventindex.model.EventIndex;
import com.peony.peonyfront.eventindex.service.EventIndexService;
import com.peony.peonyfront.industryindex.model.IndustryIndex;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.regionindex.controller.RegionindexController;
import com.peony.peonyfront.userregion.model.UserRegion;
import com.peony.peonyfront.userregion.service.UserRegionService;

@Controller
@RequestMapping("eventindex")
public class EventIndexController {
    @Autowired
    private EventIndexService     eventIndexservice;
    @Autowired
    private RegionindexController regionindexcontroller;
    @Autowired
    private UserRegionService     userregionservice;

    @RequestMapping("listEventIndex")
    public ModelAndView listEventIndex(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Calendar cal = Calendar.getInstance();
        int m = cal.get(Calendar.MONTH) + 1;
        String month = m + "";
        // 将参数传入Map集合
        Map map = new HashMap();
        map.put("date", m);
        EventIndex re = new EventIndex();
        re.setMonth(month);
        String type = request.getParameter("type");
        String type1 = type + "";
        List<EventIndex> reList = new ArrayList<EventIndex>();
        if (type1.equals("3")) {
            reList = this.eventIndexservice.selectEventIndexChinaByDate(re);
        } else if (type1.equals("1")) {
            reList = this.eventIndexservice.selectEventIndexChinaZhouByDate(re);
        } else if (type1.equals("2")) {
            reList = this.eventIndexservice.selectEventIndexChinaYueByDate(re);
        } else {
            reList = this.eventIndexservice.selectEventIndexChinaByDate(re);
        }
        int s = reList.size();
        String[] eventname = new String[s];
        float[] compositeindex = new float[s];
        float[] mediaAttentionIndex = new float[s];
        float[] netizensAttentionIndex = new float[s];
        // 开始遍历从数据库获取的数据
        Map map2 = new HashMap();
        for (int i = 0; i < reList.size(); i++) {
            EventIndex eventindex = reList.get(i);
            eventname[i] = eventindex.getTitle();
            compositeindex[i] = eventindex.getCompositeindex();
            netizensAttentionIndex[i] = eventindex.getNetizensattentionindex();
            mediaAttentionIndex[i] = eventindex.getMediaattentionindex();
            map2.put("eventname" + i, eventname[i]);
            map2.put("mediaAttentionIndex" + i, mediaAttentionIndex[i]);
            map2.put("netizensAttentionIndex" + i, netizensAttentionIndex[i]);
            map2.put("compositeindex" + i, compositeindex[i]);
        }
        ;
        mv.addAllObjects(map2);
        mv.setViewName("eventindex/list_eventindexchina");
        return mv;
    }

    @RequestMapping("listEventIndexSheng")
    public ModelAndView listEventIndexSheng(HttpServletRequest request) {
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
        map.put("date", m);
        map.put("regionid", regionid);
        EventIndex re = new EventIndex();
        re.setMonth(month);
        re.setRegionid(regionid);
        String type = request.getParameter("type");
        String type1 = type + "";
        List<EventIndex> reList = new ArrayList<EventIndex>();
        if (type1.equals("3")) {
            reList = this.eventIndexservice.selectEventIndexShengByDate(re);
        } else if (type1.equals("1")) {
            reList = this.eventIndexservice.selectEventIndexShengZhouByDate(re);
        } else if (type1.equals("2")) {
            reList = this.eventIndexservice.selectEventIndexShengYueByDate(re);
        } else {
            reList = this.eventIndexservice.selectEventIndexShengByDate(re);
        }
        int s = reList.size();
        String[] eventname = new String[s];
        float[] compositeindex = new float[s];
        float[] mediaAttentionIndex = new float[s];
        float[] netizensAttentionIndex = new float[s];
        // 开始遍历从数据库获取的数据
        Map map2 = new HashMap();
        for (int i = 0; i < reList.size(); i++) {
            EventIndex eventindex = reList.get(i);
            eventname[i] = eventindex.getTitle();
            compositeindex[i] = eventindex.getCompositeindex();
            netizensAttentionIndex[i] = eventindex.getNetizensattentionindex();
            mediaAttentionIndex[i] = eventindex.getMediaattentionindex();
            map2.put("eventname" + i, eventname[i]);
            map2.put("mediaAttentionIndex" + i, mediaAttentionIndex[i]);
            map2.put("netizensAttentionIndex" + i, netizensAttentionIndex[i]);
            map2.put("compositeindex" + i, compositeindex[i] + "");
        }
        ;
        mv.addAllObjects(map2);
        mv.setViewName("eventindex/list_eventindexsheng");
        return mv;
    }

}
