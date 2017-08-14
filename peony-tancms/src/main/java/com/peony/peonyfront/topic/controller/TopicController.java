package com.peony.peonyfront.topic.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.peony.core.base.controller.BaseController;
import com.peony.core.base.pojo.PageParameter;
import com.peony.core.base.pojo.Pagination;
import com.peony.core.common.utils.DateUtils;
import com.peony.peonyfront.key.service.IdService;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.operationlog.model.OperationLog;
import com.peony.peonyfront.operationlog.service.OperationLogService;
import com.peony.peonyfront.topic.model.Topic;
import com.peony.peonyfront.topic.service.TopicPageService;
import com.peony.peonyfront.topic.service.TopicService;
import com.peony.peonyfront.topic.util.WhJdcbConnection;
import com.peony.peonyfront.topickeywords.model.TopicKeywords;
import com.peony.peonyfront.topickeywords.service.TopicKeywordsService;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;

/**
 * 专题action
 *
 * @author lenovo41
 * @date 2014-6-17 上午9:22:59
 */
@Controller
@RequestMapping("/topic")
public class TopicController extends BaseController {
    @Autowired
    private IdService            idService;           // id服务接口
    @Autowired
    private TopicService         topicService;
    @Autowired
    private TopicKeywordsService topicKeywordsService;
    @Autowired
    private TopicPageService     topicPageService;

    @Autowired
    private OperationLogService  operationLogService;

    /**
     * 专题列表
     *
     * @return
     */
    @RequestMapping(value = "/listTopic")
    public ModelAndView listTopic(@ModelAttribute("topic") Topic topic, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("userfront");
        ModelAndView mv = new ModelAndView();
        topic.setUserid(user.getUserId());

        PageParameter pageParameter = new PageParameter();
        if (null == request.getParameter("pageNo")) {
            pageParameter.setCurrentPage(1);
        } else {
            pageParameter.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
        }
        pageParameter.setPageSize(5);
        topic.setPageParameter(pageParameter);
        Pagination<Topic> list = this.topicService.selectByPage(topic);
        // 遍历list，查询统计总量和今日总量
        for (int i = 0; i < list.getList().size(); i++) {
            Topic mytopic = list.getList().get(i);
            // 今日统计
            Map dayMap = new HashMap();
            Map map = new HashMap();
            map.put("topicid", mytopic.getId());
            map.put("stime", DateUtils.todayDateStr());
            List<Map> todaycountlist = this.topicPageService.selectByTypeCount(map);
            for (int j = 0; j < todaycountlist.size(); j++) {
                Map todaymap = todaycountlist.get(j);
                dayMap.put("type" + todaymap.get("type"), todaymap.get("count"));

            }
            mytopic.setDayMap(dayMap);
            // 总量统计
            Map allMap = new HashMap();
            Map selectmap = new HashMap();
            selectmap.put("topicid", mytopic.getId());
            List<Map> allcountlist = this.topicPageService.selectByTypeCount(selectmap);
            for (int j = 0; j < allcountlist.size(); j++) {
                Map todaymap = allcountlist.get(j);
                allMap.put("type" + todaymap.get("type"), todaymap.get("count"));

            }
            mytopic.setCountMap(allMap);
        }

        OperationLog operationLog = new OperationLog(new Date(), user.getUserId(), user.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "事件专题", OperateType.FIND.toString(), OperateMode.事件专题.toString());
        this.operationLogService.insertSelective(operationLog);

        mv.addObject("list", list);
        mv.setViewName("topic/list_topic");
        return mv;
    }

