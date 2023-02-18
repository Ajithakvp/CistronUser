package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class SpareInwardCoModel {

    // "reqNo": "2020060813E24002",
    //            "reqDt": "2020-06-08",
    //            "purpose": "Stock",
    //            "allocatedDt": "2020-06-08",
    //            "opt": "1",
    //            "reqid": "23",
    //            "reqBy": "e240",
    //            "user": "admin"

    @SerializedName("reqNo")
    private String reqNo;

    @SerializedName("reqDt")
    private String reqDt;

    @SerializedName("purpose")
    private String purpose;

    @SerializedName("allocatedDt")
    private String allocatedDt;

    @SerializedName("opt")
    private String opt;

    @SerializedName("reqid")
    private String reqid;

    @SerializedName("reqBy")
    private String reqBy;

    @SerializedName("user")
    private String user;

    public String getReqNo() {
        return reqNo;
    }

    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }

    public String getReqDt() {
        return reqDt;
    }

    public void setReqDt(String reqDt) {
        this.reqDt = reqDt;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getAllocatedDt() {
        return allocatedDt;
    }

    public void setAllocatedDt(String allocatedDt) {
        this.allocatedDt = allocatedDt;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public String getReqid() {
        return reqid;
    }

    public void setReqid(String reqid) {
        this.reqid = reqid;
    }

    public String getReqBy() {
        return reqBy;
    }

    public void setReqBy(String reqBy) {
        this.reqBy = reqBy;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
