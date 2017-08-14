package com.peony.peonyfront.job.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.peony.core.base.controller.BaseController;
import com.peony.core.base.pojo.PageParameter;
import com.peony.core.base.pojo.Pagination;
import com.peony.core.common.utils.DateUtils;
import com.peony.peonyfront.attention.model.Attention;
import com.peony.peonyfront.attention.service.AttentionService;
import com.peony.peonyfront.briefreport.model.Briefreport;
import com.peony.peonyfront.briefreport.service.BriefreportService;
import com.peony.peonyfront.event.model.Eventnews;
import com.peony.peonyfront.event.service.ExportSupportService;
import com.peony.peonyfront.key.service.IdService;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.mail.model.MailTemp;
import com.peony.peonyfront.mail.service.MailTempService;
import com.peony.peonyfront.officeplatform.model.Mail;
import com.peony.peonyfront.operationlog.model.OperationLog;
import com.peony.peonyfront.operationlog.service.OperationLogService;
import com.peony.peonyfront.region.model.Region;
import com.peony.peonyfront.region.service.RegionService;
import com.peony.peonyfront.subject.model.Subject;
import com.peony.peonyfront.subject.model.SubjectPage;
import com.peony.peonyfront.subject.model.SubjectPage;
import com.peony.peonyfront.subject.model.WebDictionary;
import com.peony.peonyfront.subject.model.WebSite;
import com.peony.peonyfront.subject.model.ZTreeNode;
import com.peony.peonyfront.subject.service.SubjectPageService;
import com.peony.peonyfront.subject.service.SubjectService;
import com.peony.peonyfront.subject.service.WebDictionaryService;
import com.peony.peonyfront.subject.service.WebSiteService;
import com.peony.peonyfront.subjectkeywords.model.SubjectKeywords;
import com.peony.peonyfront.subjectkeywords.service.SubjectKeywordsService;
import com.peony.peonyfront.userregion.model.UserRegion;
import com.peony.peonyfront.userregion.service.UserRegionService;
import com.peony.peonyfront.userservice.model.UserService;
import com.peony.peonyfront.userservice.service.UserServiceService;
import com.peony.peonyfront.util.DocumentHandler;
import com.peony.peonyfront.util.HTMLSpirit;
import com.peony.peonyfront.util.JSONUtil;
import com.peony.peonyfront.util.Snapshot;
import com.peony.peonyfront.util.export.AssignedCell;
import com.peony.peonyfront.util.export.ExportExcel;
import com.peony.peonyfront.util.export.ExportExcelParameter;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;

/**
 * 订制舆情subject
 * 
 * @author zhyz
 * @version 1
 */
@Controller
@RequestMapping("/job")
public class JobController extends BaseController {

    private Log                    logger       = LogFactory.getLog(JobController.class);

    @Autowired
    private WebDictionaryService   webDictionaryService;                                 // 订制舆情页面信息来源服务
    // 0 3 4 5 境内 1，2表示境外
    private final static String    NEW_LEVEL_JN = "0,3,4,5";

    @Autowired
    private SubjectService         subjectService;

    @Autowired
    private SubjectPageService     subjectPageService;                                   // 订制舆情页面信息查询服务

    @Autowired
    private WebSiteService         webSiteService;                                       // 订制舆情页面网站信息查询服务

    @Autowired
    private AttentionService       attentionService;                                     // 用户收藏夹

    @Autowired
    private BriefreportService     briefreportService;                                   // 用户收藏夹
    @Autowired
    private MailTempService        mailTempService;

    @Autowired
    private OperationLogService    operationLogService;
    @Autowired
    protected ExportSupportService exportSupportService;

