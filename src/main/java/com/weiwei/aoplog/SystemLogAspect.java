package com.weiwei.aoplog;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.weiwei.bean.Admin;
import com.weiwei.bean.User;
import com.weiwei.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * @author zx
 * @desc 切点类
 */

@Aspect
@Component
public class SystemLogAspect {

    @Autowired
    private LogService logService;
//    ServiceLog serviceLog= (ServiceLog) ObjectFactory.getObject("com.yaofen.servicelog.impl.ServiceLogImpl");
    //注入Service用于把日志保存数据库
//    @Resource  //这里我用resource注解
//    private SystemLogService systemLogService;


    //这里的zxtest要和log4j.properties里的配置一致，否则写不到文件中
    private static Logger logger = Logger.getLogger("zxtest");

    //Controller层切点
    //service.*表示service下面所有类及子类
    //*(..) 这个表示适配的方法，也就是所有方法，例如，要适配登录方法*login(..)
    @Pointcut("execution (* com.weiwei.service.Impl.*.*(..))")
    public  void controllerAspect() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("==========执行controller前置通知===============");
    }

    //配置controller环绕通知,使用在方法aspect()上注册的切入点 需要有返回值
    @Around("controllerAspect()")
    public Object around(JoinPoint joinPoint){
        System.out.println("==========开始执行controller环绕通知===============");
        long start = System.currentTimeMillis();
        Object object=null;
        String methodName = joinPoint.getSignature().getName();
        try {
            object= ((ProceedingJoinPoint) joinPoint).proceed();
            long end = System.currentTimeMillis();
            System.out.println("==========结束执行controller环绕通知===============");
        } catch (Throwable e) {
            System.out.println("环绕通知中的异常--------------------------------"+methodName+"-------"+e.getMessage());
            long end = System.currentTimeMillis();
        }
        return object;
    }

    /**
     * 后置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @After("controllerAspect()")
    public  void after(JoinPoint joinPoint) throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户
        //请求的IP
        //String ip = request.getRemoteAddr();
        String ip = "127.0.0.1";
        try {
            User user = (User) request.getSession().getAttribute("user");
            Admin admin = (Admin) request.getSession().getAttribute("admins");
            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            String operationType = "";
            String operationName = "";
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length) {
                        operationType = method.getAnnotation(Log.class).operationType();
                        operationName = method.getAnnotation(Log.class).operationName();
                        break;
                    }
                }
            }
            //*========控制台输出=========*//
            //*========数据库日志=========*//
            if (user!=null){
                System.out.println("=====controller后置通知开始=====");
                System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")+"."+operationType);
                System.out.println("请求类型:" + operationType);
                System.out.println("请求角色:" + operationName);
                System.out.println("请求IP:" + ip);
                System.out.println("请求人:" + user.getUserName());
                com.weiwei.bean.Log log = new com.weiwei.bean.Log();
                log.setLogUser(user.getUserName());
                log.setLogThing(operationType);
                logService.addLog(log);
                System.out.println(log.toString()+"66666666666666666666666666666666");
            }
            if (admin!=null){
                System.out.println("=====controller后置通知开始=====");
                System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")+"."+operationType);
                System.out.println("请求类型:" + operationType);
                System.out.println("请求角色:" + operationName);
                System.out.println("请求IP:" + ip);
                System.out.println("请求人:" + admin.getName());
                com.weiwei.bean.Log log = new com.weiwei.bean.Log();
                log.setLogUser(admin.getName());
                log.setLogThing(operationType);
                logService.addLog(log);
                System.out.println(log.toString()+"66666666666666666666666666666666");
            }
            System.out.println("=====controller后置通知结束=====");
        }  catch (Exception e) {
            throw e;
        }
    }

    //配置后置返回通知,使用在方法aspect()上注册的切入点
//      @AfterReturning("controllerAspect()")
//      public void afterReturn(JoinPoint joinPoint){
//          System.out.println("=====执行controller后置返回通知=====----");
//              if(logger.isInfoEnabled()){
//                  logger.info("afterReturn " + joinPoint);
//              }
//      }

    /**
     * 异常通知 用于拦截记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing="e")
    public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) throws Throwable{
        /*HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户
        User user = (User) session.getAttribute(WebConstants.CURRENT_USER);
        //获取请求ip
        String ip = request.getRemoteAddr(); */
        //获取用户请求方法的参数并序列化为JSON格式字符串
        System.out.println("异常通知开始------------------------------------------");
        String ip = "127.0.0.1";

        String params = "";
        if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {
            for ( int i = 0; i < joinPoint.getArgs().length; i++) {
//                params += JsonUtil.getJsonStr(joinPoint.getArgs()[i]) + ";";
                params += joinPoint.getArgs()[i] + ";";
            }
        }
        try {
            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            String operationType = "";
            String operationName = "";
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length) {
//                          operationType = method.getAnnotation(Log.class).operationType();
//                          operationName = method.getAnnotation(Log.class).operationName();
                        break;
                    }
                }
            }
            /*========控制台输出=========*/
            System.out.println("=====异常通知开始=====");
            System.out.println("异常代码:" + e.getClass().getName());
            System.out.println("异常信息:" + e.getMessage());
            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")+"."+operationType);
            System.out.println("方法描述:" + operationName);
//            System.out.println("请求人:" + user.getName());
            System.out.println("请求IP:" + ip);
            System.out.println("请求参数:" + params);
//            systemLogService.insert(log);
            System.out.println("=====异常通知结束=====");
        }  catch (Exception ex) {
        }
    }

}