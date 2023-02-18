package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class ReturnReqPendingReportCoModel {

    //  "retReqNo": "2022012707E34609",
    //      "retReqDt": "2022-01-05",
    //      "opt": "2",
    //      "retReqId": "12",
    //      "reqBy": "e346",
    //      "user": "admin"

    @SerializedName("retReqNo")
    private String retReqNo;

    @SerializedName("retReqDt")
    private String retReqDt;

    @SerializedName("opt")
    private String opt;

    @SerializedName("retReqId")
    private String retReqId;

    @SerializedName("reqBy")
    private String reqBy;

    @SerializedName("user")
    private String user;

    public String getRetReqId() {
        return retReqId;
    }

    public void setRetReqId(String retReqId) {
        this.retReqId = retReqId;
    }

    public String getRetReqNo() {
        return retReqNo;
    }

    public void setRetReqNo(String retReqNo) {
        this.retReqNo = retReqNo;
    }

    public String getRetReqDt() {
        return retReqDt;
    }

    public void setRetReqDt(String retReqDt) {
        this.retReqDt = retReqDt;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
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
