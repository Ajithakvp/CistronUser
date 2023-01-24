package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class SparesConsumedRecordModel {

    // "partNo": "OITTWXXX",
    //                    "partName": "1/2\" TEFLON TAPE",
    //                    "price": "0",
    //                    "qty": "7",
    //                    "dt": "2023-01-23


    @SerializedName("partNo")
    private String partNo;

    @SerializedName("partName")
    private String partName;

    @SerializedName("price")
    private String price;

    @SerializedName("qty")
    private String qty;

    @SerializedName("dt")
    private String dt;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }
}
