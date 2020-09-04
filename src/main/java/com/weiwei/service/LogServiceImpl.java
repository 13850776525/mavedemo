package com.weiwei.service;

import com.weiwei.bean.Log;
import com.weiwei.bean.User;
import com.weiwei.mapper.DocumentMapper;
import com.weiwei.mapper.LogMapper;
import com.weiwei.mapper.UserMapper;
import com.weiwei.service.LogService;
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
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;
    @com.weiwei.aoplog.Log(operationType = "",operationName = "")
    //增加日志
    @Override
    public boolean addLog(Log log) {
        boolean flag = false;
        flag = logMapper.addLog(log);
        return flag;
    }

    @Override
    public List<Log> LogList(HashMap hashMap) {
        List<Log> admin = null;
        admin = logMapper.LogList(hashMap);
        return admin;
    }

    @Override
    public int LogCount(HashMap hashMap) {
        int num = 0;
        num = logMapper.LogCount(hashMap);
        return num;
    }


}
