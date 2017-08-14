package com.peony.peonyfront.login.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.peony.core.base.controller.BaseController;
import com.peony.peonyfront.agent.model.Agent;
import com.peony.peonyfront.agent.service.AgentService;
import com.peony.peonyfront.login.model.Menu;
import com.peony.peonyfront.login.model.Role;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.login.service.MenuService;
import com.peony.peonyfront.login.service.RoleService;
import com.peony.peonyfront.login.service.UserService;
import com.peony.peonyfront.operationlog.model.OperationLog;
import com.peony.peonyfront.operationlog.service.OperationLogService;
import com.peony.peonyfront.util.MenuComparator;
import com.peony.peonyfront.util.encrypt.MD5;
import com.peony.peonyfront.util.log.LoginType;
import com.peony.peonyfront.util.log.OperateMode;
import com.peony.peonyfront.util.log.OperateType;
import com.peony.peonyfront.util.log.Type;

/**
 * 登录
 * 
 * @author jhj
 */
@Controller
public class LoginController extends BaseController {
    @Autowired
    private UserService         userService;        // 用户服务接口
    @Autowired
    private RoleService         roleService;        // 角色服务接口
    @Autowired
    private MenuService         menuService;        // 菜单服务接口
    @Autowired
    private AgentService        agentService;       // 代理商服务接口

    @Autowired
    private OperationLogService operationLogService;

