package com.peony.peonyfront.attention.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import com.peony.peonyfront.attention.model.Attention;
import com.peony.peonyfront.attention.model.AttentionInfo;
import com.peony.peonyfront.attention.service.AttentionInfoService;
import com.peony.peonyfront.attention.service.AttentionService;
import com.peony.peonyfront.briefreport.model.BriefreportInfo;
import com.peony.peonyfront.briefreport.service.BriefreportInfoService;
import com.peony.peonyfront.event.model.Eventnews;
import com.peony.peonyfront.event.service.EventnewsService;
import com.peony.peonyfront.key.service.IdService;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.operationlog.model.OperationLog;
import com.peony.peonyfront.operationlog.service.OperationLogService;
import com.peony.peonyfront.subject.model.SubjectPage;
import com.peony.peonyfront.subject.model.WebDictionary;
import com.peony.peonyfront.subject.service.SubjectPageService;
import com.peony.peonyfront.subject.service.WebDictionaryService;
import com.peony.peonyfront.util.DocumentHandler;
import com.peony.peonyfront.util.HTMLSpirit;
import com.peony.peonyfront.util.Snapshot;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;
import com.peony.peonyfront.warning.model.Warning;
import com.peony.peonyfront.warning.service.WarningService;

@Controller
@RequestMapping("/attention")
public class AttentionController extends BaseController {

    // 服务接口
    @Autowired
    private AttentionService       attentionService;      // 课件
    @Autowired
    private AttentionInfoService   attentionInfoService;
    @Autowired
    private SubjectPageService     subjectPageService;
    @Autowired
    private IdService              idService;
    @Autowired
    private EventnewsService       eventnewsService;
    @Autowired
    private WebDictionaryService   webDictionaryService;
    @Autowired
    private BriefreportInfoService briefreportInfoService;
    @Autowired
    private WarningService         warningService;

    @Autowired
    private OperationLogService    operationLogService;

    @RequestMapping(value = "/listAttention")
    public ModelAndView listAttention(Attention attention, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        User user = (User) request.getSession().getAttribute("userfront");
        if (user != null) {
            attention.setUserId(String.valueOf(user.getUserId()));
        }
        PageParameter pageParameter = new PageParameter();
        if (null == request.getParameter("pageNo")) {
            pageParameter.setCurrentPage(1);
        } else {
            pageParameter.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
        }

        attention.setPageParameter(pageParameter);
        Pagination<Attention> pagination = this.attentionService.findByPage(attention);
        List<Attention> list = pagination.getList();
        if (null == list) {
            list = new ArrayList<Attention>();
        }
        OperationLog operationLog = new OperationLog(new Date(), user.getUserId(), user.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "我的收藏", OperateType.FIND.toString(), OperateMode.我的收藏.toString());
        this.operationLogService.insertSelective(operationLog);

        mv.setViewName("attention/list_attention");
        mv.addObject("pagination", pagination);
        return mv;
    }

