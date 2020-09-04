package com.weiwei.mapper;

import com.weiwei.bean.Score;
import com.weiwei.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    //登陆
    public User login(User user);

    //注册方法
    public boolean RegUser(User user);

    //添加注册积分表
    public boolean ScoreAdd(Score score);

    public List<User> UserList(@Param("page")Integer page,
                               @Param("limit")Integer limit,
                               @Param("userName")String userName,
                               @Param("userAccount")String userAccount,
                               @Param("startTime")String startTime,
                               @Param("stopTime")String stopTime,
                               @Param("userClass")String userClass);

    public Integer UserCount(@Param("userName")String userName,
                             @Param("userAccount")String userAccount,
                             @Param("startTime")String startTime,
                             @Param("stopTime")String stopTime,
                             @Param("userClass")String userClass);

    //修改用户状态
    public boolean updateUserState(User user);

    //修改用户个人信息
    public boolean updateUser(User user);

}
