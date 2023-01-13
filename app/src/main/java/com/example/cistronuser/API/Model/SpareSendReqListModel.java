package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class SpareSendReqListModel {

    // "partName": "B.S MANUAL  ETO BOARD ",
    //            "id": "4",
    //            "partNo": "MSSSMEBV",
    //            "unitPrice": "0",
    //            "coordQty": 0,
    //            "storeQty": "1.00",
    //            "engQty": " ",
    //            "spareId": "4"

    @SerializedName("partName")
    private String partName;

    @SerializedName("id")
    private String id;

    @SerializedName("partNo")
    private String partNo;

    @SerializedName("unitPrice")
    private String unitPrice;

    @SerializedName("coordQty")
    private String coordQty;

    @SerializedName("storeQty")
    private String storeQty;


    @SerializedName("engQty")
    private String engQty;

    @SerializedName("spareId")
    private String spareId;

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCoordQty() {
        return coordQty;
    }

    public void setCoordQty(String coordQty) {
        this.coordQty = coordQty;
    }

    public String getStoreQty() {
        return storeQty;
    }

    public void setStoreQty(String storeQty) {
        this.storeQty = storeQty;
    }

    public String getEngQty() {
        return engQty;
    }

    public void setEngQty(String engQty) {
        this.engQty = engQty;
    }

    public String getSpareId() {
        return spareId;
    }

    public void setSpareId(String spareId) {
        this.spareId = spareId;
    }
}
