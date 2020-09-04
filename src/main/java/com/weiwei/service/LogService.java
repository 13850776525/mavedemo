package com.weiwei.service;

import com.weiwei.bean.Log;

import java.util.HashMap;
import java.util.List;

public interface LogService {

    /**
     * 日志操作
     */
    //增加日志
    public boolean addLog(Log log);

    public List<Log> LogList(HashMap hashMap);

    public int LogCount(HashMap hashMap);


}