    /**
     * 跳转到登陆页
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/login");
        return mv;
    }

    /**
     * 代理商跳转到登陆页
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/userlogin")
    public ModelAndView userlogin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/agentlogin");
        return mv;
    }

    /**
     * 登录检查
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
    @ResponseBody
    public String loginCheck(HttpServletRequest request) {

        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");
        request.getSession().setAttribute("userfront", null);// 用户信息
        request.getSession().setAttribute("session_user_id", null);// 用户信息

        List<Menu> treeSetList = new ArrayList();
        if (loginName != null && !"".equals(loginName) && password != null && !"".equals(password)) {

            String encryptPassword = MD5.passwordEncrypt(password);// 密码加密
            List<User> list = this.userService.selectUserByLoginName(loginName);
            if (list.size() > 0) {
                User u = list.get(0);
                if (encryptPassword.equalsIgnoreCase(u.getPassword())) {

                    Agent agent = null;
                    if (u.getAgentId() != null && u.getAgentId() > 0) {// 加入代理商logo及信息
                        agent = this.agentService.selectByPrimaryKey(u.getAgentId());
                    } else {
                        agent = new Agent();
                        agent.setAgentId(0);
                        agent.setLogo("");
                    }
                    request.getSession().setAttribute("agent", agent);// 代理商信息
                    request.getSession().setAttribute("userfront", u);// 用户信息
                    request.getSession().setAttribute("session_user_id", u.getUserId());// 用户id

                    OperationLog operationLog = new OperationLog(new Date(), u.getUserId(), u.getName(), String.valueOf(Type.LOGIN.getValue()), String.valueOf(LoginType.PC.getValue()), "pc前台登录", OperateType.FIND.toString(), OperateMode.首页.toString());
                    this.operationLogService.insertSelective(operationLog);

                    List<Role> roleList = this.roleService.selectByUserId(u.getUserId());
                    TreeSet<Menu> menuSet = new TreeSet(new MenuComparator());
                    if (roleList.size() > 0) {
                        for (Role newrole : roleList) {
                            List<Menu> menuList = this.menuService.selectByRoleId(newrole.getRoleId());
                            for (Menu menu : menuList) {
                                String code = menu.getIdentify();
                                if (code.substring(0, code.length() - 3).equals("20")) {// 权限标识字段
                                                                                        // 左移三位==10的写入后台menuset
                                                                                        // 前台则为==20的
                                    menuSet.add(menu);
                                }
                            }
                        }

                    }
                    for (Iterator<Menu> iterator = menuSet.iterator(); iterator.hasNext();) {
                        treeSetList.add(iterator.next());
                    }

                    long currentTime = new Date().getTime();
                    long expirTime = u.getExpiryTime().getTime();
                    long between = (expirTime - currentTime) / 1000;// 除以1000是为了转换成秒
                    long days = between / (24 * 3600);

                    request.getSession().setAttribute("days", days);// 过期剩余时间
                    request.getSession().setAttribute("menuSet", treeSetList);
                    return "1";
                }
            } else {
                return "0";
            }

        } else {
            return "0";
        }

        return null;

    }

    /**
     * 从后台直接登录 检查
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/backloginCheck")
    public String backloginCheck(@ModelAttribute("user") User user, HttpServletRequest request) {
        String loginName = request.getParameter("backloginName");
        String password = user.getPassword();
        request.getSession().setAttribute("userfront", null);// 用户信息
        request.getSession().setAttribute("session_user_id", null);// 用户id

        List<Menu> treeSetList = new ArrayList();
        if (loginName != null && !"".equals(loginName) && password != null && !"".equals(password)) {

            List<User> list = this.userService.selectUserByLoginName(loginName);
            if (list.size() > 0) {
                User u = list.get(0);
                if (password.equalsIgnoreCase(u.getPassword())) {

                    request.getSession().setAttribute("userfront", u);// 用户信息
                    request.getSession().setAttribute("session_user_id", u.getUserId());// 用户id

                    OperationLog operationLog = new OperationLog(new Date(), u.getUserId(), u.getName(), String.valueOf(Type.LOGIN.getValue()), String.valueOf(LoginType.PC.getValue()), "登录", OperateType.FIND.toString(), OperateMode.首页.toString());
                    this.operationLogService.insertSelective(operationLog);
                    List<Role> roleList = this.roleService.selectByUserId(u.getUserId());
                    TreeSet<Menu> menuSet = new TreeSet(new MenuComparator());
                    if (roleList.size() > 0) {
                        for (Role newrole : roleList) {
                            List<Menu> menuList = this.menuService.selectByRoleId(newrole.getRoleId());
                            for (Menu menu : menuList) {
                                String code = menu.getIdentify();
                                if (code.substring(0, code.length() - 3).equals("20")) {// 权限标识字段
                                                                                        // 左移三位==10的写入后台menuset
                                                                                        // 前台则为==20的
                                    menuSet.add(menu);
                                }
                            }
                        }

                    }
                    for (Iterator<Menu> iterator = menuSet.iterator(); iterator.hasNext();) {
                        treeSetList.add(iterator.next());
                    }
                    request.getSession().setAttribute("menuSet", treeSetList);
                    long currentTime = new Date().getTime();
                    long expirTime = u.getExpiryTime().getTime();
                    long between = (expirTime - currentTime) / 1000;// 除以1000是为了转换成秒
                    long days = between / (24 * 3600);

                    request.getSession().setAttribute("days", days);// 过期剩余时间

                    return "redirect:/homepage/listHomePage";
                }
            } else {
                return "redirect:login";
            }

        } else {
            return "redirect:login";
        }

        return null;

    }

    /**
     * 登出
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("userfront", null);
        request.getSession().setAttribute("menuSet", null);
        request.getSession().setAttribute("session_user_id", null);// 用户id
        request.getSession().invalidate();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/login");
        return mv;
    }

    /**
     * 代理商登出
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/userLogout", method = RequestMethod.GET)
    public ModelAndView userLogout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("userfront", null);
        request.getSession().setAttribute("menuSet", null);
        request.getSession().setAttribute("session_user_id", null);// 用户id
        request.getSession().invalidate();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/agentlogin");
        return mv;
    }

    @RequestMapping(value = "/toModifyPwd", method = RequestMethod.GET)
    public ModelAndView toModifyPwd(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("common/modify_pwd");
        return mv;

    }

    /**
     * 修改密码信息
     * 
     * @param request @param resModel @return @throws
     */
    @RequestMapping(value = "/updatePassword", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> updatePassword(HttpServletRequest request) throws ServletRequestBindingException {

        String oldPassword = ServletRequestUtils.getStringParameter(request, "oldPassword"); // 旧密码
        String newPassword = ServletRequestUtils.getStringParameter(request, "newPassword");// 新密码

        HashMap mapModel = Maps.newHashMap();
        // 检查session中是否有此用户
        User userModel = (User) request.getSession().getAttribute("userfront");

        if (userModel != null) {
            String encryptPassword = MD5.passwordEncrypt(oldPassword);
            if (userModel.getPassword().equals(encryptPassword)) {
                userModel.setPassword(MD5.passwordEncrypt(newPassword));
                this.userService.updateByPrimaryKeySelective(userModel);
                request.getSession().setAttribute("userfront", userModel);
                mapModel.put("status", "1");
            } else {
                mapModel.put("status", "2");
            }
        } else {
            mapModel.put("status", "0");
        }

        return mapModel;
    }

    /**
     * 修改密码信息
     * 
     * @param request @param resModel @return @throws
     */
    @RequestMapping(value = "/updateUserStyle")
    @ResponseBody
    public Map<String, Object> updateUserStyle(HttpServletRequest request) throws ServletRequestBindingException {

        String userStyle = ServletRequestUtils.getStringParameter(request, "userStyle");
        HashMap mapModel = Maps.newHashMap();
        // 检查session中是否有此用户
        User userModel = (User) request.getSession().getAttribute("userfront");

        if (userModel != null) {
            userModel.setUserStyle(userStyle);
            this.userService.updateUserStyle(userModel);
            request.getSession().setAttribute("userfront", userModel);
            mapModel.put("status", "1");

        } else {
            mapModel.put("status", "0");
        }

        return mapModel;
    }
}
