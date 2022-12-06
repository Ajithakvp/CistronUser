package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class UserDailyExpensesWMModel {

//     "empid": "e032",
//             "name": "S P Jain",
//             "startdate": "2022-10-09",
//             "enddate": "2022-10-15",
//             "date": "2022-10-12",
//             "workreport": "  ",
//             "c_amo": "390",
//             "t_amo": "0",
//             "l_amo": "0",
//             "o_amo": "0",
//             "adj_amt": 0,
//             "exp_sum": "390"
//},




    @SerializedName("empid")
    private String empid;

    @SerializedName("name")
    private String name;

    @SerializedName("startdate")
    private String startdate;

    @SerializedName("enddate")
    private String enddate;

    @SerializedName("date")
    private String date;

    @SerializedName("workreport")
    private String workreport;

    @SerializedName("c_amo")
    private String c_amo;

    @SerializedName("t_amo")
    private String t_amo;

    @SerializedName("l_amo")
    private String l_amo;

    @SerializedName("o_amo")
    private String o_amo;

    @SerializedName("adj_amt")
    private String adj_amt;

    @SerializedName("exp_sum")
    private String exp_sum;

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

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWorkreport() {
        return workreport;
    }

    public void setWorkreport(String workreport) {
        this.workreport = workreport;
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

    public String getAdj_amt() {
        return adj_amt;
    }

    public void setAdj_amt(String adj_amt) {
        this.adj_amt = adj_amt;
    }

    public String getExp_sum() {
        return exp_sum;
    }

    public void setExp_sum(String exp_sum) {
        this.exp_sum = exp_sum;
    }
}
