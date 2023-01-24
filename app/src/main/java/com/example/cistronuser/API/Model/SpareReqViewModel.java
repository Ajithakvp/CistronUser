package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class SpareReqViewModel {

    //"crNo": "CR#23010502",
    //            "partNo": "MSCSOBNE",
    //            "partName": "VHP OTHER MACHINE GASKET ",
    //            "reqQty": "0"

    @SerializedName("crNo")
    private String crNo;

    @SerializedName("partNo")
    private String partNo;

    @SerializedName("partName")
    private String partName;

    @SerializedName("reqQty")
    private String reqQty;

    public String getCrNo() {
        return crNo;
    }

    public void setCrNo(String crNo) {
        this.crNo = crNo;
    }

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
