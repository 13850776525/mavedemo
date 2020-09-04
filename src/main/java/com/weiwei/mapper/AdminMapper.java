package com.weiwei.mapper;

import com.weiwei.bean.Admin;
import com.weiwei.bean.User;
import com.weiwei.bean.rootle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {

    //登陆
    public User login(User user);

    //注册方法
    public boolean RegUser(User user);

    //管理员登陆方法
    public Admin loginManger(Admin admin);

    //管理员查询方法
//    public List<Admin> AdminList(HashMap hashMap);

    //管理员查询方法
    public List<Admin> AdminList(@Param("page")Integer page,
                                 @Param("limit")Integer limit,
                                 @Param("account")String account,
                                 @Param("name")String name,
                                 @Param("roleid")String roleid);


    //管理员添加方法
    public boolean addManger(@Param("name")String name,
                             @Param("password")String password,
                             @Param("roleid")String roleid);

    //管理员总条数
    public int AdminCount(@Param("account")String account,
                          @Param("name")String name,
                          @Param("roleid")String roleid);

    //删除管理员
    public boolean deleteManger(String[] account);

    //编辑管理员
    public boolean updateManger(Admin admin);

    //菜单查询方法
    public List<rootle> rootleList(@Param("role_id")String role_id,
                                   @Param("key")String key);
}
