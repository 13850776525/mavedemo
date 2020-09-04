package com.weiwei.service;

import com.weiwei.bean.Admin;
import com.weiwei.bean.User;
import com.weiwei.bean.rootle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface loginServer {


    //******************用户***************************//

    //登陆
    public User login(String account, String password);

    //注册方法
    public boolean RegUser(User user);

    //用户列表查询
    public List<User> UserList(HashMap hashMap);

    //用户总条数
    public int UserCount(HashMap hashMap);

    //用户更改状态方法
    public boolean updateUserState(User user);

    //修改用户个人信息
    public boolean updateUser(User user);



    //******************管理员***************************//

    //管理员登陆方法
    public Admin loginManger(String account, String password);

    //管理员查询方法
    public List<Admin> AdminList(HashMap hashMap);

    //管理员总条数
    public int AdminCount(HashMap hashMap);

    //删除管理员
    public boolean deleteManger(String[] account);

    //编辑管理员
    public boolean updateManger(Admin admin);

    //菜单查询方法
    public Map<rootle,List<rootle>> rootleList(HashMap hashMap);

    //添加管理员
    public boolean addManger(HashMap hashMap);
}
