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

    public static OrgBuilder create(){
        return new OrgBuilder();
    }

    public static final class OrgBuilder {
        private String orgNo;
        private String orgName;
        private String orgChief;

        private OrgBuilder() {
        }

        public static OrgBuilder anOrg() {
            return new OrgBuilder();
        }

        public OrgBuilder orgNo(String orgNo) {
            this.orgNo = orgNo;
            return this;
        }

        public OrgBuilder orgName(String orgName) {
            this.orgName = orgName;
            return this;
        }

        public OrgBuilder orgChief(String orgChief) {
            this.orgChief = orgChief;
            return this;
        }

        public Org build() {
            Org org = new Org();
            org.setOrgNo(orgNo);
            org.setOrgName(orgName);
            org.setOrgChief(orgChief);
            return org;
        }

    }

    public static void main(String[] args) {
//        Org org = Org.create().orgNo("1").orgChief("2").build();
        Org org = new OrgBuilder().orgNo("1").orgChief("2").build();
        System.out.println(org);

    }
}
