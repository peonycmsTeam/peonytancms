package com.peony.peonyfront.subject.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.peony.peonyfront.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping("/subject")
public class SubjectController extends BaseController {

    private final Log              logger       = LogFactory.getLog(SubjectController.class);

    /** 境内 */
    private final static String    NEW_LEVEL_JN = "0,3,4,5";

    /** 境外 */
    private final static String    NEW_LEVEL_JW = "1,2";

    @Autowired
    private IdService              idService;                                                // id服务接口

    @Autowired
    private SubjectService         subjectService;

    @Autowired
    private UserServiceService     userServiceService;

    @Autowired
    private SubjectKeywordsService subjectKeywordsService;

    @Autowired
    private UserRegionService      userRegionService;

    @Autowired
    private RegionService          regionService;

    @Autowired
    private WebDictionaryService   webDictionaryService;                                     // 订制舆情页面信息来源服务

    @Autowired
    private SubjectPageService     subjectPageService;                                       // 订制舆情页面信息查询服务

    @Autowired
    private WebSiteService         webSiteService;                                           // 订制舆情页面网站信息查询服务

    @Autowired
    private AttentionService       attentionService;                                         // 用户收藏夹

    @Autowired
    private BriefreportService     briefreportService;                                       // 用户收藏夹
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
    @RequestMapping(value = "/listSubject", method = RequestMethod.GET)
    public ModelAndView listSubject(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        // --暂设userId为1
        User user = (User) request.getSession().getAttribute("userfront");
        int userId = user.getUserId();
        List<Subject> list = this.subjectService.selectUserKeywsByUserId(userId);
        Subject s = new Subject();
        s.setId(0);
        s.setName("舆情分类");
        list.add(s);
        List<ZTreeNode> nodes = this.nodeList(list, "");
        mv.addObject("nodeList", JSONUtil.array2JSON2(nodes.toArray()));
        mv.setViewName("subject/list_subject");
        return mv;
    }

    /**
     * 跳转到添加节点页面
     *
     * @return
     */
    @RequestMapping(value = "/addSubject/{subjectId}")
    @ResponseBody
    public Map<String, Object> addUserkeyws(@PathVariable("subjectId") int subjectId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> mapModel = Maps.newHashMap();
        List<SubjectKeywords> SKList = this.subjectKeywordsService.selectSubjectListBySubjectId(subjectId);
        // 该节点下是否有关键词
        if (SKList.size() > 0) {
            // 有关键词，提示用户有关键词，不能添加节点
            mapModel.put("hasKeywords", true);
        } else {
            // 没有关键词，显示节点名称,跳转到添加节点页面
            mapModel.put("hasKeywords", false);
        }
        return mapModel;
    }

    /**
     * 跳转到修改节点页面
     *
     * @return
     */
    @RequestMapping(value = "/editNodes/{subjectId}")
    @ResponseBody
    public ModelAndView editNodes(@PathVariable("subjectId") int subjectId) {
        ModelAndView mv = new ModelAndView();
        Subject s = this.subjectService.selectByPrimaryKey(subjectId);
        mv.addObject("subject", s);
        mv.setViewName("subject/edit_nodes");
        return mv;
    }

    /**
     * 修改节点名称
     *
     * @return
     */
    @RequestMapping(value = "/updateNodes")
    @ResponseBody
    public String updateNodes(@ModelAttribute("subject") Subject subject) {
        this.subjectService.updateByPrimaryKeySelective(subject);
        return "";
    }

    /**
     * 跳转到添加节点页id
     * @return
     */
    @RequestMapping(value = "/addSubjectKeywords/{subjectId}")
    public ModelAndView addSubjectKeywords(@PathVariable("subjectId") int subjectId) {
        ModelAndView mv = new ModelAndView();
        Subject s = new Subject();
        if (subjectId == 0) {
            s.setId(0);
            s.setName("舆情分类");
        } else {
            s = this.subjectService.selectByPrimaryKey(subjectId);
        }
        mv.addObject("subject", s);
        mv.setViewName("subject/add_subject");
        return mv;
    }

    /**
     * 点击分类节点，显示分类修改页
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/editSubject/{id}")
    public ModelAndView editUserkeyws(@PathVariable("id") int id, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Subject userKeyws = this.subjectService.selectByPrimaryKey(id);
        mv.addObject("userkeyws", userKeyws);
        // --暂设用户id为1
        User user = (User) request.getSession().getAttribute("userfront");
        int userId = user.getUserId();
        // --获取用户角色，政府角色为true
        Boolean role = true;
        // 如果是政府角色
        if (role) {
            mv.addObject("role", true);
            mv.addObject("area", this.getAreaNames(userId));
        }
        List<SubjectKeywords> SKList = this.subjectKeywordsService.selectSubjectListBySubjectId(id);
        for (SubjectKeywords sk : SKList) {
            // 地域名称
            if ("area".equals(sk.getName())) {
                // 如果不是政府角色
                if (!role) {
                    mv.addObject("area", sk.getKeywords());
                }
            }
            // 主关键词
            if ("main_keywords".equals(sk.getName())) {
                mv.addObject("mainKeywords", sk.getKeywords());
            }
            // 倾向性分析词
            if ("deputy_keywords".equals(sk.getName())) {
                mv.addObject("deputyKeywords", sk.getKeywords());
            }
            // 倾向性分析词第一添加词
            if ("deputy_keywords".equals(sk.getName())) {
                mv.addObject("deputyKeywords", sk.getKeywords());
            }
            // 过滤词
            if ("ruleout_keywords".equals(sk.getName())) {
                mv.addObject("ruleoutKeywords", sk.getKeywords());
            }
            // 过滤词第一个添加词
            if ("ruleout_keywords_appendOne".equals(sk.getName())) {
                mv.addObject("ruleoutKeywordsAppendOne", sk.getKeywords());
            }
            // 过滤词第二个添加词
            if ("ruleout_keywords_appendTwo".equals(sk.getName())) {
                mv.addObject("ruleoutKeywordsAppendTwo", sk.getKeywords());
            }
        }

        mv.setViewName("subject/edit_subjectKeywords");
        return mv;
    }

    /**
     * 获取政府用户名下所属地域名称
     *
     * @param userId
     * @return
     */
    private String getAreaNames(int userId) {
        UserRegion userRegion = this.userRegionService.selectByUserId(userId);
        StringBuffer area = new StringBuffer();
        if (userRegion != null) {
            List<Region> RList = this.regionService.selectByParentId(userRegion.getRegionId());
            area.append(this.regionService.selectByPrimaryKey(userRegion.getRegionId()).getRegionname());
            for (Region r : RList) {
                area.append(" " + r.getRegionname());
            }
        }
        return area.toString();
    }

