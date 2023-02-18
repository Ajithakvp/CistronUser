package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class SpareReqPendingReportCoModel {

    //  "serno": "2023013015E334146",
    //            "date": "2023-01-30",
    //            "purpose": "23012805",
    //            "opt": "1",
    //            "reqid": "2508"
    //"reqBy": "e0003"

    @SerializedName("serno")
    private String serno;

    @SerializedName("date")
    private String date;

    @SerializedName("purpose")
    private String purpose;

    @SerializedName("opt")
    private String opt;

    @SerializedName("reqid")
    private String reqid;

    @SerializedName("reqBy")
    private String reqBy;

    @SerializedName("user")
    private String user;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getReqBy() {
        return reqBy;
    }

    public void setReqBy(String reqBy) {
        this.reqBy = reqBy;
    }

    public String getSerno() {
        return serno;
    }

    public void setSerno(String serno) {
        this.serno = serno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
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
}