    /**
     * 跳转到分类添加页面
     * 
     * @return
     */
    @RequestMapping(value = "/toListSubjectSentimentOld")
    public ModelAndView toListSubjectSentimentOld(HttpServletRequest request) {
        // 通过获取用户id，查询当前用户订制舆情
        // 此处登陆后才能获取用户id，暂定为1
        // 0 3 4 5 境内 1，2表示境外
        User user = (User) request.getSession().getAttribute("userfront");
        int userid = user.getUserId();
        SubjectPage subjectPage = new SubjectPage();

        PageParameter pageParameter = new PageParameter();
        if (null == request.getParameter("pageNo")) {
            pageParameter.setCurrentPage(1);
        } else {
            pageParameter.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
        }
        if (null != request.getParameter("pagesize")) {
            pageParameter.setPageSize(Integer.parseInt(request.getParameter("pagesize")));
        } else {
            pageParameter.setPageSize(10);
        }
        subjectPage.setPageParameter(pageParameter);

        // 标题
        if (null != request.getParameter("title") && !"".equals(request.getParameter("title"))) {
            subjectPage.setTitle(request.getParameter("title"));
        }
        // 网站来源
        if (null != request.getParameter("type")) {
            subjectPage.setType(Integer.parseInt(request.getParameter("type")));
        }
        // 正负面
        if (null != request.getParameter("polarity")) {
            subjectPage.setPolarity(Integer.parseInt(request.getParameter("polarity")));
        }
        // 已读未读
        if (null != request.getParameter("isRead")) {
            subjectPage.setIsRead(Integer.parseInt(request.getParameter("isRead")));
        }
        // 处理时间
        if (null == request.getParameter("time")) {
            dealTime("2", subjectPage);
            subjectPage.setTime("2");
        } else {
            dealTime(request.getParameter("time"), subjectPage);
            subjectPage.setTime(request.getParameter("time"));
        }

        subjectPage.setUserid(1440);
        subjectPage.setNewslevelConditions(NEW_LEVEL_JN);
        Subject subject = new Subject();

        subject.setPid(Integer.parseInt(request.getParameter("subjectid")));
        List<Subject> list = subjectService.selectSubjectByUserIdAndPid(subject);
        String subjectidArray = request.getParameter("subjectid");
        List<Integer> tempList = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {

            // 记录当前Id
            subjectidArray = subjectidArray + "," + list.get(i).getId();
            // 记录父类节点id
            if (list.get(i).getIschildnodes().equals("0")) {
                tempList.add(list.get(i).getId());
            }
        }
        outer: if (tempList.size() > 0) {
            for (int j = 0; j < tempList.size(); j++) {
                Subject tempSubject = new Subject();
                tempSubject.setIschildnodes(null);
                tempSubject.setUserid(userid);
                tempSubject.setPid(tempList.get(j));
                List<Subject> tempSubjectList = subjectService.selectSubjectByUserIdAndPid(tempSubject);
                for (int k = 0; k < tempSubjectList.size(); k++) {
                    subjectidArray = subjectidArray + "," + tempSubjectList.get(k).getId();
                    if (tempSubjectList.get(k).getIschildnodes().equals("0")) {
                        tempList.add(tempSubjectList.get(k).getId());
                    }
                }
                tempList.remove(j);
                break outer;
            }
        }

        subjectPage.setSubjectidArray(subjectidArray);
        subjectPage.setSubjectid(Integer.parseInt(request.getParameter("subjectid")));

        Pagination<SubjectPage> pagination = subjectPageService.findByPage(subjectPage);
        logger.info("境内舆情获取到的信息条数  ============= " + pagination.getList().size());
        // 页面信息来源
        ModelAndView mv = new ModelAndView();
        subjectPage.setNewslev(0);
        mv.addObject("CurrentSubjectPage", subjectPage);
        mv.addObject("pagination", pagination);
        mv.setViewName("job/list_subject_right");
        return mv;
    }

