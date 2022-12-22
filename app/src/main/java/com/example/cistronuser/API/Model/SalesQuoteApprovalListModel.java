package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class SalesQuoteApprovalListModel {


//      "oa_id": "58",
//              "hospital": "Test Hospital pvm",
//              "district": "Perambalur-TN",
//              "product": "(DST05) B-Sterile : Automatic Steam Sterilizer - 16x24 Double Door",
//              "user": "E367 : Velmurugan P"

    @SerializedName("oa_id")
    private String oa_id;

    @SerializedName("hospital")
    private String hospital;

    @SerializedName("district")
    private String district;

    @SerializedName("product")
    private String product;

    @SerializedName("user")
    private String user;

    @SerializedName("quotePdf")
    private String quotePdf;

    public String getOa_id() {
        return oa_id;
    }

    public void setOa_id(String oa_id) {
        this.oa_id = oa_id;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getQuotePdf() {
        return quotePdf;
    }

    public void setQuotePdf(String quotePdf) {
        this.quotePdf = quotePdf;
    }
}
