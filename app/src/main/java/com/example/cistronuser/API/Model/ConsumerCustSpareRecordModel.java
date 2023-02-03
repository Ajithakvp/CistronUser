package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class ConsumerCustSpareRecordModel {

    //"partNo": "MMVSXXX",
    //id
    //        "partName": "1/2\" GTD MODEL SPARE TEFLON DIAPHRAGM  ",
    //        "avlQty": "4",
    //customerStockId
    //opt
    //        "qtyToConsume": "input"

    @SerializedName("partNo")
    private String partNo;

    @SerializedName("partName")
    private String partName;

    @SerializedName("avlQty")
    private String avlQty;

    @SerializedName("qtyToConsume")
    private String qtyToConsume;

    @SerializedName("customerStockId")
    private String customerStockId;

    @SerializedName("id")
    private String id;

    @SerializedName("opt")
    private String opt;


    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public String getCustomerStockId() {
        return customerStockId;
    }

    public void setCustomerStockId(String customerStockId) {
        this.customerStockId = customerStockId;
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

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getAvlQty() {
        return avlQty;
    }

    public void setAvlQty(String avlQty) {
        this.avlQty = avlQty;
    }

    public String getQtyToConsume() {
        return qtyToConsume;
    }

    public void setQtyToConsume(String qtyToConsume) {
        this.qtyToConsume = qtyToConsume;
    }
}
