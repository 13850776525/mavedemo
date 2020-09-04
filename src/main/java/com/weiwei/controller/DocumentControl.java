package com.weiwei.controller;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.tools.javac.util.Convert;
import com.weiwei.bean.*;
import com.weiwei.bean.Class;
import com.weiwei.service.DocumentServer;
import com.weiwei.service.LogService;
import com.weiwei.service.loginServer;
import com.weiwei.utils.LayuiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.print.Doc;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/documentControl")
public class DocumentControl {

    @Autowired
    private DocumentServer documentServlet;
    @Autowired
    private loginServer loginServer;

    @RequestMapping(value = "/findDocAll",produces = "text/plain;charset=utf-8")
    //获取文档表
    @ResponseBody
    public Object findDocAll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer page = null;
        Integer limit = null;
        page = Integer.valueOf(request.getParameter("page"));
        limit = Integer.valueOf(request.getParameter("limit"));
        String docTitle = request.getParameter("key[docTitle]");
        HashMap hashMap = new HashMap();
        if (page==null){
            page=1;
        }
        if (limit==null){
            limit=10;
        }
        if (docTitle!=null&&!docTitle.equals("")) {
            hashMap.put("docTitle", docTitle);
        }
        User user = (User) request.getSession().getAttribute("user");
        hashMap.put("page",(page-1)*limit);
        hashMap.put("limit",10);
        List<Document> adminList = documentServlet.DocumentListAll(hashMap);
        int num = documentServlet.DocCountAll(hashMap);
        LayuiData layuiData = new LayuiData();
        layuiData.setMsg("");
        layuiData.setCode(0);
        layuiData.setCount(num);
        layuiData.setData(adminList);
        return JSON.toJSONString(layuiData);
    }

    @RequestMapping(value = "/findDocMy",produces = "text/plain;charset=utf-8")
    //获取我的文档表
    @ResponseBody
    public Object findDocMy(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer page = null;
        Integer limit = null;
        page = Integer.valueOf(request.getParameter("page"));
        limit = Integer.valueOf(request.getParameter("limit"));
        String docTitle = request.getParameter("key[docTitle]");
        String userId = request.getParameter("key[userId]");
        String startTime = request.getParameter("key[startTime]");
        String stopTime = request.getParameter("key[stopTime]");
        String docType = request.getParameter("key[docType]");
        HashMap hashMap = new HashMap();
        if (page==null){
            page=1;
        }
        if (limit==null){
            limit=10;
        }
        if (docTitle!=null&&!docTitle.equals("")){
            hashMap.put("docTitle",docTitle);
        }
        if (userId!=null&&!userId.equals("")){
            hashMap.put("userId",userId);
        }
        if (startTime!=null&&!startTime.equals("")){
            hashMap.put("startTime",startTime);
        }
        if (stopTime!=null&&!stopTime.equals("")){
            hashMap.put("stopTime",stopTime);
        }
        if (docType!=null&&!docType.equals("")){
            hashMap.put("docType",docType);
        }
        User user = (User) request.getSession().getAttribute("user");
        hashMap.put("page",(page-1)*limit);
        hashMap.put("limit",10);
        hashMap.put("userAccount",user.getUserAccount());
        List<Document> adminList = documentServlet.DocumentList(hashMap);
        int num = documentServlet.DocCount(hashMap);
        LayuiData layuiData = new LayuiData();
        layuiData.setMsg("");
        layuiData.setCode(0);
        layuiData.setCount(num);
        layuiData.setData(adminList);
        return JSON.toJSONString(layuiData);
    }


    @RequestMapping(value = "/findDoc",produces = "text/plain;charset=utf-8")
    //获取文档   管理员
    @ResponseBody
    public Object findDoc(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer page = null;
        Integer limit = null;
        page = Integer.valueOf(request.getParameter("page"));
        limit = Integer.valueOf(request.getParameter("limit"));
        String docTitle = request.getParameter("key[docTitle]");
        String userId = request.getParameter("key[userId]");
        String startTime = request.getParameter("key[startTime]");
        String stopTime = request.getParameter("key[stopTime]");
        String docType = request.getParameter("key[docType]");
        HashMap hashMap = new HashMap();
        if (page==null){
            page=1;
        }
        if (limit==null){
            limit=10;
        }
        if (docTitle!=null&&!docTitle.equals("")){
            hashMap.put("docTitle",docTitle);
        }
        if (userId!=null&&!userId.equals("")){
            hashMap.put("userId",userId);
        }
        if (startTime!=null&&!startTime.equals("")){
            hashMap.put("startTime",startTime);
        }
        if (stopTime!=null&&!stopTime.equals("")){
            hashMap.put("stopTime",stopTime);
        }
        if (docType!=null&&!docType.equals("")){
            hashMap.put("docType",docType);
        }

        hashMap.put("page",(page-1)*limit);
        hashMap.put("limit",10);
        List<Document> adminList = documentServlet.DocumentList(hashMap);
        int num = documentServlet.DocCount(hashMap);
        LayuiData layuiData = new LayuiData();
        layuiData.setMsg("");
        layuiData.setCode(0);
        layuiData.setCount(num);
        layuiData.setData(adminList);
        return JSON.toJSONString(layuiData);
    }



    @RequestMapping(value = "/findDtype",produces = "text/plain;charset=utf-8")
    //获取文档类型表
    @ResponseBody
    public Object findDtype(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Dtype> adminList = documentServlet.findDtype();
        LayuiData layuiData = new LayuiData();
        layuiData.setMsg("");
        layuiData.setCode(0);
        layuiData.setCount(100);
        layuiData.setData(adminList);
        return JSON.toJSONString(layuiData);
    }


    @RequestMapping(value = "/deleteDtype",produces = "text/plain;charset=utf-8")
    //文档配置删除方法
    @ResponseBody
    public Object deleteDtype(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String dtypeId = request.getParameter("dtypeId");
        String mag = "";
        boolean flag = documentServlet.deleteDtype(dtypeId);
        if (flag){
            mag = "删除成功";
        }else{
            mag = "删除失败";
        }
        return mag;
    }


    @RequestMapping(value = "/updateState",produces = "text/plain;charset=utf-8")
    //更改状态方法
    @ResponseBody
    public Object updateState(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String state = request.getParameter("state");
        Integer id = Integer.valueOf(request.getParameter("id"));
        if (state.equals("1")){
            state="0";
        }else{
            state="1";
        }
        Document document = new Document();
        document.setId(id);
        document.setDocState(state);
        String mag = "";
        boolean flag = documentServlet.updateState(document);
        if (flag){
            mag = "成功";
        }else{
            mag = "审核失败";
        }
        return mag;
    }


    @RequestMapping(value = "/updateDtypeState",produces = "text/plain;charset=utf-8")
    //更改文档配置状态方法
    @ResponseBody
    public Object updateDtypeState(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String state = request.getParameter("state");
        String id = request.getParameter("id");
        if (state.equals("开启")){
            state="禁用";
        }else{
            state="开启";
        }
        String mag = "";
        Dtype user = new Dtype();
        user.setDtypeId(id);
        user.setDtypeState(state);
        boolean flag = documentServlet.updateDtypeState(user);
        if (flag){
            mag = "成功";
        }else{
            mag = "更改失败";
        }
        return mag;
    }


    @RequestMapping(value = "/addDtype",produces = "text/plain;charset=utf-8")
    //文档配置添加方法
    @ResponseBody
    public Object addDtype(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String dtypeName = request.getParameter("dtypeName");
        String dtypeScore = request.getParameter("dtypeScore");
        String dtypeState = request.getParameter("dtypeState");
        System.out.println(dtypeState+dtypeScore+dtypeName);
        HashMap hashMap = new HashMap();
        hashMap.put("dtypeName",dtypeName);
        hashMap.put("dtypeScore",dtypeScore);
        hashMap.put("dtypeState",dtypeState);
        String msg = "";
        boolean flag = documentServlet.addDtype(hashMap);
        if (flag){
            msg = "成功";
        }else{
            msg = "添加失败";
        }
        return msg;
    }


    //文件上传
    @RequestMapping(value = "/upload")
    @ResponseBody
    public Object upload(HttpServletRequest request, HttpServletResponse response, MultipartFile file,String title,String score) {
        try {
            //获取文件名
            String originalName = file.getOriginalFilename();
            System.out.println("[提示]文件名："+originalName);
            System.out.println("新文件："+title+"积分："+score);
            User user = (User) request.getSession().getAttribute("user");
            Class classBean = documentServlet.classBean(user.getUserClass());
            //积分：一次上传基础积分100分*等级倍数
            double scoreBean = 100*Double.parseDouble(classBean.getClassUpload());
            System.out.println("[提示]积分上传数："+scoreBean);
            Score score1 = new Score();
            score1.setScoUser(user.getUserAccount());
            score1.setScoScore(scoreBean+"");
            score1.setScoType("上传文档");
            documentServlet.ScoreAdd(score1);

            //扩展名
            String prefix = originalName.substring(originalName.lastIndexOf(".") + 1);
            Document document = new Document();
            document.setDocScore(score);
            document.setDocTitle(title);
            document.setDocType(prefix);
            document.setUserId(user.getUserAccount());
            documentServlet.upload(document);
            Date date = new Date();
            //使用UUID+后缀名保存文件名，防止中文乱码问题
            String uuid = UUID.randomUUID() + "";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = simpleDateFormat.format(date);
            System.out.println(dateStr+"dateStr");
            //"/upload/"最后的的斜杠会被tomcat取消掉，需要把/放在projectPath
            String savePath = request.getSession().getServletContext().getRealPath("/upload");
            //要保存的问题件路径和名称  /upload/2020-09-09/uuid.jpg
            String projectPath = savePath + File.separator+ dateStr + File.separator + title + "." + prefix;

            System.out.println("projectPath==" + projectPath);
            File files = new File(projectPath);
            //打印查看上传路径
            if (!files.getParentFile().exists()) {//判断目录是否存在
                System.out.println("files11111=" + files.getPath());
                files.getParentFile().mkdirs();
            }
            file.transferTo(files); // 将接收的文件保存到指定文件中
            System.out.println(projectPath);
            LayuiData layuiData=new LayuiData();
            layuiData.setCode(0);
            layuiData.setMsg("上传成功");
            return layuiData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @RequestMapping(value = "/uploadDoc",produces = "text/plain;charset=utf-8")
    //下载文档
    @ResponseBody
    public Object uploadDoc(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String adminsb = request.getParameter("admin");
        Document admin = JSON.parseObject(adminsb, Document.class);
        System.out.println(admin.toString()+"----------------------"+adminsb);
//        Gson gson = new Gson();
//        DocTwo admin=gson.fromJson(adminsb,new TypeToken<DocTwo>(){}.getType());
        String msg = "";
        User users = (User) request.getSession().getAttribute("user");
        String accounts = users.getUserAccount();
        String password = users.getUserPassword();
        User user = loginServer.login(accounts,password);
        request.removeAttribute("user");
        request.getSession().setAttribute("user",user);
        Class classBean = documentServlet.classBean(user.getUserClass());
        //积分：一次下载基础积分100分*等级倍数
        double scoreBean = 100*Double.parseDouble(classBean.getClassDownload());
        if (Double.parseDouble(user.getUserScore())>=scoreBean){//----判断积分是否足够
            String account = user.getUserAccount();
//            documentServlet.deductScore(scoreBean+"",account);//----------扣除积分

            Load load = new Load();
            load.setLoadName(admin.getDocTitle());
            load.setLoadType(admin.getDocType());
            load.setUserId(user.getUserAccount());
            load.setLoadAdmin(admin.getUserId());
//            documentServlet.loadDoc(load);//-------下载到下载列表
            documentServlet.deductScoreAll(load,scoreBean+"",account);
            msg = "下载成功";
        }else{
            msg = "积分不足！";
        }
        System.out.println(JSON.toJSONString(msg)+"我是json------------------------");
//        return gson.toJson(msg);
        return JSON.toJSONString(msg);
    }

    /**
     * 文件下载
     */
    @RequestMapping("/downloads")
    @ResponseBody
    public Object downloads (HttpServletRequest request, HttpServletResponse response)  throws Exception{
        //.要下载的图片地址
        String path = request.getServletContext().getRealPath(  "/upload");
        String fileName = "基础语法.jpg" ;
        //1.设置response 駒应义
        response.reset(); //设置页面不缓存,清空buffer
        response.setCharacterEncoding("UTF-8"); //字符编码
        response.setContentType("multipart/form-data"); //- 二逃制传输数据
        //设置响应头
        response.setHeader("Content-Disposition","attachment;fileName="+ URLEncoder.encode(fileName, "UTF-8"));
        File file = new File(path, fileName);
        //2.读取文件--输入流
        InputStream input=new FileInputStream(file);
        //3.写出文件--输出流
        OutputStream out =response.getOutputStream();
        byte[] buff =new byte[1024];
        int index=0;
        //4、执行写出操作
        while((index= input.read(buff))!= -1){
            out.write(buff,  0, index);
            out.flush();
        }
        out.close();
        input.close();
        return null;
    }


    @RequestMapping(value = "/findMyLoad",produces = "text/plain;charset=utf-8")
    //获取我的下载列表
    @ResponseBody
    public Object findMyLoad(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
        User user = (User) request.getSession().getAttribute("user");
        hashMap.put("page",(page-1)*limit);
        hashMap.put("limit",10);
        hashMap.put("userAccount",user.getUserAccount());
        List<Load> adminList = documentServlet.MyLoadList(hashMap);
        int num = documentServlet.DoMyLoad(hashMap);
        LayuiData layuiData = new LayuiData();
        layuiData.setMsg("");
        layuiData.setCode(0);
        layuiData.setCount(num);
        layuiData.setData(adminList);
        return JSON.toJSONString(layuiData);
    }







}
