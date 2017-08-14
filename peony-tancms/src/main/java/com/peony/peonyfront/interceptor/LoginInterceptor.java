package com.peony.peonyfront.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 登陆拦截器
 * 
 * @author jhj
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Object obj = request.getSession().getAttribute("userfront");
        Object obj = request.getSession().getAttribute("session_user_id");
        String url = request.getServerName();
        System.out.println("*********************************url:" + url);
        if (null == obj) {
            // 未登录
            if (url.equals("mdyq.peonydata.com")) {// 普通用户登录页面
                request.getRequestDispatcher("/login").forward(request, response);
            } else {// 代理商用户登录页面
                request.getRequestDispatcher("/userlogin").forward(request, response);
            }

            return false;
        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

}
