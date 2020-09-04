package com.weiwei.bean;

import java.util.Date;

public class Log {
    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public String getLogUser() {
        return logUser;
    }

    public void setLogUser(String logUser) {
        this.logUser = logUser;
    }

    @Override
    public String toString() {
        return "Log{" +
                "logDate=" + logDate +
                ", logUser='" + logUser + '\'' +
                ", logId=" + logId +
                ", logThing='" + logThing + '\'' +
                '}';
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getLogThing() {
        return logThing;
    }

    public void setLogThing(String logThing) {
        this.logThing = logThing;
    }

    public Log() {
    }

    private Date logDate;
    private String logUser;
    private int logId;
    private String logThing;

}
