package com.peony.peonyfront.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.peony.core.base.controller.BaseController;
import com.peony.core.base.pojo.PageParameter;
import com.peony.core.base.pojo.Pagination;
import com.peony.peonyfront.event.model.Event;
import com.peony.peonyfront.event.model.Subscription;
import com.peony.peonyfront.event.service.EventService;
import com.peony.peonyfront.event.service.SubscriptionService;
import com.peony.peonyfront.key.service.IdService;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.operationlog.model.OperationLog;
import com.peony.peonyfront.operationlog.service.OperationLogService;
import com.peony.peonyfront.region.model.Region;
import com.peony.peonyfront.region.model.RegionKeywords;
import com.peony.peonyfront.region.service.RegionKeywordsService;
import com.peony.peonyfront.region.service.RegionService;
import com.peony.peonyfront.subject.model.Subject;
import com.peony.peonyfront.subject.model.SubjectPage;
import com.peony.peonyfront.subject.service.SubjectPageService;
import com.peony.peonyfront.subject.service.SubjectService;
import com.peony.peonyfront.subjectkeywords.model.SubjectKeywords;
import com.peony.peonyfront.subjectkeywords.service.SubjectKeywordsService;
import com.peony.peonyfront.system.model.BaseClass;
import com.peony.peonyfront.system.model.BaseClassKeyws;
import com.peony.peonyfront.system.model.ZTreeNode;
import com.peony.peonyfront.system.service.BaseClassKeywsService;
import com.peony.peonyfront.system.service.BaseClassService;
import com.peony.peonyfront.system.util.WhJdcbConnection;
import com.peony.peonyfront.userservice.model.UserService;
import com.peony.peonyfront.userservice.service.UserServiceService;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;
import com.peony.peonyfront.warningkeyws.model.Warningkeyws;
import com.peony.peonyfront.warningkeyws.service.WarningkeywsService;

/**
 * 系统配置模块
 *
 * @author vv
 *
 */
