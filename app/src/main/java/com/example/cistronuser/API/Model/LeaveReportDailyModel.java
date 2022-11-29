package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class LeaveReportDailyModel {


//     "employee": "E261 : Karthick S",
//             "leavetype": "CL",
//             "reason": "Not Feasible to travel",
//             "fdhd": "FD",
//             "medicalattach": ""

    @SerializedName("employee")
    private String employee;
    @SerializedName("leavetype")
    private String leavetype;
    @SerializedName("reason")
    private String reason;
    @SerializedName("fdhd")
    private String fdhd;
    @SerializedName("medicalattach")
    private String medicalattach;


    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
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

    public String getMedicalattach() {
        return medicalattach;
    }

    public void setMedicalattach(String medicalattach) {
        this.medicalattach = medicalattach;
    }
}
