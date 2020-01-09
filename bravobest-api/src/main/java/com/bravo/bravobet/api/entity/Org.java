package com.bravo.bravobet.api.entity;

import java.io.Serializable;

public class Org implements Serializable {

    private String orgNo;

    private String orgName;

    private String orgManagerNo;


    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgManagerNo() {
        return orgManagerNo;
    }

    public void setOrgManagerNo(String orgManagerNo) {
        this.orgManagerNo = orgManagerNo;
    }

    @Override
    public String toString() {
        return "Org{" +
                "orgNo='" + orgNo + '\'' +
                ", orgName='" + orgName + '\'' +
                ", orgManagerNo='" + orgManagerNo + '\'' +
                '}';
    }
}