    @RequestMapping(value = "/toListSubjectSentiment")
    public ModelAndView toListSubjectSentiment(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("job/list_subject_sentiment");

        User user = (User) request.getSession().getAttribute("userfront");

        Subject subject = new Subject();
        subject.setUserid(user.getUserId());
        List<Subject> list = subjectService.selectSubjectByUserIdExclusive(subject);
        if (list.size() == 0) {
            mv.setViewName("job/list_subject_tishi");
            return mv;
        }
        String homePageType = "";
        if (null != request.getParameter("type") && !"".equals(request.getParameter("type"))) {
            mv.addObject("homePage", "&type=" + request.getParameter("type"));
            homePageType = request.getParameter("type");
        }

        mv.addObject("homePageType", homePageType);

        if (null != request.getParameter("timeMethod") && !"".equals(request.getParameter("timeMethod"))) {
            mv.addObject("timeMethod", "1");
        } else {
            mv.addObject("timeMethod", "0");
        }

        OperationLog operationLog = new OperationLog(new Date(), user.getUserId(), user.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "定制舆情", OperateType.FIND.toString(), OperateMode.定制舆情.toString());
        this.operationLogService.insertSelective(operationLog);

        List<WebDictionary> webDictionaryList = webDictionaryService.findAllWebDictionary();
        mv.addObject("webDictionaryList", webDictionaryList);
        return mv;
    }

    /**
     * 获取Pagelist表
     * 
     * @return
     */
    private List<SubjectPage> getPageList(SubjectPage subjectPage, HttpServletRequest request, HttpServletResponse response) {
        // 通过获取用户id，查询当前用户订制舆情
        // 此处登陆后才能获取用户id，暂定为1
        User user = (User) request.getSession().getAttribute("userfront");
        int userid = user.getUserId();

        // 获取当前节点下的信息
        if (null != request.getParameter("subjectid") && !"".equals(request.getParameter("subjectid"))) {
            subjectPage.setSubjectid(Integer.parseInt(request.getParameter("subjectid")));
        }
        // if (subjectPage.getNewslev() == 0) {
        subjectPage.setNewslevelConditions(NEW_LEVEL_JN);
        // }
        // if (subjectPage.getNewslev() == 1) {
        // subjectPage.setNewslevelConditions(NEW_LEVEL_JW);
        // }
        subjectPage.setUserid(userid);
        if ("1".equals(request.getParameter("subjectid"))) {
            Subject subject = new Subject();
            subject.setUserid(userid);
            List<Subject> list = subjectService.selectSubjectByUserIdExclusive(subject);
            String subjectidArray = "";
            for (int i = 0; i < list.size(); i++) {
                if ("".equals(subjectidArray)) {
                    subjectidArray = list.get(i).getId().toString();
                } else {
                    subjectidArray = subjectidArray + "," + list.get(i).getId().toString();
                }

            }
            subjectPage.setSubjectidArray(subjectidArray);
            subjectPage.setSubjectid(1);
        } else {
            subjectPage.setSubjectidArray(request.getParameter("subjectid"));
            subjectPage.setSubjectid(Integer.parseInt(request.getParameter("subjectid")));
        }
        List<SubjectPage> listSubjectPage;
        // 相似信息单独显示
        if ("".equals(request.getParameter("isColspan")) || "1".equals(request.getParameter("isColspan"))) {
            if (subjectPageService.downExcelSelectSubjectPageByTimeNotColspanCount(subjectPage) < 5000) {
                listSubjectPage = subjectPageService.downExcelSelectSubjectPageByTimeNotColspan(subjectPage);
            } else {
                listSubjectPage = null;
            }

        } else {// 相似信息合并显示
            if (subjectPageService.downExcelSelectSubjectPageByTimeCount(subjectPage) < 5000) {
                listSubjectPage = subjectPageService.downExcelSelectSubjectPageByTime(subjectPage);
            } else {
                listSubjectPage = null;
            }
        }
        return listSubjectPage;
    }

    /**
     * 根据页面选择的时间段，来生成查询条件的开始时间、结束时间
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

    @RequestMapping(value = "/listRecruit")
    public ModelAndView listRecruit() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("job/list_subject_tishi");
        return mv;
    }
}
