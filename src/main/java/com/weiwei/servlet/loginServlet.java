package com.weiwei.servlet;

import com.alibaba.fastjson.JSON;
import com.weiwei.bean.Admin;
import com.weiwei.bean.Log;
import com.weiwei.bean.User;
import com.weiwei.bean.rootle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.weiwei.service.LogService;
import com.weiwei.service.loginServer;
import com.weiwei.utils.LayuiData;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/loginServlet")
public class loginServlet extends BaseServlet{

    @Autowired
    private loginServer loginServlets;

    @Autowired
    private LogService logService;


//    loginServer loginServlets = (loginServer) ObjectFactory.getObject("com.service.Impl.loginServerlmpl");

    //登陆方法
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("执行了login的方法");
        String account = request.getParameter("UserAccount");
        String password = request.getParameter("UserPassword");
//        String vCode = request.getParameter("vCode");
//        String vCodes = (String) request.getSession().getAttribute("vCode");
//        if (vCodes.equalsIgnoreCase(vCode)) {
            User user = loginServlets.login(account,password);
            if (user != null && !"".equals(user)) {
                request.getSession().setAttribute("user",user);
                response.getWriter().print("登陆成功");
                System.out.println("用户登陆成功！");
            } else {
                response.getWriter().print("登陆失败");
            }
//        }else {
//            response.getWriter().print("验证码错误");
//        }
    }

    //获取个人信息方法
    public void findImfor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("user",user);
        request.getRequestDispatcher("jsp/user-change.jsp").forward(request,response);//请求转发
    }


    //修改个人信息方法
    public void updateUserOk(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
        boolean flag = loginServlets.updateUser(user);
        if(flag){
//            User userNew = loginServlets.login(users.getUserAccount(),userPassword);
            request.getSession().removeAttribute("user");
            request.getSession().setAttribute("user",user);
            response.getWriter().print("成功");
        }else{
            response.getWriter().print("更改失败！");
        }


    }

    //注册方法
    public void RegUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        boolean flag = loginServlets.RegUser(user);
        if (flag){
            response.getWriter().print("注册成功");
        }else{
            response.getWriter().print("注册失败");
        }
    }

    //获取用户
    public void findUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
        List<User> adminList = loginServlets.UserList(hashMap);
        int num = loginServlets.UserCount(hashMap);
        LayuiData layuiData = new LayuiData();
        layuiData.setMsg("");
        layuiData.setCode(0);
        layuiData.setCount(num);
        layuiData.setData(adminList);
        System.out.println(JSON.toJSONString(layuiData));
        response.getWriter().write(JSON.toJSONString(layuiData));
    }

    //更改状态方法
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
        boolean flag = loginServlets.updateUserState(user);
        if (flag){
            response.getWriter().print("成功");
        }else{
            response.getWriter().print("更改失败");
        }
    }
    //用户登陆成功获取菜单跳转方法
    public void FindMenuUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("用户寻找菜单！");
        HashMap hashMap = new HashMap();
        hashMap.put("role_id","2");
        hashMap.put("key","0");
        Map<rootle,List<rootle>> adminList = loginServlets.rootleList(hashMap);
        User user =  (User) request.getSession().getAttribute("user");
        request.setAttribute("adminList",adminList);
        request.setAttribute("name",user.getUserName());
        request.getRequestDispatcher("jsp/index.jsp").forward(request,response);//请求转发
    }


    //管理员登陆成功获取菜单跳转方法
    public void FindMenu(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Admin admin = (Admin) request.getSession().getAttribute("admins");
        HashMap hashMap = new HashMap();
        hashMap.put("role_id",admin.getRoleid());
        hashMap.put("key","0");
        Map<rootle,List<rootle>> adminList = loginServlets.rootleList(hashMap);
        request.setAttribute("adminList",adminList);
        request.setAttribute("name",admin.getName());
        request.getRequestDispatcher("jsp/index.jsp").forward(request,response);//请求转发
    }

    //管理员登陆方法
    public void loginManger(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("我在这里！！");
        String Adminaccount = request.getParameter("AdminAccount");
        String Adminpassword = request.getParameter("AdminPassword");
        String vCode = request.getParameter("vCode");
        String vCodes = (String) request.getSession().getAttribute("vCode");
//        request.getSession().removeAttribute("vCode");
//        if (vCodes.equalsIgnoreCase(vCode)) {
            Admin admins = loginServlets.loginManger(Adminaccount,Adminpassword);
            if (admins != null && !"".equals(admins)) {
                request.getSession().setAttribute("admins",admins);
                response.getWriter().print("登陆成功");
            } else {
                response.getWriter().print("登陆失败");
            }
//        }else {
//            response.getWriter().print("验证码错误");
//        }
    }

    //获取管理员表
    public void findAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
        List<Admin> adminList = loginServlets.AdminList(hashMap);
        int num = loginServlets.AdminCount(hashMap);
        LayuiData layuiData = new LayuiData();
        layuiData.setMsg("");
        layuiData.setCode(0);
        layuiData.setCount(num);
        layuiData.setData(adminList);
        System.out.println(JSON.toJSONString(layuiData));
        response.getWriter().write(JSON.toJSONString(layuiData));
    }

    //管理员删除方法
    public void deleteManger(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String adminsb = request.getParameter("account");
        System.out.println(adminsb+"这是我的删除！！！");
        String[] account = adminsb.split(",");
        System.out.println(account.length+"哈哈哈66");
        boolean flag = loginServlets.deleteManger(account);
        if (flag){
            response.getWriter().print("删除成功");
        }else{
            response.getWriter().print("删除失败");
        }
    }

    //管理员编辑方法
    public void updateManger(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String admin = request.getParameter("admin");
        Gson gson = new Gson();
        Admin adminsb=gson.fromJson(admin,new TypeToken<Admin>(){}.getType());
        boolean flag = loginServlets.updateManger(adminsb);
        if (flag){
            response.getWriter().print("成功");
        }else{
            response.getWriter().print("保存失败");
        }
    }

    //管理员添加方法
    public void addManger(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String role = request.getParameter("role");
        String pass = request.getParameter("pass");
        System.out.println(username+role+pass);
        HashMap hashMap = new HashMap();
        hashMap.put("roleid",role);
        hashMap.put("name",username);
        hashMap.put("password",pass);

        boolean flag = loginServlets.addManger(hashMap);
        if (flag){
            response.getWriter().print("成功");
        }else{
            response.getWriter().print("添加失败");
        }
    }


    //获取会员等级表
    public void findClass(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
        List<Admin> adminList = loginServlets.AdminList(hashMap);
        int num = loginServlets.AdminCount(hashMap);
        LayuiData layuiData = new LayuiData();
        layuiData.setMsg("");
        layuiData.setCode(0);
        layuiData.setCount(num);
        layuiData.setData(adminList);
        System.out.println(JSON.toJSONString(layuiData));
        response.getWriter().write(JSON.toJSONString(layuiData));
    }

    //获取日志
    public void findLog(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pages = request.getParameter("page");
        String limits = request.getParameter("limit");
        Integer limit = 0;
        Integer page = 0;
        HashMap hashMap = new HashMap();
        if (pages==null){
            page=1;
        }else{
            page = Integer.valueOf(pages);
        }
        if (limits==null){
            limit=10;
        }else{
            limit = Integer.valueOf(limits);
        }
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
        response.getWriter().write(JSON.toJSONString(layuiData));
    }






}
