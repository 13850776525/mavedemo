package com.weiwei.bean;

public class rootle {

    public rootle() {
    }

    public String getRootId() {
        return rootId;
    }

    public void setRootId(String rootId) {
        this.rootId = rootId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRootName() {
        return rootName;
    }

    public void setRootName(String rootName) {
        this.rootName = rootName;
    }

    @Override
    public String toString() {
        return "rootle{" +
                "rootId='" + rootId + '\'' +
                ", roleId='" + roleId + '\'' +
                ", rootName='" + rootName + '\'' +
                ", rootUrl='" + rootUrl + '\'' +
                ", rootPid='" + rootPid + '\'' +
                ", rootUid='" + rootUid + '\'' +
                '}';
    }

    /**
     * 权限管理菜单
     */
    private String rootId;
    private String roleId;
    private String rootName;
    private String rootUrl;
    private String rootPid;
    private String rootUid;

    public String getRootPid() {
        return rootPid;
    }

    public void setRootPid(String rootPid) {
        this.rootPid = rootPid;
    }

    public String getRootUid() {
        return rootUid;
    }

    public void setRootUid(String rootUid) {
        this.rootUid = rootUid;
    }





    public String getRootUrl() {
        return rootUrl;
    }

    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }




}