    /**
     * 查看该节点是否为子节点
     */
    @RequestMapping(value = "/isChildnodes/{id}")
    @ResponseBody
    public Map<String, Object> isChildnodes(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> mapModel = Maps.newHashMap();
        List<Subject> list = this.subjectService.selectChildnodesById(id);
        if (list.size() > 0) {
            mapModel.put("isChild", false);
        } else {
            mapModel.put("isChild", true);
        }
        return mapModel;
    }

    /**
     * 删除子节点
     */
    @RequestMapping(value = "/delNode/{id}")
    @ResponseBody
    public Map<String, Object> delNode(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> mapModel = Maps.newHashMap();
        List<Subject> list = this.subjectService.selectChildnodesById(id);
        if (list.size() > 0) {
            mapModel.put("hasChild", false);
        } else {
            mapModel.put("hasChild", true);
            // 删除分类关键词表中关于该分类节点的关键词记录
            this.subjectKeywordsService.deleteBySubjectId(id);
            // 将该节点软删除，状态置为0，表示删除
            Subject subject = this.subjectService.selectByPrimaryKey(id);
            subject.setState(0);
            this.subjectService.updateByPrimaryKeySelective(subject);
        }
        return mapModel;
    }

    /**
     * 将子节点设为根节点
     */
    @RequestMapping(value = "/changeNode/{id}")
    @ResponseBody
    public String changeNode(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) {
        // 删除分类关键词表中关于该分类节点的关键词记录
        this.subjectKeywordsService.deleteBySubjectId(id);
        // 将该节点软删除，状态置为0，表示删除
        Subject subject = this.subjectService.selectByPrimaryKey(id);
        subject.setIschildnodes("1");
        this.subjectService.updateByPrimaryKeySelective(subject);
        return "";
    }

    /**
     * 添加舆情分类
     *
     * @param userkeyws
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/saveNodes", method = RequestMethod.POST)
    @ResponseBody
    public String saveNodes(@ModelAttribute("subject") Subject subject, HttpServletRequest request, HttpServletResponse response) {
        // -暂设userId为1
        int userid = 1;
        subject.setUserid(userid);
        subject.setCreatetime(new Date());
        subject.setState(1);
        subject.setId(this.idService.NextKey("id"));
        subject.setIschildnodes("1");
        UserService userService = this.userServiceService.selectByPrimaryKey(userid);
        if (userService != null) {
            subject.setDroptime(userService.getDeadline());
        }
        subject.setIschildnodes("1");
        this.subjectService.insertSelective(subject);
        return "";
    }

    /**
     * 返回ztree列表
     *
     * @param list
     * @return
     */
    public List<ZTreeNode> nodeList(List<Subject> list, String checkid) {
        List<ZTreeNode> nodeList = new ArrayList<ZTreeNode>(list.size());
        Integer pid = 0;
        for (Subject dpt1 : list) {
            if (checkid.equals(String.valueOf(dpt1.getPid()))) {
                pid = dpt1.getPid();
                break;
            }
        }
        for (Subject dpt : list) {
            ZTreeNode node = new ZTreeNode();
            if (pid.equals(dpt.getPid())) {
                node.setChecked(true);
            }
            node.setId(String.valueOf(dpt.getId()));
            node.setPId(String.valueOf(dpt.getPid()));
            node.setName(dpt.getName());
            node.setOpen(true);

            nodeList.add(node);
        }
        return nodeList;
    }

