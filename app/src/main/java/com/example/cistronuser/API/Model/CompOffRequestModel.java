package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class CompOffRequestModel {

//      "compoffId": "622",
//              "empid": "e330",
//              "empname": "Rohith Babu P",
//              "appliedDt": "2022-11-06",
//              "appliedTime": "10:34:32 am",
//              "day": "Sunday",
//              "status": "Local"


    @SerializedName("compoffId")
    private String compoffId;

    @SerializedName("empid")
    private String empid;
    @SerializedName("empname")
    private String empname;
    @SerializedName("appliedDt")
    private String appliedDt;
    @SerializedName("appliedTime")
    private String appliedTime;
    @SerializedName("day")
    private String day;
    @SerializedName("status")
    private String status;

    public String getCompoffId() {
        return compoffId;
    }

    public void setCompoffId(String compoffId) {
        this.compoffId = compoffId;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getAppliedDt() {
        return appliedDt;
    }

    public void setAppliedDt(String appliedDt) {
        this.appliedDt = appliedDt;
    }

    public String getAppliedTime() {
        return appliedTime;
    }

    public void setAppliedTime(String appliedTime) {
        this.appliedTime = appliedTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
