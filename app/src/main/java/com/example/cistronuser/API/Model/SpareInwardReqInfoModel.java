package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class SpareInwardReqInfoModel {

    // "partNo": "MXXSXX70",
    //              "partName": "CHAMPION SHEET MULTIPORT BIG WASHER ",
    //              "reqQty": "1",
    //              "allocatedQty": "0"

    @SerializedName("partNo")
    private String partNo;

    @SerializedName("partName")
    private String partName;

    @SerializedName("reqQty")
    private String reqQty;

    @SerializedName("allocatedQty")
    private String allocatedQty;

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

    public String getAllocatedQty() {
        return allocatedQty;
    }

    public void setAllocatedQty(String allocatedQty) {
        this.allocatedQty = allocatedQty;
    }
}