@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {
    private final Log              log = LogFactory.getLog(SystemController.class);
    @Autowired
    private EventService           eventService;                                   // 订制舆情分类

    @Autowired
    private SubscriptionService    subscriptionService;                            // 用户订制关系

    @Autowired
    private IdService              idService;

    @Autowired
    private SubjectService         subjectService;

    @Autowired
    private RegionService          regionService;

    @Autowired
    private BaseClassService       baseClassService;

    @Autowired
    private BaseClassKeywsService  baseClassKeywsService;

    @Autowired
    private SubjectKeywordsService subjectKeywordsService;

    @Autowired
    private WarningkeywsService    warningkeywsService;

    @Autowired
    private UserServiceService     userServiceService;

    @Autowired
    private OperationLogService    operationLogService;

    @Autowired
    private RegionKeywordsService  RegionKeywordsService;

    @Autowired
    private SubjectPageService     subjectPageService;                             // 订制舆情页面信息查询服务

    /**
     * 系统配置-政务舆情配置
     *
     * @return
     */
    @RequestMapping(value = "/toEditEvent")
    public ModelAndView toEditEvent(HttpServletRequest request, HttpServletResponse response) {
        // 获取公共分类
        Event event = new Event();
        event.setIsPublic(1);
        List<Event> list = this.eventService.findEventByIsPublic(event);
        if (null == list) {
            list = new ArrayList<Event>();
        }
        // 获取非公共分类
        event.setIsPublic(0);
        List<Event> Eventlist = this.eventService.findEventByIsPublic(event);
        if (null == Eventlist) {
            Eventlist = new ArrayList<Event>();
        }
        // 获取用户已勾选分类
        // 获取用户id，此处暂时默认为1
        User user = (User) request.getSession().getAttribute("userfront");
        Subscription subscription = this.subscriptionService.findSubscriptionByUserId(String.valueOf(user.getUserId()));
        if (null == subscription) {
            subscription = new Subscription();
        }

        OperationLog operationLog = new OperationLog(new Date(), user.getUserId(), user.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "政务舆情配置", OperateType.FIND.toString(), OperateMode.前台系统配置.toString());
        this.operationLogService.insertSelective(operationLog);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/list_event");
        mv.addObject("publicEventList", list);
        mv.addObject("Eventlist", Eventlist);
        mv.addObject("userSubscription", "," + subscription.getEventId() + ",");
        return mv;
    }

    /**
     * 系统配置-政务舆情配置
     *
     * @return
     */
    @RequestMapping(value = "/toEditIndustry")
    public ModelAndView toEditIndustry(HttpServletRequest request, HttpServletResponse response) {
        // 获取公共分类
        Event event = new Event();
        event.setIsPublic(2);
        List<Event> list = this.eventService.findEventByIsPublic(event);
        if (null == list) {
            list = new ArrayList<Event>();
        }

        User user = (User) request.getSession().getAttribute("userfront");
        Subscription subscription = this.subscriptionService.findSubscriptionByUserId(String.valueOf(user.getUserId()));
        if (null == subscription) {
            subscription = new Subscription();
        }

        OperationLog operationLog = new OperationLog(new Date(), user.getUserId(), user.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "行业舆情配置", OperateType.FIND.toString(), OperateMode.前台系统配置.toString());
        this.operationLogService.insertSelective(operationLog);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/list_industry");
        mv.addObject("publicEventList", list);
        mv.addObject("userSubscription", "," + subscription.getEventId() + ",");
        return mv;
    }

    /**
     * 新增用户与政务舆情关系
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/doAddSubscription", method = RequestMethod.POST)
    public @ResponseBody int doAddSubscription(HttpServletRequest request, HttpServletResponse response) {
        // 删除用户与订制舆情的关系
        // 从系统中获取用户id 暂定为1
        User user = (User) request.getSession().getAttribute("userfront");
        this.subscriptionService.deleteSubscriptionByUserId(String.valueOf(user.getUserId()));
        // 新增用户与订制舆情的关系
        Subscription subscription = new Subscription();
        subscription.setSubscriptionId(this.idService.NextKey("subscription_id"));
        subscription.setEventId(request.getParameter("eventId"));
        subscription.setUserId(String.valueOf(user.getUserId()));
        return this.subscriptionService.insert(subscription);
    }

    /**
     * 系统配置-定制舆情配置
     *
     * @return
     */
    @RequestMapping(value = "/toEditSubject")
    public ModelAndView toEditSubject(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("userfront");
        UserService userService = this.userServiceService.selectByUserId(user.getUserId());

        OperationLog operationLog = new OperationLog(new Date(), user.getUserId(), user.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "定制舆情配置", OperateType.FIND.toString(), OperateMode.前台系统配置.toString());
        this.operationLogService.insertSelective(operationLog);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/list_subject");
        mv.addObject("total", userService.getKeywsNumber());
        mv.addObject("currentKeyWords", this.getDealKeyWords(user.getUserId(), user.getUserType()));
        return mv;
    }

    /**
     * 根据用户id查询当前用户已设置了多少关键词
     *
     * @param userId
     * @return
     */
    private int getDealKeyWords(Integer userId, String userType) {
        List<Subject> list = this.subjectService.selectUserKeywsByUserId(userId);
        int total = 0;
        if (null == list) {
            return 0;
        } else {
            for (int i = 0; i < list.size(); i++) {
                if ("1".equals(list.get(i).getIschildnodes())) {
                    List<SubjectKeywords> subjectKeywords = this.subjectKeywordsService.selectSubjectListBySubjectId(list.get(i).getId());
                    for (int j = 0; j < subjectKeywords.size(); j++) {
                        if (userType.equals("2")) {
                            if ("main".equals(subjectKeywords.get(j).getName()) || "qxx".equals(subjectKeywords.get(j).getName()) || "qxx1textarea".equals(subjectKeywords.get(j).getName()) || "qxx2textarea".equals(subjectKeywords.get(j).getName())) {
                                total = total + subjectKeywords.get(j).getKeywords().trim().split(" ").length;
                            }
                        } else {
                            if ("area".equals(subjectKeywords.get(j).getName()) || "main".equals(subjectKeywords.get(j).getName()) || "qxx".equals(subjectKeywords.get(j).getName()) || "qxx1textarea".equals(subjectKeywords.get(j).getName()) || "qxx2textarea".equals(subjectKeywords.get(j).getName())) {
                                total = total + subjectKeywords.get(j).getKeywords().trim().split(" ").length;
                            }
                        }

                    }
                }
            }
        }
        return total;
    }

    /**
     * 系统配置-新增订制舆情分类
     *
     * @return
     */
    @RequestMapping(value = "/toAddSubject")
    public ModelAndView toAddSubject(HttpServletRequest request, HttpServletResponse response) {
        // 通过获取用户id，查询当前用户订制舆情
        // 此处登陆后才能获取用户id，暂定为1
        User user = (User) request.getSession().getAttribute("userfront");
        int userid = user.getUserId();
        Subject subject = new Subject();

        subject.setUserid(userid);
        // 默认第一次层分类节点都是0
        subject.setPid(0);

        List<Subject> list = this.subjectService.selectSubjectByUserIdAndPid(subject);
        if (null == list) {
            list = new ArrayList<Subject>();
        }
        // 如果当前是政府用户需要把地域信息带过去，如果不是则用户手动填写
        // 区域名称
        List<Region> regionList = this.regionService.selectByUserId(userid);
        Region region = new Region();
        if (regionList != null) {
            region = regionList.get(0);
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/add_subject");
        mv.addObject("subjectList", list);
        mv.addObject("region", region);
        return mv;
    }

    /**
     * 系统配置-新增订制舆情分类
     *
     * @return
     */
    @RequestMapping(value = "/doAddSubject")
    public @ResponseBody String doAddSubject(HttpServletRequest request, HttpServletResponse response) {
        Subject subject = new Subject();
        String result = "0";

        User user = (User) request.getSession().getAttribute("userfront");
        UserService userService = this.userServiceService.selectByUserId(user.getUserId());
        int total = userService.getKeywsNumber();
        int setKeyWords = this.getDealKeyWords(user.getUserId(), user.getUserType());
        int shengyu = total - setKeyWords;

        if ("1".equals(request.getParameter("gjc"))) {
            subject.setIschildnodes("1");
            // 新增时进行计算
            int count = 0;
            if (user.getUserType().equals("1")) {
                if (null != request.getParameter("area") && !"".equals(request.getParameter("area").trim())) {
                    count = request.getParameter("area").trim().split(" ").length;
                }
            }
            if (null != request.getParameter("main") && !"".equals(request.getParameter("main").trim())) {
                count = request.getParameter("main").trim().split(" ").length;
            }
            if (null != request.getParameter("qxx") && !"".equals(request.getParameter("qxx").trim())) {
                count = count + request.getParameter("qxx").trim().split(" ").length;
            }
            if (null != request.getParameter("qxx1textarea") && !"".equals(request.getParameter("qxx1textarea").trim())) {
                count = count + request.getParameter("qxx1textarea").trim().split(" ").length;
            }
            if (null != request.getParameter("qxx2textarea") && !"".equals(request.getParameter("qxx2textarea").trim())) {
                count = count + request.getParameter("qxx2textarea").trim().split(" ").length;
            }
            if (shengyu < count) {
                return "-1";
            } else {
                shengyu = shengyu - count;
                setKeyWords = setKeyWords + count;
            }
        } else {
            subject.setIschildnodes("0");
        }
        subject.setId(this.idService.NextKey("id"));
        subject.setUserid(user.getUserId());
        subject.setName(request.getParameter("subjectName"));
        subject.setCreatetime(new Date());
        subject.setPid(Integer.parseInt(request.getParameter("pid")));
        subject.setState(1);
        subject.setUpdateTime(new Date());
        result = Integer.toString(this.subjectService.insertSelective(subject));
        // 向同步库插入数据
        WhJdcbConnection whJdcbConnection = new WhJdcbConnection();
        whJdcbConnection.InsertSubject(subject);

        // 页面勾选关键词的才向page表中插入数据
        if ("1".equals(request.getParameter("gjc"))) {
            SubjectKeywords SubjectKeywords = new SubjectKeywords();
            SubjectKeywords.setSubjectid(subject.getId());
            SubjectKeywords.setRejectflag("0");
            if (null != request.getParameter("area") && !"".equals(request.getParameter("area").trim())) {
                SubjectKeywords.setId(this.idService.NextKey("subject_keywords"));
                SubjectKeywords.setName("area");
                SubjectKeywords.setKeywords(request.getParameter("area"));
                this.subjectKeywordsService.insertSelective(SubjectKeywords);
                whJdcbConnection.InsertSubjectKeywords(SubjectKeywords);
            }
            if (null != request.getParameter("main") && !"".equals(request.getParameter("main").trim())) {
                SubjectKeywords.setId(this.idService.NextKey("subject_keywords"));
                SubjectKeywords.setName("main");
                SubjectKeywords.setKeywords(request.getParameter("main").trim());
                this.subjectKeywordsService.insertSelective(SubjectKeywords);
                whJdcbConnection.InsertSubjectKeywords(SubjectKeywords);
            }
            if (null != request.getParameter("qxx") && !"".equals(request.getParameter("qxx").trim())) {
                SubjectKeywords.setId(this.idService.NextKey("subject_keywords"));
                SubjectKeywords.setName("qxx");
                SubjectKeywords.setKeywords(request.getParameter("qxx").trim());
                this.subjectKeywordsService.insertSelective(SubjectKeywords);
                whJdcbConnection.InsertSubjectKeywords(SubjectKeywords);
            }
            if (null != request.getParameter("qxx1textarea") && !"".equals(request.getParameter("qxx1textarea").trim())) {
                SubjectKeywords.setId(this.idService.NextKey("subject_keywords"));
                SubjectKeywords.setName("qxx1textarea");
                SubjectKeywords.setKeywords(request.getParameter("qxx1textarea").trim());
                this.subjectKeywordsService.insertSelective(SubjectKeywords);
                whJdcbConnection.InsertSubjectKeywords(SubjectKeywords);
            }
            if (null != request.getParameter("qxx2textarea") && !"".equals(request.getParameter("qxx2textarea").trim())) {
                SubjectKeywords.setId(this.idService.NextKey("subject_keywords"));
                SubjectKeywords.setName("qxx2textarea");
                SubjectKeywords.setKeywords(request.getParameter("qxx2textarea").trim());
                this.subjectKeywordsService.insertSelective(SubjectKeywords);
                whJdcbConnection.InsertSubjectKeywords(SubjectKeywords);
            }
            if (null != request.getParameter("glc") && !"".equals(request.getParameter("glc"))) {
                SubjectKeywords.setId(this.idService.NextKey("subject_keywords"));
                SubjectKeywords.setName("glc");
                SubjectKeywords.setKeywords(request.getParameter("glc"));
                SubjectKeywords.setRejectflag("1");
                this.subjectKeywordsService.insertSelective(SubjectKeywords);
                whJdcbConnection.InsertSubjectKeywords(SubjectKeywords);
            }
            if (null != request.getParameter("glc1textarea") && !"".equals(request.getParameter("glc1textarea").trim())) {
                SubjectKeywords.setId(this.idService.NextKey("subject_keywords"));
                SubjectKeywords.setName("glc1textarea");
                SubjectKeywords.setKeywords(request.getParameter("glc1textarea"));
                SubjectKeywords.setRejectflag("1");
                this.subjectKeywordsService.insertSelective(SubjectKeywords);
                whJdcbConnection.InsertSubjectKeywords(SubjectKeywords);
            }
            if (null != request.getParameter("glc2textarea") && !"".equals(request.getParameter("glc2textarea").trim())) {
                SubjectKeywords.setId(this.idService.NextKey("subject_keywords"));
                SubjectKeywords.setName("glc2textarea");
                SubjectKeywords.setKeywords(request.getParameter("glc2textarea"));
                SubjectKeywords.setRejectflag("1");
                this.subjectKeywordsService.insertSelective(SubjectKeywords);
                whJdcbConnection.InsertSubjectKeywords(SubjectKeywords);
            }
        }
        return result + "&" + total + "&" + setKeyWords + "&" + shengyu;
    }

    /**
     * 系统配置-显示分词词库
     *
     * @return
     */
    @RequestMapping(value = "/toShowBaseClass")
    public ModelAndView toShowBaseClass(HttpServletRequest request, HttpServletResponse response) {
        // 查询公共词库分类
        List<BaseClass> list = this.baseClassService.selectPublicBaseClass();
        if (null == list) {
            list = new ArrayList<BaseClass>();
        }
        String baseclassIdArray = "";
        for (int i = 0; i < list.size(); i++) {
            if ("".equals(baseclassIdArray)) {
                baseclassIdArray = list.get(i).getBaseclassId().toString();
            } else {
                baseclassIdArray = baseclassIdArray + "," + list.get(i).getBaseclassId().toString();
            }
        }
        BaseClassKeyws baseClassKeyws = new BaseClassKeyws();
        baseClassKeyws.setBaseclassIdArray(baseclassIdArray);
        List<BaseClassKeyws> publicBaseClassKeywsList = this.baseClassKeywsService.selectBaseClassKeywsByBaseClassId(baseClassKeyws);

        // 根据用户注册单位，查询词库
        User user = (User) request.getSession().getAttribute("userfront");
        int userid = user.getUserId();
        List<BaseClass> BaseClasslist = this.baseClassService.selectBaseClassByUserId(userid);
        if (null == BaseClasslist) {
            BaseClasslist = new ArrayList<BaseClass>();
        }
        String cikuName = "";
        String listSize = "";
        if (BaseClasslist.size() > 0) {
            cikuName = BaseClasslist.get(0).getName();
            listSize = "1";
        }
        baseclassIdArray = "";
        for (int i = 0; i < BaseClasslist.size(); i++) {
            if ("".equals(baseclassIdArray)) {
                baseclassIdArray = BaseClasslist.get(i).getBaseclassId().toString();
            } else {
                baseclassIdArray = baseclassIdArray + "," + BaseClasslist.get(i).getBaseclassId().toString();
            }
        }
        List<BaseClassKeyws> baseClassKeywsList = new ArrayList<BaseClassKeyws>();
        if (!"".equals(baseclassIdArray)) {
            baseClassKeyws.setBaseclassIdArray(baseclassIdArray);
            baseClassKeywsList = this.baseClassKeywsService.selectBaseClassKeywsByBaseClassId(baseClassKeyws);
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/show_baseclass");
        mv.addObject("cikuName", cikuName);
        mv.addObject("listSize", listSize);
        mv.addObject("publicBaseClassKeywsList", publicBaseClassKeywsList);
        mv.addObject("baseClassKeywsList", baseClassKeywsList);
        return mv;
    }

    /**
     * 系统配置-显示分词词库
     *
     * @return
     */
    @RequestMapping(value = "/toShowArea")
    public ModelAndView toShowArea(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mv = new ModelAndView();

        User user = (User) request.getSession().getAttribute("userfront");

        JSONArray json = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        List<Region> list = this.regionService.selectByUserId(user.getUserId());
        if (!CollectionUtils.isEmpty(list)) {
            Region region = list.get(0);
            list = this.regionService.selectLikeParentId(list.get(0));

            jsonObject.put("id", region.getRegionid());
            jsonObject.put("pId", region.getParentid());
            jsonObject.put("name", region.getRegionname());
            jsonObject.put("open", true);
            jsonObject.put("checked", true);
            json.add(jsonObject);

            for (Region regionlist : list) {
                jsonObject = new JSONObject();
                jsonObject.put("id", regionlist.getRegionid());
                jsonObject.put("pId", regionlist.getParentid());
                jsonObject.put("name", regionlist.getRegionname());
                jsonObject.put("open", false);
                jsonObject.put("checked", true);
                json.add(jsonObject);
            }
            mv.addObject("treeValue", json.toJSONString().replaceAll("\"id\"", "id").replaceAll("\"pId\"", "pId").replaceAll("\"name\"", "name").replaceAll("\"open\"", "open").replaceAll("\"checked\"", "checked"));
            mv.setViewName("system/show_baseArea");
        } else {
            this.log.warn("User's regional key words are empty");
            mv.setViewName("system/show_baseAreaError");
        }
        return mv;
    }

    /**
     * 回显词库
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/doFindBaseclassKeyws", produces = "text/plain;charset=utf-8")
    public @ResponseBody String doFindBaseclassKeyws(HttpServletRequest request, HttpServletResponse response) {
        String returnResult = "";
        if (null != request.getParameter("baseClassId")) {
            BaseClassKeyws baseClassKeyws = new BaseClassKeyws();
            baseClassKeyws.setBaseclassIdArray(request.getParameter("baseClassId"));
            List<BaseClassKeyws> list = this.baseClassKeywsService.selectBaseClassKeywsByBaseclassKeywsId(baseClassKeyws);
            if (null != list && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    if (i == 0) {
                        returnResult = list.get(i).getContent();
                    } else {
                        returnResult = returnResult + " " + list.get(i).getContent();
                    }
                }
            }
        }
        return returnResult;
    }

    /**
     * 回显词库
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/doFindRegionKeyWord", produces = "text/plain;charset=utf-8")
    public @ResponseBody String doFindRegionKeyWord(HttpServletRequest request, HttpServletResponse response) {
        String returnResult = "";
        if (null != request.getParameter("regionId")) {
            RegionKeywords regionKeywords = new RegionKeywords();
            regionKeywords.setRegionwords(request.getParameter("regionId"));
            List<RegionKeywords> list = this.RegionKeywordsService.selectByPK(regionKeywords);
            if (null != list && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    if (i == 0) {
                        returnResult = list.get(i).getRegionwords();
                    } else {
                        returnResult = returnResult + " " + list.get(i).getRegionwords();
                    }
                }
            }
        }
        return returnResult;
    }

    /**
     * 目录树
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/findChildren", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody List<ZTreeNode> findChildren(Model model, HttpServletRequest request) {
        // id = null 查父子节点
        Subject subject = new Subject();

        if (null == request.getParameter("id")) {
            subject.setPid(0);
        } else {
            subject.setPid(Integer.parseInt(request.getParameter("id")));
            User user = (User) request.getSession().getAttribute("userfront");
            subject.setUserid(user.getUserId());
        }

        List<Subject> list = this.subjectService.selectSubjectByUserIdAndPid(subject);

        List<ZTreeNode> nodeList = new ArrayList<ZTreeNode>();
        for (Subject tempSubject : list) {
            Subject resultSubject = new Subject();
            resultSubject.setPid(tempSubject.getId());
            resultSubject.setUserid(tempSubject.getUserid());
            ZTreeNode node = new ZTreeNode();
            node.setId(tempSubject.getId().toString());
            node.setName(tempSubject.getName());
            node.setOpen(false);
            node.setIsParent(this.subjectService.selectCountByPid(resultSubject) ? "true" : "false");
            nodeList.add(node);
        }
        return nodeList;
    }

    /**
     * 加载节点信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/toViewSubject")
    public ModelAndView toViewSubject(HttpServletRequest request, HttpServletResponse response) {
        // subjectId
        Subject subject = this.subjectService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/view_subject");

        User user = (User) request.getSession().getAttribute("userfront");
        int userid = user.getUserId();
        // 政府用户定为2
        String userType = user.getUserType();
        List<Region> regionList = this.regionService.selectByUserId(userid);
        Region region = new Region();
        if (regionList != null && regionList.size() > 0) {
            region = regionList.get(0);
            RegionKeywords regionKeywords = new RegionKeywords();
            regionKeywords.setRegionid(region.getRegionid().toString());
            List<RegionKeywords> regionKeywordsList = this.RegionKeywordsService.selectByRegionId(regionKeywords);
            String regionName = "";
            String regionIdArray = "";
            for (int i = 0; i < regionKeywordsList.size(); i++) {
                if (i == 0) {
                    regionName = regionKeywordsList.get(i).getRegionwords();
                    regionIdArray = regionKeywordsList.get(i).getRegionid();
                } else {
                    regionName = regionName + " " + regionKeywordsList.get(i).getRegionwords();
                    regionIdArray = regionIdArray + "," + regionKeywordsList.get(i).getRegionid();
                }
            }
            region.setRegionname(regionName);
            region.setRegionIdArray(regionIdArray);

        }
        List<SubjectKeywords> list = this.subjectKeywordsService.selectSubjectListBySubjectId(Integer.parseInt(request.getParameter("id")));
        SubjectKeywords subjectKeywords = new SubjectKeywords();
        if (null != list && list.size() > 0) {
            subjectKeywords = this.dealSubjectKeywords(subjectKeywords, list);
            if (null != subjectKeywords.getArea() && !subjectKeywords.getArea().equals("")) {
                region.setRegionname(subjectKeywords.getArea());
            }
        }
        mv.addObject("subject", subject);
        mv.addObject("region", region);
        mv.addObject("subjectKeywords", subjectKeywords);
        mv.addObject("userType", userType);
        return mv;
    }

    private SubjectKeywords dealSubjectKeywords(SubjectKeywords subjectKeywords, List<SubjectKeywords> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("area")) {
                subjectKeywords.setArea(list.get(i).getKeywords());
            } else if (list.get(i).getName().equals("main")) {
                subjectKeywords.setMain(list.get(i).getKeywords());
            } else if (list.get(i).getName().equals("qxx")) {
                subjectKeywords.setQxx(list.get(i).getKeywords());
            } else if (list.get(i).getName().equals("glc")) {
                subjectKeywords.setGlc(list.get(i).getKeywords());
            } else if (list.get(i).getName().equals("qxx1textarea")) {
                subjectKeywords.setQxx1textarea(list.get(i).getKeywords());
            } else if (list.get(i).getName().equals("qxx2textarea")) {
                subjectKeywords.setQxx2textarea(list.get(i).getKeywords());
            } else if (list.get(i).getName().equals("glc1textarea")) {
                subjectKeywords.setGlc1textarea(list.get(i).getKeywords());
            } else if (list.get(i).getName().equals("glc2textarea")) {
                subjectKeywords.setGlc2textarea(list.get(i).getKeywords());
            }
        }
        return subjectKeywords;
    }

    @RequestMapping(value = "/doUpdateSubject")
    public @ResponseBody int doUpdateSubject(HttpServletRequest request, HttpServletResponse response) {
        // 修改subject表中的名字
        Subject subject = new Subject();
        subject.setId(Integer.parseInt(request.getParameter("pid")));
        subject.setName(request.getParameter("subjectName"));
        subject.setUpdateTime(new Date());
        this.subjectService.updateByPrimaryKeySelective(subject);
        WhJdcbConnection whJdcbConnection = new WhJdcbConnection();
        whJdcbConnection.UpdateSubject(subject);
        return 1;
    }

    @RequestMapping(value = "/doUpdateSubjectKeyWords")
    public @ResponseBody String doUpdateSubjectKeyWords(HttpServletRequest request, HttpServletResponse response) {

        // 如果当前subject下级已经存在叶子节点，则不走下面的流程
        Subject tempSubject = new Subject();
        User user = (User) request.getSession().getAttribute("userfront");
        int userid = user.getUserId();
        tempSubject.setUserid(userid);
        tempSubject.setPid(Integer.parseInt(request.getParameter("pid")));
        // 0:不是 1：是
        tempSubject.setIschildnodes("1");
        List<Subject> list = this.subjectService.selectSubjectByUserIdAndPid(tempSubject);
        if (null == list || list.size() == 0) {
            // 计算总数
            UserService userService = this.userServiceService.selectByUserId(user.getUserId());
            int total = userService.getKeywsNumber();
            // 已设置
            int setKeyWords = this.getDealKeyWords(user.getUserId(), user.getUserType());
            // 计算删除的
            int tempTotal = 0;
            List<SubjectKeywords> subjectKeywordList = this.subjectKeywordsService.selectSubjectListBySubjectId(Integer.parseInt(request.getParameter("pid")));
            for (int j = 0; j < subjectKeywordList.size(); j++) {
                if (user.getUserType().equals("2")) {
                    if ("main".equals(subjectKeywordList.get(j).getName()) || "qxx".equals(subjectKeywordList.get(j).getName()) || "qxx1textarea".equals(subjectKeywordList.get(j).getName()) || "qxx2textarea".equals(subjectKeywordList.get(j).getName())) {
                        tempTotal = tempTotal + subjectKeywordList.get(j).getKeywords().trim().split(" ").length;
                    }
                } else {
                    if ("area".equals(subjectKeywordList.get(j).getName()) || "main".equals(subjectKeywordList.get(j).getName()) || "qxx".equals(subjectKeywordList.get(j).getName()) || "qxx1textarea".equals(subjectKeywordList.get(j).getName()) || "qxx2textarea".equals(subjectKeywordList.get(j).getName())) {
                        tempTotal = tempTotal + subjectKeywordList.get(j).getKeywords().trim().split(" ").length;
                    }
                }
            }
            int shengyu = total - setKeyWords + tempTotal;

            // 新增时进行计算
            int count = 0;
            if (user.getUserType().equals("1")) {
                if (null != request.getParameter("area") && !"".equals(request.getParameter("area").trim())) {
                    count = request.getParameter("area").trim().split(" ").length;
                }
            }
            if (null != request.getParameter("main") && !"".equals(request.getParameter("main").trim())) {
                count = request.getParameter("main").trim().split(" ").length;
            }
            if (null != request.getParameter("qxx") && !"".equals(request.getParameter("qxx").trim())) {
                count = count + request.getParameter("qxx").trim().split(" ").length;
            }
            if (null != request.getParameter("qxx1textarea") && !"".equals(request.getParameter("qxx1textarea").trim())) {
                count = count + request.getParameter("qxx1textarea").trim().split(" ").length;
            }
            if (null != request.getParameter("qxx2textarea") && !"".equals(request.getParameter("qxx2textarea").trim())) {
                count = count + request.getParameter("qxx2textarea").trim().split(" ").length;
            }
            if (shengyu < count) {
                return "-1";
            } else {
                shengyu = shengyu - count;
                setKeyWords = setKeyWords + count - tempTotal;
            }

            // 修改subject表中的名字
            Subject subject = new Subject();
            subject.setId(Integer.parseInt(request.getParameter("pid")));
            subject.setName(request.getParameter("subjectName"));
            subject.setUpdateTime(new Date());
            subject.setIschildnodes(request.getParameter("gjc"));
            this.subjectService.updateByPrimaryKeySelective(subject);
            // 更新武汉同步库
            WhJdcbConnection whJdcbConnection = new WhJdcbConnection();
            whJdcbConnection.UpdateSubject(subject);

            // 删除当前的用户配置
            this.subjectKeywordsService.deleteBySubjectId(Integer.parseInt(request.getParameter("pid")));
            whJdcbConnection.DeleteSubjectKeywords(request.getParameter("pid"));
            SubjectKeywords subjectKeywords = new SubjectKeywords();
            subjectKeywords.setSubjectid(Integer.parseInt(request.getParameter("pid")));
            subjectKeywords.setRejectflag("0");
            if (null != request.getParameter("area") && !"".equals(request.getParameter("area").trim())) {
                subjectKeywords.setId(this.idService.NextKey("subject_keywords"));
                subjectKeywords.setName("area");
                subjectKeywords.setKeywords(request.getParameter("area"));
                this.subjectKeywordsService.insertSelective(subjectKeywords);
                whJdcbConnection.InsertSubjectKeywords(subjectKeywords);
            }
            if (null != request.getParameter("main") && !"".equals(request.getParameter("main").trim())) {
                subjectKeywords.setId(this.idService.NextKey("subject_keywords"));
                subjectKeywords.setName("main");
                subjectKeywords.setKeywords(request.getParameter("main"));
                this.subjectKeywordsService.insertSelective(subjectKeywords);
                // subjectKeywords.setMain(request.getParameter("main"));
                whJdcbConnection.InsertSubjectKeywords(subjectKeywords);
            }
            if (null != request.getParameter("qxx") && !"".equals(request.getParameter("qxx").trim())) {
                subjectKeywords.setId(this.idService.NextKey("subject_keywords"));
                subjectKeywords.setName("qxx");
                subjectKeywords.setKeywords(request.getParameter("qxx"));
                this.subjectKeywordsService.insertSelective(subjectKeywords);
                // subjectKeywords.setQxx(request.getParameter("qxx"));
                whJdcbConnection.InsertSubjectKeywords(subjectKeywords);
            }
            if (null != request.getParameter("qxx1textarea") && !"".equals(request.getParameter("qxx1textarea").trim())) {
                subjectKeywords.setId(this.idService.NextKey("subject_keywords"));
                subjectKeywords.setName("qxx1textarea");
                subjectKeywords.setKeywords(request.getParameter("qxx1textarea"));
                this.subjectKeywordsService.insertSelective(subjectKeywords);
                // subjectKeywords.setQxx1textarea(request.getParameter("qxx1textarea"));
                whJdcbConnection.InsertSubjectKeywords(subjectKeywords);
            }
            if (null != request.getParameter("qxx2textarea") && !"".equals(request.getParameter("qxx2textarea").trim())) {
                subjectKeywords.setId(this.idService.NextKey("subject_keywords"));
                subjectKeywords.setName("qxx2textarea");
                subjectKeywords.setKeywords(request.getParameter("qxx2textarea"));
                this.subjectKeywordsService.insertSelective(subjectKeywords);
                // subjectKeywords.setQxx2textarea(request.getParameter("qxx2textarea"));
                whJdcbConnection.InsertSubjectKeywords(subjectKeywords);
            }
            if (null != request.getParameter("glc") && !"".equals(request.getParameter("glc").trim())) {
                subjectKeywords.setId(this.idService.NextKey("subject_keywords"));
                subjectKeywords.setName("glc");
                subjectKeywords.setKeywords(request.getParameter("glc"));
                subjectKeywords.setRejectflag("1");
                this.subjectKeywordsService.insertSelective(subjectKeywords);
                // subjectKeywords.setGlc(request.getParameter("glc"));
                whJdcbConnection.InsertSubjectKeywords(subjectKeywords);
            }
            if (null != request.getParameter("glc1textarea") && !"".equals(request.getParameter("glc1textarea").trim())) {
                subjectKeywords.setId(this.idService.NextKey("subject_keywords"));
                subjectKeywords.setName("glc1textarea");
                subjectKeywords.setKeywords(request.getParameter("glc1textarea"));
                subjectKeywords.setRejectflag("1");
                this.subjectKeywordsService.insertSelective(subjectKeywords);
                // subjectKeywords.setGlc1textarea(request.getParameter("glc1textarea"));
                whJdcbConnection.InsertSubjectKeywords(subjectKeywords);
            }
            if (null != request.getParameter("glc2textarea") && !"".equals(request.getParameter("glc2textarea").trim())) {
                subjectKeywords.setId(this.idService.NextKey("subject_keywords"));
                subjectKeywords.setName("glc2textarea");
                subjectKeywords.setKeywords(request.getParameter("glc2textarea"));
                subjectKeywords.setRejectflag("1");
                this.subjectKeywordsService.insertSelective(subjectKeywords);
                // subjectKeywords.setGlc2textarea(request.getParameter("glc2textarea"));
                whJdcbConnection.InsertSubjectKeywords(subjectKeywords);
            }
            return "1&" + total + "&" + setKeyWords + "&" + shengyu;
        } else {
            return "2";
        }

    }

    @RequestMapping(value = "/doDeleteSubject")
    public @ResponseBody String doDeleteSubject(HttpServletRequest request, HttpServletResponse response) {
        Subject subject = new Subject();
        subject.setPid(Integer.parseInt(request.getParameter("pid")));
        User user = (User) request.getSession().getAttribute("userfront");
        subject.setUserid(user.getUserId());
        List<Subject> list = this.subjectService.selectSubjectByUserIdAndPid(subject);

        if (null == list || list.size() == 0) {
            Subject tempSubject = new Subject();
            tempSubject.setId(Integer.parseInt(request.getParameter("pid")));
            tempSubject.setDroptime(new Date());
            tempSubject.setUpdateTime(new Date());
            tempSubject.setState(0);
            this.subjectService.updateByPrimaryKeySelective(tempSubject);
            WhJdcbConnection whJdcbConnection = new WhJdcbConnection();
            whJdcbConnection.DeleteSubject(tempSubject);
            // 删除subject_page
            SubjectPage subjectPage = new SubjectPage();
            subjectPage.setUserid(user.getUserId());
            subjectPage.setIdArray(request.getParameter("pid"));
            // subjectPageService.batchDelete(subjectPage);
            // 删除pe_t_subject_keywords表
            // subjectKeywordsService.deleteBySubjectId(Integer.parseInt(request.getParameter("pid")));
            UserService userService = this.userServiceService.selectByUserId(user.getUserId());
            int total = userService.getKeywsNumber();
            int setKeyWords = this.getDealKeyWords(user.getUserId(), user.getUserType());
            int shengyu = total - setKeyWords;
            return "1&" + total + "&" + setKeyWords + "&" + shengyu;
        } else {
            return "0";
        }

    }

    /**
     * 系统配置-新增订制舆情分类
     *
     * @return
     */
    @RequestMapping(value = "/toListWarningkeyws")
    public ModelAndView toListWarningkeyws(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/list_warningkeyws");
        Warningkeyws warningkeyws = new Warningkeyws();
        // 系统中获取当前用户ID
        User user = (User) request.getSession().getAttribute("userfront");
        warningkeyws.setUserId(user.getUserId());

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
        warningkeyws.setPageParameter(pageParameter);
        Pagination<Warningkeyws> pagination = this.warningkeywsService.findByPage(warningkeyws);

        OperationLog operationLog = new OperationLog(new Date(), user.getUserId(), user.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "预警关键词设置", OperateType.FIND.toString(), OperateMode.前台系统配置.toString());
        this.operationLogService.insertSelective(operationLog);

        mv.addObject("pagination", pagination);
        return mv;
    }

    @RequestMapping(value = "/deleteWarningkeyws")
    public @ResponseBody int deleteWarningkeyws(HttpServletRequest request, HttpServletResponse response) {
        if (null != request.getParameter("warningkeywsId")) {
            return this.warningkeywsService.deleteByPrimaryKey(Integer.parseInt(request.getParameter("warningkeywsId")));
        } else {
            return 0;
        }
    }

    @RequestMapping(value = "/showWarningkeyws/{warningkeywsId}")
    public ModelAndView showWarningkeyws(@PathVariable("warningkeywsId") int warningkeywsId, HttpServletRequest request, HttpServletResponse response) {
        Warningkeyws warningkeyws = this.warningkeywsService.selectByPrimaryKey(warningkeywsId);
        if (null == warningkeyws) {
            warningkeyws = new Warningkeyws();
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("warningkeyws", warningkeyws);
        mv.setViewName("system/show_warningkeyws");
        return mv;
    }

    @RequestMapping(value = "/toUpdateWarningkeyws/{warningkeywsId}")
    public ModelAndView toUpdateWarningkeyws(@PathVariable("warningkeywsId") int warningkeywsId, HttpServletRequest request, HttpServletResponse response) {
        Warningkeyws warningkeyws = this.warningkeywsService.selectByPrimaryKey(warningkeywsId);
        if (null == warningkeyws) {
            warningkeyws = new Warningkeyws();
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("warningkeyws", warningkeyws);
        mv.setViewName("system/update_warningkeyws");
        return mv;
    }

    @RequestMapping(value = "/doUpdateWarningkeyws")
    public @ResponseBody int doUpdateWarningkeyws(@ModelAttribute("warningkeyws") Warningkeyws warningkeyws, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("userfront");
        int userid = user.getUserId();
        warningkeyws.setUserId(userid);
        return this.warningkeywsService.updateByPrimaryKey(warningkeyws);
    }

    @RequestMapping(value = "/addWarningkeyws")
    public ModelAndView addWarningkeyws(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/add_warningkeyws");
        return mv;
    }

    @RequestMapping(value = "/doAddWarningkeyws")
    public @ResponseBody int doAddWarningkeyws(@ModelAttribute("warningkeyws") Warningkeyws warningkeyws, HttpServletRequest request, HttpServletResponse response) {
        warningkeyws.setWarningkeywsId(this.idService.NextKey("warningkeyws_id"));
        User user = (User) request.getSession().getAttribute("userfront");
        warningkeyws.setUserId(user.getUserId());
        return this.warningkeywsService.insertWarningkeyws(warningkeyws);
    }

}
