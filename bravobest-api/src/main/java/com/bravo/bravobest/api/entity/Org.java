package com.bravo.bravobest.api.entity;

import java.io.Serializable;

public class Org implements Serializable {

    private static final long serialVersionUID = -8708525144902181786L;


    private String orgNo;

    private String orgName;

    private String orgChief;


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

    public String getOrgChief() {
        return orgChief;
    }

    public void setOrgChief(String orgChief) {
        this.orgChief = orgChief;
    }

    @Override
    public String toString() {
        return "Org{" +
                "orgNo='" + orgNo + '\'' +
                ", orgName='" + orgName + '\'' +
                ", orgChief='" + orgChief + '\'' +
                '}';
    }
}
