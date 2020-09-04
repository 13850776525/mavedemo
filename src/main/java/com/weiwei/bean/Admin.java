package com.weiwei.bean;

public class Admin {
    public Admin() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", account='" + account + '\'' +
                ", roleid=" + roleid +
                '}';
    }

    public Admin(String password) {
        this.password = password;
    }

    /**
     * 管理员
     */

    private String name;
    private String password;
    private String account;
    private String roleid;

//    public void init(){
//        this.name="帅哥";
//    }



}
