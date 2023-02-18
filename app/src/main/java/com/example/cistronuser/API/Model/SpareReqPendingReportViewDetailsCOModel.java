package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class SpareReqPendingReportViewDetailsCOModel {

    //  "partNo": "MMVSDXX",
    //      "partName": "SOLENOID SEAL KIT ",
    //      "reqQty": "1"

    @SerializedName("partNo")
    private String partNo;

    @SerializedName("partName")
    private String partName;

    @SerializedName("reqQty")
    private String reqQty;

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getReqQty() {
        return reqQty;
    }

    public void setReqQty(String reqQty) {
        this.reqQty = reqQty;
    }
}
