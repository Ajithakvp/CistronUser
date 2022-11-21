package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class ReportExpensesViewModel {



    @SerializedName("date")
    private String date;

    @SerializedName("c_amo")
    private String c_amo;

    @SerializedName("t_amo")
    private String t_amo;

    @SerializedName("l_amo")
    private String l_amo;

    @SerializedName("o_amo")
    private String o_amo;

    @SerializedName("workreport")
    private String workreport;

    @SerializedName("filename_all")
    private String filename_all;

    @SerializedName("filename_t")
    private String filename_t;

    @SerializedName("filename_l")
    private String filename_l;

    @SerializedName("filename_o")
    private String filename_o;

    @SerializedName("sum")
    private String sum;

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getC_amo() {
        return c_amo;
    }

    public void setC_amo(String c_amo) {
        this.c_amo = c_amo;
    }

    public String getT_amo() {
        return t_amo;
    }

    public void setT_amo(String t_amo) {
        this.t_amo = t_amo;
    }

    public String getL_amo() {
        return l_amo;
    }

    public void setL_amo(String l_amo) {
        this.l_amo = l_amo;
    }

    public String getO_amo() {
        return o_amo;
    }

    public void setO_amo(String o_amo) {
        this.o_amo = o_amo;
    }

    public String getWorkreport() {
        return workreport;
    }

    public void setWorkreport(String workreport) {
        this.workreport = workreport;
    }

    public String getFilename_all() {
        return filename_all;
    }

    public void setFilename_all(String filename_all) {
        this.filename_all = filename_all;
    }

    public String getFilename_t() {
        return filename_t;
    }

    public void setFilename_t(String filename_t) {
        this.filename_t = filename_t;
    }

    public String getFilename_l() {
        return filename_l;
    }

    public void setFilename_l(String filename_l) {
        this.filename_l = filename_l;
    }

    public String getFilename_o() {
        return filename_o;
    }

    public void setFilename_o(String filename_o) {
        this.filename_o = filename_o;
    }
}
