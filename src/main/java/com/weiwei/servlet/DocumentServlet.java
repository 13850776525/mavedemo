package com.weiwei.servlet;

import com.alibaba.fastjson.JSON;
import com.weiwei.bean.Document;
import com.weiwei.bean.Dtype;
import com.weiwei.bean.User;
import com.weiwei.service.DocumentServer;
import com.weiwei.utils.LayuiData;
import com.weiwei.utils.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/documentServlet")
public class DocumentServlet extends BaseServlet {

//    DocumentServer documentServlet = (DocumentServer) ObjectFactory.getObject("com.service.Impl.DocumentServerlmpl");
    @Autowired
    private DocumentServer documentServlet;


    //获取我的文档表
    public void findDocAll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
        System.out.println(JSON.toJSONString(layuiData));
        response.getWriter().write(JSON.toJSONString(layuiData));
    }


    //获取我的文档表
    public void findDocMy(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
        System.out.println(JSON.toJSONString(layuiData));
        response.getWriter().write(JSON.toJSONString(layuiData));
    }


    //获取文档表
    public void findDoc(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
        System.out.println(JSON.toJSONString(layuiData));
        response.getWriter().write(JSON.toJSONString(layuiData));
    }


    //获取文档类型表
    public void findDtype(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("我在这11111");
        List<Dtype> adminList = documentServlet.findDtype();
        LayuiData layuiData = new LayuiData();
        layuiData.setMsg("");
        layuiData.setCode(0);
        layuiData.setCount(100);
        layuiData.setData(adminList);
        System.out.println(JSON.toJSONString(layuiData));
        response.getWriter().write(JSON.toJSONString(layuiData));
    }

    //文档配置删除方法
    public void deleteDtype(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String dtypeId = request.getParameter("dtypeId");
        boolean flag = documentServlet.deleteDtype(dtypeId);
        if (flag){
            response.getWriter().print("删除成功");
        }else{
            response.getWriter().print("删除失败");
        }
    }

    //更改状态方法
    public void updateState(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        boolean flag = documentServlet.updateState(document);
        if (flag){
            response.getWriter().print("成功");
        }else{
            response.getWriter().print("审核失败");
        }
    }

    //更改文档配置状态方法
    public void updateDtypeState(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String state = request.getParameter("state");
        String id = request.getParameter("id");
        if (state.equals("开启")){
            state="禁用";
        }else{
            state="开启";
        }
        Dtype user = new Dtype();
        user.setDtypeId(id);
        user.setDtypeState(state);
        boolean flag = documentServlet.updateDtypeState(user);
        if (flag){
            response.getWriter().print("成功");
        }else{
            response.getWriter().print("更改失败");
        }
    }

    //文档配置添加方法
    public void addDtype(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String dtypeName = request.getParameter("dtypeName");
        String dtypeScore = request.getParameter("dtypeScore");
        String dtypeState = request.getParameter("dtypeState");
        System.out.println(dtypeState+dtypeScore+dtypeName);
        HashMap hashMap = new HashMap();
        hashMap.put("dtypeName",dtypeName);
        hashMap.put("dtypeScore",dtypeScore);
        hashMap.put("dtypeState",dtypeState);
        boolean flag = documentServlet.addDtype(hashMap);
        if (flag){
            response.getWriter().print("成功");
        }else{
            response.getWriter().print("添加失败");
        }
    }
}
