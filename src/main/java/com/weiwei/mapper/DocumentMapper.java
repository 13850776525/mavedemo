package com.weiwei.mapper;

import com.weiwei.bean.*;
import com.weiwei.bean.Class;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface DocumentMapper {

    //文档查询方法
    public List<Document> DocList(@Param("page")Integer page,
                                  @Param("limit")Integer limit,
                                  @Param("docTitle")String docTitle,
                                  @Param("userId")String userId,
                                  @Param("startTime")String startTime,
                                  @Param("stopTime")String stopTime,
                                  @Param("docType")String docType,
                                  @Param("userAccount")String userAccount);


    //文档添加方法
    public boolean add(@Param("name")String name,
                             @Param("password")String password,
                             @Param("roleid")String roleid);

    //管理员总条数
    public int DocCount(@Param("docTitle")String docTitle,
                        @Param("userId")String userId,
                        @Param("startTime")String startTime,
                        @Param("stopTime")String stopTime,
                        @Param("docType")String docType,
                        @Param("userAccount")String userAccount);

    //搜索文档查询方法
    public List<Document> DocListAll(@Param("page")Integer page,
                                  @Param("limit")Integer limit,
                                  @Param("docTitle")String docTitle);


    //搜索文档总条数
    public int DocCountAll(@Param("docTitle")String docTitle);

    //上传文档
    public boolean upload(Document document);


    //删除管理员
    public boolean deleteDtype(@Param("dtypeId")String dtypeId);

    //编辑管理员
    public boolean updateState(Document document);

    //编辑管理员
    public boolean updateDtypeState(Dtype dtype);

    //菜单查询方法
    public List<rootle> rootleList(HashMap hashMap);

    //文档添加方法
    public boolean addDtype(HashMap hashMap);

    //文档类型查询
    public List<Dtype> findDtype();

    //查询等级对象
    public Class classBean(@Param("userClass")String userClass);

    //下载文档到下载文档
    public boolean loadDoc(Load load);

    //下载文档扣除积分
    public boolean deductScore(@Param("score")String score,
                               @Param("account")String account);

    //我的下载文档列表
    public List<Load> MyLoadList(@Param("page")Integer page,
                                 @Param("limit")Integer limit,
                                 @Param("userAccount")String userAccount);

    //搜索文档查询方法
    public int DoMyLoad(@Param("page")Integer page,
                               @Param("limit")Integer limit,
                               @Param("userAccount")String userAccount);

}
