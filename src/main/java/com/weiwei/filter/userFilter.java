package com.weiwei.filter;

import com.weiwei.bean.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class userFilter implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入登录拦截器");
        System.out.println(request.getRequestURL());
        User admin = (User) request.getSession().getAttribute("user");
        System.out.println("当前登录信息：" + admin);
        if (admin == null) {
            //未登录im
            if (request.getHeader("x-requested-with") != null
                    && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                //处理ajax请求
                System.out.println("ajax调用");
                response.setHeader("sessionstatus", "timeout");
                response.setStatus(403);
                return false;
            } else {
                //处理controller请求
                String loginStr = request.getContextPath() + "/jsp/login.jsp";
                System.out.println("页面请求");
                System.out.println("登录界面:" + loginStr);
                response.sendRedirect(loginStr);
                return false;
            }
        } else
            return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
