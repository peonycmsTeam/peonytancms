package com.peony.peonyfront.briefreport.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.peony.core.base.controller.BaseController;
import com.peony.core.base.pojo.PageParameter;
import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.attention.model.AttentionInfo;
import com.peony.peonyfront.attention.service.AttentionInfoService;
import com.peony.peonyfront.briefreport.model.Briefreport;
import com.peony.peonyfront.briefreport.model.BriefreportInfo;
import com.peony.peonyfront.briefreport.service.BriefreportInfoService;
import com.peony.peonyfront.briefreport.service.BriefreportService;
import com.peony.peonyfront.event.model.Eventnews;
import com.peony.peonyfront.event.service.EventnewsService;
import com.peony.peonyfront.key.service.IdService;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.subject.model.SubjectPage;
import com.peony.peonyfront.subject.model.WebDictionary;
import com.peony.peonyfront.subject.service.SubjectPageService;
import com.peony.peonyfront.subject.service.WebDictionaryService;
import com.peony.peonyfront.util.HTMLSpirit;
import com.peony.peonyfront.warning.model.Warning;
import com.peony.peonyfront.warning.service.WarningService;

/**
 * 简报信息
 * 
 * @author zhyz
 * 
 */
@Controller
@RequestMapping("/briefreportInfo")
public class BriefreportInfoController extends BaseController {
    @Autowired
    private BriefreportInfoService briefreportInfoService;
    @Autowired
    private BriefreportService     briefreportService;
    @Autowired
    private SubjectPageService     subjectPageService;
    @Autowired
    private IdService              idService;
    @Autowired
    private WebDictionaryService   webDictionaryService;
    @Autowired
    private EventnewsService       eventnewsService;
    @Autowired
    private WarningService         warningService;
    @Autowired
    private AttentionInfoService   attentionInfoService;

    /**
     * 根据简报id查询简报下的舆情列表
     * 
     * @param briefreportId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listBriefreportInfo")
    public ModelAndView listBriefreportInfo(@ModelAttribute("briefreportInfo") BriefreportInfo briefreportInfo, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<WebDictionary> dictonary = this.webDictionaryService.findAllWebDictionary();
        mv.addObject("dictonary", dictonary);
        PageParameter pageParameter = new PageParameter();
        // 每页显示条数
        if (briefreportInfo.getPageSize() != 0) {
            mv.addObject("pageSize", briefreportInfo.getPageSize());
            pageParameter.setPageSize(briefreportInfo.getPageSize());
        }
        if (null == request.getParameter("pageNo")) {
            pageParameter.setCurrentPage(1);
            mv.addObject("page", 1);
        } else {
            pageParameter.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
            mv.addObject("page", request.getParameter("pageNo"));
        }
        briefreportInfo.setPageParameter(pageParameter);
        // 选择类型为全部
        if (briefreportInfo.getType() != null) {
            mv.addObject("type", briefreportInfo.getType());
            if (briefreportInfo.getType() == 0) {
                briefreportInfo.setType(null);
            }
        }
        // 选择倾向性
        if (briefreportInfo.getPolarity() != null) {
            mv.addObject("polarity", briefreportInfo.getPolarity());
            if (briefreportInfo.getPolarity() == 2) {
                briefreportInfo.setPolarity(null);
            }
        }
        // 排序类型(发布时间和预警时间)
        if (briefreportInfo.getTime() != null) {
            mv.addObject("time", briefreportInfo.getTime());
            if ("1".equals(briefreportInfo.getTime())) {
                briefreportInfo.setTime(null);
            }
        }
        // 选择显示时间
        SimpleDateFormat formatterBegin = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar cal = Calendar.getInstance();
        if (briefreportInfo.getSelectDate() != null) {
            mv.addObject("selectDate", briefreportInfo.getSelectDate());
            // 显示全部
            if ("1".equals(briefreportInfo.getSelectDate())) {
                briefreportInfo.setSelectDate(null);
            }
            // 近三天（前两天加今天）
            if ("3".equals(briefreportInfo.getSelectDate())) {
                cal.add(Calendar.DAY_OF_MONTH, -2);
            }
            // 近七天（前六天加今天）
            if ("4".equals(briefreportInfo.getSelectDate())) {
                cal.add(Calendar.DAY_OF_MONTH, -6);
            }
            // 近30天（前29天加今天）
            if ("5".equals(briefreportInfo.getSelectDate())) {
                cal.add(Calendar.MONTH, -1);
            }
        } else {
            briefreportInfo.setSelectDate("3");
            cal.add(Calendar.DAY_OF_MONTH, -2);
            mv.addObject("selectDate", "3");
        }
        briefreportInfo.setBeginDate(formatterBegin.format(cal.getTime()));
        Pagination<BriefreportInfo> pagination = briefreportInfoService.selectBriefreportInfoByBreiefreportIdByPage(briefreportInfo);
        Briefreport briefreport = briefreportService.selectByPrimaryKey(briefreportInfo.getBriefreportId());
        mv.addObject("briefreport", briefreport);
        mv.addObject("pagination", pagination);
        mv.setViewName("briefreport/list_briefreportInfo");
        return mv;
    }

    /**
     * 查看简报内容详细
     * 
     * @return
     */
    @RequestMapping(value = "/getBriefreportInfoCnt/{briefreportInfoId}")
    public ModelAndView getBriefreportInfoCnt(@PathVariable("briefreportInfoId") int briefreportInfoId) {
        ModelAndView mv = new ModelAndView();
        BriefreportInfo briefreportInfo = this.briefreportInfoService.selectByPrimaryKey(briefreportInfoId);
        mv.addObject("briefreportInfo", briefreportInfo);
        mv.setViewName("briefreport/briefreportInfoCnt");
        return mv;
    }

