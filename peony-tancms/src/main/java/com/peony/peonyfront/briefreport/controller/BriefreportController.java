package com.peony.peonyfront.briefreport.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.peony.peonyfront.briefreport.model.BriefReportTemp;
import com.peony.peonyfront.briefreport.model.Briefreport;
import com.peony.peonyfront.briefreport.model.BriefreportInfo;
import com.peony.peonyfront.briefreport.service.BriefreportInfoService;
import com.peony.peonyfront.briefreport.service.BriefreportService;
import com.peony.peonyfront.briefreport.service.BriefreportTempService;
import com.peony.peonyfront.event.model.Event;
import com.peony.peonyfront.event.model.Eventnews;
import com.peony.peonyfront.event.service.EventService;
import com.peony.peonyfront.event.service.EventServiceImpl;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.operationlog.model.OperationLog;
import com.peony.peonyfront.operationlog.service.OperationLogService;
import com.peony.peonyfront.subject.model.Subject;
import com.peony.peonyfront.subject.service.SubjectService;
import com.peony.peonyfront.util.DocumentHandler;
import com.peony.peonyfront.util.HTMLSpirit;
import com.peony.peonyfront.util.Snapshot;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;

/**
 * 舆情简报
 * 
 * @author zhyz
 * 
 */
@Controller
@RequestMapping("/briefreport")
public class BriefreportController extends BaseController {
    @Autowired
    private BriefreportService     briefreportService;
    @Autowired
    private BriefreportInfoService briefreportInfoService;
    @Autowired
    private BriefreportTempService briefreportTempService;
    @Autowired
    private EventService           eventService;
    @Autowired
    private SubjectService         subjectService;

    @Autowired
    private OperationLogService    operationLogService;

    /**
     * 跳转到舆情简报列表页面
     * 
     * @return
     */
    @RequestMapping(value = "/listBriefreport")
    public ModelAndView listBriefreport(@ModelAttribute("briefreport") Briefreport briefreport, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        // --uerId从session里面获取
        User user = (User) request.getSession().getAttribute("userfront");
        briefreport.setUserId(String.valueOf(user.getUserId()));
        PageParameter pageParameter = new PageParameter();
        if (null == request.getParameter("pageNo")) {
            pageParameter.setCurrentPage(1);
        } else {
            pageParameter.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
        }
        briefreport.setPageParameter(pageParameter);
        Pagination<Briefreport> pagination = briefreportService.selectByPage(briefreport);

        OperationLog operationLog = new OperationLog(new Date(), user.getUserId(), user.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "舆情简报", OperateType.FIND.toString(), OperateMode.舆情简报.toString());
        this.operationLogService.insertSelective(operationLog);

        mv.addObject("pagination", pagination);
        mv.addObject("briefreport", briefreport);
        mv.setViewName("briefreport/list_briefreport");
        return mv;
    }

