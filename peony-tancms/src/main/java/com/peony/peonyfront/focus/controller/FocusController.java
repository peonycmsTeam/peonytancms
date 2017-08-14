package com.peony.peonyfront.focus.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
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

import com.peony.core.base.controller.BaseController;
import com.peony.core.base.pojo.PageParameter;
import com.peony.core.base.pojo.Pagination;
import com.peony.core.common.utils.DateUtils;
import com.peony.peonyfront.focus.model.Focus;
import com.peony.peonyfront.focus.service.FocusPageService;
import com.peony.peonyfront.focus.service.FocusService;
import com.peony.peonyfront.key.service.IdService;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.operationlog.model.OperationLog;
import com.peony.peonyfront.operationlog.service.OperationLogService;
import com.peony.peonyfront.region.model.Region;
import com.peony.peonyfront.region.service.RegionService;
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
@RequestMapping("/focus")
public class FocusController extends BaseController {
    @Autowired
    private IdService           idService;                                     // id服务接口
    @Autowired
    private FocusService        focusService;
    @Autowired
    private FocusPageService    focusPageService;

    @Autowired
    private RegionService       regionService;

    @Autowired
    private OperationLogService operationLogService;

    private static final Log    log = LogFactory.getLog(FocusController.class);

    /**
     * 专题列表
     *
     * @return
     */
    @RequestMapping(value = "/listFocus")
    public ModelAndView listFocus(@ModelAttribute("focus") Focus focus, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        User user = (User) request.getSession().getAttribute("userfront");
        List<Region> list = this.regionService.selectByUserId(user.getUserId());
        if (CollectionUtils.isEmpty(list)) {
            log.warn("User's regional key words are empty");
            mv.setViewName("event/list_event_region_missing");
            return mv;
        }

        OperationLog operationLog = new OperationLog(new Date(), user.getUserId(), user.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "公共专题", OperateType.FIND.toString(), OperateMode.公共专题.toString());
        this.operationLogService.insertSelective(operationLog);

        mv.addObject("firstRegionID", list.get(0).getRegionid());
        mv.setViewName("focus/list_focus");
        return mv;
    }

    /**
     * 进入到公共专题分页页面
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/toListFocusPage")
    public ModelAndView toListFocusPage(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        PageParameter pageParameter = new PageParameter();
        if (null == request.getParameter("pageNo")) {
            pageParameter.setCurrentPage(1);
        } else {
            pageParameter.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
        }
        Focus focus = new Focus();
        if (null == request.getParameter("regionID")) {
            User user = (User) request.getSession().getAttribute("userfront");
            List<Region> Regionlist = this.regionService.selectByUserId(user.getUserId());
            if (null != Regionlist) {
                focus.setRegionid(Regionlist.get(0).getRegionid());
            }
        } else {
            focus.setRegionid(Integer.parseInt(request.getParameter("regionID")));
        }
        pageParameter.setPageSize(5);
        focus.setPageParameter(pageParameter);
        Pagination<Focus> list = this.focusService.selectByPage(focus);
        // 遍历list，查询统计总量和今日总量
        for (int i = 0; i < list.getList().size(); i++) {
            Focus myfocus = list.getList().get(i);
            // 今日统计
            Map dayMap = new HashMap();
            Map map = new HashMap();
            map.put("focusid", myfocus.getId());
            map.put("stime", DateUtils.todayDateStr());
            List<Map> todaycountlist = this.focusPageService.selectByTypeCount(map);
            for (int j = 0; j < todaycountlist.size(); j++) {
                Map todaymap = todaycountlist.get(j);
                dayMap.put("type" + todaymap.get("type"), todaymap.get("count"));

            }
            myfocus.setDayMap(dayMap);
            // 总量统计
            Map allMap = new HashMap();
            Map selectmap = new HashMap();
            selectmap.put("focusid", myfocus.getId());
            List<Map> allcountlist = this.focusPageService.selectByTypeCount(selectmap);
            for (int j = 0; j < allcountlist.size(); j++) {
                Map todaymap = allcountlist.get(j);
                allMap.put("type" + todaymap.get("type"), todaymap.get("count"));

            }
            myfocus.setCountMap(allMap);
        }
        mv.addObject("pagination", list);
        mv.setViewName("focus/list_focus_right");
        return mv;
    }

    /**
     * 修改专题
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/editFocus/{id}")
    public ModelAndView editFocus(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView();
        Focus focus = this.focusService.selectByPrimaryKey(id);
        mv.addObject("focus", focus);
        mv.setViewName("focus/edit_focus");
        return mv;

    }

    /**
     * 保存修改
     *
     * @param focus
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateFocus", method = RequestMethod.POST)
    public @ResponseBody String updateFocus(@ModelAttribute("focus") Focus focus, HttpServletRequest request) {

        this.focusService.updateByPrimaryKeySelective(focus);
        return "";
    }

    /**
     * 删除专题
     *
     * @param ids
     * @param commodity
     * @return
     */
    @RequestMapping(value = "/deleteFocus")
    public @ResponseBody int batchDeleteFocus(@RequestParam(value = "id", required = true) String id) {
        return this.focusService.deleteByPrimaryKey(id);
    }

    /**
     * 事件综述
     *
     * @return
     */
    @RequestMapping(value = "/listFocusInfo/{id}")
    public ModelAndView listFocusInfo(@PathVariable("id") String id, @ModelAttribute("stime") String stime, @ModelAttribute("etime") String etime) {
        // 专题信息
        ModelAndView mv = new ModelAndView();
        Focus focus = this.focusService.selectByPrimaryKey(id);
        mv.addObject("focus", focus);
        if (etime == null || etime.equals("")) {
            etime = DateUtils.todayDateStr();
        }
        if (stime == null || stime.equals("")) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(DateUtils.strToDate(etime, "yyyy-MM-dd"));

            cal.add(Calendar.DATE, -6);
            stime = DateUtils.dateToStr(cal.getTime(), "yyyy-MM-dd");
        }
        mv.addObject("stime", stime);
        mv.addObject("etime", etime);
        mv.setViewName("focus/list_focusinfo");
        return mv;
    }
}
