package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class ConsumeSpareRecordModel {

    //"sparePartId": "859",
    //        "opt": "1",
    //        "partNo": "MSOIXBXB",
    //        "partName": "B- STERILE  SILICON GASKET FOR DOOR BELLOW RING",
    //        "myQty": "5",
    //        "unitPrice": "942",
    //        "QtyToConsume": "input"

    @SerializedName("sparePartId")
    private String sparePartId;

    @SerializedName("opt")
    private String opt;

    @SerializedName("partNo")
    private String partNo;

    @SerializedName("partName")
    private String partName;

    @SerializedName("myQty")
    private String myQty;

    @SerializedName("unitPrice")
    private String unitPrice;

    @SerializedName("QtyToConsume")
    private String QtyToConsume;

    public String getSparePartId() {
        return sparePartId;
    }

    public void setSparePartId(String sparePartId) {
        this.sparePartId = sparePartId;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
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

    public String getMyQty() {
        return myQty;
    }

    public void setMyQty(String myQty) {
        this.myQty = myQty;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getQtyToConsume() {
        return QtyToConsume;
    }

    public void setQtyToConsume(String qtyToConsume) {
        QtyToConsume = qtyToConsume;
    }
}
