package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class ReturnRequestViewCoModel {

    // "partNo": "ESHPD21X",
    //      "partName": "SWITCH  OF ON/OFF/ON  230V AC ",
    //      "retQty": "1"

    @SerializedName("partNo")
    private String partNo;

    @SerializedName("partName")
    private String partName;

    @SerializedName("retQty")
    private String retQty;

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

    public String getRetQty() {
        return retQty;
    }

    public void setRetQty(String retQty) {
        this.retQty = retQty;
    }
}
