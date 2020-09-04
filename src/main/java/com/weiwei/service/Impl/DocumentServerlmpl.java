package com.weiwei.service.Impl;

import com.weiwei.aoplog.Log;
import com.weiwei.bean.*;
import com.weiwei.bean.Class;
import com.weiwei.mapper.DocumentMapper;
import com.weiwei.mapper.LogMapper;
import com.weiwei.mapper.UserMapper;
import com.weiwei.service.DocumentServer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
@Service
public class DocumentServerlmpl implements DocumentServer {

    @Autowired
    private DocumentMapper documentMapper;

    @Autowired
    private UserMapper userMapper;


    @Log(operationType = "查询文档",operationName = "管理员")
    //管理员查询方法
    @Override
    public List<Document> DocumentList(HashMap hashMap) {
        List<Document> admin = null;
        Integer page = (Integer) hashMap.get("page");
        Integer limit = (Integer) hashMap.get("limit");
        String docTitle = (String) hashMap.get("docTitle");
        String userId = (String) hashMap.get("userId");
        String startTime = (String) hashMap.get("startTime");
        String stopTime = (String) hashMap.get("stopTime");
        String docType = (String) hashMap.get("docType");
        String userAccount = (String) hashMap.get("userAccount");
        admin = documentMapper.DocList(page,limit,docTitle,userId,startTime,stopTime,docType,userAccount);
        return admin;
    }

    @Log(operationType = "查询文档数",operationName = "管理员")
    //管理员总条数
    @Override
    public int DocCount(HashMap hashMap) {
        int num = 0;
        String docTitle = (String) hashMap.get("docTitle");
        String userId = (String) hashMap.get("userId");
        String startTime = (String) hashMap.get("startTime");
        String stopTime = (String) hashMap.get("stopTime");
        String docType = (String) hashMap.get("docType");
        String userAccount = (String) hashMap.get("userAccount");
        num = documentMapper.DocCount(docTitle,userId,startTime,stopTime,docType,userAccount);
        return num;
    }
    @Log(operationType = "查询文档列表",operationName = "管理员")
    @Override
    public List<Document> DocumentListAll(HashMap hashMap) {
        List<Document> admin = null;
        Integer page = (Integer) hashMap.get("page");
        Integer limit = (Integer) hashMap.get("limit");
        String docTitle = (String) hashMap.get("docTitle");
        admin = documentMapper.DocListAll(page,limit,docTitle);
        return admin;
    }

    @Log(operationType = "查询文档条数",operationName = "管理员")
    @Override
    public int DocCountAll(HashMap hashMap) {
        int num = 0;
        String docTitle = (String) hashMap.get("docTitle");
        num = documentMapper.DocCountAll(docTitle);
        return num;
    }

    @Log(operationType = "文件上传",operationName = "管理员")
    //积分上传添加
    @Override
    public boolean ScoreAdd(Score score) {
        boolean flag = false;
        userMapper.ScoreAdd(score);
        return flag;
    }



    @Log(operationType = "查询用户等级",operationName = "管理员")
    //查询等级对象
    @Override
    public Class classBean(String userClass) {
        Class classBean =  documentMapper.classBean(userClass);
        return classBean;
    }

    @Log(operationType = "编辑文档",operationName = "管理员")
    //编辑文档
    @Override
    public boolean updateState(Document document) {
        boolean flag = false;
        flag = documentMapper.updateState(document);
        return flag;
    }

    //上传文档
    @Log(operationType = "上传文档",operationName = "管理员")
    @Override
    public boolean upload(Document document) {
        boolean flag = false;
        flag = documentMapper.upload(document);
        return flag;
    }



    @Log(operationType = "更改文档配置状态",operationName = "管理员")
    //更改文档配置状态
    @Override
    public boolean updateDtypeState(Dtype dtype){
        boolean flag = false;
        flag = documentMapper.updateDtypeState(dtype);
        return flag;
    }

    @Log(operationType = "文档类型查询",operationName = "管理员")
    //文档类型查询
    @Override
    public List<Dtype> findDtype() {
        List<Dtype> dtypes = null;
        dtypes = documentMapper.findDtype();
        return dtypes;
    }


    //添加文档配置
    @Log(operationType = "添加文档配置",operationName = "管理员")
    @Override
    public boolean addDtype(HashMap hashMap) {
        boolean flag = false;
        flag = documentMapper.addDtype(hashMap);
        return flag;
    }



    //删除管理员
    @Log(operationType = "删除文档类型",operationName = "管理员")
    @Override
    public boolean deleteDtype(String dtypeId) {
        boolean flag = false;
        flag = documentMapper.deleteDtype(dtypeId);
        return flag;
    }

    //下载文档
    @Log(operationType = "下载文档",operationName = "管理员")
    @Override
    public boolean loadDoc(Load load) {
        boolean flag = false;
        flag = documentMapper.loadDoc(load);
        return flag;
    }

    //扣除积分
    @Log(operationType = "下载扣除积分",operationName = "管理员")
    @Override
    public boolean deductScore(String score, String account) {
        boolean flag = false;
        flag = documentMapper.deductScore(score,account);
        return flag;
    }


    //总方法
    @Log(operationType = "下载扣除积分并保存列表",operationName = "管理员")
    @Override
    public boolean deductScoreAll(Load load, String score, String account) {
        boolean flag = false;
//        try{
            boolean a1 = documentMapper.loadDoc(load);
            boolean a2 = documentMapper.deductScore(score,account);
//        }catch (Exception e){
//            e.getStackTrace();
//        }
        return flag;
    }

    @Log(operationType = "查询我的下载",operationName = "管理员")
    @Override
    public List<Load> MyLoadList(HashMap hashMap) {
        List<Load> admin = null;
        Integer page = (Integer) hashMap.get("page");
        Integer limit = (Integer) hashMap.get("limit");
        String userAccount = (String) hashMap.get("userAccount");
        admin = documentMapper.MyLoadList(page,limit,userAccount);
        return admin;
    }
    @Log(operationType = "查询我的下载条数",operationName = "管理员")
    @Override
    public int DoMyLoad(HashMap hashMap) {
        int num = 0;
        Integer page = (Integer) hashMap.get("page");
        Integer limit = (Integer) hashMap.get("limit");
        String userAccount = (String) hashMap.get("userAccount");
        num = documentMapper.DoMyLoad(page,limit,userAccount);
        return num;
    }




}
