package com.weiwei.servlet;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    //servlet使用@Autowired 需要使用下面的初始化方法
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        //1.获取方法名
        String methodName = req.getParameter("methodName");//------------->接收数据,封装实体
        System.out.println("我在Base:"+methodName);
        Class claszz = this.getClass();//-------->获取当前类的全类名---获取这个类
        try {
            if (methodName != null && !"".equals(methodName.trim())) {
                Method method = claszz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);//-------->反射获得方法
                String url = (String) method.invoke(this, req, res);//-------->反射调用
                System.out.println("url的值："+url);
                if (url != null) {
                    req.getRequestDispatcher(url).forward(req, res);//------->请求转发
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
