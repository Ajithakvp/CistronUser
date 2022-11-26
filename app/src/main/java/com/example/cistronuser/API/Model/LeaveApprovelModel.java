package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class LeaveApprovelModel {


//     "leaveid": "22",
//             "empid": "E367",
//             "empname": "VELMURUGAN P",
//             "emptype": "3",
//             "cl": "0.0",
//             "ml": "0.0",
//             "pl": "0.0",
//             "probl": "0.0",
//             "compoff": "0.0",
    //lop
//             "applied_date": "2022-11-25, Fri ",
//             "applied_time": "2:45PM",
//             "leavedate": "2022-11-24",
//             "leaveday": "Thursday",
//             "leavetype": "Privilege Leave(LOP)",
//             "reason": "Sick and not Hospitalized",
//             "fdhd": "Full Day",
//             "medattach": "",
//             "btn_txt": "LOP Approve",
//             "expired": "Date Expired"

    @SerializedName("leaveid")
    private String leaveid;

    @SerializedName("empid")
    private String empid;

    @SerializedName("empname")
    private String empname;

    @SerializedName("emptype")
    private String emptype;

    @SerializedName("cl")
    private String cl;

    @SerializedName("ml")
    private String ml;

    @SerializedName("pl")
    private String pl;

    @SerializedName("probl")
    private String probl;

    @SerializedName("compoff")
    private String compoff;

    @SerializedName("applied_date")
    private String applied_date;

    @SerializedName("applied_time")
    private String applied_time;

    @SerializedName("leavedate")
    private String leavedate;

    @SerializedName("leaveday")
    private String leaveday;

    @SerializedName("leavetype")
    private String leavetype;

    @SerializedName("lop")
    private String lop;


    @SerializedName("reason")
    private String reason;

    @SerializedName("fdhd")
    private String fdhd;

    @SerializedName("medattach")
    private String medattach;

    @SerializedName("btn_txt")
    private String btn_txt;

    @SerializedName("expired")
    private String expired;


    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getLeaveid() {
        return leaveid;
    }

    public void setLeaveid(String leaveid) {
        this.leaveid = leaveid;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getEmptype() {
        return emptype;
    }

    public void setEmptype(String emptype) {
        this.emptype = emptype;
    }

    public String getCl() {
        return cl;
    }

    public void setCl(String cl) {
        this.cl = cl;
    }

    public String getMl() {
        return ml;
    }

    public void setMl(String ml) {
        this.ml = ml;
    }

    public String getPl() {
        return pl;
    }

    public void setPl(String pl) {
        this.pl = pl;
    }

    public String getProbl() {
        return probl;
    }

    public void setProbl(String probl) {
        this.probl = probl;
    }

    public String getCompoff() {
        return compoff;
    }

    public void setCompoff(String compoff) {
        this.compoff = compoff;
    }

    public String getApplied_date() {
        return applied_date;
    }

    public void setApplied_date(String applied_date) {
        this.applied_date = applied_date;
    }

    public String getApplied_time() {
        return applied_time;
    }

    public void setApplied_time(String applied_time) {
        this.applied_time = applied_time;
    }

    public String getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(String leavedate) {
        this.leavedate = leavedate;
    }

    public String getLeaveday() {
        return leaveday;
    }

    public void setLeaveday(String leaveday) {
        this.leaveday = leaveday;
    }

    public String getLeavetype() {
        return leavetype;
    }

    public void setLeavetype(String leavetype) {
        this.leavetype = leavetype;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFdhd() {
        return fdhd;
    }

    public void setFdhd(String fdhd) {
        this.fdhd = fdhd;
    }

    public String getMedattach() {
        return medattach;
    }

    public void setMedattach(String medattach) {
        this.medattach = medattach;
    }

    public String getBtn_txt() {
        return btn_txt;
    }

    public void setBtn_txt(String btn_txt) {
        this.btn_txt = btn_txt;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }
}
