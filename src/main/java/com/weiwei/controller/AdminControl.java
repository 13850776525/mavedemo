package com.weiwei.controller;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mysql.jdbc.util.ResultSetUtil;
import com.weiwei.bean.Admin;
import com.weiwei.bean.Log;
import com.weiwei.bean.User;
import com.weiwei.bean.rootle;
import com.weiwei.service.LogService;
import com.weiwei.service.loginServer;
import com.weiwei.utils.LayuiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/adminControl")
public class AdminControl {

    @Autowired
    private loginServer loginServer;

    @Autowired
    private LogService logService;

    @RequestMapping("/t1")
    public String text1(){
        //转发
        return "login";
    }
    @RequestMapping("/t2")
    public String text2(){
        //重定向
        return "redirect:/login.jsp";
    }



    @RequestMapping(value = "/login",produces = "text/plain;charset=utf-8")
    @ResponseBody
    //登陆方法
    public Object login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("[提示]执行了login的方法-----Controller");
        String account = request.getParameter("UserAccount");
        String password = request.getParameter("UserPassword");
        User user = loginServer.login(account,password);
        Map<String,Object> map = null;
        String msg = "";
        if (user != null && !"".equals(user)) {
            request.getSession().setAttribute("user",user);
            msg = "登陆成功";
            System.out.println("用户登陆成功！");
        } else {
            msg = "登陆失败";
        }
        return msg;
    }


    @RequestMapping(value = "/RegUser",produces = "text/plain;charset=utf-8")
    @ResponseBody
    //注册方法
    public Object RegUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String UserName = request.getParameter("UserName");
        String UserPassword = request.getParameter("UserPassword");
        String UserPhone = request.getParameter("UserPhone");
        String UserEmall = request.getParameter("UserEmall");
        String UserSex = request.getParameter("UserSex");
        System.out.println(UserSex+UserName+UserPassword);
        User user = new User();
        user.setUserName(UserName);
        user.setUserPassword(UserPassword);
        user.setUserPhone(UserPhone);
        user.setUserEmall(UserEmall);
        user.setUserSex(UserSex);
        String msg = "";
        boolean flag = loginServer.RegUser(user);
        if (flag){
            msg = "注册成功";
        }else{
            msg = "注册失败";
        }
        return msg;
    }

    @RequestMapping(value = "/FindMenuUser",produces = "text/plain;charset=utf-8")
    //寻找用户菜单
    @ResponseBody
    public ModelAndView FindMenuUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("用户寻找菜单！");
        HashMap hashMap = new HashMap();
        hashMap.put("role_id","2");
        hashMap.put("key","0");
        Map<rootle, List<rootle>> adminList = loginServer.rootleList(hashMap);
        User user = (User) request.getSession().getAttribute("user");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("adminList",adminList);
        modelAndView.addObject("name",user.getUserName());
        return modelAndView;
    }

    @RequestMapping(value = "/findImfor",produces = "text/plain;charset=utf-8")
    //获取个人信息方法
    @ResponseBody
    public ModelAndView findImfor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        ModelAndView modelAndView = new ModelAndView("user-change");
        modelAndView.addObject("user",user);
        return modelAndView;
    }


    @RequestMapping(value = "/updateUserOk",produces = "text/plain;charset=utf-8")
    //修改个人信息方法
    @ResponseBody
    public Object updateUserOk(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userPassword = request.getParameter("pass");
        String userName = request.getParameter("userName");
        String userPhone = request.getParameter("userPhone");
        String userEmall = request.getParameter("userEmall");
        String userSex = request.getParameter("userSex");
        User users = (User) request.getSession().getAttribute("user");
        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setUserPhone(userPhone);
        user.setUserEmall(userEmall);
        user.setUserSex(userSex);
        user.setUserAccount(users.getUserAccount());
        boolean flag = loginServer.updateUser(user);
        String msg = "";
        if (flag) {
            request.getSession().removeAttribute("user");
            request.getSession().setAttribute("user", user);
            msg = "成功";
        } else {
            msg = "更改失败";
        }
        return msg;
    }


    @RequestMapping(value = "/findUser",produces = "text/plain;charset=utf-8")
    //获取用户表
    @ResponseBody
    public Object findUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer page = null;
        Integer limit = null;
        page = Integer.valueOf(request.getParameter("page"));
        limit = Integer.valueOf(request.getParameter("limit"));
        String userName = request.getParameter("key[userName]");
        String userAccount = request.getParameter("key[userAccount]");
        String userClass = request.getParameter("key[userClass]");
        String startTime = request.getParameter("key[startTime]");
        String stopTime = request.getParameter("key[stopTime]");
        HashMap hashMap = new HashMap();
        if (page==null){
            page=1;
        }
        if (limit==null){
            limit=10;
        }
        if (userClass!=null&&!userClass.equals("")){
            hashMap.put("userClass",userClass);
        }
        if (userAccount!=null&&!userAccount.equals("")){
            hashMap.put("userAccount",userAccount);
        }
        if (userName!=null&&!userName.equals("")){
            hashMap.put("userName",userName);
        }
        if (startTime!=null&&!startTime.equals("")){
            hashMap.put("startTime",startTime);
        }
        if (stopTime!=null&&!stopTime.equals("")){
            hashMap.put("stopTime",stopTime);
        }
        hashMap.put("page",(page-1)*limit);
        hashMap.put("limit",10);
        List<User> adminList = loginServer.UserList(hashMap);
        int num = loginServer.UserCount(hashMap);
        LayuiData layuiData = new LayuiData();
        layuiData.setMsg("");
        layuiData.setCode(0);
        layuiData.setCount(num);
        layuiData.setData(adminList);
        return JSON.toJSONString(layuiData);
    }


    @RequestMapping(value = "/findLog",produces = "text/plain;charset=utf-8")
    //获取日志
    @ResponseBody
    public Object findLog(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer page = null;
        Integer limit = null;
        page = Integer.valueOf(request.getParameter("page"));
        limit = Integer.valueOf(request.getParameter("limit"));
        HashMap hashMap = new HashMap();
        if (page==null){
            page=1;
        }
        if (limit==null){
            limit=10;
        }
        System.out.println(limit+page+"666666666666我是！！");
        hashMap.put("page",(page-1)*limit);
        hashMap.put("limit",10);
        List<Log> adminList = logService.LogList(hashMap);
        int num = logService.LogCount(hashMap);
        LayuiData layuiData = new LayuiData();
        layuiData.setMsg("");
        layuiData.setCode(0);
        layuiData.setCount(num);
        layuiData.setData(adminList);
        System.out.println(JSON.toJSONString(layuiData));
        return JSON.toJSONString(layuiData);
    }



    @RequestMapping(value = "/addManger",produces = "text/plain;charset=utf-8")
    //管理员添加方法
    @ResponseBody
    public Object addManger(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String role = request.getParameter("role");
        String pass = request.getParameter("pass");
        System.out.println(username+role+pass);
        HashMap hashMap = new HashMap();
        hashMap.put("roleid",role);
        hashMap.put("name",username);
        hashMap.put("password",pass);
        String msg = "";
        boolean flag = loginServer.addManger(hashMap);
        if (flag){
            msg = "成功";
        }else{
            msg = "添加失败";
        }
        return msg;
    }


    @RequestMapping(value = "/updateUserState",produces = "text/plain;charset=utf-8")
    //更改状态方法
    @ResponseBody
    public void updateUserState(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String state = request.getParameter("state");
        String id = request.getParameter("id");
        if (state.equals("开启")){
            state="禁用";
        }else{
            state="开启";
        }
        User user = new User();
        user.setUserAccount(id);
        user.setUserState(state);
        boolean flag = loginServer.updateUserState(user);
        if (flag){
            response.getWriter().print("成功");
        }else{
            response.getWriter().print("更改失败");
        }
    }


    @RequestMapping(value = "/findClass",produces = "text/plain;charset=utf-8")
    //获取会员等级表
    @ResponseBody
    public Object findClass(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer page = null;
        Integer limit = null;
        page = Integer.valueOf(request.getParameter("page"));
        limit = Integer.valueOf(request.getParameter("limit"));
        String account = request.getParameter("key[account]");
        String role = request.getParameter("key[role]");
        String name = request.getParameter("key[name]");
        HashMap hashMap = new HashMap();
        if (page==null){
            page=1;
        }
        if (limit==null){
            limit=10;
        }
        if (name!=null&&!name.equals("")){
            hashMap.put("name",name);
        }
        if (role!=null&&!role.equals("")){
            hashMap.put("roleid",role);
        }
        if (account!=null&&!account.equals("")){
            hashMap.put("account",account);
        }
        hashMap.put("page",(page-1)*limit);
        hashMap.put("limit",10);
        List<Admin> adminList = loginServer.AdminList(hashMap);
        int num = loginServer.AdminCount(hashMap);
        LayuiData layuiData = new LayuiData();
        layuiData.setMsg("");
        layuiData.setCode(0);
        layuiData.setCount(num);
        layuiData.setData(adminList);
        return JSON.toJSONString(layuiData);
    }




    @RequestMapping(value = "/findAdmin",produces = "text/plain;charset=utf-8")
    //获取管理员表
    @ResponseBody
    public Object findAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer page = null;
        Integer limit = null;
        page = Integer.valueOf(request.getParameter("page"));
        limit = Integer.valueOf(request.getParameter("limit"));
        String account = request.getParameter("key[account]");
        String role = request.getParameter("key[role]");
        String name = request.getParameter("key[name]");
        HashMap hashMap = new HashMap();
        if (page==null){
            page=1;
        }
        if (limit==null){
            limit=10;
        }
        if (name!=null&&!name.equals("")){
            hashMap.put("name",name);
        }
        if (role!=null&&!role.equals("")){
            hashMap.put("roleid",role);
        }
        if (account!=null&&!account.equals("")){
            hashMap.put("account",account);
        }
        hashMap.put("page",(page-1)*limit);
        hashMap.put("limit",10);
        List<Admin> adminList = loginServer.AdminList(hashMap);
        int num = loginServer.AdminCount(hashMap);
        LayuiData layuiData = new LayuiData();
        layuiData.setMsg("");
        layuiData.setCode(0);
        layuiData.setCount(num);
        layuiData.setData(adminList);
        return JSON.toJSONString(layuiData);
    }

    @RequestMapping(value = "/deleteManger",produces = "text/plain;charset=utf-8")
    //管理员删除方法
    @ResponseBody
    public Object deleteManger(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String adminsb = request.getParameter("account");
        String[] account = adminsb.split(",");
        boolean flag = loginServer.deleteManger(account);
        String msg = "";
        if (flag){
            msg = "删除成功";
        }else{
            msg = "删除失败";
        }
        return msg;
    }


    @RequestMapping(value = "/updateManger",produces = "text/plain;charset=utf-8")
    //管理员编辑方法
    @ResponseBody
    public Object updateManger(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String admin = request.getParameter("admin");
        Gson gson = new Gson();
        Admin adminsb=gson.fromJson(admin,new TypeToken<Admin>(){}.getType());
        boolean flag = loginServer.updateManger(adminsb);
        String msg = "";
        if (flag){
            msg = "成功";
        }else{
            msg = "保存失败";
        }
        return msg;
    }


    @RequestMapping(value = "/FindMenu",produces = "text/plain;charset=utf-8")
    //管理员登陆成功获取菜单跳转方法
    @ResponseBody
    public ModelAndView FindMenu(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Admin admin = (Admin) request.getSession().getAttribute("admins");
        HashMap hashMap = new HashMap();
        hashMap.put("role_id",admin.getRoleid());
        hashMap.put("key","0");
        ModelAndView modelAndView = new ModelAndView("index");
        Map<rootle,List<rootle>> adminList = loginServer.rootleList(hashMap);
        modelAndView.addObject("adminList",adminList);
        modelAndView.addObject("name",admin.getName());
        return modelAndView;
    }

    @RequestMapping(value = "/loginManger",produces = "text/plain;charset=utf-8")
    //管理员登陆方法
    @ResponseBody
    public Object loginManger(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("我在这里！！");
        String Adminaccount = request.getParameter("AdminAccount");
        String Adminpassword = request.getParameter("AdminPassword");
        String vCode = request.getParameter("vCode");
        String vCodes = (String) request.getSession().getAttribute("vCode");
        String msg = "";
        Admin admins = loginServer.loginManger(Adminaccount,Adminpassword);
        if (admins != null && !"".equals(admins)) {
            request.getSession().setAttribute("admins",admins);

            msg = "登陆成功";
        } else {
            msg = "登陆失败";
        }
        return msg;
    }

}
