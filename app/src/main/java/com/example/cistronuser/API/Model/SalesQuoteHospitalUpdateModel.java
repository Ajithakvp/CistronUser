package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class SalesQuoteHospitalUpdateModel {


//            "quoteId": "1",
//                    "visitId": "2",
//                    "productId": "457",
//                    "hospitalId": "15165",
//                    "refNo": "CIS/TN/22L/0001",
//                    "quote": "http://192.168.29.173/coms/merge_quote/19211_e367_BST05.pdf",
//                    "product": "B-Sterile : Automatic Steam Sterilizer - 16x24 Single Door | BST05",
//                    "date": "15-Dec-2022",
//                    "user": "Velmurugan P(E367)",
//                    "status": "Submitted for OA Approval"


    @SerializedName("quoteId")
    private String quoteId;

    @SerializedName("visitId")
    private String visitId;

    @SerializedName("productId")
    private String productId;

    @SerializedName("hospitalId")
    private String hospitalId;

    @SerializedName("refNo")
    private String refNo;

    @SerializedName("quote")
    private String quote;

    @SerializedName("product")
    private String product;

    @SerializedName("date")
    private String date;

    @SerializedName("user")
    private String user;

    @SerializedName("status")
    private String status;

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