    /**
     * 跳转到订制舆情页面
     *
     * @return
     */
    @RequestMapping(value = "/toListSubjectSentimentOld")
    public ModelAndView toListSubjectSentimentOld(HttpServletRequest request, HttpServletResponse response) {
        /*通过获取用户id，查询当前用户订制舆情
          此处登陆后才能获取用户id，暂定为1
          0 3 4 5 境内 1，2表示境外*/
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
        if (null != request.getParameter("title") && StringUtils.isNotBlank(request.getParameter("title"))) {
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
            this.dealTime("2", subjectPage);
            subjectPage.setTime("2");
        } else {
            this.dealTime(request.getParameter("time"), subjectPage);
            subjectPage.setTime(request.getParameter("time"));
        }
        subjectPage.setUserid(userid);
        subjectPage.setNewslevelConditions(NEW_LEVEL_JN);
        Subject subject = new Subject();
        if ("1".equals(request.getParameter("subjectid"))) {
            subject.setUserid(userid);
            List<Subject> list = this.subjectService.selectSubjectByUserIdExclusive(subject);
            String subjectidArray = "";
            for (int i = 0; i < list.size(); i++) {
                if (StringUtils.isBlank(subjectidArray)) {
                    subjectidArray = list.get(i).getId().toString();
                } else {
                    subjectidArray = subjectidArray + "," + list.get(i).getId().toString();
                }
            }
            subjectPage.setSubjectidArray(subjectidArray);
            subjectPage.setSubjectid(1);
        } else {
            subject.setUserid(userid);
            subject.setPid(Integer.parseInt(request.getParameter("subjectid")));
            List<Subject> list = this.subjectService.selectSubjectByUserIdAndPid(subject);
            String subjectidArray = request.getParameter("subjectid");
            Queue<Integer> fifoQueue = new LinkedList<Integer>();
            for (Subject subj : list) {
                // 记录当前Id
                subjectidArray = subjectidArray + "," + subj.getId();
                // 记录父类节点id
                if ("0".equals(subj.getIschildnodes())) {
                    fifoQueue.offer(subj.getId());
                }
            }
            while (!fifoQueue.isEmpty()) {
                Integer pid = fifoQueue.poll();
                Subject tempSubject = new Subject();
                tempSubject.setIschildnodes(null);
                tempSubject.setUserid(userid);
                tempSubject.setPid(pid);
                List<Subject> tempSubjectList = this.subjectService.selectSubjectByUserIdAndPid(tempSubject);
                for (Subject tempSubj : tempSubjectList) {
                    subjectidArray = subjectidArray + "," + tempSubj.getId();
                    if ("0".equals(tempSubj.getIschildnodes())) {
                        fifoQueue.offer(tempSubj.getId());
                    }
                }
            }
            subjectPage.setSubjectidArray(subjectidArray);
            subjectPage.setSubjectid(Integer.parseInt(request.getParameter("subjectid")));
        }
        Pagination<SubjectPage> pagination = this.subjectPageService.findByPage(subjectPage);
        if(pagination!=null){
            this.logger.info("境内舆情获取到的信息条数:" + pagination.getList().size());
        }
        // 页面信息来源
        ModelAndView mv = new ModelAndView();
        subjectPage.setNewslev(0);
        mv.addObject("CurrentSubjectPage", subjectPage);
        mv.addObject("pagination", pagination);
        mv.setViewName("subject/list_subject_right");
        return mv;
    }

