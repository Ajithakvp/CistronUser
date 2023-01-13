package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class ServiceSpareListModel {


//     "partName": "",
//             "partNo": "CANNBXXX",
//             "unitPrice": "825",
//             "coordQty": 0,
//             "storeQty": "48.00",
//             "engQty": 0,
//           p": "539",
//            "pcat": " ",
//            "s": "1",
    //suk_cis


    @SerializedName("partName")
    private String partName;

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

    @SerializedName("s")
    private String s;

    @SerializedName("p")
    private String p;

    @SerializedName("pcat")
    private String pcat;

    @SerializedName("suk_cis")
    private String suk_cis;

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
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

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getPcat() {
        return pcat;
    }

    public void setPcat(String pcat) {
        this.pcat = pcat;
    }

    public String getSuk_cis() {
        return suk_cis;
    }

    public void setSuk_cis(String suk_cis) {
        this.suk_cis = suk_cis;
    }
}