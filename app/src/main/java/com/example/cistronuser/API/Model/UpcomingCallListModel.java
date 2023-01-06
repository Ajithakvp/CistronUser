package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class UpcomingCallListModel {



//    "crId": "17289",
//            "crNo": "22060123",
//            "date": "11-01-2023 11:00:00",
//            "hospital": "Pranavi Children and Eye Hospital\\n21AP255229",
//            "address": "Kandukur South - Prakasam\\nAP",
//            "issue": "Preventive Maintenance",
//            "contact": "Dr.  Prasanthi\\n8985816515",
//            "createdBy": "System Generated (e000)",
//            "button": "13856~17289~24357~dashboard~105~up"

    @SerializedName("crId")
    private String crId;

    @SerializedName("crNo")
    private String crNo;

    @SerializedName("date")
    private String date;

    @SerializedName("hospital")
    private String hospital;

    @SerializedName("address")
    private String address;

    @SerializedName("issue")
    private String issue;

    @SerializedName("contact")
    private String contact;

    @SerializedName("createdBy")
    private String createdBy;

    @SerializedName("button")
    private String button;

    @SerializedName("product")
    private String product;

    @SerializedName("mobile")
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCrId() {
        return crId;
    }

    public void setCrId(String crId) {
        this.crId = crId;
    }

    public String getCrNo() {
        return crNo;
    }

    public void setCrNo(String crNo) {
        this.crNo = crNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
