package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.ReportExpensesViewModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReportExpensesViewResponses {


//     "active": "2",
//             "chk_hardcopy": "0",
//             "date_hardcopy": "0000-00-00",
//             "hardcopy_person": "",
//             "chk_pay": "0",
//             "date_pay": "0000-00-00",
//             "chk_paid": "0",
//             "date_paid": "0000-00-00",
//             "filename_r": "exp_report/1329414627~IMG20221116212421.jpg",
//             "active_upload_r": "1",
//             "adj_op": "0",
//             "adj_amt": "0",
//             "adj_reason": "",
//             "recCount": 5,
//             "attachBaseUrl": "http://192.168.29.173/"



    @SerializedName("active")
    private String active;

    @SerializedName("expenses")
    private ArrayList<ReportExpensesViewModel>reportExpensesViewModels;

    @SerializedName("chk_hardcopy")
    private String chk_hardcopy;

    @SerializedName("date_hardcopy")
    private String date_hardcopy;

    @SerializedName("hardcopy_person")
    private String hardcopy_person;

    @SerializedName("chk_pay")
    private String chk_pay;

    @SerializedName("date_pay")
    private String date_pay;

    @SerializedName("chk_paid")
    private String chk_paid;

    @SerializedName("date_paid")
    private String date_paid;


    @SerializedName("filename_r")
    private String filename_r;

    @SerializedName("adj_op")
    private String adj_op;

    @SerializedName("adj_amt")
    private String adj_amt;

    @SerializedName("adj_reason")
    private String adj_reason;

    @SerializedName("recCount")
    private String recCount;

    @SerializedName("grandSum")
    private String grandSum;


    @SerializedName("attachBaseUrl")
    private String attachBaseUrl;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getGrandSum() {
        return grandSum;
    }

    public void setGrandSum(String grandSum) {
        this.grandSum = grandSum;
    }

    public ArrayList<ReportExpensesViewModel> getReportExpensesViewModels() {
        return reportExpensesViewModels;
    }

    public void setReportExpensesViewModels(ArrayList<ReportExpensesViewModel> reportExpensesViewModels) {
        this.reportExpensesViewModels = reportExpensesViewModels;
    }

    public String getChk_hardcopy() {
        return chk_hardcopy;
    }

    public void setChk_hardcopy(String chk_hardcopy) {
        this.chk_hardcopy = chk_hardcopy;
    }

    public String getDate_hardcopy() {
        return date_hardcopy;
    }

    public void setDate_hardcopy(String date_hardcopy) {
        this.date_hardcopy = date_hardcopy;
    }

    public String getHardcopy_person() {
        return hardcopy_person;
    }

    public void setHardcopy_person(String hardcopy_person) {
        this.hardcopy_person = hardcopy_person;
    }

    public String getChk_pay() {
        return chk_pay;
    }

    public void setChk_pay(String chk_pay) {
        this.chk_pay = chk_pay;
    }

    public String getDate_pay() {
        return date_pay;
    }

    public void setDate_pay(String date_pay) {
        this.date_pay = date_pay;
    }

    public String getChk_paid() {
        return chk_paid;
    }

    public void setChk_paid(String chk_paid) {
        this.chk_paid = chk_paid;
    }

    public String getDate_paid() {
        return date_paid;
    }

    public void setDate_paid(String date_paid) {
        this.date_paid = date_paid;
    }

    public String getFilename_r() {
        return filename_r;
    }

    public void setFilename_r(String filename_r) {
        this.filename_r = filename_r;
    }

    public String getAdj_op() {
        return adj_op;
    }

    public void setAdj_op(String adj_op) {
        this.adj_op = adj_op;
    }

    public String getAdj_amt() {
        return adj_amt;
    }

    public void setAdj_amt(String adj_amt) {
        this.adj_amt = adj_amt;
    }

    public String getAdj_reason() {
        return adj_reason;
    }

    public void setAdj_reason(String adj_reason) {
        this.adj_reason = adj_reason;
    }

    public String getRecCount() {
        return recCount;
    }

    public void setRecCount(String recCount) {
        this.recCount = recCount;
    }

    public String getAttachBaseUrl() {
        return attachBaseUrl;
    }

    public void setAttachBaseUrl(String attachBaseUrl) {
        this.attachBaseUrl = attachBaseUrl;
    }
}
