package com.weiwei.service.Impl;

import com.weiwei.aoplog.Log;
import com.weiwei.bean.Admin;
import com.weiwei.bean.Score;
import com.weiwei.bean.User;
import com.weiwei.bean.rootle;
import com.weiwei.mapper.AdminMapper;
import com.weiwei.mapper.UserMapper;
import com.weiwei.service.loginServer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class loginServerlmpl implements loginServer {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;

    @Log(operationType = "登陆",operationName = "管理员")
    @Override
    public User login(String account, String password) {
        User admin = null;
        User user = new User();
        user.setUserAccount(account);
        user.setUserPassword(password);
        admin = userMapper.login(user);
        System.out.println("[提示]这是查找的用户："+admin.toString());
        return admin;
    }

    @Log(operationType = "注册",operationName = "管理员")
    //注册方法
    @Override
    public boolean RegUser(User user) {
        boolean flag = false;
        flag = userMapper.RegUser(user);
        //同时给积分表添加注册积分
        Score score = new Score();
        score.setScoScore("200");
        score.setScoType("注册");
        score.setScoUser(user.getUserAccount());
        userMapper.ScoreAdd(score);
        return flag;
    }

    @Log(operationType = "用户查询",operationName = "管理员")
    //用户查询方法
    @Override
    public List<User> UserList(HashMap hashMap) {
        List<User> admin = null;
        Integer page = (Integer) hashMap.get("page");
        Integer limit = (Integer) hashMap.get("limit");
        String userName = (String) hashMap.get("userName");
        String userAccount = (String) hashMap.get("userAccount");
        String userClass = (String) hashMap.get("userClass");
        String startTime = (String) hashMap.get("stopTime");
        String stopTime = (String) hashMap.get("stopTime");
        admin = userMapper.UserList(page,limit,userName,userAccount,startTime,stopTime,userClass);
        return admin;
    }

    @Log(operationType = "用户总条数",operationName = "管理员")
    //用户总条数
    @Override
    public int UserCount(HashMap hashMap) {
        int num = 0;
        String userName = (String) hashMap.get("userName");
        String userAccount = (String) hashMap.get("userAccount");
        String userClass = (String) hashMap.get("userClass");
        String startTime = (String) hashMap.get("stopTime");
        String stopTime = (String) hashMap.get("stopTime");
        num = userMapper.UserCount(userName,userAccount,startTime,stopTime,userClass);
        return num;
    }

    @Log(operationType = "用户更改状态",operationName = "管理员")
    //用户更改状态方法
    @Override
    public boolean updateUserState(User user) {
        boolean flag = false;
        flag = userMapper.updateUserState(user);
        return flag;
    }

    @Log(operationType = "修改用户",operationName = "管理员")
    @Override
    public boolean updateUser(User user) {
        boolean flag = false;
        flag = userMapper.updateUser(user);//调用接口中的方法，方法名对应映射文件中的方法id
        return flag;
    }


    //*****************************************管理员*****************************************//
    //管理员登陆方法
    @Log(operationType = "管理员登陆",operationName = "管理员")
    @Override
    public Admin loginManger(String account, String password) {
        Admin admin = null;
        Admin admins = new Admin();
        admins.setAccount(account);
        admins.setPassword(password);
        admin = adminMapper.loginManger(admins);
        return admin;
    }

    //管理员查询方法
    @Log(operationType = "管理员查询",operationName = "管理员")
    @Override
    public List<Admin> AdminList(HashMap hashMap) {
        List<Admin> admin = null;
        Integer page = (Integer) hashMap.get("page");
        Integer limit = (Integer) hashMap.get("limit");
        String name = (String) hashMap.get("name");
        String account = (String) hashMap.get("account");
        String role = (String) hashMap.get("roleid");
        admin = adminMapper.AdminList(page,limit,account,name,role);
        return admin;
    }

    //管理员总条数
    @Log(operationType = "管理员总条数",operationName = "管理员")
    @Override
    public int AdminCount(HashMap hashMap) {
        int num = 0;
        String name = (String) hashMap.get("name");
        String account = (String) hashMap.get("account");
        String role = (String) hashMap.get("roleid");
        num = adminMapper.AdminCount(account,name,role);
        return num;
    }


    //添加管理员
    @Log(operationType = "添加管理员",operationName = "管理员")
    @Override
    public boolean addManger(HashMap hashMap) {
        boolean flag = false;
        String name = (String) hashMap.get("name");
        String password = (String) hashMap.get("password");
        String roleid = (String) hashMap.get("roleid");
        flag = adminMapper.addManger(name,password,roleid);
        return flag;
    }

    //删除管理员
    @Log(operationType = "删除管理员",operationName = "管理员")
    @Override
    public boolean deleteManger(String[] account) {
        boolean flag = false;
        flag = adminMapper.deleteManger(account);
        return flag;
    }
    //编辑管理员
    @Log(operationType = "编辑管理员",operationName = "管理员")
    @Override
    public boolean updateManger(Admin admin) {
        boolean flag = false;
        flag = adminMapper.updateManger(admin);
        return flag;
    }

    //菜单查询方法
    @Log(operationType = "查询菜单列表",operationName = "管理员")
    @Override
    public Map<rootle,List<rootle>> rootleList(HashMap hashMap) {
        Map<rootle,List<rootle>> admin = new HashMap<rootle,List<rootle>>();
        List<rootle> pidList = null;
        List<rootle> uidList = null;
        String role_id = String.valueOf(hashMap.get("role_id"));
        String key = String.valueOf(hashMap.get("key"));
        pidList = adminMapper.rootleList(role_id,key);
        for (int i = 0;i<pidList.size();i++){
            uidList = adminMapper.rootleList(role_id, pidList.get(i).getRootPid());
            admin.put(pidList.get(i),uidList);
        }
        return admin;
    }


}
