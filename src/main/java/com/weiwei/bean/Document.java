package com.weiwei.bean;


import java.util.Date;


public class Document {

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", docTitle='" + docTitle + '\'' +
                ", userId='" + userId + '\'' +
                ", docTime=" + docTime +
                ", docScore='" + docScore + '\'' +
                ", docNum=" + docNum +
                ", docState='" + docState + '\'' +
                ", docType='" + docType + '\'' +
                '}';
    }

    /**
     * 文档对象
     */

    private Integer id;
    private String docTitle;
    private String userId;
    private Date docTime;
    private String docScore;
    private Integer docNum;
    private String docState;

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    private String docType;


    public Document() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDocTime() {
        return docTime;
    }

    public void setDocTime(Date docTime) {
        this.docTime = docTime;
    }

    public String getDocScore() {
        return docScore;
    }

    public void setDocScore(String docScore) {
        this.docScore = docScore;
    }

    public Integer getDocNum() {
        return docNum;
    }

    public void setDocNum(Integer docNum) {
        this.docNum = docNum;
    }

    public String getDocState() {
        return docState;
    }

    public void setDocState(String docState) {
        this.docState = docState;
    }






}