    /**
     * 点击收藏，显示收藏加内的列表
     *
     * @return
     */
    @RequestMapping(value = "/listAttentionInfoByAttentionId")
    public ModelAndView listAttentionInfoByAttentionId(AttentionInfo attentionInfo, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        if (null != attentionInfo.getAttentionId()) {
            Attention attention = this.attentionService.selectByPrimaryKey(attentionInfo.getAttentionId());
            PageParameter pageParameter = new PageParameter();
            List<WebDictionary> dictonary = this.webDictionaryService.findAllWebDictionary();
            mv.addObject("dictonary", dictonary);
            // 每页显示条数
            if (attentionInfo.getPageSize() != 0) {
                mv.addObject("pageSize", attentionInfo.getPageSize());
                pageParameter.setPageSize(attentionInfo.getPageSize());
            }
            if (null == request.getParameter("pageNo")) {
                pageParameter.setCurrentPage(1);
                mv.addObject("page", 1);
            } else {
                pageParameter.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
                mv.addObject("page", request.getParameter("pageNo"));
            }
            attentionInfo.setPageParameter(pageParameter);
            // 选择倾向性
            if (attentionInfo.getPolarity() != null) {
                mv.addObject("polarity", attentionInfo.getPolarity());
                if (attentionInfo.getPolarity() == 2) {
                    attentionInfo.setPolarity(null);
                }
            }
            // 查询已读未读
            if (attentionInfo.getIsRead() != null) {
                mv.addObject("isRead", attentionInfo.getIsRead());
                if (2 == attentionInfo.getIsRead()) {
                    attentionInfo.setIsRead(null);
                }
            }
            // 排序类型(发布时间和预警时间)
            if (attentionInfo.getTime() != null) {
                mv.addObject("time", attentionInfo.getTime());
                if ("1".equals(attentionInfo.getTime())) {
                    attentionInfo.setTime(null);
                }
            }
            // 选择类型为全部
            if (attentionInfo.getType() != null) {
                mv.addObject("type", attentionInfo.getType());
                if (attentionInfo.getType() == 0) {
                    attentionInfo.setType(null);
                }
            }
            // 选择显示时间
            SimpleDateFormat formatterBegin = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            Calendar cal = Calendar.getInstance();
            if (attentionInfo.getSelectDate() != null) {
                mv.addObject("selectDate", attentionInfo.getSelectDate());
                // 显示全部
                if ("1".equals(attentionInfo.getSelectDate())) {
                    attentionInfo.setSelectDate(null);
                }
                // 近三天（前两天加今天）
                if ("3".equals(attentionInfo.getSelectDate())) {
                    cal.add(Calendar.DAY_OF_MONTH, -2);
                }
                // 近七天（前六天加今天）
                if ("4".equals(attentionInfo.getSelectDate())) {
                    cal.add(Calendar.DAY_OF_MONTH, -6);
                }
                // 近30天（前29天加今天）
                if ("5".equals(attentionInfo.getSelectDate())) {
                    cal.add(Calendar.MONTH, -1);
                }
            } else {
                mv.addObject("selectDate", "3");
                cal.add(Calendar.DAY_OF_MONTH, -2);
                attentionInfo.setSelectDate("3");
            }
            attentionInfo.setBeginDate(formatterBegin.format(cal.getTime()));
            Pagination<AttentionInfo> pagination = this.attentionInfoService.selectAttentionInfoByAttentionIdByPage(attentionInfo);
            List<AttentionInfo> list = pagination.getList();
            if (null == list) {
                list = new ArrayList<AttentionInfo>();
            }
            mv.addObject("pagination", pagination);
            mv.addObject("attention", attention);
        }
        mv.setViewName("attention/list_info_attention");
        return mv;
    }

    /**
     * 根据id删除attentioninfo
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delAttentionInfo")
    @ResponseBody
    public Map<String, Object> delBriefreportInfo(@ModelAttribute("ids") String ids, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> mapModel = Maps.newHashMap();
        String[] id = ids.split(",");
        int state = this.attentionInfoService.delAttentionInfoByAttentionInfoIds(id);
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
     * 点击info进入详细页
     *
     * @param attentionInfoId
     * @return
     */
    @RequestMapping(value = "/getAttentionInfoCnt/{attentionInfoId}")
    public ModelAndView getAttentionInfoCnt(@PathVariable("attentionInfoId") int attentionInfoId) {
        ModelAndView mv = new ModelAndView();
        AttentionInfo attentionInfo = this.attentionInfoService.selectByPrimaryKey(attentionInfoId);
        attentionInfo.setIsRead(1);
        this.attentionInfoService.updateByPrimaryKeySelective(attentionInfo);
        mv.addObject("attentionInfo", attentionInfo);
        mv.setViewName("attention/info_attention");
        return mv;
    }