    /**
     * 根据id删除BriefreportInfo
     * 
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delBriefreportInfo")
    @ResponseBody
    public Map<String, Object> delBriefreportInfo(@ModelAttribute("ids") String ids, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> mapModel = Maps.newHashMap();
        String[] id = ids.split(",");
        int state = this.briefreportInfoService.delBriefreportInfoByBriefreportInfoIds(id);
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
     * 添加到简报(pageId)
     * 
     * @param briefreportInfo
     * @return
     */
    @RequestMapping(value = "/addToBriefreport")
    @ResponseBody
    public int addToBriefreport(@ModelAttribute("attention") BriefreportInfo briefreportInfo, SubjectPage subjectPage, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("userfront");
        subjectPage.setUserid(user.getUserId());
        subjectPage = this.subjectPageService.load(subjectPage);
        if (subjectPage != null) {
            briefreportInfo.setBriefreportInfoId(idService.NextKey("briefreportInfoId"));
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
            briefreportInfo.setSubjectId(subjectPage.getSubjectid());
            briefreportInfo.setNewsLevel(subjectPage.getNewslevel());
            return this.briefreportInfoService.insertSelective(briefreportInfo);
        } else {
            return 0;
        }
    }

    /**
     * 添加到简报(eventnewId)
     * 
     * @param eventnews
     * @param briefreportInfo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/saveToBriefreportByEvennews")
    @ResponseBody
    public int saveToBriefreportByEvennews(@ModelAttribute("Eventnews") Eventnews eventnews, @ModelAttribute("attention") BriefreportInfo briefreportInfo, HttpServletRequest request, HttpServletResponse response) {
        eventnews.setProvinceid(Integer.parseInt(eventnews.getRegionID().substring(0, 2)));
        eventnews = this.eventnewsService.load(eventnews);
        if (eventnews != null) {
            briefreportInfo.setBriefreportInfoId(idService.NextKey("briefreportInfoId"));
            briefreportInfo.setTitle(eventnews.getTitle());
            briefreportInfo.setType(eventnews.getType());
            briefreportInfo.setWebsite(eventnews.getWebsite());
            briefreportInfo.setUrl(eventnews.getUrl());
            briefreportInfo.setPtime(new Date());
            briefreportInfo.setPublishdate(eventnews.getPublishdate());
            briefreportInfo.setSummary(eventnews.getSummary());
            briefreportInfo.setPolarity(eventnews.getPolarity());
            briefreportInfo.setPageId(eventnews.getPageid());
            briefreportInfo.setNewsLevel(eventnews.getNewslevel());
            return this.briefreportInfoService.insertSelective(briefreportInfo);
        } else {
            return 0;
        }
    }

    /**
     * 添加到简报（warningId）
     * 
     * @param warning
     * @param briefreportInfo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/saveToBriefreportByWarningId")
    @ResponseBody
    public int saveToBriefreportByWarningId(Warning warning, BriefreportInfo briefreportInfo, HttpServletRequest request, HttpServletResponse response) {
        if (warning.getWarningId() != null) {
            warning = this.warningService.selectByPrimaryKey(warning.getWarningId());
            briefreportInfo.setBriefreportInfoId(idService.NextKey("briefreportInfoId"));
            briefreportInfo.setTitle(warning.getTitle());
            briefreportInfo.setType(warning.getWaringType());
            briefreportInfo.setWebsite(warning.getWebsite());
            briefreportInfo.setUrl(warning.getUrl());
            briefreportInfo.setPtime(new Date());
            briefreportInfo.setPublishdate(warning.getPublishdate());
            briefreportInfo.setSummary(warning.getSummary());
            briefreportInfo.setPolarity(warning.getPolarity());
            briefreportInfo.setPageId(warning.getPageId());
            briefreportInfo.setNewsLevel(warning.getNewsLevel());
            return this.briefreportInfoService.insertSelective(briefreportInfo);
        } else {
            return 0;
        }
    }

    /**
     * 添加到简报（attentioninfoId）
     * 
     * @param attentioninfo
     * @param briefreportInfo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/saveToBriefreportByAttention")
    @ResponseBody
    public int saveToBriefreportByAttention(AttentionInfo attentioninfo, BriefreportInfo briefreportInfo, HttpServletRequest request, HttpServletResponse response) {
        if (attentioninfo.getAttentionInfoId() != null) {
            attentioninfo = this.attentionInfoService.selectByPrimaryKey(attentioninfo.getAttentionInfoId());
            briefreportInfo.setBriefreportInfoId(idService.NextKey("briefreportInfoId"));
            briefreportInfo.setTitle(attentioninfo.getTitle());
            briefreportInfo.setType(attentioninfo.getType());
            briefreportInfo.setWebsite(attentioninfo.getWebsite());
            briefreportInfo.setUrl(attentioninfo.getUrl());
            briefreportInfo.setPtime(new Date());
            briefreportInfo.setPublishdate(attentioninfo.getPublishdate());
            briefreportInfo.setSummary(attentioninfo.getSummary());
            briefreportInfo.setPolarity(attentioninfo.getPolarity());
            briefreportInfo.setPageId(attentioninfo.getPageId());
            briefreportInfo.setNewsLevel(attentioninfo.getNewsLevel());
            return this.briefreportInfoService.insertSelective(briefreportInfo);
        } else {
            return 0;
        }
    }

    /**
     * 跳转到添加专题页面
     * 
     * @param briefreportInfoId
     * @return
     */
    @RequestMapping(value = "/toAddTopic/{briefreportInfoId}")
    @ResponseBody
    public ModelAndView toAddTopic(@PathVariable("briefreportInfoId") int briefreportInfoId) {
        ModelAndView mv = new ModelAndView();
        BriefreportInfo briefreportInfo = this.briefreportInfoService.selectByPrimaryKey(briefreportInfoId);
        briefreportInfo.setSummary(HTMLSpirit.delHTMLTag(briefreportInfo.getSummary()));
        mv.addObject("topic", briefreportInfo);
        mv.setViewName("subject/add_topic_info");
        return mv;
    }

}