    /**
     * 增加专题
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/addTopic")
    public String toAddTopic(HttpServletRequest request) {
        return "topic/add_topic";
    }

    /**
     * 保存增加
     *
     * @param topic
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveTopic", method = RequestMethod.POST)
    public String saveTopic(@ModelAttribute("topic") Topic topic, HttpServletRequest request, @ModelAttribute("mainKeywords") String mainKeywords, @ModelAttribute("area") String area, @ModelAttribute("ruleoutKeywords") String ruleoutKeywords, @ModelAttribute("deputyKeywords") String deputyKeywords) {
        WhJdcbConnection whJdcbConnection = new WhJdcbConnection();
        User user = (User) request.getSession().getAttribute("userfront");
        int topicId = this.idService.NextKey("topic_id");
        topic.setId(topicId);
        topic.setState(1);
        topic.setCreateTime(new Date());
        topic.setUpdateTime(new Date());
        topic.setUserid(user.getUserId());
        topic.setStartTime(new Date());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, +6);
        topic.setEndTime(cal.getTime());
        this.topicService.insertSelective(topic);
        whJdcbConnection.InsertTopic(topic);// 同步

        // 地域关键词*************************
        if (!"".equals(area)) {
            TopicKeywords areaNames = new TopicKeywords();
            areaNames.setId(this.idService.NextKey("topickeyws_id"));
            areaNames.setTopicid(topicId);
            areaNames.setName("area");
            areaNames.setKeywords(area);
            areaNames.setRejectflag("0");
            this.topicKeywordsService.insertSelective(areaNames);
            whJdcbConnection.InsertTopicKeywords(areaNames);// 同步
        }

        // 主关键词*********************
        if (!"".equals(mainKeywords)) {
            TopicKeywords main = new TopicKeywords();
            main.setId(this.idService.NextKey("topickeyws_id"));
            main.setTopicid(topicId);
            main.setName("main_keywords");
            main.setKeywords(mainKeywords);
            main.setRejectflag("0");
            this.topicKeywordsService.insertSelective(main);
            whJdcbConnection.InsertTopicKeywords(main);// 同步
        }

        // 倾向性分析词*******************
        if (!"".equals(deputyKeywords)) {
            TopicKeywords deputy = new TopicKeywords();
            deputy.setId(this.idService.NextKey("topickeyws_id"));
            deputy.setTopicid(topicId);
            deputy.setName("deputy_keywords");
            deputy.setKeywords(deputyKeywords);
            deputy.setRejectflag("0");
            this.topicKeywordsService.insertSelective(deputy);
            whJdcbConnection.InsertTopicKeywords(deputy);// 同步
        }

        // 过滤词***********************
        if (!"".equals(ruleoutKeywords)) {
            TopicKeywords ruleout = new TopicKeywords();
            ruleout.setId(this.idService.NextKey("topickeyws_id"));
            ruleout.setTopicid(topicId);
            ruleout.setName("ruleout_keywords");
            ruleout.setKeywords(ruleoutKeywords);
            ruleout.setRejectflag("1");
            this.topicKeywordsService.insertSelective(ruleout);
            whJdcbConnection.InsertTopicKeywords(ruleout);
        }
        return "";
    }

    /**
     * 修改专题
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/editTopic/{id}")
    public ModelAndView editTopic(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView();
        Topic topic = this.topicService.selectByPrimaryKey(id);
        mv.addObject("topic", topic);
        mv.setViewName("topic/edit_topic");
        List<TopicKeywords> SKList = this.topicKeywordsService.selectTopicListByPid(id);
        for (TopicKeywords sk : SKList) {
            mv.addObject(sk.getName(), sk.getKeywords());
        }
        return mv;

    }

    /**
     * 保存修改
     *
     * @param topic
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateTopic", method = RequestMethod.POST)
    public @ResponseBody String updateTopic(@ModelAttribute("topic") Topic topic, HttpServletRequest request, @ModelAttribute("mainKeywords") String mainKeywords, @ModelAttribute("area") String area, @ModelAttribute("ruleoutKeywords") String ruleoutKeywords, @ModelAttribute("deputyKeywords") String deputyKeywords) {
        WhJdcbConnection whJdcbConnection = new WhJdcbConnection();
        topic.setUpdateTime(new Date());
        this.topicService.updateByPrimaryKeySelective(topic);
        whJdcbConnection.UpdateTopic(topic);
        int topicId = topic.getId();
        this.topicKeywordsService.deleteByTopicId(topicId);
        whJdcbConnection.DeleteTopicKeywords(topicId);
        // whJdcbConnection.DeleteTopic(topic);

        // 地域关键词*************************
        if (!"".equals(area)) {
            TopicKeywords areaNames = new TopicKeywords();
            areaNames.setId(this.idService.NextKey("topickeyws_id"));
            areaNames.setTopicid(topicId);
            areaNames.setName("area");
            areaNames.setKeywords(area);
            areaNames.setRejectflag("0");
            this.topicKeywordsService.insertSelective(areaNames);
            whJdcbConnection.InsertTopicKeywords(areaNames);
        }

        // 主关键词*********************
        if (!"".equals(mainKeywords)) {
            TopicKeywords main = new TopicKeywords();
            main.setId(this.idService.NextKey("topickeyws_id"));
            main.setTopicid(topicId);
            main.setName("main_keywords");
            main.setKeywords(mainKeywords);
            main.setRejectflag("0");
            this.topicKeywordsService.insertSelective(main);
            whJdcbConnection.InsertTopicKeywords(main);
        }

        // 倾向性分析词*******************
        if (!"".equals(deputyKeywords)) {
            TopicKeywords deputy = new TopicKeywords();
            deputy.setId(this.idService.NextKey("topickeyws_id"));
            deputy.setTopicid(topicId);
            deputy.setName("deputy_keywords");
            deputy.setKeywords(deputyKeywords);
            deputy.setRejectflag("0");
            this.topicKeywordsService.insertSelective(deputy);
            whJdcbConnection.InsertTopicKeywords(deputy);
        }

        // 过滤词***********************
        if (!"".equals(ruleoutKeywords)) {
            TopicKeywords ruleout = new TopicKeywords();
            ruleout.setId(this.idService.NextKey("topickeyws_id"));
            ruleout.setTopicid(topicId);
            ruleout.setName("ruleout_keywords");
            ruleout.setKeywords(ruleoutKeywords);
            ruleout.setRejectflag("1");
            this.topicKeywordsService.insertSelective(ruleout);
            whJdcbConnection.InsertTopicKeywords(ruleout);
        }
        return "";
    }

    /**
     * 删除专题
     *
     * @param ids
     * @param commodity
     * @return
     */
    @RequestMapping(value = "/deleteTopic")
    public @ResponseBody Map<String, Object> batchDeleteTopic(@RequestParam(value = "id", required = true) int id, HttpServletRequest request) {
        Topic topic = new Topic();
        // WhJdcbConnection whJdcbConnection = new WhJdcbConnection();
        topic.setId(id);
        topic.setState(0);
        topic.setUpdateTime(new Date());
        User user = (User) request.getSession().getAttribute("userfront");
        topic.setUserid(user.getUserId());
        Map<String, Object> mapModel = Maps.newHashMap();
        int totalCount = Integer.parseInt(request.getParameter("totalCount"));
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));

        if (this.topicService.deleteByPrimaryKey(topic) == 1) {
            // whJdcbConnection.DeleteTopic(topic);
            mapModel.put("state", true);
            int totalPage = 0;
            if ((totalCount - 1) % 5 == 0) {
                totalPage = (totalCount - 1) / 5;
            } else {
                totalPage = (totalCount - 1) / 5 + 1;
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
            mapModel.put("state", false);
        }
        return mapModel;
    }

    /**
     * 事件综述
     *
     * @return
     */
    @RequestMapping(value = "/listTopicInfo/{id}")
    public ModelAndView listTopicInfo(@PathVariable("id") int id, @ModelAttribute("stime") String stime, @ModelAttribute("etime") String etime) {
        // 专题信息
        ModelAndView mv = new ModelAndView();
        Topic topic = this.topicService.selectByPrimaryKey(id);
        mv.addObject("topic", topic);
        if (etime == null || etime.equals("")) {
            // 当前日期
            Date etime1 = DateUtils.strToDate(DateUtils.todayDateStr(), "yyyy-MM-dd");
            // 结束时期
            Date etime2 = topic.getEndTime();
            etime = DateUtils.dateToStr(topic.getEndTime(), "yyyy-MM-dd");
            if (etime1.getTime() > etime2.getTime()) {
                etime = DateUtils.dateToStr(etime2, "yyyy-MM-dd");
            } else {
                etime = DateUtils.dateToStr(etime1, "yyyy-MM-dd");
            }
        }
        if (stime == null || stime.equals("")) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(DateUtils.strToDate(etime, "yyyy-MM-dd"));
            cal.add(Calendar.DATE, -15);
            stime = DateUtils.dateToStr(cal.getTime(), "yyyy-MM-dd");
            // stime = DateUtils.dateToStr(topic.getStartTime(), "yyyy-MM-dd");
        }
        mv.addObject("stime", stime);
        mv.addObject("etime", etime);
        mv.setViewName("topic/list_topicinfo");

        return mv;
    }

    /**
     * 查询该用户是否有创建专题的个数
     *
     * @return
     */
    @RequestMapping(value = "/isTopicEnough")
    @ResponseBody
    public int isTopicEnough(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("userfront");
        int i = this.topicService.selectTopicCount(user.getUserId());
        return i;
    }
}
