package com.weiwei.bean;

public class Dtype {

    public Dtype() {
    }

    public Dtype(String dtypeId, String dtypeName, String dtypeScore, String dtypeState) {
        this.dtypeId = dtypeId;
        this.dtypeName = dtypeName;
        this.dtypeScore = dtypeScore;
        this.dtypeState = dtypeState;
    }

    /**
     * 文档类型表
     */


    private String dtypeId;
    private String dtypeName;
    private String dtypeScore;
    private String dtypeState;



    public String getDtypeId() {
        return dtypeId;
    }

    public void setDtypeId(String dtypeId) {
        this.dtypeId = dtypeId;
    }

    public String getDtypeName() {
        return dtypeName;
    }

    public void setDtypeName(String dtypeName) {
        this.dtypeName = dtypeName;
    }

    public String getDtypeScore() {
        return dtypeScore;
    }

    public void setDtypeScore(String dtypeScore) {
        this.dtypeScore = dtypeScore;
    }

    public String getDtypeState() {
        return dtypeState;
    }

    public void setDtypeState(String dtypeState) {
        this.dtypeState = dtypeState;
    }





}
