package com.peony.peonyfront.warningkeyws.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.peony.core.base.controller.BaseController;
import com.peony.peonyfront.key.service.IdService;
import com.peony.peonyfront.warningkeyws.model.Warningkeyws;
import com.peony.peonyfront.warningkeyws.service.WarningkeywsService;

/**
 * 预警关键词设置
 * 
 * @author zhyz
 *
 */
@Controller
@RequestMapping("/warningkeyws")
public class WarningkeywsController extends BaseController {
    @Autowired
    private IdService           idService;          // id服务接口
    @Autowired
    private WarningkeywsService warningkeywsService;

    /**
     * 跳转到添加关键词设置页面
     * 
     * @return
     */
    @RequestMapping(value = "/addwarningkeyws", method = RequestMethod.GET)
    public ModelAndView addWarningkeyws() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("warningkeyws/add_warningkeyws");
        return mv;
    }

    /**
     * 添加关键词
     * 
     * @param userkeyws
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/saveWarningkeyws", method = RequestMethod.POST)
    @ResponseBody
    public String saveUserkeyws(@ModelAttribute("warningkeyws") Warningkeyws warningkeyws, HttpServletRequest request, HttpServletResponse response) {
        warningkeyws.setWarningkeywsId(idService.NextKey("userkeyws_id"));
        warningkeywsService.insertWarningkeyws(warningkeyws);
        return "";
    }
}
