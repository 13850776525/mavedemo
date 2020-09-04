package com.weiwei.bean;

import java.util.Date;

public class Score {

    /**
     * 积分表
     */
    private String scoUser;
    private String scoType;
    private Date scoTime;
    private String scoScore;
    private int scoId;



    public String getScoUser() {
        return scoUser;
    }

    public void setScoUser(String scoUser) {
        this.scoUser = scoUser;
    }

    public String getScoType() {
        return scoType;
    }

    public void setScoType(String scoType) {
        this.scoType = scoType;
    }

    public Date getScoTime() {
        return scoTime;
    }

    public void setScoTime(Date scoTime) {
        this.scoTime = scoTime;
    }

    public String getScoScore() {
        return scoScore;
    }

    public void setScoScore(String scoScore) {
        this.scoScore = scoScore;
    }

    public int getScoId() {
        return scoId;
    }

    public void setScoId(int scoId) {
        this.scoId = scoId;
    }



}