    @RequestMapping(value = "/toListSubjectSentiment")
    public ModelAndView toListSubjectSentiment(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("subject/list_subject_sentiment");

        User user = (User) request.getSession().getAttribute("userfront");
        Subject subject = new Subject();
        subject.setUserid(user.getUserId());
        List<Subject> list = this.subjectService.selectSubjectByUserIdExclusive(subject);
        if (CollectionUtils.isEmpty(list)) {
            mv.setViewName("subject/list_subject_tishi");
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

        List<WebDictionary> webDictionaryList = this.webDictionaryService.findAllWebDictionary();
        mv.addObject("webDictionaryList", webDictionaryList);
        return mv;

    }

    @RequestMapping(value = "/toListOverseasSubjectSentiment")
    public ModelAndView toListOverseasSubjectSentiment(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("subject/list_overseas_subject_sentiment");

        User user = (User) request.getSession().getAttribute("userfront");

        Subject subject = new Subject();
        subject.setUserid(user.getUserId());
        List<Subject> list = this.subjectService.selectSubjectByUserIdExclusive(subject);
        if (list.size() == 0) {
            mv.setViewName("subject/list_subject_tishi");
            return mv;
        }

        OperationLog operationLog = new OperationLog(new Date(), user.getUserId(), user.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "境外舆情", OperateType.FIND.toString(), OperateMode.境外舆情.toString());
        this.operationLogService.insertSelective(operationLog);

        List<WebDictionary> webDictionaryList = this.webDictionaryService.findAllWebDictionary();
        mv.addObject("webDictionaryList", webDictionaryList);
        return mv;
    }

    /**
     * 跳转到境外舆情页面
     *
     * @return
     */
    @RequestMapping(value = "/toListOverseasSubjectSentimentOld")
    public ModelAndView toListOverseasSubjectSentimentOld(HttpServletRequest request, HttpServletResponse response) {
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
            this.dealTime("2", subjectPage);
            subjectPage.setTime("2");
        } else {
            this.dealTime(request.getParameter("time"), subjectPage);
            subjectPage.setTime(request.getParameter("time"));
        }

        subjectPage.setUserid(userid);
        subjectPage.setNewslevelConditions(NEW_LEVEL_JW);
        Subject subject = new Subject();
        if ("1".equals(request.getParameter("subjectid"))) {
            subject.setUserid(userid);
            List<Subject> list = this.subjectService.selectSubjectByUserIdExclusive(subject);
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
            subject.setUserid(userid);
            subject.setPid(Integer.parseInt(request.getParameter("subjectid")));
            List<Subject> list = this.subjectService.selectSubjectByUserIdAndPid(subject);
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
                    List<Subject> tempSubjectList = this.subjectService.selectSubjectByUserIdAndPid(tempSubject);
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
        }
        Pagination<SubjectPage> pagination = this.subjectPageService.findByPage(subjectPage);
        if(pagination!=null){
            this.logger.info("境外舆情获取到的信息条数:" + pagination.getList().size());
        }
        // 页面信息来源
        ModelAndView mv = new ModelAndView();
        subjectPage.setNewslev(1);
        mv.addObject("CurrentSubjectPage", subjectPage);
        mv.addObject("pagination", pagination);
        mv.setViewName("subject/list_subject_right");
        return mv;
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

    /**
     * 展示网站信息
     *
     * @return
     */
    @RequestMapping(value = "/toShowWebInfo/{url}")
    public ModelAndView toShowWebInfo(@PathVariable String url, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        WebSite webSite = new WebSite();
        webSite.setUrl(url);
        List<WebSite> list = this.webSiteService.findWebSiteByUrl(webSite);
        if (null == list || list.size() == 0) {
            list = new ArrayList<WebSite>();
            mv.addObject("resultMsg", "0");
        } else {
            mv.addObject("webSite", list.get(0));
            mv.addObject("resultMsg", "1");
        }
        mv.setViewName("subject/show_web_info");
        return mv;
    }

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteSubjectPage", method = RequestMethod.POST)
    public @ResponseBody int deleteSubjectPage(@RequestParam(value = "ids[]", required = true) String[] ids, HttpServletRequest request) {
        SubjectPage subjectPage = new SubjectPage();
        // 从缓存中加载用户id
        User user = (User) request.getSession().getAttribute("userfront");
        int userId = user.getUserId();
        subjectPage.setUserid(userId);
        String result = "";
        for (int i = 0; i < ids.length; i++) {
            if (i == 0) {
                result = "'" + ids[i] + "'";
            } else {
                result = result + "," + "'" + ids[i] + "'";
            }
        }
        subjectPage.setIdArray(result);
        int totalCount = Integer.parseInt(request.getParameter("totalCount"));
        int pagesize = Integer.parseInt(request.getParameter("pagesize"));
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));
        if (this.subjectPageService.batchDelete(subjectPage) > 0) {
            int totalPage = 0;
            if ((totalCount - ids.length) % pagesize == 0) {
                totalPage = (totalCount - ids.length) / pagesize;
            } else {
                totalPage = (totalCount - ids.length) / pagesize + 1;
            }
            if (totalPage == 0) {
                return 1;
            } else {
                if (pageNo <= totalPage) {
                    return pageNo;
                } else {
                    return totalPage;
                }
            }
        } else {
            return 0;
        }
    }

    /**
     * 修改倾向性
     *
     * @param subjectPage
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateSubjectPagePolarity", method = RequestMethod.POST)
    public @ResponseBody int updateSubjectPagePolarity(@ModelAttribute("subjectPage") SubjectPage subjectPage, HttpServletRequest request) {
        // 从缓存中加载用户id
        User user = (User) request.getSession().getAttribute("userfront");
        int userId = user.getUserId();
        subjectPage.setUserid(userId);
        return this.subjectPageService.update(subjectPage);
    }

    /**
     * 快照接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/loadSnapshot")
    public ModelAndView loadSnapshot(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("subject/show_snapshot");
        mv.addObject("html", Snapshot.getHTMLContent(request.getParameter("pageid")));
        return mv;
    }

    /**
     * 订制信息详细页
     *
     * @return
     */
    @RequestMapping(value = "/loadSubjectPage")
    public ModelAndView loadSubjectPage(HttpServletRequest request, HttpServletResponse response) {
        SubjectPage subjectPage = new SubjectPage();
        List<SubjectPage> list = new ArrayList<SubjectPage>();
        ModelAndView mv = new ModelAndView();
        if (null != request.getParameter("currentId") && !"".equals(request.getParameter("currentId"))) {
            subjectPage.setId(request.getParameter("currentId"));
            subjectPage.setIsRead(1);
            // 从缓存中加载用户id
            User user = (User) request.getSession().getAttribute("userfront");
            int userId = user.getUserId();
            subjectPage.setUserid(userId);
            this.subjectPageService.updateSubjectPageIsRead(subjectPage);
            subjectPage = this.subjectPageService.load(subjectPage);
            subjectPage.setGroupseedid(request.getParameter("groupseedid"));
            subjectPage.setUrl(SubjectPageUtil.correctURL(subjectPage.getUrl()));
            list = this.subjectPageService.selectSubjectPageByGroupseedid(subjectPage);
            if (NEW_LEVEL_JW.contains(subjectPage.getNewslevel().toString())) {
                if (subjectPage.getType() == 6) {
                    mv.addObject("html", subjectPage.getSummary());
                } else {
                    mv.addObject("html", Snapshot.getHTMLContent(subjectPage.getPageid()));
                }
                mv.setViewName("subject/show_page_ywinfo");
            } else {
                mv.setViewName("subject/show_page_info");
            }
        }

        mv.addObject("subjectPage", subjectPage);
        mv.addObject("list", list);
        return mv;
    }

    /**
     * 添加事件专题信息
     *
     * @return
     */
    @RequestMapping(value = "/toAddTopicInfo/{id}")
    public ModelAndView toAddTopicInfo(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        SubjectPage subjectPage = new SubjectPage();
        subjectPage.setId(id);
        subjectPage = this.subjectPageService.load(subjectPage);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("subject/add_topic_info");
        mv.addObject("subjectPage", subjectPage);
        return mv;
    }

    /**
     * 添加收藏夹信息
     *
     * @return
     */
    @RequestMapping(value = "/showAttention/{pageId}")
    public ModelAndView showAttention(@PathVariable String pageId, HttpServletRequest request, HttpServletResponse response) {
        // 根据用户id查询当前用户的收藏夹
        Attention attention = new Attention();
        User user = (User) request.getSession().getAttribute("userfront");
        int userId = user.getUserId();
        attention.setUserId(Integer.toString(userId));
        List<Attention> list = this.attentionService.findAll(attention);
        if (list == null) {
            list = new ArrayList<Attention>();
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("attentionList", list);
        mv.setViewName("subject/show_attention");
        mv.addObject("pageId", pageId);
        return mv;
    }

    /**
     * 添加简报信息
     *
     * @return
     */
    @RequestMapping(value = "/showBriefreport/{pageId}")
    public ModelAndView showBriefreport(@PathVariable String pageId, HttpServletRequest request, HttpServletResponse response) {
        // 根据用户id查询当前用户的收藏夹
        Briefreport briefreport = new Briefreport();
        User user = (User) request.getSession().getAttribute("userfront");
        int userId = user.getUserId();
        briefreport.setUserId(Integer.toString(userId));
        List<Briefreport> list = this.briefreportService.findAll(briefreport);
        if (list == null) {
            list = new ArrayList<Briefreport>();
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("briefreportList", list);
        mv.setViewName("subject/show_briefreport");
        mv.addObject("pageId", pageId);
        return mv;
    }

    /**
     * 按id导出(word)
     *
     * @param ids
     * @param request
     * @param response
     */
    @RequestMapping(value = "/downloadByIds/{ids}")
    @ResponseBody
    public void downloadByIds(@PathVariable("ids") String ids, HttpServletRequest request, HttpServletResponse response) {
        SimpleDateFormat datatime = new SimpleDateFormat("yyyyMMdd");
        // map内放模板需要的数据
        Map<String, Object> dataMap = new HashMap<String, Object>();
        String[] pageIds = ids.split(",");
        List<SubjectPage> listSubjectPage = this.subjectPageService.selectSubjectPageByIds(pageIds);
        // 显示正文、去除html标签
        for (int i = 0; i < listSubjectPage.size(); i++) {
            SubjectPage a = listSubjectPage.get(i);
            if (a.getType() != 3 && a.getType() != 6) {
                a.setSummary(Snapshot.getTestContent(a.getPageid()));
            } else {
                a.setSummary(HTMLSpirit.delHTMLTag(a.getSummary()));
            }
            listSubjectPage.set(i, a);
        }
        dataMap.put("list", listSubjectPage);
        User user = (User) request.getSession().getAttribute("userfront");
        dataMap.put("name", user.getName());
        Date dt = new Date();
        dataMap.put("date", dt);
        dataMap.put("title", "定制舆情");
        // 生成文件名
        String fileName = user.getName() + "_" + dt.getTime() + "_舆情.doc";
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
     * 按id导出Excel 选择合并或单独显示
     *
     * @param ids
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/toDownloadExcelByIds/{ids}")
    @ResponseBody
    public ModelAndView toDownloadExcelByIds(@PathVariable("ids") String ids, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("ids", ids);
        mv.setViewName("subject/select_exportWay");
        return mv;
    }

    /**
     * 按id导出(excel)
     *
     * @param ids
     * @param request
     * @param response
     */
    @RequestMapping(value = "/downloadExcelByIds")
    @ResponseBody
    public Object downloadExcelByIds(HttpServletRequest request, HttpServletResponse response) {
        String ids = request.getParameter("ids");
        String isColspan = request.getParameter("isColspan");
        String[] pageIds = ids.split(",");
        List<SubjectPage> listSubjectPage;
        // 显示正文、去除html标签
        // for (int i = 0; i < listSubjectPage.size(); i++) {
        // SubjectPage a = listSubjectPage.get(i);
        // a.setSummary(HTMLSpirit.delHTMLTag(a.getSummary()));
        // listSubjectPage.set(i, a);
        // }
        ExportExcelParameter para = new ExportExcelParameter();
        List<AssignedCell[]> data;
        if ("".equals(request.getParameter("isColspan")) || "1".equals(request.getParameter("isColspan"))) {// 相似信息单独显示
            // para.setTemplateName("/template/exportColspan.xls");
            listSubjectPage = this.subjectPageService.selectSubjectPageByIdsNotColspan(pageIds);
            para.setTemplateName("/template/export.xls");
            data = this.getAssignedCellData(listSubjectPage);
        } else {
            // para.setTemplateName("/template/export.xls");//相似信息合并显示
            listSubjectPage = this.subjectPageService.selectSubjectPageByIds(pageIds);
            para.setTemplateName("/template/exportColspan.xls");
            data = this.getAssignedCellDataColspan(listSubjectPage);
        }
        User user = (User) request.getSession().getAttribute("userfront");
        Date dt = new Date();
        // 生成文件名
        // List<AssignedCell[]> data=getAssignedCellData(listSubjectPage);
        String title = "舆情";
        String downName = user.getName() + "_" + dt.getTime() + "_" + title + ".xls";
        ExportExcel ee = new ExportExcel();
        return ee.exportExcelByTemplateByTime(title, downName, response, request.getSession(), data, this.exportSupportService, para);
    }

    /**
     * 弹出选择时间页面
     *
     * @return
     */
    @RequestMapping(value = "/toExportByTime/{val}")
    @ResponseBody
    public ModelAndView toExportByTime(@PathVariable("val") String val) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("val", val);
        mv.setViewName("subject/select_subjectPageTime");
        return mv;
    }

    /**
     * 按选择时间下载(word)
     *
     * @param warning
     * @param request
     * @param response
     */
    @RequestMapping(value = "/downloadBySelectTime")
    @ResponseBody
    public void downloadBySelectTime(SubjectPage subjectPage, HttpServletRequest request, HttpServletResponse response) {
        SimpleDateFormat datatime = new SimpleDateFormat("yyyyMMdd");
        // map内放模板需要的数据
        Map<String, Object> dataMap = new HashMap<String, Object>();
        User user = (User) request.getSession().getAttribute("userfront");
        dataMap.put("name", user.getName());
        subjectPage.setBeginTime(subjectPage.getStartTime() + " 00:00:00");
        subjectPage.setEndTime(subjectPage.getOverTime() + " 23:59:59");
        subjectPage.setNewslevel(subjectPage.getNewslev());
        List<SubjectPage> listSubjectPage = this.getPageList(subjectPage, request, response);
        // 显示正文、去除html标签
        for (int i = 0; i < listSubjectPage.size(); i++) {
            SubjectPage a = listSubjectPage.get(i);
            if (a.getType() != 3 && a.getType() != 6) {
                a.setSummary(Snapshot.getTestContent(a.getPageid()));
            } else {
                a.setSummary(HTMLSpirit.delHTMLTag(a.getSummary()));
            }
            listSubjectPage.set(i, a);
        }
        Date dt = new Date();
        dataMap.put("list", listSubjectPage);
        dataMap.put("date", dt);
        dataMap.put("title", "定制舆情");
        // 生成文件名
        String fileName = user.getName() + "_" + dt.getTime() + "_舆情.doc";
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
     * 按选择时间下载(excel)
     *
     * @param warning
     * @param request
     * @param response
     */
    @RequestMapping(value = "/downloadExcelBySelectTime")
    @ResponseBody
    public ModelAndView downloadExcelBySelectTime(SubjectPage subjectPage, HttpServletRequest request, HttpServletResponse response) {
        ExportExcelParameter para = new ExportExcelParameter();
        User user = (User) request.getSession().getAttribute("userfront");
        subjectPage.setBeginTime(subjectPage.getStartTime());
        subjectPage.setEndTime(subjectPage.getOverTime());
        subjectPage.setNewslevel(subjectPage.getNewslev());
        List<SubjectPage> listSubjectPage = this.getPageList(subjectPage, request, response);
        ModelAndView mv = new ModelAndView();
        if (listSubjectPage == null) {
            mv = this.toListSubjectSentiment(request, response);
            mv.addObject("error", "信息数大于5000，请选择更短的时间间隔。");
            return mv;
        }
        // // 显示正文、去除html标签
        // for (int i = 0; i < listSubjectPage.size(); i++) {
        // SubjectPage a = listSubjectPage.get(i);
        // a.setSummary(HTMLSpirit.delHTMLTag(a.getSummary()));
        // listSubjectPage.set(i, a);
        // }
        List<AssignedCell[]> data;
        if ("".equals(request.getParameter("isColspan")) || "1".equals(request.getParameter("isColspan"))) {// 相似信息单独显示
            // para.setTemplateName("/template/exportColspan.xls");
            para.setTemplateName("/template/export.xls");
            data = this.getAssignedCellData(listSubjectPage);
        } else {
            // para.setTemplateName("/template/export.xls");//相似信息合并显示
            para.setTemplateName("/template/exportColspan.xls");
            data = this.getAssignedCellDataColspan(listSubjectPage);
        }

        // 生成文件名
        String title = "舆情";
        Date dt = new Date();
        String downName = user.getName() + "_" + dt.getTime() + "_" + title + ".xls";
        ExportExcel ee = new ExportExcel();
        ee.exportExcelByTemplateByTime(title, downName, response, request.getSession(), data, this.exportSupportService, para);
        return null;
    }

    /**
     * 获取excel里要输入的数据(显示相似信息)
     *
     * @param listeventNews
     * @return
     */
    private List<AssignedCell[]> getAssignedCellDataColspan(List<SubjectPage> listeventNews) {
        List<AssignedCell[]> data = new ArrayList<AssignedCell[]>();
        // 添加数据
        for (SubjectPage e : listeventNews) {
            String polarity = "";
            if (e.getPolarity() == -1) {
                polarity = "负面";
            } else if (e.getPolarity() == 0) {
                polarity = "争议";
            } else {
                polarity = "正面";
            }
            // System.out.println(e.getPolarity()+":"+polarity);
            String temp = "";
            if (e.getType() == 1) {
                temp = "新闻";
            } else if (e.getType() == 2) {
                temp = "论坛";
            } else if (e.getType() == 3) {
                temp = "微博";
            } else if (e.getType() == 4) {
                temp = "博客";
            } else if (e.getType() == 5) {
                temp = "报刊";
            } else if (e.getType() == 6) {
                temp = "twitter";
            } else if (e.getType() == 7) {
                temp = "微信";
            }
            AssignedCell[] row1 = { new AssignedCell(0, 0, e.getTitle(), 0),
                    // new AssignedCell(0, 1, e.getSummary(), 0),
                    new AssignedCell(0, 1, e.getUrl(), 0), new AssignedCell(0, 2, e.getWebsite(), 0), new AssignedCell(0, 3, DateUtils.dateToStr(e.getPublishdate(), "yyyy-MM-dd HH:mm:ss"), 0), new AssignedCell(0, 4, polarity, 0), new AssignedCell(0, 5, e.getGroupcount(), 0), new AssignedCell(0, 6, temp, 0), };

            data.add(row1);
        }
        return data;
    }

    /**
     * 获取excel里要输入的数据
     *
     * @param listeventNews
     * @return
     */
    private List<AssignedCell[]> getAssignedCellData(List<SubjectPage> listeventNews) {
        List<AssignedCell[]> data = new ArrayList<AssignedCell[]>();
        // 添加数据
        for (SubjectPage e : listeventNews) {
            String polarity = "";
            if (e.getPolarity() == -1) {
                polarity = "负面";
            } else if (e.getPolarity() == 0) {
                polarity = "争议";
            } else {
                polarity = "正面";
            }
            // System.out.println(e.getPolarity()+":"+polarity);
            AssignedCell[] row1 = { new AssignedCell(0, 0, e.getTitle(), 0),
                    // new AssignedCell(0, 1, e.getSummary(), 0),
                    new AssignedCell(0, 1, e.getUrl(), 0), new AssignedCell(0, 2, e.getWebsite(), 0), new AssignedCell(0, 3, DateUtils.dateToStr(e.getPublishdate(), "yyyy-MM-dd HH:mm:ss"), 0), new AssignedCell(0, 4, polarity, 0),
                    // new AssignedCell(0, 6, "123", 0),
            };

            data.add(row1);
        }
        return data;
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
            List<Subject> list = this.subjectService.selectSubjectByUserIdExclusive(subject);
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
            if (this.subjectPageService.downExcelSelectSubjectPageByTimeNotColspanCount(subjectPage) < 5000) {
                listSubjectPage = this.subjectPageService.downExcelSelectSubjectPageByTimeNotColspan(subjectPage);
            } else {
                listSubjectPage = null;
            }

        } else {// 相似信息合并显示
            if (this.subjectPageService.downExcelSelectSubjectPageByTimeCount(subjectPage) < 5000) {
                listSubjectPage = this.subjectPageService.downExcelSelectSubjectPageByTime(subjectPage);
            } else {
                listSubjectPage = null;
            }
        }
        return listSubjectPage;
    }

    /**
     * 发送邮件
     *
     * @param ids
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/sendMail")
    @ResponseBody
    public int sendMail(Mail mail, HttpServletRequest request, HttpServletResponse response) {
        int i = 0;
        // System.out.println(mail.getIds()+"*********"+mail.getReceiveUser());
        String ids = mail.getIds();
        try {
            SimpleDateFormat datatime = new SimpleDateFormat("yyyyMMdd");
            // map内放模板需要的数据
            Map<String, Object> dataMap = new HashMap<String, Object>();
            String[] pageIds = ids.split(",");
            List<SubjectPage> listSubjectPage = this.subjectPageService.selectSubjectPageByIds(pageIds);
            dataMap.put("list", listSubjectPage);
            User user = (User) request.getSession().getAttribute("userfront");
            Date dt = new Date();
            // 生成文件名
            String fileName = user.getName() + "_" + dt.getTime() + "_mail.html";
            // --模板(从数据库查找所需要模板)
            String template = "mailTemplate.ftl";
            MailTemp mailTemp = this.mailTempService.findTemplateByUserId(user.getUserId());
            if (null != mailTemp) {
                template = mailTemp.getAddress();
            }
            DocumentHandler doc = new DocumentHandler();
            String ctxPath = request.getSession().getServletContext().getRealPath("/") + "files/" + datatime.format(new Date()) + "/";
            String lines = doc.createHtml(ctxPath, fileName, template, dataMap, request, response);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("content", lines);
            if ("".equals(mail.getTitle())) {
                map.put("title", user.getName() + "_定制舆情");
            } else {
                map.put("title", mail.getTitle());
            }
            map.put("email", mail.getReceiveUser());
            i = doc.SendMailtest(map, request, response);
        } catch (Exception e) {
            i = 0;
        }
        return i;
    }

    /**
     * 用户分析
     *
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "/toshowfenxi")
    public ModelAndView toshowfenxi(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("subject/show_fenxi");
        mv.addObject("subjectid", request.getParameter("subjectid"));
        return mv;
    }

    @RequestMapping(value = "/toshowJYfenxi")
    public ModelAndView toshowJYfenxi(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("subject/show_jy_fenxi");
        mv.addObject("subjectid", request.getParameter("subjectid"));
        return mv;
    }

    @RequestMapping(value = "/showfenxi")
    public ModelAndView showfenxi(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("subject/doshow_fenxi");
        User user = (User) request.getSession().getAttribute("userfront");
        SubjectPage subjectPage = new SubjectPage();
        subjectPage.setUserid(user.getUserId());
        this.dealTime("3", subjectPage);// 近7天数据
        subjectPage.setNewslevelConditions(NEW_LEVEL_JN);
        if ("1".equals(request.getParameter("subjectid"))) {
            Subject subject = new Subject();
            subject.setUserid(user.getUserId());
            subject.setPid(1);
            List<Subject> list = this.subjectService.selectSubjectByUserIdAndPid(subject);
            if (null != list && list.size() > 0) {
                String subjectidArray = "";
                for (int i = 0; i < list.size(); i++) {
                    if ("".equals(subjectidArray)) {
                        subjectidArray = list.get(i).getId().toString();
                    } else {
                        subjectidArray = subjectidArray + "," + list.get(i).getId().toString();
                    }
                }
                subjectPage.setSubjectidArray(subjectidArray);
            } else {
                subjectPage.setSubjectidArray("1");
            }
        } else {
            Subject subject = new Subject();
            subject.setUserid(user.getUserId());
            subject.setPid(Integer.parseInt(request.getParameter("subjectid")));
            List<Subject> list = this.subjectService.selectSubjectByUserIdAndPid(subject);
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
                    tempSubject.setUserid(user.getUserId());
                    tempSubject.setPid(tempList.get(j));
                    List<Subject> tempSubjectList = this.subjectService.selectSubjectByUserIdAndPid(tempSubject);
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
            // subjectPage.setSubjectidArray(request.getParameter("subjectid"));
        }
        List<SubjectPage> list = this.subjectPageService.countSubjectTypeByUserId(subjectPage);

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("value", list.get(i).getCount());
            jsonObject.put("name", this.dealTypeName(list.get(i).getType()));
            jsonArray.add(jsonObject);
        }
        mv.addObject("result1", jsonArray.toString());

        list = this.subjectPageService.countSubjectWebSiteByUserId(subjectPage);
        JSONArray jsonArray2 = new JSONArray();
        JSONArray jsonArray3 = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            jsonArray2.add(list.get(i).getWebsite());
            jsonArray3.add(list.get(i).getCount());
        }
        mv.addObject("result2", jsonArray2.toString());
        mv.addObject("result3", jsonArray3.toString());

        list = this.subjectPageService.countSubjectPolarityByUserId(subjectPage);
        JSONArray jsonArray4 = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("value", list.get(i).getCount());
            jsonObject.put("name", this.dealPolarity(list.get(i).getPolarity()));
            jsonArray4.add(jsonObject);
        }
        mv.addObject("result4", jsonArray4.toString());

        // 页面信息来源
        JSONArray jsonArray5 = new JSONArray();
        JSONArray jsonArray6 = new JSONArray();
        JSONArray jsonArray7 = new JSONArray();
        List<WebDictionary> webDictionaryList = this.webDictionaryService.findAllWebDictionary();
        for (int i = 0; i < webDictionaryList.size(); i++) {
            subjectPage.setType(Integer.parseInt(webDictionaryList.get(i).getValue()));
            list = this.subjectPageService.countSubjectPolarityAndTypeByUserId(subjectPage);
            for (int j = 0; j < list.size(); j++) {
                // count,polarity,type
                if (list.get(j).getPolarity() == 1) {
                    jsonArray5.add(list.get(j).getCount());
                } else if (list.get(j).getPolarity() == -1) {
                    jsonArray6.add(list.get(j).getCount());
                } else {
                    jsonArray7.add(list.get(j).getCount());
                }
            }
        }
        mv.addObject("result5", jsonArray5.toString());
        mv.addObject("result6", jsonArray6.toString());
        mv.addObject("result7", jsonArray7.toString());

        return mv;
    }

    /**
     * 用户分析
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/JYshowfenxi")
    public ModelAndView JYshowfenxi(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("subject/doshow_fenxi");
        User user = (User) request.getSession().getAttribute("userfront");
        SubjectPage subjectPage = new SubjectPage();
        subjectPage.setUserid(user.getUserId());
        subjectPage.setNewslevelConditions(NEW_LEVEL_JW);

        this.dealTime("3", subjectPage);// 近7天数据

        if ("1".equals(request.getParameter("subjectid"))) {
            Subject subject = new Subject();
            subject.setUserid(user.getUserId());
            subject.setPid(1);
            List<Subject> list = this.subjectService.selectSubjectByUserIdAndPid(subject);
            if (null != list && list.size() > 0) {
                String subjectidArray = "";
                for (int i = 0; i < list.size(); i++) {
                    if ("".equals(subjectidArray)) {
                        subjectidArray = list.get(i).getId().toString();
                    } else {
                        subjectidArray = subjectidArray + "," + list.get(i).getId().toString();
                    }
                }
                subjectPage.setSubjectidArray(subjectidArray);
            } else {
                subjectPage.setSubjectidArray("1");
            }
        } else {
            Subject subject = new Subject();
            subject.setUserid(user.getUserId());
            subject.setPid(Integer.parseInt(request.getParameter("subjectid")));
            List<Subject> list = this.subjectService.selectSubjectByUserIdAndPid(subject);
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
                    tempSubject.setUserid(user.getUserId());
                    tempSubject.setPid(tempList.get(j));
                    List<Subject> tempSubjectList = this.subjectService.selectSubjectByUserIdAndPid(tempSubject);
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
        }
        List<SubjectPage> list = this.subjectPageService.countSubjectTypeByUserId(subjectPage);

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("value", list.get(i).getCount());
            jsonObject.put("name", this.dealTypeName(list.get(i).getType()));
            jsonArray.add(jsonObject);
        }
        mv.addObject("result1", jsonArray.toString());

        list = this.subjectPageService.countSubjectWebSiteByUserId(subjectPage);
        JSONArray jsonArray2 = new JSONArray();
        JSONArray jsonArray3 = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            jsonArray2.add(list.get(i).getWebsite());
            jsonArray3.add(list.get(i).getCount());
        }
        mv.addObject("result2", jsonArray2.toString());
        mv.addObject("result3", jsonArray3.toString());

        list = this.subjectPageService.countSubjectPolarityByUserId(subjectPage);
        JSONArray jsonArray4 = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("value", list.get(i).getCount());
            jsonObject.put("name", this.dealPolarity(list.get(i).getPolarity()));
            jsonArray4.add(jsonObject);
        }
        mv.addObject("result4", jsonArray4.toString());

        // 页面信息来源
        JSONArray jsonArray5 = new JSONArray();
        JSONArray jsonArray6 = new JSONArray();
        JSONArray jsonArray7 = new JSONArray();
        List<WebDictionary> webDictionaryList = this.webDictionaryService.findAllWebDictionary();
        for (int i = 0; i < webDictionaryList.size(); i++) {
            subjectPage.setType(Integer.parseInt(webDictionaryList.get(i).getValue()));
            list = this.subjectPageService.countSubjectPolarityAndTypeByUserId(subjectPage);
            for (int j = 0; j < list.size(); j++) {
                // count,polarity,type
                if (list.get(j).getPolarity() == 1) {
                    jsonArray5.add(list.get(j).getCount());
                } else if (list.get(j).getPolarity() == -1) {
                    jsonArray6.add(list.get(j).getCount());
                } else {
                    jsonArray7.add(list.get(j).getCount());
                }
            }
        }
        mv.addObject("result5", jsonArray5.toString());
        mv.addObject("result6", jsonArray6.toString());
        mv.addObject("result7", jsonArray7.toString());

        return mv;
    }

    private String dealPolarity(int polarity) {
        if (polarity == 1) {
            return "正面";
        } else if (polarity == -1) {
            return "负面";
        } else if (polarity == 0) {
            return "争议";
        } else {
            return "其他";
        }
    }

    private String dealTypeName(int type) {
        if (type == 1) {
            return "新闻";
        } else if (type == 2) {
            return "论坛";
        } else if (type == 3) {
            return "微博";
        } else if (type == 4) {
            return "博客";
        } else if (type == 5) {
            return "报刊";
        } else if (type == 6) {
            return "twiter";
        } else if (type == 7) {
            return "微信";
        } else {
            return "其他";
        }
    }

    /**
     * 跳出到填写邮件页面
     *
     * @return
     */
    @RequestMapping(value = "/toWriteEmail")
    @ResponseBody
    public ModelAndView toWriteEmail(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        User user = (User) request.getSession().getAttribute("userfront");
        mv.addObject("title", user.getName() + "_定制舆情");
        mv.setViewName("subject/write_email");
        return mv;
    }

    /**
     * 订制信息详细页
     *
     * @return
     */
    @RequestMapping(value = "/toAddTopic")
    public ModelAndView toAddTopic(HttpServletRequest request, HttpServletResponse response) {
        SubjectPage subjectPage = new SubjectPage();
        if (null != request.getParameter("currentId") && !"".equals(request.getParameter("currentId"))) {
            subjectPage.setId(request.getParameter("currentId"));

            // 从缓存中加载用户id
            User user = (User) request.getSession().getAttribute("userfront");
            int userId = user.getUserId();
            subjectPage.setUserid(userId);

            subjectPage = this.subjectPageService.load(subjectPage);

        }

        ModelAndView mv = new ModelAndView();
        subjectPage.setSummary(HTMLSpirit.delHTMLTag(subjectPage.getSummary()));
        mv.setViewName("subject/add_topic_info");
        mv.addObject("topic", subjectPage);
        return mv;
    }
}