    /**
     * 根据id删除attention
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delAttentionByAttentionId")
    @ResponseBody
    public Map<String, Object> delAttentionByAttentionId(@ModelAttribute("attentionId") int attentionId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> mapModel = Maps.newHashMap();
        int totalCount = Integer.parseInt(request.getParameter("totalCount"));
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));

        if (this.attentionService.deleteByPrimaryKey(attentionId) > 0) {
            mapModel.put("state", true);
            int totalPage = 0;
            if ((totalCount - 1) % 10 == 0) {
                totalPage = (totalCount - 1) / 10;
            } else {
                totalPage = (totalCount - 1) / 10 + 1;
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
     * 跳转到修改页
     *
     * @param attentionInfoId
     * @return
     */
    @RequestMapping(value = "/editAttentionByAttentionId/{attentionId}")
    public ModelAndView editAttentionByAttentionId(@PathVariable("attentionId") int attentionId) {
        ModelAndView mv = new ModelAndView();
        Attention attention = this.attentionService.selectByPrimaryKey(attentionId);
        mv.addObject("attention", attention);
        mv.setViewName("attention/edit_attention");
        return mv;
    }

    /**
     * 修改收藏夹名称
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/updateAttention")
    @ResponseBody
    public String updateAttention(@ModelAttribute("attention") Attention attention, HttpServletRequest request, HttpServletResponse response) {
        attention.setTime(new Date());
        this.attentionService.updateByPrimaryKeySelective(attention);
        return "";
    }

    /**
     * 跳转到修改页
     *
     * @param attentionInfoId
     * @return
     */
    @RequestMapping(value = "/toAddAttention")
    public ModelAndView toAddAttention() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("attention/add_attention");
        return mv;
    }

    /**
     * 添加收藏夹名称
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/saveAttention")
    @ResponseBody
    public String saveAttention(@ModelAttribute("attention") Attention attention, HttpServletRequest request, HttpServletResponse response) {
        attention.setTime(new Date());
        User user = (User) request.getSession().getAttribute("userfront");
        if (user != null) {
            attention.setUserId(String.valueOf(user.getUserId()));
        }
        attention.setAttentionId(this.idService.NextKey("attentionId"));
        this.attentionService.insertSelective(attention);
        return "";
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
        User user = (User) request.getSession().getAttribute("userfront");
        subjectPage.setUserid(user.getUserId());
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
     * 添加到收藏夹(eventnewsId)
     *
     * @param eventnewsId
     *            regionID
     * @return
     */
    @RequestMapping(value = "/addToAttentionByEvennews")
    @ResponseBody
    public int addToAttentionByEvennews(@ModelAttribute("Eventnews") Eventnews eventnews, @ModelAttribute("attention") AttentionInfo attentionInfo, HttpServletRequest request, HttpServletResponse response) {
        eventnews.setProvinceid(Integer.parseInt(eventnews.getRegionID().substring(0, 2)));
        if (eventnews.getId() != null) {
            eventnews = this.eventnewsService.load(eventnews);
            if (eventnews != null) {
                attentionInfo.setAttentionInfoId(this.idService.NextKey("attentionInfoId"));
                attentionInfo.setTitle(eventnews.getTitle());
                attentionInfo.setType(eventnews.getType());
                attentionInfo.setWebsite(eventnews.getWebsite());
                attentionInfo.setUrl(eventnews.getUrl());
                attentionInfo.setPtime(new Date());
                attentionInfo.setPublishdate(eventnews.getPublishdate());
                attentionInfo.setPolarity(eventnews.getPolarity());
                attentionInfo.setIsRead(0);
                attentionInfo.setSummary(eventnews.getSummary());
                attentionInfo.setPageId(eventnews.getPageid());
                attentionInfo.setNewsLevel(eventnews.getNewslevel());
                return this.attentionInfoService.insertSelective(attentionInfo);
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * 添加到收藏(warningId)
     *
     * @param warning
     * @param attentionInfo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addToAttentionByWarningId")
    @ResponseBody
    public int addToAttentionByWarningId(@ModelAttribute("Eventnews") Warning warning, @ModelAttribute("attention") AttentionInfo attentionInfo, HttpServletRequest request, HttpServletResponse response) {
        if (warning.getWarningId() != null) {
            warning = this.warningService.selectByPrimaryKey(warning.getWarningId());
            if (warning != null) {
                attentionInfo.setAttentionInfoId(this.idService.NextKey("attentionInfoId"));
                attentionInfo.setTitle(warning.getTitle());
                attentionInfo.setType(warning.getWaringType());
                attentionInfo.setWebsite(warning.getWebsite());
                attentionInfo.setUrl(warning.getUrl());
                attentionInfo.setPtime(new Date());
                attentionInfo.setPublishdate(warning.getPublishdate());
                attentionInfo.setPolarity(warning.getPolarity());
                attentionInfo.setIsRead(0);
                attentionInfo.setSummary(warning.getSummary());
                attentionInfo.setPageId(warning.getPageId());
                attentionInfo.setNewsLevel(warning.getNewsLevel());
                return this.attentionInfoService.insertSelective(attentionInfo);
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * 添加到收藏夹(briefreportId)
     *
     * @param briefreport
     * @param attentionInfo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addToAttentionByBriefreport")
    @ResponseBody
    public int addToAttentionByBriefreport(BriefreportInfo briefreportInfo, AttentionInfo attentionInfo, HttpServletRequest request, HttpServletResponse response) {
        if (briefreportInfo.getBriefreportInfoId() != null) {
            briefreportInfo = this.briefreportInfoService.selectByPrimaryKey(briefreportInfo.getBriefreportInfoId());
            attentionInfo.setAttentionInfoId(this.idService.NextKey("attentionInfoId"));
            attentionInfo.setTitle(briefreportInfo.getTitle());
            attentionInfo.setType(briefreportInfo.getType());
            attentionInfo.setWebsite(briefreportInfo.getWebsite());
            attentionInfo.setUrl(briefreportInfo.getUrl());
            attentionInfo.setPtime(new Date());
            attentionInfo.setPublishdate(briefreportInfo.getPublishdate());
            attentionInfo.setPolarity(briefreportInfo.getPolarity());
            attentionInfo.setIsRead(0);
            attentionInfo.setSummary(briefreportInfo.getSummary());
            attentionInfo.setPageId(briefreportInfo.getPageId());
            attentionInfo.setNewsLevel(briefreportInfo.getNewsLevel());
            return this.attentionInfoService.insertSelective(attentionInfo);
        } else {
            return 0;
        }
    }

    /**
     * 下载
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/downloadAttention")
    @ResponseBody
    public void download(AttentionInfo attentionInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // map内放模板需要的数据
        Map<String, Object> dataMap = new HashMap<String, Object>();
        List<AttentionInfo> listAttentionInfo = this.attentionInfoService.selectAttentionInfoByAttentionId(attentionInfo);
        // 显示正文、去除html标签
        for (int i = 0; i < listAttentionInfo.size(); i++) {
            AttentionInfo a = listAttentionInfo.get(i);
            if (a.getType() != 3 && a.getType() != 6) {
                a.setSummary(Snapshot.getTestContent(a.getPageId()));
            } else {
                a.setSummary(HTMLSpirit.delHTMLTag(a.getSummary()));
            }
            listAttentionInfo.set(i, a);
        }
        dataMap.put("list", listAttentionInfo);
        Attention attention = this.attentionService.selectByPrimaryKey(attentionInfo.getAttentionId());
        User user = (User) request.getSession().getAttribute("userfront");
        dataMap.put("name", user.getName());
        dataMap.put("title", "收藏夹");
        dataMap.put("secondTitle", attention.getName());
        dataMap.put("date", new Date());
        Date dt = new Date();
        // 生成文件名
        String fileName = attention.getName() + "_" + dt.getTime() + "_收藏夹.doc";
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
     * 跳转到添加专题页面
     *
     * @param briefreportInfoId
     * @return
     */
    @RequestMapping(value = "/toAddTopic/{attentionInfoId}")
    @ResponseBody
    public ModelAndView toAddTopic(@PathVariable("attentionInfoId") int attentionInfoId) {
        ModelAndView mv = new ModelAndView();
        AttentionInfo attentionInfo = this.attentionInfoService.selectByPrimaryKey(attentionInfoId);
        attentionInfo.setSummary(HTMLSpirit.delHTMLTag(attentionInfo.getSummary()));
        mv.addObject("topic", attentionInfo);
        mv.setViewName("subject/add_topic_info");
        return mv;
    }

}
