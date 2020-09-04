package com.weiwei.service;

import com.weiwei.bean.*;
import com.weiwei.bean.Class;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface DocumentServer {


    //管理员查询方法
    public List<Document> DocumentList(HashMap hashMap);

    //管理员总条数
    public int DocCount(HashMap hashMap);

    //文档配置管理员
    public boolean deleteDtype(String dtypeId);

    //上传文档
    public boolean upload(Document document);

//    //编辑管理员
//    public boolean updateManger(Admin admin);
//
    //修改文档状态
    public boolean updateState(Document document);


    //修改文档配置状态
    public boolean updateDtypeState(Dtype dtype);


    //文档类型查询
    public List<Dtype> findDtype();

    //添加文档配置
    public boolean addDtype(HashMap hashMap);


    //文档搜索查询方法
    public List<Document> DocumentListAll(HashMap hashMap);

    //文档搜索总条数
    public int DocCountAll(HashMap hashMap);


    //查询等级对象
    public Class classBean(@Param("userClass")String userClass);

    //积分上传添加
    public boolean ScoreAdd(Score score);

    //下载文档到下载文档
    public boolean loadDoc(Load load);

    //下载文档扣除积分
    public boolean deductScore(String score,String account);

    //总方法
    public boolean deductScoreAll(Load load,String score,String account);


    //我的下载文档列表
    public List<Load> MyLoadList(HashMap hashMap);

    //搜索下载文档数量
    public int DoMyLoad(HashMap hashMap);

}
