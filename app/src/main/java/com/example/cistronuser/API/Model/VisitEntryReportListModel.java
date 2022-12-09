package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class VisitEntryReportListModel {



//     "id": "53006",
//             "entry": "2022-12-07",
//             "hospital": "Lakshmi Clinic And Vedio Endoscopic Centre / Madurai\nDr.  Periasamy T\nMs Mch Gastero",
//             "doctor": "0",
//             "product": "B-Sterile : Automatic Steam Sterilizer - 20x48 Single Door / BST01",
//             "comment": "AMC renewal cheque received and deposited. AMC renewal agreement submitted ",
//             "empid": "",
//             "name": "",
//             "hospitalId": "10585",
//             "quote": "",
//             "quotePdf": "",
//             "oaNo": "",
//             "oaPdf": ""





    @SerializedName("id")
    private String id;

    @SerializedName("entry")
    private String entry;

    @SerializedName("hospital")
    private String hospital;


    @SerializedName("doctor")
    private String doctor;

    @SerializedName("product")
    private String product;

    @SerializedName("comment")
    private String comment;

    @SerializedName("empid")
    private String empid;


    @SerializedName("name")
    private String name;


    @SerializedName("hospitalId")
    private String hospitalId;


    @SerializedName("quote")
    private String quote;


    @SerializedName("quotePdf")
    private String quotePdf;


    @SerializedName("oaNo")
    private String oaNo;


    @SerializedName("oaPdf")
    private String oaPdf;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getQuotePdf() {
        return quotePdf;
    }

    public void setQuotePdf(String quotePdf) {
        this.quotePdf = quotePdf;
    }

    public String getOaNo() {
        return oaNo;
    }

    public void setOaNo(String oaNo) {
        this.oaNo = oaNo;
    }

    public String getOaPdf() {
        return oaPdf;
    }

    public void setOaPdf(String oaPdf) {
        this.oaPdf = oaPdf;
    }
}
