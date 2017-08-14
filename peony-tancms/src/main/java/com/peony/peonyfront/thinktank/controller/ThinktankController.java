package com.peony.peonyfront.thinktank.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.peony.core.base.pojo.PageParameter;
import com.peony.core.base.pojo.Pagination;
import com.peony.core.common.utils.log.OperateType;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.operationlog.model.OperationLog;
import com.peony.peonyfront.operationlog.service.OperationLogService;
import com.peony.peonyfront.thinktank.model.Channel;
import com.peony.peonyfront.thinktank.model.Pubinfo;
import com.peony.peonyfront.thinktank.service.ChannelService;
import com.peony.peonyfront.thinktank.service.PubinfoService;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.Type;

@Controller
@RequestMapping("/thinktank")
public class ThinktankController {
    @Autowired
    private ChannelService      channelService;
    @Autowired
    private PubinfoService      pubinfoService;
    @Autowired
    private OperationLogService operationLogService;

    /**
     * 频道列表
     * 
     * @param channel
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listChannel")
    public ModelAndView listChannel(@ModelAttribute("channel") Channel channel, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        // --设置案例库节点
        channel.setChannelPid(4);
        PageParameter pageParameter = new PageParameter();
        if (null == request.getParameter("pageNo")) {
            pageParameter.setCurrentPage(1);
        } else {
            pageParameter.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
        }
        channel.setPageParameter(pageParameter);
        Pagination<Channel> pagination = channelService.selectChannelByPage(channel);

        User u = (User) request.getSession().getAttribute("userfront");
        OperationLog operationLog = new OperationLog(new Date(), u.getUserId(), u.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "案例库", OperateType.FIND.toString(), OperateMode.智库.toString());
        this.operationLogService.insertSelective(operationLog);

        mv.addObject("pagination", pagination);
        mv.setViewName("thinktank/list_channel");
        return mv;
    }

    /**
     * 点击节点，进入到该节点下的案例列表页
     * 
     * @param pubinfo
     * @return
     */
    @RequestMapping(value = "/listPubinfo")
    @ResponseBody
    public ModelAndView listPubinfo(@ModelAttribute("pubinfo") Pubinfo pubinfo, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        if (pubinfo.getFirst() != null && !"-1".equals(pubinfo.getFirst())) {
            Channel channelSecond = this.channelService.selectByPrimaryKey(Integer.parseInt(pubinfo.getChannelId()));
            mv.addObject("channel", channelSecond);
            List<Channel> channelSecondList = this.channelService.selectChannelByPid(channelSecond);
            mv.addObject("channelSecondList", channelSecondList);
        }
        if (pubinfo.getFirst() == null) {
            pubinfo.setFirst("-1");
        }

        if (pubinfo.getFirst().equals("-1")) {
            pubinfo.setChannelId(null);
        }

        // 查出案例库下所有子节点
        /*
         * Channel channel=new Channel(); channel.setChannelPid(4);
         * List<Channel>
         * channelList=this.channelService.selectChannelByPid(channel);
         * mv.addObject("channelList", channelList);
         */
        // 查询推荐类型
        if (pubinfo.getClassify() != null) {
            mv.addObject("classify", pubinfo.getClassify());
            if (pubinfo.getClassify() == 1) {
                pubinfo.setSubjectRecommend("1");
            }
            if (pubinfo.getClassify() == 2) {
                pubinfo.setCaseRecommend("1");
            }
        }

        PageParameter pageParameter = new PageParameter();
        // 每页显示条数
        if (pubinfo.getPageSize() != 0) {
            mv.addObject("pageSize", pubinfo.getPageSize());
            pageParameter.setPageSize(pubinfo.getPageSize());
        }
        if (null == request.getParameter("pageNo")) {
            pageParameter.setCurrentPage(1);
        } else {
            pageParameter.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
        }
        pubinfo.setPageParameter(pageParameter);

        Pagination<Pubinfo> pagination = this.pubinfoService.selectPubinfoByPage(pubinfo);

        User u = (User) request.getSession().getAttribute("userfront");
        OperationLog operationLog = new OperationLog(new Date(), u.getUserId(), u.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "案例库", OperateType.FIND.toString(), OperateMode.智库.toString());
        this.operationLogService.insertSelective(operationLog);

        mv.addObject("pubinfo", pubinfo);
        mv.addObject("pagination", pagination);
        mv.setViewName("thinktank/list_pubinfo");
        return mv;
    }

    /**
     * 点击处置经验，跳转到处置经验列表
     * 
     * @param channelId
     * @return
     */
    @RequestMapping(value = "/listAllPubinfo")
    @ResponseBody
    public ModelAndView listAllPubinfo(@ModelAttribute("pubinfo") Pubinfo pubinfo, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        // --设置处置经验频道
        pubinfo.setChannelId("5");
        PageParameter pageParameter = new PageParameter();
        if (null == request.getParameter("pageNo")) {
            pageParameter.setCurrentPage(1);
        } else {
            pageParameter.setCurrentPage(Integer.parseInt(request.getParameter("pageNo")));
        }
        pubinfo.setPageParameter(pageParameter);
        Pagination<Pubinfo> pagination = this.pubinfoService.selectPubByPage(pubinfo);

        User u = (User) request.getSession().getAttribute("userfront");
        OperationLog operationLog = new OperationLog(new Date(), u.getUserId(), u.getName(), String.valueOf(Type.OPERATE.getValue()), String.valueOf(LoginType.PC.getValue()), "处置经验", OperateType.FIND.toString(), OperateMode.智库.toString());
        this.operationLogService.insertSelective(operationLog);
        mv.addObject("pagination", pagination);
        mv.setViewName("thinktank/list_allPubinfo");
        return mv;
    }

    /**
     * 点击列表，查看文章详细
     * 
     * @param channelId
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/getInfoPubinfo")
    @ResponseBody
    public ModelAndView getInfoPubinfo(@ModelAttribute("pubinfo") Pubinfo pubinfo, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView mv = new ModelAndView();
        pubinfo = this.pubinfoService.selectContentByPrimaryKey(pubinfo);
        mv.addObject("pubinfo", pubinfo);
        mv.setViewName("thinktank/info_pubinfo");
        return mv;
    }

    /**
     * 获取分类下的列表（政府类或企业类）
     * 
     * @return
     */
    @RequestMapping(value = "/getChannelList")
    @ResponseBody
    public Map<String, Object> getChannelList(Channel channel, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> mapModel = Maps.newHashMap();
        List<Channel> channelSecondList = this.channelService.selectChannelByPid(channel);
        mapModel.put("list", channelSecondList);
        return mapModel;
    }
}
