package com.weiwei.bean;

import java.util.Date;

public class Load {


    /**
     * 下载文档
     */
    private String loadName;
    private String loadType;
    private Date loadTime;
    private String loadAdmin;
    private String loadId;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Load() {
    }

    public String getLoadName() {
        return loadName;
    }

    public void setLoadName(String loadName) {
        this.loadName = loadName;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    public Date getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(Date loadTime) {
        this.loadTime = loadTime;
    }

    public String getLoadAdmin() {
        return loadAdmin;
    }

    public void setLoadAdmin(String loadAdmin) {
        this.loadAdmin = loadAdmin;
    }

    public String getLoadId() {
        return loadId;
    }

    public void setLoadId(String loadId) {
        this.loadId = loadId;
    }
}
