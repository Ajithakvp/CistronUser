package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class CustomerPoSpareRecordModel {

    // "partNo": "MFDSMBCX",
    //        "partName": "3/8\" SS DUMMY PLUG (BSP)",
    //        "qtyToConsume": "1"

    @SerializedName("partNo")
    private String partNo;

    @SerializedName("partName")
    private String partName;

    @SerializedName("qtyToConsume")
    private String qtyToConsume;

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

    public String getQtyToConsume() {
        return qtyToConsume;
    }

    public void setQtyToConsume(String qtyToConsume) {
        this.qtyToConsume = qtyToConsume;
    }
}