    /**
     * 根据id删除简报
     * 
     * @param briefreport
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/delBriefreport")
    @ResponseBody
    public Map<String, Object> delBriefreport(@ModelAttribute("briefreport") Briefreport briefreport, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> mapModel = Maps.newHashMap();
        int totalCount = Integer.parseInt(request.getParameter("totalCount"));
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));
        int totalPage = 0;
        // 删除成功
        if (briefreportService.deleteByPrimaryKey(briefreport.getBriefreportId()) > 0) {
            mapModel.put("state", true);
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
     * 保存简报
     * 
     * @param briefreport
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/saveBriefreport")
    @ResponseBody
    public String saveBriefreport(@ModelAttribute("briefreport") Briefreport briefreport, HttpServletRequest request, HttpServletResponse response) {
        // --用户id
        User user = (User) request.getSession().getAttribute("userfront");
        briefreport.setUserId(String.valueOf(user.getUserId()));
        briefreport.setTime(new Date());
        this.briefreportService.insertSelective(briefreport);
        briefreport.setName(null);
        return "";
    }

    /**
     * 保存修改的信息
     * 
     * @param briefreport
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/updateBriefreport")
    @ResponseBody
    public String uopdateBriefreport(@ModelAttribute("briefreport") Briefreport briefreport, HttpServletRequest request, HttpServletResponse response) {
        briefreport.setTime(new Date());
        this.briefreportService.updateByPrimaryKeySelective(briefreport);
        return "";
    }

    /**
     * 跳转到添加简报页面
     * 
     * @return
     */
    @RequestMapping(value = "/toAddBriefreport")
    @ResponseBody
    public ModelAndView toSaveBriefreport() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("briefreport/add_briefreport");
        return mv;
    }

    /**
     * 跳转到修改简报页面
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/toEditBriefreport/{briefreportId}")
    public ModelAndView toEditBriefreport(@PathVariable("briefreportId") int briefreportId) {
        ModelAndView mv = new ModelAndView();
        Briefreport briefreport = this.briefreportService.selectByPrimaryKey(briefreportId);
        mv.addObject("briefreport", briefreport);
        mv.setViewName("briefreport/edit_briefreport");
        return mv;
    }

    /**
     * 下载
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/downloadBrifreport")
    @ResponseBody
    public void downloadBrifreport(BriefreportInfo briefreportInfo, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("userfront");
        SimpleDateFormat datatime = new SimpleDateFormat("yyyyMMdd");
        // map内放模板需要的数据
        Map<String, Object> dataMap = new HashMap<String, Object>();
        List<BriefreportInfo> listBriefreportInfo = this.briefreportInfoService.selectBriefreportInfoByBreiefreportId(briefreportInfo);
        // 显示正文、去除html标签
        for (int i = 0; i < listBriefreportInfo.size(); i++) {
            BriefreportInfo a = listBriefreportInfo.get(i);
            if (a.getType() != 3 && a.getType() != 6) {
                a.setSummary(Snapshot.getTestContent(a.getPageId()));
            } else {
                a.setSummary(HTMLSpirit.delHTMLTag(a.getSummary()));
            }
            listBriefreportInfo.set(i, a);
        }
        dataMap.put("list", listBriefreportInfo);
        Briefreport briefreport = this.briefreportService.selectByPrimaryKey(briefreportInfo.getBriefreportId());
        dataMap.put("name", user.getName());
        dataMap.put("title", "舆情简报");
        dataMap.put("secondTitle", "第" + briefreport.getPeriods() + "期");
        Date dt = new Date();
        dataMap.put("date", dt);
        // 生成文件名
        String fileName = briefreport.getName() + "_" + dt.getTime() + "_简报.doc";
        String template = "";

        // --模板(从数据库查找所需要模板)
        BriefReportTemp briefreportTemp = this.briefreportTempService.findTemByUserId(user.getUserId());
        // 企业用户
        if ("1".equals(user.getUserType())) {
            dataMap.put("industry", briefreport.getCompany());
            template = "TemplateIndustry.ftl";
            List<BriefreportInfo> IList = this.briefreportInfoService.selectEventByBreiefreportId(briefreportInfo);
            List<Event> ilist = new ArrayList<Event>();
            for (int i = 0; i < IList.size(); i++) {
                ilist.add(this.eventService.selectByPrimaryKey(IList.get(i).getEventId()));
            }
            dataMap.put("Ilist", ilist);
            List<BriefreportInfo> SList = this.briefreportInfoService.selectSubjectByBreiefreportId(briefreportInfo);
            List<Subject> slist = new ArrayList<Subject>();
            for (int i = 0; i < SList.size(); i++) {
                slist.add(this.subjectService.selectByPrimaryKey(SList.get(i).getSubjectId()));
            }
            dataMap.put("Slist", slist);
        } else {// 政府用户
            if (null != briefreportTemp) {
                template = briefreportTemp.getAddress();
            } else {
                template = "Template.ftl";
            }
        }

        DocumentHandler doc = new DocumentHandler();
        doc.createDoc(fileName, template, dataMap, request, response);
    }
}
